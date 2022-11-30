package Screens;

import Enums.SchoolStudyType;

import characters.Student;

import providers.CollegesProvider;
import providers.DepartmentsProvider;
import providers.StudentsProvider;
import providers.UniversitiesProvider;

import university_information.College;
import university_information.Department;
import university_information.University;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.util.*;

public class StudentScreen {
    private final Scanner input = new Scanner(System.in);
    private Student student;
    private int studentIndex;
    private boolean isSubmitted = false;
    private boolean isFilledDepartmentsChanged = false;

    public StudentScreen(Student student) throws IOException, ClassNotFoundException {
        this.student = student;
        getStudentIndex();
        showStudentInformation();
        form();
    }

    private void getStudentIndex() {
        if (student.getType() == SchoolStudyType.Zansty) {
            studentIndex = StudentsProvider.getZanstyStudentsHolder().indexOf(student);
        } else if (student.getType() == SchoolStudyType.Wezhaiy) {
            studentIndex = StudentsProvider.getWezhaiyStudentsHolder().indexOf(student);
        } else if (student.getType() == SchoolStudyType.Ayny) {
            studentIndex = StudentsProvider.getAynyStudentsHolder().indexOf(student);
        }
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

    private void form() throws IOException, ClassNotFoundException {
        System.out.println("Your Form\n");
        formView();
        if (!isSubmitted) {
            System.out.println("""
                    Enter a number to do an action:
                    1. Edit your form
                    2. Submit your form
                    """);
            int option = input.nextInt();
            switch (option) {
                case 1 -> editForm();
                case 2 -> submitForm();
                default -> {
                    System.err.println("Enter a right number.");
                    form();
                }
            }
        } else {
            System.out.println("Your Form was submitted");
        }

    }

    private void formView() {
        if (student.filledDepartments.isEmpty()) {
            System.out.println("Your form is Empty.");
        } else {
            System.out.println("Your Selected departments: ");
            HashMap<List<String>, Department> departmentsHashMap = Objects.requireNonNull(DepartmentsProvider.getDepartmentsByType(student.getType()));
            for (int i = 0; i < student.filledDepartments.size(); i++) {
                List<String> keyOfDepartment = student.filledDepartments.get(i);
                String departmentName = departmentsHashMap.get(keyOfDepartment).getName();
                String universityId = keyOfDepartment.get(0);
                University university = UniversitiesProvider.getUNIVERSITIES().get(universityId);
                String collegeId = keyOfDepartment.get(1);
                College college = CollegesProvider.getCOLLEGES().get(Arrays.asList(universityId, collegeId));
                if (college == null) {
                    System.out.println((i + 1) + ". " + university.location() + university.name() + "/" + departmentName);
                    System.out.println();
                } else {
                    System.out.println((i + 1) + ". " + university.location() + "/" + university.name() + "/" + college.name() + "/" + departmentName);
                    System.out.println();
                }
            }
        }

    }

    private void editForm() throws IOException, ClassNotFoundException {
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
        DepartmentsProvider.displayDepartmentsByType(student.getType());
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

    private HashMap<List<String>, Department> getAvailableDepartments() {
        return DepartmentsProvider.getDepartmentsByType(student.getType());
    }

    private void selectDepartmentsToTheForm() {
        showAvailableDepartments();
        System.out.println("To select a Department write the number that beside it, or put ( 0 ) to back to edit options: ");
        int deptIndex = input.nextInt() - 1;
        if (deptIndex == -1) return;
        if (deptIndex >= 0) {
            List<String> deptKey;
            deptKey = Objects.requireNonNull(getAvailableDepartments()).keySet().stream().toList().get(deptIndex);

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
        try {
            if (isFilledDepartmentsChanged) {
                if (student.getType() == SchoolStudyType.Zansty) {
                    ArrayList<Student> zStudents = StudentsProvider.getZanstyStudentsHolder();
                    zStudents.set(studentIndex, student);
                    ObjectOutputStream zOutputStream = new ObjectOutputStream(new FileOutputStream("ACP_Project/src/DataFiles/Students_Zansty.txt"));
                    zOutputStream.writeObject(zStudents);
                } else if (student.getType() == SchoolStudyType.Wezhaiy) {
                    ArrayList<Student> wStudents = StudentsProvider.getWezhaiyStudentsHolder();
                    wStudents.set(studentIndex, student);
                    ObjectOutputStream wOutputStream = new ObjectOutputStream(new FileOutputStream("ACP_Project/src/DataFiles/Students_Wezhaiy.txt"));
                    wOutputStream.writeObject(wStudents);
                } else if (student.getType() == SchoolStudyType.Ayny) {
                    ArrayList<Student> ayStudents = StudentsProvider.getAynyStudentsHolder();
                    ayStudents.set(studentIndex, student);
                    ObjectOutputStream ayOutputStream = new ObjectOutputStream(new FileOutputStream("ACP_Project/src/DataFiles/Students_Ayny.txt"));
                    ayOutputStream.writeObject(ayStudents);
                }
                isFilledDepartmentsChanged = false;
            } else {
                System.out.println("You didn't change anything, please change your form then try to save it.");
            }
        } catch (IOException e) {
            System.err.println("(The system cannot find the path specified)");
        }

    }

    private void submitForm() throws IOException, ClassNotFoundException {
        System.out.println("Are you sure?  ( yes , no )");
        String answer = input.nextLine();
        if (answer.equals("yes")) {
            isSubmitted = true;
            form();
        } else {
            form();
        }
    }

}
