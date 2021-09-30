package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
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
@Audited
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "amenity")
    private Amenities amenity;
    @JsonBackReference
    @ManyToMany(mappedBy = "amenities", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Property> properties = new ArrayList<>();

    public Amenity() {
    }

    public Amenity(Amenities amenity, List<Property> properties) {
        this.amenity = amenity;
        this.properties = properties;
    }

    public Amenity(int id, Amenities amenity, List<Property> properties) {
        this.id = id;
        this.amenity = amenity;
        this.properties = properties;
    }

    public void addProperty (Property property) {
        properties.add(property);
    }

    public Amenities getAmenity() {
        return amenity;
    }

    public void setAmenity(Amenities amenity) {
        this.amenity = amenity;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "Amenity{" +
                "id=" + id +
                '}';
    }
}
