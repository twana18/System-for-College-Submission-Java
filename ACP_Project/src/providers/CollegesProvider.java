package providers;

import Custom_Generic_Classes_Interfaces.PairContainer;
import university_information.College;

import java.util.HashMap;

public class CollegesProvider {
    public static HashMap<PairContainer<String, String>, College> COLLEGES = new HashMap<>();
    
    public static void gettingData() { }
    
    public static void addCollege(String universityID, String collegeID, String collegeName){
            COLLEGES.put(new PairContainer<>(universityID,collegeID), new College(collegeID,collegeName));
    }

    public static void removeCollege(String universityID, String collegeID){
            COLLEGES.remove(new PairContainer<>(universityID,collegeID));
    }
}

