package university_information;

import Enums.DepartmentType;

public class Department {
    private final String id;
    private final String name;
    private int capacity;
    private final DepartmentType departmentType;

    public Department(String departmentId, String departmentName, DepartmentType departmentType, int departmentCapacity) {
        id = departmentId;
        name = departmentName;
        capacity = departmentCapacity;
        this.departmentType = departmentType;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}
