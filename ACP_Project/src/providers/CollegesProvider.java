package providers;

import university_information.College;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CollegesProvider {
    private static HashMap<List<String>, College> COLLEGES = new HashMap<>();
    private static boolean isChanged = false;

    public static void fetchColleges() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Colleges.txt"));
        COLLEGES = (HashMap<List<String>, College>) reader.readObject();
        reader.close();
        if (isChanged) {
            isChanged = false;
            System.out.println("Changes canceled");
        }
    }

    public static HashMap<List<String>, College> getCOLLEGES() {
        return COLLEGES;
    }

    public static void addCollege(String universityID, String collegeID, String collegeName) {
        COLLEGES.put(Arrays.asList(universityID, collegeID), new College(collegeID, collegeName));
        if (!isChanged) {
            isChanged = true;
        }
    }

    public static void removeCollege(String universityID, String collegeID) {
        COLLEGES.remove(Arrays.asList(universityID, collegeID));
        if (!isChanged) {
            isChanged = true;
        }
    }
    
    public static void submitChanges() throws IOException, ClassNotFoundException {
        if (isChanged) {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("ACP_Project/src/DataFiles/Colleges.txt"));
            outputStream.writeObject(COLLEGES);
            outputStream.close();
            COLLEGES.clear();
            isChanged = false;
            fetchColleges();
        } else {
            System.out.println("Please add or remove at least a College");
        }
    }
}
