package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "properties")
@NamedQueries({
        @NamedQuery(name = "Property.findById",
                query = "SELECT p FROM Property p WHERE p.id = :id"),
        @NamedQuery(name = "Property.findAllByUserId",
                query = "SELECT p FROM Property p WHERE p.user.id = :id"),
})
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @Column(name = "bed_count")
    private int beds;
    @Column(name = "bathroom_count")
    private int bathrooms;
    @Column(name = "guest_max")
    private int guests;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "daily_price")
    private int dailyPrice;
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private Address address;
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "Property-Images")
    private List<Image> images = new ArrayList<>();
    @JsonManagedReference
    @OneToMany(mappedBy = "property")
    private List<Review> reviews;
    @JsonBackReference
    @OneToMany
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private List<Booking> bookings;
    @ManyToMany(cascade = CascadeType.ALL)
    
    @JsonBackReference(value = "amenity-property")
    @JoinTable(
            name = "properties_x_amenities",
            joinColumns = @JoinColumn(name = "property_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "amenities_id", referencedColumnName = "id")
    )
    private List<Amenity> amenities = new ArrayList<>();
    
    @JsonBackReference(value = "User - Properties")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    
    public Property() {
    }

//    public Property(int id, int userId, String title, String description, int beds, int bathrooms, int guests, Date createdAt, Date startDate, Date endDate, int dailyPrice) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.beds = beds;
//        this.bathrooms = bathrooms;
//        this.guests = guests;
//        this.createdAt = createdAt;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.dailyPrice = dailyPrice;
//    }
    
    public Property(String title, String description, int beds, int bathrooms, int guests, Date startDate, Date endDate, int dailyPrice) {
        this.title = title;
        this.description = description;
        this.beds = beds;
        this.bathrooms = bathrooms;
        this.guests = guests;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyPrice = dailyPrice;
    }

    public void addUser(User user) {
        user.getProperties().add(this);
        this.setUser(user);
    }

    public void addAddress(Address address) {
        this.setAddress(address);
        address.setProperty(this);
    }
    
    public void addImage(Image image) {
        images.add(image);
        image.setProperty(this);
    }
    
    public void addAmenities(Amenity amenity) {
        amenities.add(amenity);
        amenity.getProperties().add(this);
    }
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public List<Image> getImages() {
        return images;
    }
    
    public void setImages(List<Image> images) {
        this.images = images;
    }
    
    public List<Review> getReviews() {
        return reviews;
    }
    
    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
    
    public List<Booking> getBookings() {
        return bookings;
    }
    
    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
    
    public List<Amenity> getAmenities() {
        return amenities;
    }
    
    public void setAmenities(List<Amenity> amenities) {
        this.amenities = amenities;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public int getBeds() {
        return beds;
    }
    
    public void setBeds(int beds) {
        this.beds = beds;
    }
    
    public int getBathrooms() {
        return bathrooms;
    }
    
    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }
    
    public int getGuests() {
        return guests;
    }
    
    public void setGuests(int guests) {
        this.guests = guests;
    }
    
    public Date getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    
    public Date getStartDate() {
        return startDate;
    }
    
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    public int getDailyPrice() {
        return dailyPrice;
    }
    
    public void setDailyPrice(int dailyPrice) {
        this.dailyPrice = dailyPrice;
    }
    
    @Override
    public String toString() {
        return "Property{" +
                "id=" + id +
//                ", user_id=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", beds=" + beds +
                ", bathrooms=" + bathrooms +
                ", guests=" + guests +
                ", created_at=" + createdAt +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", dailyPrice=" + dailyPrice +
                '}';
    }
}
