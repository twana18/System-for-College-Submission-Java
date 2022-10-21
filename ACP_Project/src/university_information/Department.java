package university_information;

import Enums.SchoolStudyType;

public record Department (String departmentId, String departmentName, SchoolStudyType type, int departmentCapacity) { }
