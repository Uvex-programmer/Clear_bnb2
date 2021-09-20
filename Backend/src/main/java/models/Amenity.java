package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

enum Amenities {
    DISHWASHER,
    AC,
    WIFI,
    FRIDGE,
    IRON,
    MICROWAVE
}

@Entity
@Table(name = "amenities")
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "amenity")
    private Amenities amenity;
    @ManyToMany(mappedBy = "amenities", cascade = CascadeType.ALL)
    private List<Property> properties = new ArrayList<>();
    
    
    public Amenity() {
    }
    
    public Amenity(String amenity) {
        this.amenity = Amenities.valueOf(amenity.toUpperCase());
    }
    
    public List<Property> getProperties() {
        return properties;
    }
    
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Amenities getAmenity() {
        return amenity;
    }
    
    public void setAmenity(Amenities amenity) {
        this.amenity = amenity;
    }
    
    @Override
    public String toString() {
        return "Amenity{" +
                "id=" + id +
                ", amenity=" + amenity +
                '}';
    }
}
