public class Artifact {
    private String name;
    private String description;

    public Artifact(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() { return name; }

    public String getDescription() { return description; }

    @Override
    public String toString() {
        return name + ": " + description;
    }

    public String toFileString() {
        return name + "|" + description;
    }

    public static Artifact fromFileString(String line) {
        String[] parts = line.split("\\|");
        return new Artifact(parts[0], parts[1]);
    }
}