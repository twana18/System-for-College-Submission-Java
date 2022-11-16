package characters;

import Enums.SchoolStudyType;
import university_information.Department;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class Student implements Serializable {
    private final String id;
    private final String name;
    private final String password;
    private final String gender;
    private final String dateOfBirth;
    private final SchoolStudyType type;
    private HashMap<String, Double> subjectsAndGrades = new HashMap<String, Double>();
    public LinkedList<List<String>> filledDepartments = new LinkedList<>();
    private Department receivedDepartment;


    public Student(String studentId, String studentName, String studentPassword, String studentGender,
                   String studentDateOfBirth, SchoolStudyType type, List<String> subjects, List<Double> grades) {
        id = studentId;
        name = studentName;
        password = studentPassword;
        gender = studentGender;
        dateOfBirth = studentDateOfBirth;
        this.type = type;
        setSubjectsAndGrades(subjects, grades);
    }

    public DoubleSummaryStatistics gradeStats() {
        return subjectsAndGrades
                .values()
                .stream()
                .collect(Collectors.summarizingDouble(Double::doubleValue));
    }

    private void setSubjectsAndGrades(List<String> subjects, List<Double> grades) {
        grades.forEach(g -> {
            if (g == null){
                grades.set(grades.indexOf(g), 00.00);
            }
        });
        Iterator<String> iterator1 = subjects.stream().iterator();
        Iterator<Double> iterator2 = grades.stream().iterator();
        try {
            while (iterator1.hasNext()) {
                if (iterator2.hasNext()) {
                    subjectsAndGrades.put(iterator1.next(), iterator2.next());
                }else {
                    subjectsAndGrades.put(iterator1.next(), 00.00);
                }
            }
        } catch (NoSuchElementException e) {
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

    public String getGender() {
        return gender;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public SchoolStudyType getType() {
        return type;
    }

    public HashMap<String, Double> getSubjectsAndGrades() {
        return subjectsAndGrades;
    }

}
