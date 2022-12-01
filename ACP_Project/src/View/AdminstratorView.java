package Views;

import Models.*;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class AdminstratorView {
    private final Scanner input = new Scanner(System.in);
    private Adminstrator admin;
    private Socket socket;
    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;



    public AdminstratorView(Adminstrator admin) throws ClassNotFoundException, IOException {
        this.admin = admin;
        try {
            this.socket = new Socket("127.0.0.3", 4444);
            outputStream = new ObjectOutputStream(this.socket.getOutputStream());
            inputStream = new ObjectInputStream(this.socket.getInputStream());
            
        } catch (IOException e) {
            System.out.println("IOException in AdminstratorView() constructor: " + e.getMessage());
        }
        showAdminInformation();
            view();
    }

    private void showAdminInformation() {
        System.out.println("********************************************************************");
        System.out.println(
                "Welcome :) \n\n" +
                        "Admin ID: " + admin.id() + "\n" +
                        "Admin Name: " + admin.name()
        );
        System.out.println("********************************************************************");
    }

    private void view() throws ClassNotFoundException, IOException {
        System.out.println("\n");
        
            while(true) {
            	System.out.println("""
                        Enter a number to do an action:
                        1. Add Admin
                        2. Remove Admin
                        3. Edit Profile
                        4. Add department
                        5. Remove Department
                        6. Add College 
                        7. Remove College
                        8. Add University
                        9. Remove University
                        10. Show All Admins
                        11. Server Shutdown
                        12. Exit
                        """);
                int option = input.nextInt();
                switch (option) {
                    case 1 -> addAdmin();
                    case 2 -> removeAdmin();
                    case 3 -> editProfile();
                    case 4 -> addDepartment();
                    case 5 -> removeDepartment();
                    case 6 -> addCollege();
                    case 7 -> removeCollege();
                    case 8 -> addUniversity();
                    case 9 -> removeUniversity();
                    case 10 -> showAllAdmins();
                    case 11 -> shutdown();
                    case 12 -> exit();
                    default -> {
                        System.err.println("Enter a right number.");
                        view();
                    }
                }
            }
    }

    private void addAdmin() {

        try {
        	System.out.println("Enter ID for new Admin");
        	String adminId=input.next();
        	System.out.println("Enter Name for new admin");
        	String name=input.next();
        	System.out.println("Enter Password for new admin");
        	String password=input.next();	
		
        	outputStream.writeObject(new SendOrReceiveData<>("add-admin", Arrays.asList(adminId,name,password)));
        	outputStream.flush();

        	SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
        	System.out.println(receivedData.getCommand());
        }catch (IOException | ClassNotFoundException e) {
        	System.out.println("Adding Admin Error " + e.getMessage());
        }
		
    }
    
    private void removeAdmin()  {
    	
        try {
        	System.out.println("Enter Admin ID to remove");
        	String adminId=input.next().toUpperCase();
		
        	outputStream.writeObject(new SendOrReceiveData<>("remove-admin", adminId));
        	outputStream.flush();

        	SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
        	System.out.println(receivedData.getCommand());
        }catch (IOException | ClassNotFoundException e) {
        	System.out.println("Removing Admin Error " + e.getMessage());
        }
    	
    }
    
    private void addDepartment() {
		 try {
			 	System.out.println("Enter Department ID");
				String departmentId=input.next();
				System.out.println("Enter College ID");
				String collegeId=input.next();
				System.out.println("Enter University ID");
				String universityId=input.next();
				
				outputStream.writeObject(new SendOrReceiveData<>("find-university-by-id", universityId));
		        outputStream.flush();
		        SendOrReceiveData<String, Object> receivedDataUniversity = (SendOrReceiveData<String, Object>) inputStream.readObject();
		            
				outputStream.writeObject(new SendOrReceiveData<>("find-college-by-key", Arrays.asList(universityId, collegeId)));
		        outputStream.flush();
		        SendOrReceiveData<String, Object> receivedDataCollege = (SendOrReceiveData<String, Object>) inputStream.readObject();
		        
		        outputStream.writeObject(new SendOrReceiveData<>("find-department-by-key", Arrays.asList(universityId, collegeId)));
		        outputStream.flush();
		        SendOrReceiveData<String, Object> receivedDataDepartment = (SendOrReceiveData<String, Object>) inputStream.readObject();
				
		        if (!receivedDataCollege.getCommand().equals("find-college-by-key-failed") && !receivedDataUniversity.getCommand().equals("find-university-by-id-failed")
		        		&& receivedDataDepartment.getCommand().equals("find-department-by-key-failed")) {
		            	
		            System.out.println("Enter Department Name");
					String departmentname=input.next();
					System.out.println("Enter Department Capacity");
					int capacity=input.nextInt();
					System.out.println("Enter Z for Zansty, W for Wezhaiy, A for Ayny");
					char school=input.next().trim().toUpperCase().charAt(0);
		            	
		        if(school=='Z') {
					
					outputStream.writeObject(new SendOrReceiveData<>("add-department", Arrays.asList(universityId,collegeId,departmentId,departmentname, SchoolStudyType.Zansty,capacity)));
			        outputStream.flush();

			        SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
			        System.out.println("Zansty "+receivedData.getCommand());
				}else if (school=='W') {
					
					outputStream.writeObject(new SendOrReceiveData<>("add-department", Arrays.asList(universityId,collegeId,departmentId,departmentname, SchoolStudyType.Wezhaiy,capacity)));
			        outputStream.flush();
			        
			        SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
			        System.out.println("Wezhayi "+receivedData.getCommand());
				}else if (school=='A') {
					
					outputStream.writeObject(new SendOrReceiveData<>("add-department", Arrays.asList(universityId,collegeId,departmentId,departmentname, SchoolStudyType.Ayny,capacity)));
			        outputStream.flush();

			        SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
			        System.out.println("Ayny "+receivedData.getCommand());
					
				} else {
					System.out.println("Input invalid Please Enter School type Carefully");
			}    
		            } else {
					
					if (receivedDataCollege.getCommand().equals("find-college-by-key-failed")){ System.out.println("College not found ");} 
					if(receivedDataUniversity.getCommand().equals("find-university-by-id-failed")) { System.out.println("University Not Found");}
				}
				
				
			} catch (IOException | ClassNotFoundException e) {
				
				System.out.println("Adding Department Error " + e.getMessage());
			}
    	
    }
    
	
	

	
    private void removeDepartment(){

		try {
			System.out.println("Enter Department ID");
			String departmentId=input.next();
			System.out.println("Enter College ID");
			String collegeId=input.next();
			System.out.println("Enter University ID");
			String universityId=input.next();
			
			outputStream.writeObject(new SendOrReceiveData<>("find-university-by-id", universityId));
	        outputStream.flush();
	        SendOrReceiveData<String, Object> receivedDataUniversity = (SendOrReceiveData<String, Object>) inputStream.readObject();
			outputStream.writeObject(new SendOrReceiveData<>("find-college-by-key", Arrays.asList(universityId, collegeId)));
	        outputStream.flush();
	        SendOrReceiveData<String, Object> receivedDataCollege = (SendOrReceiveData<String, Object>) inputStream.readObject();
	        outputStream.writeObject(new SendOrReceiveData<>("find-department-by-key", Arrays.asList(universityId, collegeId)));
	        outputStream.flush();
	        SendOrReceiveData<String, Object> receivedDataDepartment = (SendOrReceiveData<String, Object>) inputStream.readObject();	
			
			
	        if (!receivedDataCollege.getCommand().equals("find-college-by-key-failed") && !receivedDataUniversity.getCommand().equals("find-university-by-id-failed") 
	        		&& receivedDataDepartment.getCommand().equals("find-department-by-key-failed")) {
	        	System.out.println("Enter Z for Zansty, W for Wezhaiy, A for Ayny");
	        	char school=input.next().trim().toUpperCase().charAt(0);
			
	        	if(school=='Z') {
				
				outputStream.writeObject(new SendOrReceiveData<>("remove-department", Arrays.asList(universityId,collegeId,departmentId, SchoolStudyType.Zansty)));
		        outputStream.flush();

		        SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
		        System.out.println(receivedData.getCommand());
					
	        	}else if (school=='W') {			
				outputStream.writeObject(new SendOrReceiveData<>("remove-department", Arrays.asList(universityId,collegeId,departmentId, SchoolStudyType.Wezhaiy)));
		        outputStream.flush();

		        SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
		        System.out.println(receivedData.getCommand());
	        	}else if (school=='A') {
				outputStream.writeObject(new SendOrReceiveData<>("remove-department", Arrays.asList(universityId,collegeId,departmentId, SchoolStudyType.Ayny)));
		        outputStream.flush();

		        SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
		        System.out.println(receivedData.getCommand());
				
			} else {
					System.out.println("Input invalid Please Enter School type Carefully");
			}
	        }else {
				
				if (receivedDataCollege.getCommand().equals("find-college-by-key-failed")){ System.out.println("Invalid College ID ");} 
				if(receivedDataUniversity.getCommand().equals("find-university-by-id-failed")) { System.out.println("Invalid University ID");}
				if(receivedDataDepartment.getCommand().equals("find-department-by-id-failed")) { System.out.println("Invalid Department ID");}
			}
			
			
			
			
		} catch (IOException | ClassNotFoundException e) {
			
			System.out.println("Removing Department Error " + e.getMessage());
		}
		
	}
	
	
	private void addCollege(){
		
        
        try {
			System.out.println("Enter College Name");
			String collegeName=input.next();
			System.out.println("Enter College ID");
			String collegeId=input.next();
			System.out.println("Enter University ID");
			String universityId=input.next();
			
			outputStream.writeObject(new SendOrReceiveData<>("find-university-by-id", universityId));
	        outputStream.flush();
	        SendOrReceiveData<String, Object> receivedDataUniversity = (SendOrReceiveData<String, Object>) inputStream.readObject();
			outputStream.writeObject(new SendOrReceiveData<>("find-college-by-key", Arrays.asList(universityId, collegeId)));
	        outputStream.flush();
	        SendOrReceiveData<String, Object> receivedDataCollege = (SendOrReceiveData<String, Object>) inputStream.readObject();
			
	        if (receivedDataCollege.getCommand().equals("find-college-by-key-failed") && !receivedDataUniversity.getCommand().equals("find-university-by-id-failed")) {
			outputStream.writeObject(new SendOrReceiveData<>("add-college", Arrays.asList(universityId,collegeId,collegeName)));
			outputStream.flush();	
			
	        SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
			System.out.println(receivedData.getCommand());		
			} else {
				if (receivedDataCollege.getCommand().equals("find-college-by-key-successful")){ System.out.println("Duplicate College ID ");} 
				if(receivedDataUniversity.getCommand().equals("find-university-by-id-failed")) { System.out.println("University Not found");}
				
			}

		} catch (IOException | ClassNotFoundException e) {
			
			System.out.println("Adding College Error " + e.getMessage());
		}
		
	}
	
	
	private void removeCollege() {

        try {
			System.out.println("Enter College ID");
			String collegeId=input.next();
			System.out.println("Enter University ID");
			String universityId=input.next();
			
			outputStream.writeObject(new SendOrReceiveData<>("find-university-by-id", universityId));
	        outputStream.flush();
	        SendOrReceiveData<String, Object> receivedDataUniversity = (SendOrReceiveData<String, Object>) inputStream.readObject();
			outputStream.writeObject(new SendOrReceiveData<>("find-college-by-key", Arrays.asList(universityId, collegeId)));
	        outputStream.flush();
	        SendOrReceiveData<String, Object> receivedDataCollege = (SendOrReceiveData<String, Object>) inputStream.readObject();
			
	        if (!receivedDataCollege.getCommand().equals("find-college-by-key-failed") && !receivedDataUniversity.getCommand().equals("find-university-by-id-failed")) {
	        outputStream.writeObject(new SendOrReceiveData<>("remove-college", Arrays.asList(universityId,collegeId)));
			outputStream.flush();	
	        SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
			System.out.println(receivedData.getCommand());	
	        }else {
	        	if (receivedDataCollege.getCommand().equals("find-college-by-key-successful")){ System.out.println("College Not Found ");} 
				if(receivedDataUniversity.getCommand().equals("find-university-by-id-failed")) { System.out.println("University Not found");}
	        	
	        }
			
			
		} catch (IOException | ClassNotFoundException e) {
			
			System.out.println("Removing College Error " + e.getMessage());
		}
		
	}
	
	
	private void addUniversity() {
        try {
			
			System.out.println("Enter University ID");
			String universityId=input.next();
			
			outputStream.writeObject(new SendOrReceiveData<>("find-university-by-id", universityId));
	        outputStream.flush();
	        SendOrReceiveData<String, Object> receivedDataUniversity = (SendOrReceiveData<String, Object>) inputStream.readObject();
			
	        if (receivedDataUniversity.getCommand().equals("find-university-by-id-failed")) {
	        	System.out.println("Enter University Name");
	        	String universityName=input.next();
	        	System.out.println("Enter University Location");
	        	String location=input.next();
		
	        	outputStream.writeObject(new SendOrReceiveData<>("add-university", Arrays.asList(universityId,universityName,location)));
	        	outputStream.flush();

	        	SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
	        	System.out.println(receivedData.getCommand());	
	        	
	        	
	        }else {
	        	if(receivedDataUniversity.getCommand().equals("find-university-by-id-failed")) { System.out.println("This University is in The System");}
	        }
			
			
		} catch (IOException | ClassNotFoundException e) {
			
			System.out.println("Adding University Error " + e.getMessage());
		}
		
	}
	private void removeUniversity(){

        try {
			System.out.println("Enter University ID");
			String universityId=input.next();
			
			outputStream.writeObject(new SendOrReceiveData<>("find-university-by-id", universityId));
	        outputStream.flush();
	        SendOrReceiveData<String, Object> receivedDataUniversity = (SendOrReceiveData<String, Object>) inputStream.readObject();
			
			if (!receivedDataUniversity.getCommand().equals("find-university-by-id-failed")) {
				outputStream.writeObject(new SendOrReceiveData<>("remove-university", universityId));
				outputStream.flush();

				SendOrReceiveData<String, Object> receivedData = (SendOrReceiveData<String, Object>) inputStream.readObject();
				System.out.println(receivedData.getCommand());
				
			}else {
				if(receivedDataUniversity.getCommand().equals("find-university-by-id-failed")) { System.out.println("University Not found");}
				
			}

			
		} catch (IOException | ClassNotFoundException e) {
			
			System.out.println("Remove University Error " + e.getMessage());
		}
        
	}
	
	private void showAllAdmins() {

	        String str;
			providers.AdminstratorsProvider.fetchAdminstrators();
			for (int i = 0; i < providers.AdminstratorsProvider.adminstrators.size(); i++) {
				str=providers.AdminstratorsProvider.adminstrators.get(i).toString();
				str=str.replace("password", "~");
				str="Admin "+(i+1)+" -->       "+str.substring(13,(str.indexOf("~")-2));
				System.out.println(str);
			}
				
		}
		
	
	
	private void editProfile() {
		
		
        try {
			System.out.println("You Can Change Name/Password...");
		System.out.println("Enter Your Name");
		String name=input.next();
		System.out.println("Enter Your new Password");
		String password=input.next();
		
		outputStream.writeObject(new SendOrReceiveData<>("edit-profile", Arrays.asList(admin.id(),name,password)));
        outputStream.flush();
		} catch (IOException e) {
			
			System.out.println("Edit Profile Error " + e.getMessage());
		}
		
	}
	
	private void shutdown() {
		
		try {
			outputStream.writeObject(new SendOrReceiveData<>("server-shutdown", null));
			outputStream.flush();
		} catch (IOException e) {
			
			System.out.println("Server Shutdown Error " + e.getMessage());
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
