package providers;

import Models.College;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class CollegesProvider {
    private static ConcurrentHashMap<List<String>, College> COLLEGES = new ConcurrentHashMap<>();
    private static boolean isChanged = false;

    public static void fetchColleges() {
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Colleges.txt"));
            COLLEGES = (ConcurrentHashMap<List<String>, College>) reader.readObject();
            reader.close();
            if (isChanged) {
                isChanged = false;
                System.out.println("Changes canceled");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Cannot fetch colleges: " + e.getMessage());
        }

    }

    public static ConcurrentHashMap<List<String>, College> getCOLLEGES() {
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

    public static void submitChanges() {
        try {
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
        } catch (IOException e) {
            System.out.println("Cannot submit changes in colleges: " + e.getMessage());
        }

    }

}
