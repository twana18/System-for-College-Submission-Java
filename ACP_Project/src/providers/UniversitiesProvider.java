package providers;

import Models.University;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class UniversitiesProvider {
    private static ConcurrentHashMap<String, University> UNIVERSITIES = new ConcurrentHashMap<>();
    private static boolean isChanged = false;

    public static void fetchUniversities() {
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Universities.txt"));
            UNIVERSITIES = (ConcurrentHashMap<String, University>) reader.readObject();
            reader.close();
            if (isChanged) {
                isChanged = false;
                System.out.println("Changes canceled");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Cannot fetch universities: " + e.getMessage());
        }

    }

    public static void addUniversity(String universityID, String universityName, String location) {
        UNIVERSITIES.put(universityID, new University(universityID, universityName, location));
        if (!isChanged) {
            isChanged = true;
        }
    }

    public static void removeUniversity(String universityID) {
        UNIVERSITIES.remove(universityID);
        if (!isChanged) {
            isChanged = true;
        }
    }

    public static void submitChanges() {
        try {
            if (isChanged) {
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("ACP_Project/src/DataFiles/Universities.txt"));
                outputStream.writeObject(UNIVERSITIES);
                outputStream.close();
                UNIVERSITIES.clear();
                isChanged = false;
                fetchUniversities();
            } else {
                System.out.println("Please add or remove at least a University");
            }
        } catch (IOException e) {
            System.out.println("Cannot submit changes in universities");
        }

    }

    public static ConcurrentHashMap<String, University> getUNIVERSITIES() {
        return UNIVERSITIES;
    }

}
