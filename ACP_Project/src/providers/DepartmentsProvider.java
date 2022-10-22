package providers;

import Custom_Generic_Classes_Interfaces.TripleContainer;
import Enums.SchoolStudyType;
import university_information.Department;

import java.util.HashMap;

public class DepartmentsProvider {
    public static HashMap<TripleContainer<String, String, String>, Department> DEPARTMENTSforZansty = new HashMap<>();
    public static HashMap<TripleContainer<String, String, String>, Department> DEPARTMENTSforWezhaiy = new HashMap<>();
    public static HashMap<TripleContainer<String, String, String>, Department> DEPARTMENTSforAyny = new HashMap<>();

    public static void gettingData(){ }

    public static void addDepartment(String universityID,String collegeID, String deptID, String deptName, SchoolStudyType type, int deptCapacity){
        if (type == SchoolStudyType.Zansty){
            DEPARTMENTSforZansty.put(new TripleContainer<>(universityID, collegeID, deptID), new Department(deptID, deptName,type,deptCapacity));
        }
        if (type == SchoolStudyType.Wezhaiy){
            DEPARTMENTSforWezhaiy.put(new TripleContainer<>(universityID, collegeID, deptID), new Department(deptID, deptName,type,deptCapacity));
        }
        if (type == SchoolStudyType.Ayny){
            DEPARTMENTSforAyny.put(new TripleContainer<>(universityID, collegeID, deptID), new Department(deptID, deptName,type,deptCapacity));
        }
    }

    public static void removeDepartment(String universityID,String collegeID, String deptID){
                DEPARTMENTSforZansty.remove(new TripleContainer<>(universityID,collegeID, deptID));
                DEPARTMENTSforWezhaiy.remove(new TripleContainer<>(universityID,collegeID, deptID));
                DEPARTMENTSforAyny.remove(new TripleContainer<>(universityID,collegeID, deptID));
    }
    
}
