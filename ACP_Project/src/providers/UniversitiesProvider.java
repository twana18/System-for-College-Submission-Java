package providers;

import university_information.University;

import java.io.*;
import java.util.HashMap;

public class UniversitiesProvider {
    private static HashMap<String, University> UNIVERSITIES = new HashMap<>();
    private static boolean isChanged = false;

    public static void fetchUniversities() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Universities.txt"));
        UNIVERSITIES = (HashMap<String, University>) reader.readObject();
        reader.close();
        if (isChanged) {
            isChanged = false;
            System.out.println("Changes canceled");
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

    public static void submitChanges() throws IOException, ClassNotFoundException {
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
    }

    public static HashMap<String, University> getUNIVERSITIES() {
        return UNIVERSITIES;
    }
    
}
