package model;

public enum Gender {
    MACHO("Macho"),
    FEMEA("Fêmea");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
