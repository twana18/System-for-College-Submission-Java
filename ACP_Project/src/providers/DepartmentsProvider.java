package providers;

import Enums.SchoolStudyType;
import university_information.College;
import university_information.Department;
import university_information.University;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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

    public static HashMap<List<String>, Department> getDepartmentsByType(SchoolStudyType type) {
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

    public static void displayDepartmentsByType(SchoolStudyType type) {
        String departmentName;
        String universityId;
        University university;
        String collegeId;
        College college;
        HashMap<List<String>, Department> departmentsHashMap;

        if (type == SchoolStudyType.Zansty) {
            departmentsHashMap = Objects.requireNonNull(getDepartmentsByType(SchoolStudyType.Zansty));
            for (int i = 0; i < departmentsHashMap.size(); i++) {
                departmentName = departmentsHashMap.values().stream().toList().get(i).getName();
                universityId = departmentsHashMap.keySet().stream().toList().get(i).get(0);
                university = UniversitiesProvider.getUNIVERSITIES().get(universityId);
                collegeId = departmentsHashMap.keySet().stream().toList().get(i).get(1);
                college = CollegesProvider.getCOLLEGES().get(Arrays.asList(universityId, collegeId));
                if (college == null) {
                    System.out.println((i + 1) + ". " + university.location() + university.name() + "/" + departmentName);
                    System.out.println();
                } else {
                    System.out.println((i + 1) + ". " + university.location() + "/" + university.name() + "/" + college.name() + "/" + departmentName);
                    System.out.println();
                }
            }
        } else if (type == SchoolStudyType.Wezhaiy) {
            departmentsHashMap = Objects.requireNonNull(getDepartmentsByType(SchoolStudyType.Wezhaiy));
            for (int i = 0; i < departmentsHashMap.size(); i++) {
                departmentName = departmentsHashMap.values().stream().toList().get(i).getName();

                universityId = departmentsHashMap.keySet().stream().toList().get(i).get(0);
                university = UniversitiesProvider.getUNIVERSITIES().get(universityId);
                collegeId = departmentsHashMap.keySet().stream().toList().get(i).get(1);
                college = CollegesProvider.getCOLLEGES().get(Arrays.asList(universityId, collegeId));
                if (college == null) {
                    System.out.println((i + 1) + ". " + university.location() + university.name() + "/" + departmentName);
                    System.out.println();
                } else {
                    System.out.println((i + 1) + ". " + university.location() + "/" + university.name() + "/" + college.name() + "/" + departmentName);
                    System.out.println();
                }
            }
        } else if (type == SchoolStudyType.Ayny) {
            departmentsHashMap = Objects.requireNonNull(getDepartmentsByType(SchoolStudyType.Ayny));
            for (int i = 0; i < departmentsHashMap.size(); i++) {
                departmentName = departmentsHashMap.values().stream().toList().get(i).getName();

                universityId = departmentsHashMap.keySet().stream().toList().get(i).get(0);
                university = UniversitiesProvider.getUNIVERSITIES().get(universityId);
                collegeId = departmentsHashMap.keySet().stream().toList().get(i).get(1);
                college = CollegesProvider.getCOLLEGES().get(Arrays.asList(universityId, collegeId));
                if (college == null) {
                    System.out.println((i + 1) + ". " + university.location() + university.name() + "/" + departmentName);
                    System.out.println();
                } else {
                    System.out.println((i + 1) + ". " + university.location() + "/" + university.name() + "/" + college.name() + "/" + departmentName);
                    System.out.println();
                }
            }
        }

    }

}
