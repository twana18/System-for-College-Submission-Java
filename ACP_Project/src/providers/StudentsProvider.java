package providers;

import characters.Student;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class StudentsProvider {
    private static ArrayList<Student> zanstyStudentsHolder = new ArrayList<>();
    private static ArrayList<Student> wezhaiyStudentsHolder = new ArrayList<>();
    private static ArrayList<Student> aynyStudentsHolder = new ArrayList<>();

//    final static String[] zanstySubjects = {"Math", "Science", "Chemistry", "Physic", "Arabic", "English", "Kurdish"};
//    final static String[] WezhaiySubjects = {"Math", "Mezhu", "Abury", "Jugraphia", "Arabic", "English", "Kurdish"};
//    final static String[] aynySubjects = {"Math", "FiqeIslami", "FaraizQuraan", "UsulFiqe", "Arabic", "English", "Kurdish"};

    public static void fetchZanstyStudents() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Students_Zansty.txt"));
        zanstyStudentsHolder = (ArrayList<Student>) reader.readObject();
        reader.close();
    }
    public static void fetchWezhaiyStudents() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Students_Wezhaiy.txt"));
        wezhaiyStudentsHolder = (ArrayList<Student>) reader.readObject();
        reader.close();
    }
    public static void fetchAynyStudents() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Students_Ayny.txt"));
        aynyStudentsHolder = (ArrayList<Student>) reader.readObject();
        reader.close();
    }

    public static ArrayList<Student> getZanstyStudentsHolder() {
        return zanstyStudentsHolder;
    }
    public static ArrayList<Student> getWezhaiyStudentsHolder() {
        return wezhaiyStudentsHolder;
    }
    public static ArrayList<Student> getAynyStudentsHolder() {
        return aynyStudentsHolder;
    }
}
