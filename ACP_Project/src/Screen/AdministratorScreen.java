

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
	
	
	public static void menu() {

        System.out.println("*************************************");
        System.out.println("***********   WELCOME   *************");
        System.out.println("*************************************");
        System.out.println("******* [1]Add Admin ****************");
        System.out.println("******* [2]Remove Admin *************");
        System.out.println("******* [3]Add Department ***********");
        System.out.println("******* [4]Remove Department ********");
        System.out.println("******* [5]Add College **************");
        System.out.println("******* [6]Remove College ***********");
        System.out.println("******* [7]Add University ***********");
        System.out.println("******* [8]Remove University ********");
        System.out.println("******* [9]Show Admins **************");
        System.out.println("******* [10]Edit Profile ************");
        System.out.println("******* [11]System Exit *************");
        System.out.println("*************************************");

    }

    public static void addAdmin(String id,String name,String password) throws ClassNotFoundException, IOException {


        providers.AdminstratorsProvider.fetchAdminstrators();
        providers.AdminstratorsProvider.addAdminstrator(id, name, password);
        providers.AdminstratorsProvider.submitChanges();

    }

    public static void removeAdmin(String id) throws ClassNotFoundException, IOException {


        providers.AdminstratorsProvider.fetchAdminstrators();
        providers.AdminstratorsProvider.removeAdminstrator(id);
        providers.AdminstratorsProvider.submitChanges();

    }

    public static void addDepartment(String universityID,String collegeID,String departmentID,String departmentname, char school,int capacity ) throws ClassNotFoundException, IOException {

        if(school=='Z') {
            providers.DepartmentsProvider.fetchZanstyDepartments();
            providers.DepartmentsProvider.addDepartment(universityID,collegeID,departmentID,departmentname, SchoolStudyType.Zansty,capacity);
            providers.DepartmentsProvider.zanstySubmitChanges();

        }else if (school=='W') {
            providers.DepartmentsProvider.fetchWezhaiyDepartments();
            providers.DepartmentsProvider.addDepartment(universityID,collegeID,departmentID,departmentname, SchoolStudyType.Wezhaiy,capacity);
            providers.DepartmentsProvider.wezhaiySubmitChanges();

        }else if (school=='A') {
            providers.DepartmentsProvider.fetchAynyDepartments();
            providers.DepartmentsProvider.addDepartment(universityID,collegeID,departmentID,departmentname, SchoolStudyType.Ayny,capacity);
            providers.DepartmentsProvider.aynySubmitChanges();

        } else {
            System.out.println("Input invalid Please Enter School type Carefully");
        }




    }
    public static void removeDepartment(String departmentID,String collegeID,String universityID,char school) throws ClassNotFoundException, IOException {

        if(school=='Z') {
            providers.DepartmentsProvider.fetchZanstyDepartments();
            providers.DepartmentsProvider.removeDepartment(universityID,collegeID,departmentID, SchoolStudyType.Zansty);
            providers.DepartmentsProvider.zanstySubmitChanges();

        }else if (school=='W') {
            providers.DepartmentsProvider.fetchWezhaiyDepartments();
            providers.DepartmentsProvider.removeDepartment(universityID,collegeID,departmentID, SchoolStudyType.Wezhaiy);
            providers.DepartmentsProvider.wezhaiySubmitChanges();

        }else if (school=='A') {
            providers.DepartmentsProvider.fetchAynyDepartments();
            providers.DepartmentsProvider.removeDepartment(universityID,collegeID,departmentID, SchoolStudyType.Ayny);
            providers.DepartmentsProvider.aynySubmitChanges();


        } else {
            System.out.println("Input invalid Please Enter School type Carefully");
        }
    }


    public static void addCollege(String universityID, String collegeID, String name) {

        providers.CollegesProvider.addCollege(universityID, collegeID, name);

    }


    public static void removeCollege(String collegeID, String universityID) {

        providers.CollegesProvider.removeCollege(universityID, collegeID);

    }


    public static void addUniversity(String universityID, String name, String location) throws ClassNotFoundException, IOException {


        providers.UniversitiesProvider.fetchUniversities();
        providers.UniversitiesProvider.addUniversity(universityID, name, location);
        providers.UniversitiesProvider.submitChanges();

    }
    public static void removeUniversity(String universityID) throws ClassNotFoundException, IOException {


        providers.UniversitiesProvider.fetchUniversities();
        providers.UniversitiesProvider.removeUniversity(universityID);
        providers.UniversitiesProvider.submitChanges();

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
