package providers;

import Models.College;
import Models.Department;
import Models.SchoolStudyType;
import Models.University;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class DepartmentsProvider {
    private static ConcurrentHashMap<List<String>, Department> ZanstyDepartmentHolder = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<List<String>, Department> WezhaiyDepartmentHolder = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<List<String>, Department> AynyDepartmentHolder = new ConcurrentHashMap<>();

    private static boolean isZanstyChanged = false;
    private static boolean isWezhaiyChanged = false;
    private static boolean isAynyChanged = false;

    public static void fetchDepartmentsByType(SchoolStudyType type) {
        if (type == SchoolStudyType.Zansty) {
            try {
                ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Departments_Zansty.txt"));
                ZanstyDepartmentHolder = (ConcurrentHashMap<List<String>, Department>) reader.readObject();
                reader.close();
                if (isZanstyChanged) {
                    isZanstyChanged = false;
                    System.out.println("Changes canceled for zansty Departments");
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Cannot fetch zansty departments: " + e.getMessage());
            }
        } else if (type == SchoolStudyType.Wezhaiy) {
            try {
                ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Departments_Wezhaiy.txt"));
                WezhaiyDepartmentHolder = (ConcurrentHashMap<List<String>, Department>) reader.readObject();
                reader.close();
                if (isWezhaiyChanged) {
                    isWezhaiyChanged = false;
                    System.out.println("Changes canceled for wezhaiy Departments");
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Cannot fetch wezhaiy departments: " + e.getMessage());
            }
        } else if (type == SchoolStudyType.Ayny) {
            try {
                ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Departments_Ayny.txt"));
                AynyDepartmentHolder = (ConcurrentHashMap<List<String>, Department>) reader.readObject();
                reader.close();
                if (isAynyChanged) {
                    isAynyChanged = false;
                    System.out.println("Changes canceled for Ayny Departments");
                }
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Cannot fetch ayny departments: " + e.getMessage());
            }
        }

    }

    public static ConcurrentHashMap<List<String>, Department> getDepartmentsByType(SchoolStudyType type) {
        if (type == SchoolStudyType.Zansty) return ZanstyDepartmentHolder;
        else if (type == SchoolStudyType.Wezhaiy) return WezhaiyDepartmentHolder;
        else if (type == SchoolStudyType.Ayny) return AynyDepartmentHolder;
        else return null;
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

    public static void zanstySubmitChanges() {
        try {
            if (isZanstyChanged) {
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("ACP_Project/src/DataFiles/Departments_Zansty.txt"));
                outputStream.writeObject(ZanstyDepartmentHolder);
                outputStream.close();
                ZanstyDepartmentHolder.clear();
                isZanstyChanged = false;
                fetchDepartmentsByType(SchoolStudyType.Zansty);
            } else {
                System.out.println("Please add or remove at least a zansty Department");
            }
        } catch (IOException e) {
            System.out.println("Cannot submit changes in zansty departments: " + e.getMessage());
        }
    }

    public static void wezhaiySubmitChanges() {
        try {
            if (isWezhaiyChanged) {
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("ACP_Project/src/DataFiles/Departments_Wezhaiy.txt"));
                outputStream.writeObject(WezhaiyDepartmentHolder);
                outputStream.close();
                WezhaiyDepartmentHolder.clear();
                isWezhaiyChanged = false;
                fetchDepartmentsByType(SchoolStudyType.Wezhaiy);
            } else {
                System.out.println("Please add or remove at least a wezhaiy Department");
            }
        } catch (IOException e) {
            System.out.println("Cannot submit changes in wezhaiy departments: " + e.getMessage());
        }


    }

    public static void aynySubmitChanges() {
        try {
            if (isAynyChanged) {
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("ACP_Project/src/DataFiles/Departments_Ayny.txt"));
                outputStream.writeObject(AynyDepartmentHolder);
                outputStream.close();
                AynyDepartmentHolder.clear();
                isAynyChanged = false;
                fetchDepartmentsByType(SchoolStudyType.Ayny);
            } else {
                System.out.println("Please add or remove at least an ayny Department");
            }
        } catch (IOException e) {
            System.out.println("Cannot submit changes in ayny departments: " + e.getMessage());
        }

    }

}
