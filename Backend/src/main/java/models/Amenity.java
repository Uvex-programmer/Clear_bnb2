package models;

import javax.persistence.*;

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
    @Enumerated
    private Amenities amenity;
    
    public Amenity() {
    }
    
    public Amenity(int id, Amenities amenity) {
        this.id = id;
        this.amenity = amenity;
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
}
