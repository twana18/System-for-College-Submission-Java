package Controller;

import Models.*;
import providers.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
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
        AdminstratorsProvider.fetchAdminstrators();
        
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
                        case "add-admin" ->{
                        	
                        	List<String> admininfo = (List<String>) receivedDataFromClient.getObject();
                        	addAdmin(admininfo);
                        	
                        }
                        case "remove-admin" -> {
                        	String adminId = (String) receivedDataFromClient.getObject();
                        	removeAdmin(adminId);
                        	
                        }
                        case "add-department" -> {
                        	List <String> department= (List) receivedDataFromClient.getObject();
                        	addDepartment(department);
                        	
                        }
                        case "remove-department" -> {
                        	List <String> department= (List) receivedDataFromClient.getObject();
                        	removeDepartment(department);
                        }
                        case "add-college" -> {
                        	List <String> college= (List) receivedDataFromClient.getObject();
                        	addCollege(college);
                        	
                        }
                        case "remove-college" -> {
                        	List <String> college= (List) receivedDataFromClient.getObject();
                        	removeCollege(college);
                        	
                        }
                        case "add-university" -> {
                        	List <String> university= (List) receivedDataFromClient.getObject();
                        	addUniversity(university);
                        	
                        }
                        case "remove-university" -> {
                        	String universityId= (String)receivedDataFromClient.getObject();
                        	removeUniversity(universityId);
                        	
                        }
                        case "edit-profile" -> {
                        	List<String> profile = (List<String>) receivedDataFromClient.getObject();
                        	editProfile(profile);
                        	
                        }case "find-department-by-key" ->{
                        	List<String> departmentKey = (List<String>) receivedDataFromClient.getObject();
                            findDepartmentByKey(departmentKey);
                        }
                        
                        case "shutdown" -> {
                        	inputStream.close();
                        	outputStream.close();
                        	socket.close();
                        	System.exit(0);
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
    
    
    private void findDepartmentByKey(List<String> departmentKey) {
        try {
            
            ConcurrentHashMap<List<String>, Department> Aynydepartment = DepartmentsProvider.getDepartmentsByType(SchoolStudyType.Ayny);
            ConcurrentHashMap<List<String>, Department> Zanstydepartment = DepartmentsProvider.getDepartmentsByType(SchoolStudyType.Zansty);
            ConcurrentHashMap<List<String>, Department> Wezhaiydepartment = DepartmentsProvider.getDepartmentsByType(SchoolStudyType.Wezhaiy);
            if (Aynydepartment.get(departmentKey) != null) {
                outputStream.writeObject(new SendOrReceiveData<>("find-department-by-key-successful", Aynydepartment));
                outputStream.flush();
            } else if(Zanstydepartment.get(departmentKey) !=null) {
            	outputStream.writeObject(new SendOrReceiveData<>("find-department-by-key-successful", Zanstydepartment));
                outputStream.flush();
            } else if(Wezhaiydepartment.get(departmentKey) !=null) {
            	outputStream.writeObject(new SendOrReceiveData<>("find-department-by-key-successful", Wezhaiydepartment));
                outputStream.flush();
            }
            
            else {
                outputStream.writeObject(new SendOrReceiveData<>("find-department-by-key-failed", null));
                outputStream.flush();
            }
        } catch (IOException e) {
            System.out.println("IOException in Controller.getDepartmentByKey(): " + e.getMessage());
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
    
    private void addAdmin(List admininfo) {
    	
    	try {
    	providers.AdminstratorsProvider.addAdminstrator(admininfo.get(0).toString(),admininfo.get(1).toString(),admininfo.get(2).toString());
    	providers.AdminstratorsProvider.submitChanges();
    	
    	 outputStream.writeObject(new SendOrReceiveData<>("adding-admin-successful", null));
         outputStream.flush();
			
    		
    		
    	}catch (IOException e) {
    		System.out.println("There was an Error While Adding Admin");
    	}
    	
    	
    	
    	
    	
    }
    private void removeAdmin(String adminId) {
    	
    	try {
    		providers.AdminstratorsProvider.removeAdminstrator(adminId);
    		providers.AdminstratorsProvider.submitChanges();
        	
        	 outputStream.writeObject(new SendOrReceiveData<>("removing-admin-successfull", null));
             outputStream.flush();
    			
        	}catch (IOException e) {
        		System.out.println("There was an Error While Removing Admin");
        	}

		
    }
    private void addDepartment(List department) {
    	
    	
    	try {
        	if(department.get(5).toString().equals('Z')) {
			
			providers.DepartmentsProvider.addDepartment(department.get(0).toString(),department.get(1).toString(),department.get(2).toString(),department.get(3).toString(),SchoolStudyType.Zansty,(int)department.get(5));	
			providers.DepartmentsProvider.zanstySubmitChanges();
			
			outputStream.writeObject(new SendOrReceiveData<>("adding-department-succesfull", null));
            outputStream.flush();

	        
			
		}else if (department.get(5).toString().equals('W')) {
			providers.DepartmentsProvider.addDepartment(department.get(0).toString(),department.get(1).toString(),department.get(2).toString(),department.get(3).toString(),SchoolStudyType.Wezhaiy,(int)department.get(5));	
			providers.DepartmentsProvider.wezhaiySubmitChanges();
			
			outputStream.writeObject(new SendOrReceiveData<>("adding-department-succesfull", null));
            outputStream.flush();
			
			

	        
			
		}else if (department.get(5).toString().equals('A')) {
			providers.DepartmentsProvider.addDepartment(department.get(0).toString(),department.get(1).toString(),department.get(2).toString(),department.get(3).toString(),SchoolStudyType.Ayny,(int)department.get(5));
			providers.DepartmentsProvider.aynySubmitChanges();
			
			outputStream.writeObject(new SendOrReceiveData<>("adding-department-succesfull", null));
            outputStream.flush();
		}
        	 
    			
        	}catch (IOException e) {
        		System.out.println("There was an Error While Adding Department");
        	}

    }
    
    
    private void removeDepartment(String department) {
    	try {
			providers.DepartmentsProvider.removeDepartment(department);	
			providers.DepartmentsProvider.zanstySubmitChanges();
			
			outputStream.writeObject(new SendOrReceiveData<>("removing-department-successfull", null));
            outputStream.flush();
	
        	}catch (IOException e) {
        		System.out.println("There was an Error While Removing department");
        	}
    	
    }
    private void addCollege(List college) {
    
    	try {
        	providers.CollegesProvider.addCollege(college.get(0).toString(),college.get(1).toString(),college.get(2).toString());
        	
        	 outputStream.writeObject(new SendOrReceiveData<>("adding-college-successfull", null));
             outputStream.flush();
    			
        	}catch (IOException e) {
        		System.out.println("There was an Error While Adding College");
        	}
    }
    private void removeCollege(List college) {

    	try {
        	providers.CollegesProvider.removeCollege(college.get(0).toString(),college.get(1).toString());
        	
        	 outputStream.writeObject(new SendOrReceiveData<>("removing-college-successfull", null));
             outputStream.flush();
    			
        	}catch (IOException e) {
        		System.out.println("There was an Error While Removing College");
        	}
    	
    }
    private void addUniversity(List university) {
    	
    	try {
        	providers.UniversitiesProvider.addUniversity(university.get(0).toString(),university.get(1).toString(),university.get(2).toString());
        	providers.UniversitiesProvider.submitChanges();
        	 outputStream.writeObject(new SendOrReceiveData<>("adding-university-successfull", null));
             outputStream.flush();
    			
        	}catch (IOException e) {
        		System.out.println("There was an Error While Adding University");
        	}
    	
    	
    	
    }
    private void removeUniversity(String universityId) {
    	try {
        	providers.UniversitiesProvider.removeUniversity(universityId);
        	providers.UniversitiesProvider.submitChanges();
        	
        	 outputStream.writeObject(new SendOrReceiveData<>("removing-university-successfull", null));
             outputStream.flush();
    			
        	}catch (IOException e) {
        		System.out.println("There was an Error While Removing University");
        	}
    	
    	
    	
    }
    
    private void editProfile(List profile) {
    	
    	try {
        	providers.AdminstratorsProvider.removeAdminstrator(profile.get(0).toString());
        	providers.AdminstratorsProvider.addAdminstrator(profile.get(0).toString(),profile.get(1).toString(),profile.get(2).toString());
        	providers.AdminstratorsProvider.submitChanges();
        	
        	 outputStream.writeObject(new SendOrReceiveData<>("editing-admin-successfull", null));
             outputStream.flush();
    			
        	}catch (IOException e) {
        		System.out.println("There was an Error While Editing Profile");
        	}
    	
    	
    	
    	
    }

}
