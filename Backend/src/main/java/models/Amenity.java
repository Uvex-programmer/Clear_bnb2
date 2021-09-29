package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
    @ManyToMany(mappedBy = "amenities", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Property> properties = new ArrayList<>();

    public Amenity() {
    }

    public Amenity(Amenities amenity, List<Property> properties) {
        this.amenity = amenity;
        this.properties = properties;
    }

  /*  public List<Property> getProperties() {
        return properties;
    }
    
    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

   */
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
