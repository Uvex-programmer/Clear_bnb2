package models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name = "amenities_log")
public class AmenityLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    @Column(name = "amenity")
    private Amenities amenity;
    @JsonBackReference
    @ManyToMany(mappedBy = "amenities", cascade = {
            CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<PropertyLog> properties = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addProperty (PropertyLog property) {
        properties.add(property);
    }

    public Amenities getAmenity() {
        return amenity;
    }

    public void setAmenity(Amenities amenity) {
        this.amenity = amenity;
    }

    public List<PropertyLog> getProperties() {
        return properties;
    }

    public void setProperties(List<PropertyLog> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "AmenityLog{" +
                "id=" + id +
                ", amenity=" + amenity +
                ", properties=" + properties +
                '}';
    }
}
