package model;

public class Pet {

    private int id;
    private String name;
    private Type type;
    private Gender gender;
    private Address address;
    private Float age;
    private Float weight;
    private String breed;
    private String petFilename;

    public Pet() {
    }

    public Pet(int id, String name, Type typeModel, Gender genderModel, Address addressModel, Float age, Float weight, String breed) {
        this.id = id;
        this.name = name;
        this.type = typeModel;
        this.gender = genderModel;
        this.address = addressModel;
        this.age = age;
        this.weight = weight;
        this.breed = breed;
    }

    @Override
    public String toString() {
        Integer age = null;
        Integer weight = null;

        if (this.age != null) {
            age = this.age.intValue();
        }
        if (this.weight != null) {
            weight = this.weight.intValue();
        }
        return this.name +
                " - " + this.type +
                " - " + this.gender +
                " - " + this.address.toString() +
                " - " + age +
                " anos - " + weight +
                "kg - " + this.breed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address adress) {
        this.address = adress;
    }

    public Float getAge() {
        return age;
    }

    public void setAge(Float age) {
        this.age = age;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getFileName() {
        return petFilename;
    }

    public void setPetFilename(String petFilename) {
        this.petFilename = petFilename;
    }
}
