package providers;

import university_information.University;

import java.io.*;
import java.util.HashMap;

public class UniversitiesProvider {
    public static HashMap<String, University> UNIVERSITIES = new HashMap<>();
    private static boolean isChanged = false;

    public static void getUniversities() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("src/DataFiles/Universities.txt"));
        UNIVERSITIES = (HashMap<String, University>) reader.readObject();
        reader.close();
        if (isChanged) {
            isChanged = false;
            System.out.println("Changes canceled");
        }
    }

    public static void addUnversity(String universityID, String universityName, String location) {
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
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/DataFiles/Universities.txt"));
            outputStream.writeObject(UNIVERSITIES);
            outputStream.close();
            UNIVERSITIES.clear();
            isChanged = false;
            getUniversities();
        } else {
            System.out.println("Please add or remove at least a University");
        }
    }

    public static void testMethod() throws IOException, ClassNotFoundException {
        System.out.println("Universities before getting data: " + UNIVERSITIES);
        getUniversities();
        System.out.println("Universities after getting data: " + UNIVERSITIES);
        submitChanges();
        addUnversity("1", "this is a test", "test");
        addUnversity("2", "this is a test", "test");
        getUniversities();
        submitChanges();
        addUnversity("1", "this is a test", "test");
        addUnversity("2", "this is a test", "test");
        submitChanges();
        System.out.println("Universities after submitting changes: " + UNIVERSITIES);
        removeUniversity("1");
        removeUniversity("2");
        submitChanges();
        System.out.println("Universities after submitting changes: " + UNIVERSITIES);
    }

}
