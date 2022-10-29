package providers;

import Custom_Generic_Classes_Interfaces.TripleContainer;
import Enums.SchoolStudyType;
import university_information.Department;
import java.util.*;
import java.util.HashMap;
import java.io.*;

public class DepartmentsProvider {
    public static HashMap<List<String>, Department> DEPARTMENTSforZansty = new HashMap<>();
    public static HashMap<List<String>, Department> DEPARTMENTSforWezhaiy = new HashMap<>();
    public static HashMap<List<String>, Department> DEPARTMENTSforAyny = new HashMap<>();

    public static void gettingData(){
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL01" , "dept01"), new Department("dept01" ,"Department of Architecture Engineering" ,SchoolStudyType.Zansty,50 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL01" , "dept02"), new Department("dept02" ,"Department of Civil Engineering" ,SchoolStudyType.Zansty,65 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL01" , "dept03"), new Department("dept03" ,"Department of Electrical Engineering" ,SchoolStudyType.Zansty,75 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL01" , "dept04"), new Department("dept04" ,"Department of Software Engineering" ,SchoolStudyType.Zansty,70 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL01" , "dept05"), new Department("dept05" ,"Department of Mechanical Engineering" ,SchoolStudyType.Zansty,80 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL01" , "dept06"), new Department("dept06" ,"Department of Geomatic Engineering" ,SchoolStudyType.Zansty,90 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL02" , "dept01"), new Department("dept01" ,"Chemistry Department" ,SchoolStudyType.Zansty,58 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL02" , "dept02"), new Department("dept02" ,"Physics Department" ,SchoolStudyType.Zansty,52 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL02" , "dept03"), new Department("dept03" ,"Mathematics Department" ,SchoolStudyType.Zansty,75 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL02" , "dept01"), new Department("dept01" ,"Kurdish Language Department" ,SchoolStudyType.Zansty,50 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL02" , "dept02"), new Department("dept02" ,"Arabic Language Department" ,SchoolStudyType.Zansty,15));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL02" , "dept03"), new Department("dept03" ,"English Language Department" ,SchoolStudyType.Zansty,55 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL02" , "dept04"), new Department("dept04" ,"Sryani Language Department" ,SchoolStudyType.Zansty,40));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL02" , "dept05"), new Department("dept05" ,"Biology Department" ,SchoolStudyType.Zansty,65 ));
        DEPARTMENTSforWezhaiy.put(Arrays.asList("UN01","COL02" , "dept02"), new Department("dept02" ,"Arabic Language Department" ,SchoolStudyType.Wezhaiy,20));
        DEPARTMENTSforAyny.put(Arrays.asList("UN01", "COL02" , "dept02"), new Department("dept02","Arabic Language Department" ,SchoolStudyType.Ayny,30));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL03" , "dept01"), new Department("dept01" ,"Biology Department" ,SchoolStudyType.Zansty,55 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL03" , "dept02"), new Department("dept02" ,"Chemistry Department" ,SchoolStudyType.Zansty,68 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL03" , "dept03"), new Department("dept03" ,"IT Department" ,SchoolStudyType.Zansty,70 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL03" , "dept04"), new Department("dept04" ,"Computer Science Department" ,SchoolStudyType.Zansty,65 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL03" , "dept05"), new Department("dept05" ,"Mathematics Department" ,SchoolStudyType.Zansty,66 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL04" , "dept01"), new Department("dept01" ,"Department of Arts" ,SchoolStudyType.Zansty,20 ));
        DEPARTMENTSforWezhaiy.put(Arrays.asList("UN01", "COL04" , "dept01"), new Department("dept01" ,"Department of Arts" ,SchoolStudyType.Zansty,45 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL05" , "dept01"), new Department("dept01" ,"Islamic Law Department" ,SchoolStudyType.Zansty,10 ));
        DEPARTMENTSforWezhaiy.put(Arrays.asList("UN01", "COL05" , "dept01"), new Department("dept01" ,"Islamic Law Department" ,SchoolStudyType.Wezhaiy,15 ));
        DEPARTMENTSforAyny.put(Arrays.asList("UN01", "COL05" , "dept01"), new Department( "dept01" ,"Islamic Law Department" ,SchoolStudyType.Ayny,35 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL06" , "dept01"), new Department( "dept01" ,"Department of Law" ,SchoolStudyType.Zansty,30 ));
        DEPARTMENTSforWezhaiy.put(Arrays.asList("UN01", "COL06" , "dept01"), new Department( "dept01" ,"Department of Law" ,SchoolStudyType.Wezhaiy,45 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL07" , "dept01"), new Department("dept01" ,"All Departments" ,SchoolStudyType.Zansty,56 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL08" , "dept01"), new Department("dept01" ,"Department of Business Administration" ,SchoolStudyType.Zansty,40));
        DEPARTMENTSforWezhaiy.put(Arrays.asList("UN01", "COL08" , "dept01"), new Department( "dept01" ,"Department of Business Administration" ,SchoolStudyType.Wezhaiy,25 ));
        DEPARTMENTSforWezhaiy.put(Arrays.asList("UN01", "COL09" , "dept01"), new Department( "dept01" ,"Department of History" ,SchoolStudyType.Wezhaiy,40 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL09" , "dept01"), new Department( "dept01" ,"Department of History" ,SchoolStudyType.Wezhaiy,20 ));
        DEPARTMENTSforWezhaiy.put(Arrays.asList("UN01", "COL09" , "dept02"), new Department( "dept02" ,"Department of Geography" ,SchoolStudyType.Wezhaiy,35 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL09" , "dept02"), new Department( "dept02" ,"Department of Geography" ,SchoolStudyType.Zansty,20 ));
        DEPARTMENTSforWezhaiy.put(Arrays.asList("UN01", "COL10" , "dept03"), new Department( "dept03" ,"Arabic Department" ,SchoolStudyType.Wezhaiy,20 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN01", "COL10" , "dept03"), new Department( "dept03" ,"Arabic Department" ,SchoolStudyType.Zansty,15 ));
        DEPARTMENTSforAyny.put(Arrays.asList("UN01", "COL10" , "dept03"), new Department( "dept03" ,"Arabic Department" ,SchoolStudyType.Ayny,35 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN02", "COL01" , "dept01"), new Department( "dept01" ,"Medicine Department" ,SchoolStudyType.Zansty,100 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN02", "COL02" , "dept01"), new Department( "dept01" ,"Dental Department" ,SchoolStudyType.Zansty,87 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN02", "COL03" , "dept01"), new Department( "dept01" ,"Pharmacy Department" ,SchoolStudyType.Zansty,75 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN03", "COL06" , "dept01"), new Department( "dept01" ,"Department of Law" ,SchoolStudyType.Zansty,25 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN03", "COL06" , "dept02"), new Department( "dept02" ,"Department of Political Science" ,SchoolStudyType.Zansty,20 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN03", "COL06" , "dept03"), new Department( "dept03" ,"International Relations Department" ,SchoolStudyType.Zansty,30 ));
        DEPARTMENTSforWezhaiy.put(Arrays.asList("UN03", "COL06" , "dept01"), new Department( "dept01" ,"Department of Law" ,SchoolStudyType.Wezhaiy,20 ));
        DEPARTMENTSforWezhaiy.put(Arrays.asList("UN03", "COL06" , "dept02"), new Department( "dept02" ,"Department of Political Science" ,SchoolStudyType.Wezhaiy,20 ));
        DEPARTMENTSforWezhaiy.put(Arrays.asList("UN03", "COL06" , "dept03"), new Department( "dept03" ,"International Relations Department" ,SchoolStudyType.Wezhaiy,30 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN05", "COL01" , "dept01"), new Department( "dept01" ,"Department of Civil Engineering " ,SchoolStudyType.Zansty,48));
        DEPARTMENTSforZansty.put(Arrays.asList("UN05", "COL01" , "dept02"), new Department( "dept02" ,"Department of Energy and Mechanic Engineering" ,SchoolStudyType.Zansty,68 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN05", "COL01" , "dept03"), new Department( "dept03" ,"Department of System Information Engineering" ,SchoolStudyType.Zansty,55 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN05", "COL02" , "dept01"), new Department( "dept01" ,"Department of Pharmacy" ,SchoolStudyType.Zansty,65 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN05", "COL02" , "dept02"), new Department( "dept02" ,"Anesthesia Department" ,SchoolStudyType.Zansty,79 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN05", "COL02" , "dept03"), new Department( "dept03" ,"Disease Analysis Department" ,SchoolStudyType.Zansty,69 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN05", "COL02" , "dept04"), new Department( "dept04" ,"Dental Assisting Department" ,SchoolStudyType.Zansty,69 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN05", "COL03" , "dept01"), new Department( "dept01" ,"IT Department" ,SchoolStudyType.Zansty,77 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN05", "COL03" , "dept02"), new Department( "dept02" ,"ICT Department" ,SchoolStudyType.Zansty,86 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN05", "COL03" , "dept03"), new Department( "dept03" ,"Oil Technology Department" ,SchoolStudyType.Zansty,54 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN05", "COL03" , "dept04"), new Department( "dept04" ,"Electric Department" ,SchoolStudyType.Zansty,49 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN05", "COL04" , "dept01"), new Department( "dept01" ,"International Marketing Department" ,SchoolStudyType.Zansty,49 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN06", "COL01" , "dept01"), new Department( "dept01" ,"Mechanical Engineering Department" ,SchoolStudyType.Zansty,49 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN06", "COL01" , "dept02"), new Department( "dept02" ,"Oil and Energy Engineering Department" ,SchoolStudyType.Zansty,59 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN06", "COL02" , "dept01"), new Department( "dept01" ,"Department of Pharmacy" ,SchoolStudyType.Zansty,69 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN06", "COL02" , "dept02"), new Department( "dept02" ,"Anesthesia Department" ,SchoolStudyType.Zansty,85 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN06", "COL02" , "dept03"), new Department( "dept03" ,"Disease Analysis Department" ,SchoolStudyType.Zansty,75 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN06", "COL02" , "dept04"), new Department( "dept04" ,"Nursing Department" ,SchoolStudyType.Zansty,76 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN06", "COL03" , "dept01"), new Department( "dept01" ,"DB Technology Department" ,SchoolStudyType.Zansty,67 ));
        DEPARTMENTSforZansty.put(Arrays.asList("UN06", "COL04" , "dept01"), new Department( "dept01" ,"Social Marketing Department" ,SchoolStudyType.Zansty,74 ));
    }

    public static void addDepartment(String universityID,String collegeID, String deptID, String deptName, SchoolStudyType type, int deptCapacity){
        if (type == SchoolStudyType.Zansty){
            DEPARTMENTSforZansty.put(Arrays.asList(universityID, collegeID, deptID), new Department(deptID, deptName,type,deptCapacity));
        }
        if (type == SchoolStudyType.Wezhaiy){
            DEPARTMENTSforWezhaiy.put(Arrays.asList(universityID, collegeID, deptID), new Department(deptID, deptName,type,deptCapacity));
        }
        if (type == SchoolStudyType.Ayny){
            DEPARTMENTSforAyny.put(Arrays.asList(universityID, collegeID, deptID), new Department(deptID, deptName,type,deptCapacity));
        }
    }

    public static void removeDepartment(String universityID,String collegeID, String deptID, SchoolStudyType type){
                DEPARTMENTSforZansty.remove(Arrays.asList(universityID, collegeID, deptID));
                DEPARTMENTSforWezhaiy.remove(Arrays.asList(universityID, collegeID, deptID));
                DEPARTMENTSforAyny.remove(Arrays.asList(universityID, collegeID, deptID));
    }
    
}
