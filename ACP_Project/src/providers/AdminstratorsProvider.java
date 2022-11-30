package providers;

import Models.Adminstrator;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class AdminstratorsProvider {
    public static ArrayList<Adminstrator> adminstrators = new ArrayList<>();
    private static boolean isChanged = false;

    public static void fetchAdminstrators() {
        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Adminstrators.txt"));
            adminstrators = (ArrayList<Adminstrator>) reader.readObject();
            reader.close();
            if (isChanged) {
                isChanged = false;
                System.out.println("Changes canceled");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Cannot fetch Adminstrators" + e.getMessage() + "  " + Arrays.toString(e.getStackTrace()));
        }
    }

    public static void addAdminstrator(String id, String name, String password) {
        adminstrators.add(0, new Adminstrator(id, name, password));
        if (!isChanged) {
            isChanged = true;
        }
    }

    public static void removeAdminstrator(String id) {
        adminstrators.removeIf(admin -> admin.id().equals(id));
        if (!isChanged) {
            isChanged = true;
        }
    }

    public static void submitChanges() {
        try {
            if (isChanged) {
                ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("ACP_Project/src/DataFiles/Adminstrators.txt"));
                outputStream.writeObject(adminstrators);
                outputStream.close();
                adminstrators.clear();
                isChanged = false;
                fetchAdminstrators();
            } else {
                System.out.println("Please add or remove at least an Adminstrator");
            }
        } catch (IOException e) {
            System.out.println("Cannot submit changes in adminstrators");
        }

    }

    public static ArrayList<Adminstrator> getAdminstrators() {
        return adminstrators;
    }

    public static Adminstrator findAdminstrator(String id, String password) {
        fetchAdminstrators();
        Adminstrator found = null;
        for (Adminstrator adminstrator : getAdminstrators()) {
            if (adminstrator.id().equals(id) && adminstrator.password().equals(password)) {
                found = adminstrator;
                break;
            }
        }
        return found;
    }

}
