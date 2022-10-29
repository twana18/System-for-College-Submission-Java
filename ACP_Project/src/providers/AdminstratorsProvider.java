package providers;

import characters.Adminstrator;

import java.io.*;
import java.util.ArrayList;

public class AdminstratorsProvider {

    public static ArrayList<Adminstrator> adminstratorsHolder = new ArrayList<>();
    public static boolean isChanged = false;

    public static void getAdminstrators() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("src/DataFiles/Adminstrators.txt"));
        adminstratorsHolder = (ArrayList<Adminstrator>) reader.readObject();
        reader.close();
        if (isChanged) {
            isChanged = false;
            System.out.println("Changes canceled");
        }
    }

    public static void addAdminstrator(String id, String name, String password) {
        adminstratorsHolder.add(0, new Adminstrator(id, name, password));
        if (!isChanged) {
            isChanged = true;
        }
    }

    public static void removeAdminstrator(String id) {
        adminstratorsHolder.removeIf(admin -> admin.id().equals(id));
        if (!isChanged) {
            isChanged = true;
        }
    }

    public static void submitChanges() throws IOException, ClassNotFoundException {
        if (isChanged) {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("src/DataFiles/Adminstrators.txt"));
            outputStream.writeObject(adminstratorsHolder);
            outputStream.close();
            adminstratorsHolder.clear();
            isChanged = false;
            getAdminstrators();
        } else {
            System.out.println("Please add or remove at least an Adminstrator");
        }
    }

    public static void testMethod() throws IOException, ClassNotFoundException {
        System.out.println("Adminstrators before getting data: " + adminstratorsHolder);
        getAdminstrators();
        System.out.println("Adminstrators after getting data: " + adminstratorsHolder);
        submitChanges();
        addAdminstrator("1", "its Me", "12345678");
        addAdminstrator("2", "its Me", "12345678");
        getAdminstrators();
        submitChanges();
        addAdminstrator("1", "its Me", "12345678");
        addAdminstrator("2", "its Me", "12345678");
        submitChanges();
        System.out.println("Adminstrators after submitting data: " + adminstratorsHolder);
        removeAdminstrator("1");
        removeAdminstrator("2");
        submitChanges();
        System.out.println("Adminstrators after submitting data: " + adminstratorsHolder);
    }

}
