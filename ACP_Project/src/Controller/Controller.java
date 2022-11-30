package Controller;

import Models.*;
import providers.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Objects;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Controller extends Thread {
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;

    public Controller(Socket socket) {
        this.socket = socket;
    }

    public static void fetchUniversityInformations() {
        UniversitiesProvider.fetchUniversities();
        CollegesProvider.fetchColleges();
        DepartmentsProvider.fetchDepartmentsByType(SchoolStudyType.Zansty);
        DepartmentsProvider.fetchDepartmentsByType(SchoolStudyType.Wezhaiy);
        DepartmentsProvider.fetchDepartmentsByType(SchoolStudyType.Ayny);
    }

    @Override
    public void run() {
        try {
            fetchUniversityInformations();
            this.inputStream = new ObjectInputStream(socket.getInputStream());
            this.outputStream = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                try {
                    SendOrReceiveData<String, Object> receivedDataFromClient = (SendOrReceiveData<String, Object>) inputStream.readObject();
                    String command = receivedDataFromClient.getCommand();

                    switch (command) {
                        case "find-user" -> {
                            List<String> userData = (List<String>) receivedDataFromClient.getObject();
                            findUser(userData);
                        }
                        case "get-available-departments" -> {
                            Student student = (Student) receivedDataFromClient.getObject();
                            getAvailableDepartmentsByType(student.getType());
                        }
                        case "find-university-by-id" -> {
                            String universityId = (String) receivedDataFromClient.getObject();
                            findUniversityByID(universityId);
                        }
                        case "find-college-by-key" -> {
                            List<String> collegeKey = (List<String>) receivedDataFromClient.getObject();
                            findCollegeByKey(collegeKey);
                        }
                        case "save-changes-of-student-in-the-form" -> {
                            ConcurrentHashMap<Student, CopyOnWriteArrayList<List<String>>> receivedData = (ConcurrentHashMap<Student, CopyOnWriteArrayList<List<String>>>) receivedDataFromClient.getObject();
                            Student student = receivedData.keySet().stream().toList().get(0);
                            student.filledDepartments.clear();
                            student.filledDepartments.addAll(receivedData.values().stream().toList().get(0));
                            submitOrSaveChangesOfTheStudentForm(student);
                        }
                        case "submit-student-form"-> {
                            Student student = (Student) receivedDataFromClient.getObject();
                            student.setFormSubmitted(true);
                            submitOrSaveChangesOfTheStudentForm(student);
                        }
                        case "exit" -> {
                            inputStream.close();
                            outputStream.close();
                            socket.close();
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    inputStream.close();
                    outputStream.close();
                    socket.close();
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("IOException in Controller: " + e.getMessage());
        } finally {
            try {
                inputStream.close();
                outputStream.close();
                socket.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }

    }

    private void findUser(List<String> userData) {
        String id = userData.get(0);
        String password = userData.get(1);
        try {
            Adminstrator adminstrator = AdminstratorsProvider.findAdminstrator(id, password);
            Student student = new StudentsProvider().findStudent(id, password);
            if (adminstrator != null) {
                outputStream.writeObject(new SendOrReceiveData<>("adminstrator-found", adminstrator));
                outputStream.flush();
            } else if (student != null) {
                outputStream.writeObject(new SendOrReceiveData<>("student-found", student));
                outputStream.flush();
            } else {
                outputStream.writeObject(new SendOrReceiveData<>("find-user-failed", null));
                outputStream.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException in Controller.findUser(): " + e.getMessage());
        }
    }

    private void getAvailableDepartmentsByType(SchoolStudyType type) {
        try {
            ConcurrentHashMap<List<String>, Department> availableDepartments = DepartmentsProvider.getDepartmentsByType(type);

            if (availableDepartments != null) {
                outputStream.writeObject(new SendOrReceiveData<>("get-available-departments-successful", availableDepartments));
                outputStream.flush();
            } else {
                outputStream.writeObject(new SendOrReceiveData<>("get-available-departments-failed", null));
                outputStream.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException in Controller.getAvailableDepartmentsByType(): " + e.getMessage());
        }
    }

    private void findUniversityByID(String universityId) {
        try {
            University university = UniversitiesProvider.getUNIVERSITIES().get(universityId);
            if (university != null) {
                outputStream.writeObject(new SendOrReceiveData<>("find-university-by-id-successful", university));
                outputStream.flush();
            } else {
                outputStream.writeObject(new SendOrReceiveData<>("find-university-by-id-failed", null));
                outputStream.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException in Controller.getUniversityByID(): " + e.getMessage());
        }
    }

    private void findCollegeByKey(List<String> collegeKey) {
        try {
            College college = CollegesProvider.getCOLLEGES().get(collegeKey);
            if (college != null) {
                outputStream.writeObject(new SendOrReceiveData<>("find-college-by-key-successful", college));
                outputStream.flush();
            } else {
                outputStream.writeObject(new SendOrReceiveData<>("find-college-by-key-failed", null));
                outputStream.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException in Controller.getCollegeByKey(): " + e.getMessage());
        }
    }

    private void submitOrSaveChangesOfTheStudentForm(Student student) {
        StudentsProvider studentsProvider = new StudentsProvider();
        studentsProvider.fetchStudentsByType(student.getType());

        Vector<Student> students = studentsProvider.getStudentsByType(student.getType());
        students.removeIf(studentPrevious -> Objects.equals(studentPrevious.getId(), student.getId()));
        students.addElement(student);
        try {
            if (student.getType() == SchoolStudyType.Zansty) {
                ObjectOutputStream zOutputStream = new ObjectOutputStream(new FileOutputStream("ACP_Project/src/DataFiles/Students_Zansty.txt"));
                zOutputStream.writeObject(students);
                zOutputStream.close();
            } else if (student.getType() == SchoolStudyType.Wezhaiy) {
                ObjectOutputStream wOutputStream = new ObjectOutputStream(new FileOutputStream("ACP_Project/src/DataFiles/Students_Wezhaiy.txt"));
                wOutputStream.writeObject(students);
                wOutputStream.close();
            } else if (student.getType() == SchoolStudyType.Ayny) {
                ObjectOutputStream ayOutputStream = new ObjectOutputStream(new FileOutputStream("ACP_Project/src/DataFiles/Students_Ayny.txt"));
                ayOutputStream.writeObject(students);
                ayOutputStream.close();
            }
        } catch (IOException e) {
            System.out.println("IOException in Controller.saveChangesOfTheStudentForm(): " + e.getMessage());
        } finally {
            try {
                outputStream.writeObject(new SendOrReceiveData<>("Your request accepted!", null));
                outputStream.flush();
            } catch (IOException e) {
                System.out.println("IOException in Controller.saveChangesOfTheStudentForm(): " + e.getMessage());
            }
        }

    }

}
