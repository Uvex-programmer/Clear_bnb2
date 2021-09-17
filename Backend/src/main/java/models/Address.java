package models;


import javax.persistence.*;

@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "property_id")
    private int propertyId;
    private String street;
    private String zipcode;
    private String city;
    @OneToOne(optional = false)
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private Property property;
    
    
    public Address() {
    }
    
    public Address(int id, String street, String zipcode, String city, Property property) {
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
    
    public int getPropertyId() {
        return propertyId;
    }
    
    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
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
