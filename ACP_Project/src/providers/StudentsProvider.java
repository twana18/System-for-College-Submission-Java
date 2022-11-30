package providers;

import Models.SchoolStudyType;
import Models.Student;

import java.io.*;
import java.util.Vector;

public class StudentsProvider {
    final static String[] zanstySubjects = {"Math", "Science", "Chemistry", "Physic", "Arabic", "English", "Kurdish"};
    final static   String[] WezhaiySubjects = {"Math", "Mezhu", "Abury", "Jugraphia", "Arabic", "English", "Kurdish"};
    final static String[] aynySubjects = {"Math", "FiqeIslami", "FaraizQuraan", "UsulFiqe", "Arabic", "English", "Kurdish"};
    private Vector<Student> zanstyStudentsHolder = new Vector<>();
    private  Vector<Student> wezhaiyStudentsHolder = new Vector<>();
    private Vector<Student> aynyStudentsHolder = new Vector<>();

    public void fetchStudentsByType(SchoolStudyType type) {
        if (type == SchoolStudyType.Zansty) {
            try {
                ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Students_Zansty.txt"));
                zanstyStudentsHolder = (Vector<Student>) reader.readObject();
                reader.close();
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("Cannot fetch zansty Students: " + e.getMessage());
            }
        } else if (type == SchoolStudyType.Wezhaiy) {
            try {
                ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Students_Wezhaiy.txt"));
                wezhaiyStudentsHolder = (Vector<Student>) reader.readObject();
                reader.close();
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("Cannot fetch wezhaiy Students: " + e.getMessage());
            }
        } else if (type == SchoolStudyType.Ayny) {
            try {
                ObjectInputStream reader = new ObjectInputStream(new FileInputStream("ACP_Project/src/DataFiles/Students_Ayny.txt"));
                aynyStudentsHolder = (Vector<Student>) reader.readObject();
                reader.close();
            } catch (ClassNotFoundException | IOException e) {
                System.out.println("Cannot fetch ayny Students: " + e.getMessage());
            }
        }
    }

    public Vector<Student> getStudentsByType(SchoolStudyType type) {
        if (type == SchoolStudyType.Zansty) {
            return zanstyStudentsHolder;
        } else if (type == SchoolStudyType.Wezhaiy) {
            return wezhaiyStudentsHolder;
        } else {
            return aynyStudentsHolder;
        }

    }

    public Student findStudent(String id, String password) {
        fetchStudentsByType(SchoolStudyType.Zansty);
        fetchStudentsByType(SchoolStudyType.Wezhaiy);
        fetchStudentsByType(SchoolStudyType.Ayny);
        Student found = null;
        for (Student student : getStudentsByType(SchoolStudyType.Zansty)) {
            if (student.getId().equals(id) && student.getPassword().equals(password)) {
                found = student;
                break;
            }
        }
        for (Student student : getStudentsByType(SchoolStudyType.Wezhaiy)) {
            if (student.getId().equals(id) && student.getPassword().equals(password)) {
                found = student;
                break;
            }
        }
        for (Student student : getStudentsByType(SchoolStudyType.Ayny)) {
            if (student.getId().equals(id) && student.getPassword().equals(password)) {
                found = student;
                break;
            }
        }
        return found;
    }

}
