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
    
    
    public static void ZanstysubmitChanges() throws IOException, ClassNotFoundException {
        if(isChanged) {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/DataFiles/Departments_Zansty.txt"));
            outputStream.writeObject(ZanstyDepartmentHolder);
            outputStream.close();
            ZanstyDepartmentHolder.clear();
            isChanged = false;
            getZanstydept();
        }else {
                System.out.println("Please add or remove at least a University");
            }

    }
    
    public static void WezhaiysubmitChanges() throws IOException, ClassNotFoundException {
        if(isChanged) {
        	ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/DataFiles/Departments_Wezhaiy.txt"));
            outputStream.writeObject(WezhaiyDepartmentHolder);
            outputStream.close();
            WezhaiyDepartmentHolder.clear();
            isChanged = false;
            getWezhaiydept();
        }else {
                System.out.println("Please add or remove at least a University");
            }
      
    }
    public static void AynysubmitChanges() throws IOException, ClassNotFoundException {
        if(isChanged) {
        	ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/DataFiles/Departments_Ayny.txt"));
            outputStream.writeObject(AynyDepartmentHolder);
            outputStream.close();
            AynyDepartmentHolder.clear();
            isChanged = false;
            getAynydept();
        }else {
                System.out.println("Please add or remove at least a University");
            }
    }
   
    public static void ZanstytestMethod() throws IOException, ClassNotFoundException {
        System.out.println("Zansty Department before getting data: " + ZanstyDepartmentHolder);
        getZanstydept();
        System.out.println("Zansty Department after getting data: " + ZanstyDepartmentHolder);
        ZanstysubmitChanges();
        adddept("1",SchoolStudyType.Zansty,"Test","Test","Test",30);
        adddept("2",SchoolStudyType.Zansty,"","","",35);
        getZanstydept();
        ZanstysubmitChanges();
        adddept("1",SchoolStudyType.Zansty,"","","",30);
        adddept("2",SchoolStudyType.Zansty,"","","",35);
        ZanstysubmitChanges();
        System.out.println("Zansty Department after submitting changes: " + ZanstyDepartmentHolder);
        removedept("1",SchoolStudyType.Zansty);
        removedept("2",SchoolStudyType.Zansty);
        ZanstysubmitChanges();
        System.out.println("Zansty Department after submitting changes: " + ZanstyDepartmentHolder);
    }
    
    public static void WezhaiytestMethod() throws IOException, ClassNotFoundException {
        System.out.println("Wezhaiy Department before getting data: " + WezhaiyDepartmentHolder);
        getWezhaiydept();
        System.out.println("Wezhaiy Department after getting data: " + WezhaiyDepartmentHolder);
        WezhaiysubmitChanges();
        adddept("dept05",SchoolStudyType.Wezhaiy,"07","04","English Department",30);
        adddept("2",SchoolStudyType.Wezhaiy,"","","",35);
        getWezhaiydept();
        WezhaiysubmitChanges();
        adddept("1",SchoolStudyType.Wezhaiy,"","","",30);
        adddept("2",SchoolStudyType.Wezhaiy,"","","",35);
        WezhaiysubmitChanges();
        System.out.println("Wezhaiy Department after submitting changes: " + WezhaiyDepartmentHolder);
        removedept("1",SchoolStudyType.Wezhaiy);
        removedept("2",SchoolStudyType.Wezhaiy);
        WezhaiysubmitChanges();
        System.out.println("Wezhaiy Department after submitting changes: " + WezhaiyDepartmentHolder);
    }
    
    public static void AynytestMethod() throws IOException, ClassNotFoundException {
        System.out.println("Ayny Department before getting data: " + AynyDepartmentHolder);
        getAynydept();
        System.out.println("Ayny Department after getting data: " + AynyDepartmentHolder);
        AynysubmitChanges();
        adddept("1",SchoolStudyType.Ayny,"","","",30);
        adddept("2",SchoolStudyType.Ayny,"","","",35);
        getAynydept();
        AynysubmitChanges();
        adddept("1",SchoolStudyType.Ayny,"","","",30);
        adddept("2",SchoolStudyType.Ayny,"","","",35);
        AynysubmitChanges();
        System.out.println("Ayny Department after submitting changes: " + AynyDepartmentHolder);
        removedept("1",SchoolStudyType.Ayny);
        removedept("2",SchoolStudyType.Ayny);
        AynysubmitChanges();
        System.out.println("Ayny Department after submitting changes: " + AynyDepartmentHolder);
    }



}

