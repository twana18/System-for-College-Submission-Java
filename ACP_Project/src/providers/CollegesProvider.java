package providers;

import university_information.College;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CollegesProvider {
    public static HashMap<List<String>, College> COLLEGES = new HashMap<>();

    public static void gettingData() {
        COLLEGES.put(Arrays.asList("UN01", "COL01"), new College("COL01", "College of Engineering"));
        COLLEGES.put(Arrays.asList("UN01", "COL02"), new College("COL02", "College of Education"));
        COLLEGES.put(Arrays.asList("UN01", "COL03"), new College("COL03", "College of Science"));
        COLLEGES.put(Arrays.asList("UN01", "COL04"), new College("COL04", "College of Art"));
        COLLEGES.put(Arrays.asList("UN01", "COL05"), new College("COL05", "College of Islamic Science"));
        COLLEGES.put(Arrays.asList("UN01", "COL06"), new College("COL06", "College of Law"));
        COLLEGES.put(Arrays.asList("UN01", "COL07"), new College("COL07", "College of agriculture Engineering"));
        COLLEGES.put(Arrays.asList("UN01", "COL08"), new College("COL08", "College of Administration and Economic"));
        COLLEGES.put(Arrays.asList("UN01", "COL09"), new College("COL09", "College of Literature"));
        COLLEGES.put(Arrays.asList("UN01", "COL10"), new College("COL10", "College of Language"));
        COLLEGES.put(Arrays.asList("UN02", "COL01"), new College("COL01", "College of Medicine"));
        COLLEGES.put(Arrays.asList("UN02", "COL02"), new College("COL02", "Dental College"));
        COLLEGES.put(Arrays.asList("UN02", "COL03"), new College("COL03", "College of Pharmacy"));
        COLLEGES.put(Arrays.asList("UN02", "COL04"), new College("COL04", "College of Veterinary"));
        COLLEGES.put(Arrays.asList("UN02", "COL05"), new College("COL05", "College of nursing"));
        COLLEGES.put(Arrays.asList("UN03", "COL01"), new College("COL01", "College of Medicine"));
        COLLEGES.put(Arrays.asList("UN03", "COL02"), new College("COL02", "Dental College"));
        COLLEGES.put(Arrays.asList("UN03", "COL03"), new College("COL03", "College of Engineering"));
        COLLEGES.put(Arrays.asList("UN03", "COL04"), new College("COL04", "College of Science"));
        COLLEGES.put(Arrays.asList("UN03", "COL05"), new College("COL05", "College of Islamic Science"));
        COLLEGES.put(Arrays.asList("UN03", "COL06"), new College("COL06", "College of Law"));
        COLLEGES.put(Arrays.asList("UN03", "COL07"), new College("COL07", "College of agriculture Engineering"));
        COLLEGES.put(Arrays.asList("UN03", "COL08"), new College("COL08", "College of Administration and Economic"));
        COLLEGES.put(Arrays.asList("UN03", "COL09"), new College("COL09", "College of Literature"));
        COLLEGES.put(Arrays.asList("UN03", "COL10"), new College("COL10", "College of Language"));
        COLLEGES.put(Arrays.asList("UN04", "COL01"), new College("COL01", "College of Medicine"));
        COLLEGES.put(Arrays.asList("UN04", "COL02"), new College("COL02", "Dental College"));
        COLLEGES.put(Arrays.asList("UN04", "COL03"), new College("COL03", "College of Pharmacy"));
        COLLEGES.put(Arrays.asList("UN04", "COL04"), new College("COL04", "College of Engineering"));
        COLLEGES.put(Arrays.asList("UN04", "COL05"), new College("COL05", "College of Science"));
        COLLEGES.put(Arrays.asList("UN04", "COL06"), new College("COL06", "College of Islamic Science"));
        COLLEGES.put(Arrays.asList("UN04", "COL07"), new College("COL07", "College of Law"));
        COLLEGES.put(Arrays.asList("UN04", "COL08"), new College("COL08", "College of agriculture Engineering"));
        COLLEGES.put(Arrays.asList("UN04", "COL09"), new College("COL09", "College of Administration and Economic"));
        COLLEGES.put(Arrays.asList("UN04", "COL10"), new College("COL10", "College of Literature"));
        COLLEGES.put(Arrays.asList("UN04", "COL11"), new College("COL11", "College of Language"));
        COLLEGES.put(Arrays.asList("UN05", "COL01"), new College("COL01", "College of Engineering Technique Erbil"));
        COLLEGES.put(Arrays.asList("UN05", "COL02"), new College("COL02", "Institute of Medicine Technique Erbil"));
        COLLEGES.put(Arrays.asList("UN05", "COL03"), new College("COL03", "College of Erbil Technology"));
        COLLEGES.put(Arrays.asList("UN05", "COL04"), new College("COL04", "Institute of Administration Technique Erbil"));
        COLLEGES.put(Arrays.asList("UN05", "COL01"), new College("COL01", "College of Engineering Technique Slemani"));
        COLLEGES.put(Arrays.asList("UN05", "COL02"), new College("COL02", "Institute of Medicine Technique Slemani"));
        COLLEGES.put(Arrays.asList("UN05", "COL03"), new College("COL03", "DB Technology College of Slemani Technology"));
        COLLEGES.put(Arrays.asList("UN05", "COL04"), new College("COL04", "Institute of Administration Technique Slemani"));
    }

    public static void addCollege(String universityID, String collegeID, String collegeName) {
        COLLEGES.put(Arrays.asList(universityID, collegeID), new College(collegeID, collegeName));
    }
    
}
