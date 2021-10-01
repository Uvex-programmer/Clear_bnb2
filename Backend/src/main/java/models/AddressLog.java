package models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
@Entity
@Table(name = "address_log")
public class AddressLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String street;
    private String zipcode;
    private String city;
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private PropertyLog property;

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

    public PropertyLog getProperty() {
        return property;
    }

    public void setProperty(PropertyLog property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "AddressLog{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                ", property=" + property +
                '}';
    }
}
