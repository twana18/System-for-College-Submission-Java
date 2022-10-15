package characters;

import Enums.StudentType;

import java.util.*;

public class Student {
    private final String id;
    private final String name;
    private final String password;
    private final StudentType type;
    private HashMap<String, Double> subjectsAndGrades;
    private double gradesAverage = 0.0;
    public TreeSet<String> availableDepartments;
    public LinkedList<String> filledDepartments;

    public Student(String studentId, String studentName, String studentPassword, StudentType studentType,
                   HashMap<String, Double> subjectsAndGrades) {
        id = studentId;
        name = studentName;
        password = studentPassword;
        type = studentType;
        this.subjectsAndGrades = subjectsAndGrades;
    }

    
    

    public String getId() { return id;}

    public String getName() { return name; }

    public String getPassword() { return password; }

    public StudentType getType() { return type; }

    public HashMap<String, Double> getSubjectsAndGrades() { return subjectsAndGrades; }

    public double getGradesAverage() { return gradesAverage; }
}
