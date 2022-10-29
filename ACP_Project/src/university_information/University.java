package university_information;

import java.io.Serializable;

public record University(String id, String name, String location) implements Serializable {}
