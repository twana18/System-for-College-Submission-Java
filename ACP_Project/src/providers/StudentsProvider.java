package providers;

import characters.Student;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class StudentsProvider {
    public static ArrayList<Student> zanstyStudentsHolder = new ArrayList<>();
    public static ArrayList<Student> wezhaiyStudentsHolder = new ArrayList<>();
    public static ArrayList<Student> aynyStudentsHolder = new ArrayList<>();

//    final static String[] zanstySubjects = {"Math", "Science", "Chemistry", "Physic", "Arabic", "English", "Kurdish"};
//    final static String[] WezhaiySubjects = {"Math", "Mezhu", "Abury", "Jugraphia", "Arabic", "English", "Kurdish"};
//    final static String[] aynySubjects = {"Math", "FiqeIslami", "FaraizQuraan", "UsulFiqe", "Arabic", "English", "Kurdish"};

    public static void getZanstyStudents() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("src/DataFiles/Students_Zansty.txt"));
        zanstyStudentsHolder = (ArrayList<Student>) reader.readObject();
        reader.close();
    }

    public static void getWezhaiyStudents() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("src/DataFiles/Students_Wezhaiy.txt"));
        wezhaiyStudentsHolder = (ArrayList<Student>) reader.readObject();
        reader.close();
    }

    public static void getAynyStudents() throws IOException, ClassNotFoundException {
        ObjectInputStream reader = new ObjectInputStream(new FileInputStream("src/DataFiles/Students_Ayny.txt"));
        aynyStudentsHolder = (ArrayList<Student>) reader.readObject();
        reader.close();
    }

    public static void testMethod() throws IOException, ClassNotFoundException {
        System.out.println(zanstyStudentsHolder);
        getZanstyStudents();
        System.out.println(zanstyStudentsHolder);
        System.out.println(zanstyStudentsHolder.get(0).getName());
        System.out.println(zanstyStudentsHolder.get(0).getSubjectsAndGrades());
        System.out.println(zanstyStudentsHolder.get(0).gradeStats().getAverage());
        System.out.println(zanstyStudentsHolder.get(0).gradeStats().getSum());

        System.out.println(wezhaiyStudentsHolder);
        getWezhaiyStudents();
        System.out.println(wezhaiyStudentsHolder);
        System.out.println(wezhaiyStudentsHolder.get(0).getName());
        System.out.println(wezhaiyStudentsHolder.get(0).getSubjectsAndGrades());
        System.out.println(wezhaiyStudentsHolder.get(0).gradeStats().getAverage());
        System.out.println(wezhaiyStudentsHolder.get(0).gradeStats().getSum());

        System.out.println(aynyStudentsHolder);
        getAynyStudents();
        System.out.println(aynyStudentsHolder);
        System.out.println(aynyStudentsHolder.get(0).getName());
        System.out.println(aynyStudentsHolder.get(0).getSubjectsAndGrades());
        System.out.println(aynyStudentsHolder.get(0).gradeStats().getAverage());
        System.out.println(aynyStudentsHolder.get(0).gradeStats().getSum());
    }
}
