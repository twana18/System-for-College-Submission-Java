package providers;

import university_information.College;
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CollegesProvider {
    public static HashMap<List<String>, College> COLLEGES = new HashMap<>();
    private static boolean isChanged = false;
    
    public static void getColleges() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("src/DataFiles/Colleges.txt"));
        COLLEGES = (HashMap<List<String>, College>) reader.readObject();
        reader.close();
        if (isChanged) {
            isChanged = false;
            System.out.println("Changes canceled");
        }
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
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/DataFiles/Colleges.txt"));
            outputStream.writeObject(COLLEGES);
            outputStream.close();
            COLLEGES.clear();
            isChanged = false;
            getColleges();
        } else {
            System.out.println("Please add or remove at least a College");
        }
    }
    public static void testMethod() throws IOException, ClassNotFoundException {
        System.out.println("Colleges before getting data: " + COLLEGES);
        getColleges();
        System.out.println("Colleges after getting data: " + COLLEGES);
        submitChanges();
        addCollege("1", "this is a test", "test");
        addCollege("2", "this is a test", "test");
        getColleges();
        submitChanges();
        addCollege("1", "this is a test", "test");
        addCollege("2", "this is a test", "test");
        submitChanges();
        System.out.println("Colleges after submitting changes: " + COLLEGES);
        removeCollege("UN01","COL01");
        removeCollege("UN05","COL04");
        submitChanges();
        System.out.println("Colleges after submitting changes: " + COLLEGES);
    }

}

