package providers;

import university_information.University;
import java.util.HashMap;

public class UniversitiesProvider {
    public static HashMap<String, University> UNIVERSITIES = new HashMap<>();

    public static void gettingData() {
    	UNIVERSITIES.put("UN01",new University("UN01","Salahaddin","Hawler"));
    	UNIVERSITIES.put("UN02",new University("UN02","Hawler Medicine","Hawler"));
    	UNIVERSITIES.put("UN03",new University("UN03","Slemani","Slemani"));
    	UNIVERSITIES.put("UN04",new University("UN04","Duhok","Duhok"));
    	UNIVERSITIES.put("UN05",new University("UN05","PolyTechnique","Hawler"));
    	UNIVERSITIES.put("UN06",new University("UN06","PolyTechnique","Slemani"));
    }
    public static void addUnversity(String location, String universityID, String universityName){
        UNIVERSITIES.put(universityID, new University(universityID, universityName, location));
    }
    public static void removeUniversity(String universityID){
            UNIVERSITIES.remove(universityID);
    }
}

