package providers;

import Enums.SchoolStudyType;
import university_information.Department;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DepartmentsProvider {
    private static HashMap<List<String>, Department> ZanstyDepartmentHolder = new HashMap<>();
    private static HashMap<List<String>, Department> WezhaiyDepartmentHolder = new HashMap<>();
    private static HashMap<List<String>, Department> AynyDepartmentHolder = new HashMap<>();

    private static boolean isZanstyChanged = false;
    private static boolean isWezhaiyChanged = false;
    private static boolean isAynyChanged = false;

    public static void fetchZanstyDepartments() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Departments_Zansty.txt"));
        ZanstyDepartmentHolder = (HashMap<List<String>, Department>) reader.readObject();
        reader.close();
        if (isZanstyChanged) {
            isZanstyChanged = false;
            System.out.println("Changes canceled for zansty Departments");
        }
    }

    public static void fetchWezhaiyDepartments() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Departments_Wezhaiy.txt"));
        WezhaiyDepartmentHolder = (HashMap<List<String>, Department>) reader.readObject();
        reader.close();
        if (isWezhaiyChanged) {
            isWezhaiyChanged = false;
            System.out.println("Changes canceled for wezhaiy Departments");
        }
    }

    public static void fetchAynyDepartments() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Departments_Ayny.txt"));
        AynyDepartmentHolder = (HashMap<List<String>, Department>) reader.readObject();
        reader.close();
        if (isAynyChanged) {
            isAynyChanged = false;
            System.out.println("Changes canceled for Ayny Departments");
        }
    }

    public static HashMap<List<String>, Department> getZanstyDepartments() {
        return ZanstyDepartmentHolder;
    }

    public static HashMap<List<String>, Department> getWezhaiyDepartments() {
        return WezhaiyDepartmentHolder;
    }

    public static HashMap<List<String>, Department> getAynyDepartments() {
        return AynyDepartmentHolder;
    }

    public static void addDepartment(String universityID, String collegeID, String deptID, String deptName, SchoolStudyType type, int deptCapacity) {
        if (type == SchoolStudyType.Zansty) {
            ZanstyDepartmentHolder.put(Arrays.asList(universityID, collegeID, deptID), new Department(deptID, deptName, type, deptCapacity));
            if (!isZanstyChanged) {
                isZanstyChanged = true;
            }
        } else if (type == SchoolStudyType.Wezhaiy) {
            WezhaiyDepartmentHolder.put(Arrays.asList(universityID, collegeID, deptID), new Department(deptID, deptName, type, deptCapacity));
            if (!isWezhaiyChanged) {
                isWezhaiyChanged = true;
            }
        } else if (type == SchoolStudyType.Ayny) {
            AynyDepartmentHolder.put(Arrays.asList(universityID, collegeID, deptID), new Department(deptID, deptName, type, deptCapacity));
            if (!isAynyChanged) {
                isAynyChanged = true;
            }
        }
    }

    public static void removeDepartment(String universityId, String collegeID, String deptID, SchoolStudyType type) {
        if (type == SchoolStudyType.Zansty) {
            ZanstyDepartmentHolder.remove(Arrays.asList(universityId, collegeID, deptID));
            if (!isZanstyChanged) {
                isZanstyChanged = true;
            }
        } else if (type == SchoolStudyType.Wezhaiy) {
            WezhaiyDepartmentHolder.remove(Arrays.asList(universityId, collegeID, deptID));
            if (!isWezhaiyChanged) {
                isWezhaiyChanged = true;
            }
        } else if (type == SchoolStudyType.Ayny) {
            AynyDepartmentHolder.remove(Arrays.asList(universityId, collegeID, deptID));
            if (!isAynyChanged) {
                isAynyChanged = true;
            }
        }
    }

    public static void zanstySubmitChanges() throws IOException, ClassNotFoundException {
        if (isZanstyChanged) {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("ACP_Project/src/DataFiles/Departments_Zansty.txt"));
            outputStream.writeObject(ZanstyDepartmentHolder);
            outputStream.close();
            ZanstyDepartmentHolder.clear();
            isZanstyChanged = false;
            fetchZanstyDepartments();
        } else {
            System.out.println("Please add or remove at least a zansty Department");
        }
    }

    public static void wezhaiySubmitChanges() throws IOException, ClassNotFoundException {
        if (isWezhaiyChanged) {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("ACP_Project/src/DataFiles/Departments_Wezhaiy.txt"));
            outputStream.writeObject(WezhaiyDepartmentHolder);
            outputStream.close();
            WezhaiyDepartmentHolder.clear();
            isWezhaiyChanged = false;
            fetchWezhaiyDepartments();
        } else {
            System.out.println("Please add or remove at least a wezhaiy Department");
        }

    }

    public static void aynySubmitChanges() throws IOException, ClassNotFoundException {
        if (isAynyChanged) {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("ACP_Project/src/DataFiles/Departments_Ayny.txt"));
            outputStream.writeObject(AynyDepartmentHolder);
            outputStream.close();
            AynyDepartmentHolder.clear();
            isAynyChanged = false;
            fetchAynyDepartments();
        } else {
            System.out.println("Please add or remove at least an ayny Department");
        }
    }

    public static void displayZanstyDepartments(){
        for (int i = 0; i < getZanstyDepartments().values().size(); i++) {
            System.out.println(getZanstyDepartments().keySet().stream().toList().get(i));
            System.out.println(getZanstyDepartments().values().stream().toList().get(i));
            System.out.println();
        }
    }
    public static void displayWezhaiyDepartments(){
        for (int i = 0; i < getWezhaiyDepartments().values().size(); i++) {
            System.out.println(getWezhaiyDepartments().keySet().stream().toList().get(i));
            System.out.println(getWezhaiyDepartments().values().stream().toList().get(i));
            System.out.println();
        }
    }
    public static void displayAynyDepartments(){
        for (int i = 0; i < getAynyDepartments().values().size(); i++) {
            System.out.println(getAynyDepartments().keySet().stream().toList().get(i));
            System.out.println(getAynyDepartments().values().stream().toList().get(i));
            System.out.println();
        }
    }
    
}
