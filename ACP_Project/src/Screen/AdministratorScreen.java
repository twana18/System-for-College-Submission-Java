

package Screen;


import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import Enums.SchoolStudyType;
import characters.Adminstrator;


public class AdminstratorScreen {

	
	
	public static void ShowAdminstrator(Adminstrator admin) throws ClassNotFoundException, IOException {
		
		Scanner s=new Scanner(System.in);
		int input;
		String adminID,name,password,departmentID,collegeID,universityID,location;
		char school;
		int capacity;
			
		
		
		
		while (true) {
			menu();
			try {
				input=s.nextInt();
				if(input==1) {
					System.out.println("Enter ID for new Admin");
					adminID=s.next();
					System.out.println("Enter Name for new admin");
					name=s.next();
					System.out.println("Enter Password for new admin");
					password=s.next();
					addAdmin(adminID,name,password);
					
				}else if(input==2) {
					System.out.println("Enter Admin ID to remove");
					adminID=s.next().toUpperCase();
					removeAdmin(adminID);
					
					
				}else if(input==3) {
					System.out.println("Enter Department ID");
					departmentID=s.next();
					//department Checking
					System.out.println("Enter College ID");
					collegeID=s.next();
					//College Checking
					System.out.println("Enter University ID");
					universityID=s.next();
					//University Checking
					System.out.println("Enter Department Name");
					name=s.next();
					System.out.println("Enter Department Capacity");
					capacity=s.nextInt();
					System.out.println("Enter Z for Zansty, W for Wezhaiy, A for Ayny");
					school=s.next().trim().toUpperCase().charAt(0);
					addDepartment(departmentID,collegeID,universityID,name,school,capacity);
					
					
					
					
				}else if(input==4) {
					System.out.println("Enter Department ID");
					departmentID=s.next();
					System.out.println("Enter College ID");
					collegeID=s.next();
					System.out.println("Enter University ID");
					universityID=s.next();
					System.out.println("Enter Z for Zansty, W for Wezhaiy, A for Ayny");
					school=s.next().trim().toUpperCase().charAt(0);
					
					removeDepartment(departmentID,collegeID,universityID,school);
					
					
				}else if(input==5) {
					System.out.println("Enter College Name");
					name=s.next();
					System.out.println("Enter College ID");
					collegeID=s.next();
					//College ID Checking
					System.out.println("Enter University ID");
					universityID=s.next();
					//University Checking
					
					addCollege(universityID, collegeID, name);
					
					
				}else if(input==6) {
					System.out.println("Enter College ID");
					collegeID=s.next();
					System.out.println("Enter University ID");
					universityID=s.next();
					
					removeCollege(collegeID,universityID);
					
				}else if(input==7) {
					System.out.println("Enter University Name");
					name=s.next();
					System.out.println("Enter University ID");
					universityID=s.next();
					//Checking University ID
					System.out.println("Enter University Location");
					location=s.next();
					
					addUniversity(universityID, name, location);
					
					
					
				}else if(input==8) {
					System.out.println("Enter University ID");
					universityID=s.next();
					
					removeUniversity(universityID);
					
					
					
					
				}else if(input==9) {
					
					showAllAdmins();
					
				}else if(input==10) {
					System.out.println("You Can Change Name/Password...");
					System.out.println("Enter Your Name");
					name=s.next();
					System.out.println("Enter Your new Password");
					password=s.next();
					EditProfile(admin,name,password);
				}
				
				
				
				
				else if(input==11) {
					System.exit(0);
					break;
				} else {
					System.out.println("Invalid! Try Again");
				}
				
				
			}catch(InputMismatchException e) {
				System.out.println("invalid input!");
				s.next();
			}
		}
		
			
		s.close();
		
		
		
		

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
public static void showAllAdmins() throws ClassNotFoundException, IOException {
		String str;
		providers.AdminstratorsProvider.fetchAdminstrators();
		for (int i = 0; i < providers.AdminstratorsProvider.adminstrators.size(); i++) {
			str=providers.AdminstratorsProvider.adminstrators.get(i).toString();
			str=str.replace("password", "~");
			str="Admin "+(i+1)+" --> "+str.substring(13,(str.indexOf("~")-2));
			System.out.println(str);
			
			
			
		}
		
	}
	
	public static void EditProfile(Adminstrator admin, String name, String password) throws ClassNotFoundException, IOException {
		providers.AdminstratorsProvider.fetchAdminstrators();
		providers.AdminstratorsProvider.removeAdminstrator(admin.id());
		providers.AdminstratorsProvider.addAdminstrator(admin.id(), name, password);
		providers.AdminstratorsProvider.submitChanges();
		
		
		
		
	}
	
	
	

}
