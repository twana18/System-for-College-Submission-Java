import characters.Adminstrator;

import java.io.*;
import java.util.*;

public class AdminstratorsProvider {
    public static ArrayList<Adminstrator> adminstrators = new ArrayList<>();
    private static boolean isChanged = false;
    public static void fetchAdminstrators() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Adminstrators.txt"));
        adminstrators = (ArrayList<Adminstrator>) reader.readObject();
        reader.close();
        if (isChanged) {
            isChanged = false;
            System.out.println("Changes canceled");
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

    public static void submitChanges() throws IOException, ClassNotFoundException {
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
    }

    public static ArrayList<Adminstrator> getAdminstrators() {
        return adminstrators;
    }
}
