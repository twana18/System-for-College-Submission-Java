package Views;

import Models.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class StudentView {
    private final Scanner input = new Scanner(System.in);
    private Student student;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private boolean isFilledDepartmentsChanged = false;
    private ConcurrentHashMap<List<String>, Department> availableDepartments;

    public StudentView(Student student) {
        this.student = student;
        try {
            this.socket = new Socket("127.0.0.2", 4444);
            outputStream = new ObjectOutputStream(this.socket.getOutputStream());
            inputStream = new ObjectInputStream(this.socket.getInputStream());
        } catch (IOException e) {
            System.out.println("IOException in StudentView() constructor: " + e.getMessage());
        }
        getAvailableDepartments();
        showStudentInformation();
        form();
    }

    private void showStudentInformation() {
        System.out.println("********************************************************************");
        System.out.println(
                "Student Informations\n\n" +
                        "Student ID: " + student.getId() + "\n" +
                        "Student Name: " + student.getName() + "\n" +
                        "Student Gender: " + student.getGender() + "\n" +
                        "Student DateOfBirth: " + student.getDateOfBirth() + "\n" +
                        "Student Type: " + student.getType() + "\n\nStudent Subjects And Grades: \n"
        );
        HashMap<String, Double> subjectsAndGrades = student.getSubjectsAndGrades();
        for (int i = 0; i < subjectsAndGrades.size(); i++) {
            System.out.println(
                    subjectsAndGrades.keySet().stream().toList().get(i)
                            + ": " +
                            subjectsAndGrades.values().stream().toList().get(i)
            );
        }
        System.out.println("\nTotal: " + student.gradeStats().getSum() + "    Average: " + student.gradeStats().getAverage());
        System.out.println("********************************************************************");
    }

    private void form() {
        System.out.println("Your Form\n");
        formView();
        if (!student.isFormSubmitted()) {
            System.out.println("""
                    Enter a number to do an action:
                    1. Edit your form
                    2. Submit your form
                    3. Exit
                    """);
            int option = input.nextInt();
            switch (option) {
                case 1 -> editForm();
                case 2 -> submitForm();
                case 3 -> exit();
                default -> {
                    System.err.println("Enter a right number.");
                    form();
                }
            }
        } else {
            System.out.println("Your Form was submitted!");
        }

    }

    private void getAvailableDepartments() {
        try {
            outputStream.writeObject(new SendOrReceiveData<>("get-available-departments", student));
            outputStream.flush();

            SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
            if (!receivedData.getCommand().equals("get-available-departments-failed")) {
                this.availableDepartments = (ConcurrentHashMap<List<String>, Department>) receivedData.getObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("IOException in StudentView.getAvailableDepartments(): " + e.getMessage());
        }
    }

    private void formView() {
        if (student.filledDepartments.isEmpty()) {
            System.out.println("Your form is Empty.");
        } else {
            System.out.println("Your Selected departments: ");
            showSelectedDepartments();
        }
    }

    private void showSelectedDepartments() {
        for (int i = 0; i < student.filledDepartments.size(); i++) {
            List<String> keyOfDepartment = student.filledDepartments.get(i);
            String departmentName = availableDepartments.get(keyOfDepartment).getName();

            String universityId = keyOfDepartment.get(0);
            University university = getUniversityById(universityId);

            String collegeId = keyOfDepartment.get(1);
            College college = getCollegeByKey(collegeId, universityId);

            if (university != null) {
                if (college == null) {
                    System.out.println((i + 1) + ". " + university.location() + "/" + university.name() + "/" + departmentName);
                    System.out.println();
                } else {
                    System.out.println((i + 1) + ". " + university.location() + "/" + university.name() + "/" + college.name() + "/" + departmentName);
                    System.out.println();
                }
            }

        }
    }

    private University getUniversityById(String universityId) {
        University university = null;
        try {
            outputStream.writeObject(new SendOrReceiveData<>("find-university-by-id", universityId));
            outputStream.flush();

            SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
            if (!receivedData.getCommand().equals("find-university-by-id-failed")) {
                university = (University) receivedData.getObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("IOException in StudentView.getUniversityById(): " + e.getMessage());
        }
        return university;
    }

    private College getCollegeByKey(String universityId, String collegeId) {
        College college = null;
        try {
            outputStream.writeObject(new SendOrReceiveData<>("find-college-by-key", Arrays.asList(universityId, collegeId)));
            outputStream.flush();

            SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
            if (!receivedData.getCommand().equals("find-college-by-key-failed")) {
                college = (College) receivedData.getObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("IOException in StudentView.getCollegeById(): " + e.getMessage());
        }
        return college;
    }

    private void editForm() {
        System.out.println("""
                What do you want?
                1. Select more Departments
                2. Remove a Department
                3. Change the order of two Departments in your form
                4. Save Changes
                5. Back to My Form
                """);
        int option = input.nextInt();
        switch (option) {
            case 1:
                if (student.filledDepartments.size() <= 20) {
                    selectDepartmentsToTheForm();
                    editForm();
                } else {
                    System.err.println("Your form was full of departments!");
                    editForm();
                }

            case 2:
                removeDepartmentsFromTheForm();
                editForm();
            case 3:
                changeOrder();
                editForm();
            case 4:
                saveChanges();
                editForm();
            case 5:
                form();
            default:
                editForm();
        }
    }

    private void showAvailableDepartments() {
        String departmentName;
        String universityId;
        University university;
        String collegeId;
        College college;

        for (int i = 0; i < availableDepartments.size(); i++) {
            departmentName = availableDepartments.values().stream().toList().get(i).getName();
            universityId = availableDepartments.keySet().stream().toList().get(i).get(0);
            university = getUniversityById(universityId);
            collegeId = availableDepartments.keySet().stream().toList().get(i).get(1);
            college = getCollegeByKey(collegeId, universityId);
            if (university != null) {
                if (college == null) {
                    System.out.println((i + 1) + ". " + university.location() + "/" + university.name() + "/" + departmentName);
                    System.out.println();
                } else {
                    System.out.println((i + 1) + ". " + university.location() + "/" + university.name() + "/" + college.name() + "/" + departmentName);
                    System.out.println();
                }
            }
        }
    }

    private boolean isDepartmentExistInForm(List<String> deptKey) {
        int count = 0;
        for (int i = 0; i < student.filledDepartments.size(); i++) {
            if (student.filledDepartments.contains(deptKey)) {
                count++;
            }
        }
        return count > 0;
    }

    private void selectDepartmentsToTheForm() {
        showAvailableDepartments();
        System.out.println("To select a Department write the number that beside it, or put ( 0 ) to back to edit options: ");
        int deptIndex = input.nextInt() - 1;
        if (deptIndex == -1) return;
        if (deptIndex >= 0 && deptIndex < availableDepartments.size()) {
            List<String> deptKey;
            deptKey = Objects.requireNonNull(availableDepartments).keySet().stream().toList().get(deptIndex);

            if (!isDepartmentExistInForm(deptKey)) {
                student.filledDepartments.add(deptKey);
                if (!isFilledDepartmentsChanged) {
                    isFilledDepartmentsChanged = true;
                }
            } else {
                System.out.println("You already selected this department try to select another one.");
            }
        } else {
            System.out.println("Select a correct index of department.");
        }
    }

    private void removeDepartmentsFromTheForm() {
        formView();

        if (student.filledDepartments.isEmpty()) {
            System.out.println("You don't have any departments to remove.");
        } else {
            System.out.println("To remove a Department write the number that beside it, or put ( 0 ) to back to edit options: ");
            int deptIndex = input.nextInt() - 1;
            if (deptIndex == -1) return;
            if (deptIndex >= 0 && deptIndex < student.filledDepartments.size()) {
                student.filledDepartments.remove(deptIndex);

                if (!isFilledDepartmentsChanged) {
                    isFilledDepartmentsChanged = true;
                }
            } else {
                System.out.println("You don't have any department with the given index.");
            }
        }

    }

    private void changeOrder() {
        formView();
        if (student.filledDepartments.size() < 2) {
            System.out.println("You don't have enough departments to swap.");
        } else {
            System.out.println("To swap two Departments write the number that beside them, or put ( 0 ) to back to edit options: " +
                    "\nEnter number of the first department: ");
            int dept1Index = input.nextInt() - 1;
            if (dept1Index == -1) return;
            System.out.println("Enter number of the second department: ");
            int dept2Index = input.nextInt() - 1;
            if (dept2Index == -1) return;
            if (dept1Index < student.filledDepartments.size() &&
                    dept1Index >= 0 &&
                    dept2Index < student.filledDepartments.size() &&
                    dept2Index >= 0 &&
                    dept1Index != dept2Index) {
                List<String> dept1Key = student.filledDepartments.get(dept1Index);
                List<String> dept2Key = student.filledDepartments.get(dept2Index);
                student.filledDepartments.set(dept1Index, dept2Key);
                student.filledDepartments.set(dept2Index, dept1Key);

                if (!isFilledDepartmentsChanged) {
                    isFilledDepartmentsChanged = true;
                }
            } else {
                System.out.println("Cannot swap these departments, it might something is wrong!");
            }
        }

    }

    private void saveChanges() {
        if (isFilledDepartmentsChanged) {
            try {
                ConcurrentHashMap<Student, List<List<String>>> concurrentHashMap = new ConcurrentHashMap<>();
                concurrentHashMap.put(student, new CopyOnWriteArrayList<>(student.filledDepartments));
                outputStream.writeObject(new SendOrReceiveData<>("save-changes-of-student-in-the-form", concurrentHashMap));
                outputStream.flush();

                SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
                System.out.println(receivedData.getCommand());
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("IOException in StudentView.formView(): " + e.getMessage());
            }
            isFilledDepartmentsChanged = false;
        } else {
            System.out.println("You didn't change anything, please change your form then try to save it.");
        }
    }

    private void submitForm() {
        System.out.println("Are you sure?  ( (1) to yes , (0) to no )");
        int answer = input.nextInt();
        if (answer == 1) {
            student.setFormSubmitted(true);
            try {
                outputStream.writeObject(new SendOrReceiveData<>("submit-student-form", student));
                outputStream.flush();

                SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
                System.out.println(receivedData.getCommand());
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("IOException in StudentView.submitForm(): " + e.getMessage());
            }
            System.out.println();
            form();
        } else if (answer == 0){
            System.out.println();
            form();
        }else {
            System.out.println("Enter a right number: \n");
            submitForm();
        }
    }

    private void exit() {
        try {
            outputStream.writeObject(new SendOrReceiveData<>("exit", null));
            outputStream.flush();

            input.close();
            outputStream.close();
            inputStream.close();
            socket.close();
            System.exit(0);
        } catch (IOException e) {
            System.out.println("IOException in form.EXit: " + e.getMessage());
            System.exit(1);
        }
    }

}
