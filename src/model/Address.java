package model;

public class Address {

    private String houseNumber;
    private String street;
    private String city;

    public Address() {
    }

    public Address(String houseNumber, String street, String city) {
        this.houseNumber = houseNumber;
        this.street = street;
        this.city = city;
    }

    @Override
    public String toString() {
        return this.street + ", " + this.houseNumber + ", " + this.city;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity(String stringAddress) {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

