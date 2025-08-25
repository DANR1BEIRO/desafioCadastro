package model;

public enum Type {
    GATO("Gato"),
    CACHORRO("Cachorro");

    private final String description;

    Type(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
