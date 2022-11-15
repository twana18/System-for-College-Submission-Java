package Screen;
import characters.*;
import providers.*;
import java.io.IOException;
import java.util.Scanner;




public class LoginScreen {
	
	

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		 
		 
		 
	       try (Scanner scanner = new Scanner(System.in)) {
	            System.out.print(" Enter username id: ");
	            String Userid = scanner.nextLine().toUpperCase().trim();

	            System.out.print(" Enter password : ");
	            String password = scanner.nextLine();
	           characters.Student Sd= StudentsProvider.findStudent(Userid,password);
	           Adminstrator Ad = AdminstratorsProvider.find(Userid, password);
	           
	            if( Sd!=null&&Sd.getId().equals(Userid)&&Sd.getPassword().equals(password)) 
	            {
	            	new StudentScreen();
	            }
	            else if(Ad!=null&&Ad.id().equals(Userid) &&Ad.password().equals(password)) {
	            	Screen.AdminstratorScreen.ShowAdminstrator(Ad);
	            }
	            else {System.out.println("Invalid Password Or UserId");}
	        }
	        }
            }
