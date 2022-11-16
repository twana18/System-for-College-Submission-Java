package characters;

import java.io.Serializable;

public record Adminstrator(String id, String name, String password) implements Serializable {}
