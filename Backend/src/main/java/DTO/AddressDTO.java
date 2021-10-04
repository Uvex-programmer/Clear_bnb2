package DTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Property;

public class AddressDTO {

    private int id;
    private String street;
    private String zipcode;
    private String city;
    private Property property;


    public AddressDTO() {
    }

    public AddressDTO(int id, String street, String zipcode, String city, Property property) {
        this.id = id;
        this.street = street;
        this.zipcode = zipcode;
        this.city = city;
        this.property = property;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
