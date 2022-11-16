package university_information;

import Enums.SchoolStudyType;

import java.io.Serializable;

public class Department implements Serializable {
    private final String id;
    private final String name;
    private final SchoolStudyType type;
    private final int capacity;
    private int receivedStudents = 0;

    public Department(String departmentId, String departmentName, SchoolStudyType type, int departmentCapacity) {
        id = departmentId;
        name = departmentName;
        this.type = type;
        capacity = departmentCapacity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SchoolStudyType getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getReceivedStudents() {
        return receivedStudents;
    }

    public void increaseReceivedStudents() {
        if (receivedStudents < capacity) {
            receivedStudents++;
        }
    }

    public void decreaseReceivedStudents() {
        if (receivedStudents > 0) {
            receivedStudents--;
        }
    }

    public boolean isFull() {
        return receivedStudents == capacity;
    }
}
