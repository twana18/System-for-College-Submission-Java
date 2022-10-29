package providers;

import characters.Adminstrator;
package providers;
import Enums.SchoolStudyType;
import university_information.Department;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DepartmentsProvider {
	
	public static void main(String[] args) {
		
		
	}
    public static HashMap<List<String>, Department> ZanstyDepartmentHolder = new HashMap<>();
    public static HashMap<List<String>, Department> WezhaiyDepartmentHolder = new HashMap<>();
    public static HashMap<List<String>, Department> AynyDepartmentHolder = new HashMap<>();
    private static boolean isChanged = false;

    public static void getZanstydept() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("src/DataFiles/Departments_Zansty.txt"));
        ZanstyDepartmentHolder = (HashMap<List<String>, Department>) reader.readObject();
        reader.close();
        if (isChanged) {
            isChanged = false;
            System.out.println("Changes canceled");
        }
    }
    public static void getWezhaiydept() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("src/DataFiles/Departments_Wezhaiy.txt"));
        WezhaiyDepartmentHolder = (HashMap<List<String>, Department>) reader.readObject();
        reader.close();
        if (isChanged) {
            isChanged = false;
            System.out.println("Changes canceled");
        }
    }
    public static void getAynydept() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("src/DataFiles/Departments_Ayny.txt"));
        AynyDepartmentHolder = (HashMap<List<String>, Department>) reader.readObject();
        reader.close();
        if (isChanged) {
            isChanged = false;
            System.out.println("Changes canceled");
        }
    }
    
    public static void removedept(String deptID, SchoolStudyType type) {
    	if (type == SchoolStudyType.Zansty) {
    		ZanstyDepartmentHolder.remove(deptID);
    		
		}
    	if (type == SchoolStudyType.Wezhaiy) {
    		WezhaiyDepartmentHolder.remove(deptID);
    		
		}
    	if (type == SchoolStudyType.Ayny) {
    		AynyDepartmentHolder.remove(deptID);
    		
		}
    	
    	 if (!isChanged) {
             isChanged = true;
         }
    }

    public static void adddept(String deptID, SchoolStudyType type,String universityID, String collegeID, String deptName, int deptCapacity) {
    	if (type == SchoolStudyType.Zansty) {
    		ZanstyDepartmentHolder.put(Arrays.asList(universityID, collegeID, deptID), new Department(deptID, deptName, type, deptCapacity));
    		
    	}
    	if (type == SchoolStudyType.Wezhaiy) {
    		WezhaiyDepartmentHolder.put(Arrays.asList(universityID, collegeID, deptID), new Department(deptID, deptName, type, deptCapacity));
    		
    		}
    	
    	if (type == SchoolStudyType.Ayny) {
    		AynyDepartmentHolder.put(Arrays.asList(universityID, collegeID, deptID), new Department(deptID, deptName, type, deptCapacity));
    		
    		}
    	
    	 if (!isChanged) {
             isChanged = true;
         }
    
    }
    
    



}

