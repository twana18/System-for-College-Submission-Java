package characters;

import Enums.SchoolStudyType;

import java.util.*;

public class Student {
    private final String id;
    private final String name;
    private final String password;
    private final String gender;
    private final String dateOfBirth;
    private final SchoolStudyType type;
    private HashMap<String, Double> subjectsAndGrades = new HashMap<String, Double>();
    private double gradesTotal;
    private double gradesAverage = 0.0;
    public LinkedList<String> filledDepartments;


    public Student(String studentId, String studentName, String studentPassword, String studentGender,
            String studentDateOfBirth, SchoolStudyType type, List<String> subjects, List<Double> grades) {
        id = studentId;
        name = studentName;
        password = studentPassword;
        gender = studentGender;
        dateOfBirth = studentDateOfBirth;
        this.type = type;
        setSubjectsAndGrades(subjects, grades);
        this.gradesTotal = calculateTotalOfGrades();
        calculateAverageOfGrades();
    }

    private double calculateTotalOfGrades() {
        Collection<Double> grades = subjectsAndGrades.values();
        double total = 0.0;
        for (Double g : grades) {
            total += g;
        }
        return total;
    }

    private void calculateAverageOfGrades() {
        this.gradesAverage = gradesTotal / subjectsAndGrades.values().size();
    }

    private void setSubjectsAndGrades(List<String> subjects, List<Double> grades) {
        Iterator<String> iterator1 = subjects.stream().iterator();
        Iterator<Double> iterator2 = grades.stream().iterator();
        try {
            while (iterator1.hasNext() && iterator2.hasNext()){
                subjectsAndGrades.put(iterator1.next(), iterator2.next());
            }
        }catch (NoSuchElementException e){
            System.out.println(e);
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public SchoolStudyType getType() {
        return type;
    }

    public HashMap<String, Double> getSubjectsAndGrades() {
        return subjectsAndGrades;
    }

    public double getGradesTotal() {
        return gradesTotal;
    }

    public double getGradesAverage() {
        return gradesAverage;
    }

}
