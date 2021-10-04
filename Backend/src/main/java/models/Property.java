package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.bson.codecs.pojo.annotations.BsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "properties")
@NamedQueries({
        @NamedQuery(name = "Property.findById",
                query = "SELECT p FROM Property p WHERE p.id = :id"),
        @NamedQuery(name = "Property.findAllByUserId",
                query = "SELECT p FROM Property p WHERE p.user.id = :id"),
        @NamedQuery(name = "Property.findByPropertyIdReturnUser",
                query = "SELECT user FROM Property p WHERE p.id = :id"),
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
    @BsonIgnore
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "start_date")
    @BsonIgnore
    private Date startDate;
    @Column(name = "end_date")
    @BsonIgnore
    private Date endDate;
    @Column(name = "daily_price")
    private int dailyPrice;
    @JsonManagedReference(value = "Property - Address")
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BsonIgnore
    private Address address;
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "Property-Images")
    @BsonIgnore
    private List<Image> images = new ArrayList<>();
    @JsonManagedReference(value = "Property - Reviews")
    @OneToMany(mappedBy = "property")
    @BsonIgnore
    private List<Review> reviews;
    @JsonBackReference(value = "Property - Bookings")
    @OneToMany(mappedBy = "property")
    @BsonIgnore
    private List<Booking> bookings;
    @JsonBackReference(value = "User - Properties")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @BsonIgnore
    private User user;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Properties_Amenities",
            joinColumns = @JoinColumn(name = "property_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "amenities_id", referencedColumnName = "id")
    )
    @BsonIgnore
    private List<Amenity> amenities = new ArrayList<>();
    
    @JsonManagedReference(value = "property-propertyLogs")
    @OneToMany(mappedBy = "property", cascade = {
            CascadeType.ALL
    })
    @BsonIgnore
    private List<PropertyLog> propertyLogs = new ArrayList<>();
    
    public Property() {
    }
    
    public Property(String title, String description, int beds, int bathrooms, int guests, Date startDate, Date endDate, int dailyPrice, Address address, List<Image> images, List<Review> reviews, List<Booking> bookings, User user, List<Amenity> amenities) {
        this.title = title;
        this.description = description;
        this.beds = beds;
        this.bathrooms = bathrooms;
        this.guests = guests;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyPrice = (int) Math.ceil(dailyPrice * 1.15);
        this.address = address;
        this.images = images;
        this.reviews = reviews;
        this.bookings = bookings;
        this.user = user;
        this.amenities = amenities;
    }
    
    public Property(String title, String description, int beds, int bathrooms, int guests, Date startDate, Date endDate, int dailyPrice) {
        this.title = title;
        this.description = description;
        this.beds = beds;
        this.bathrooms = bathrooms;
        this.guests = guests;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyPrice = (int) Math.ceil(dailyPrice * 1.15);
        
    }
    
    public List<PropertyLog> getPropertyLogs() {
        return propertyLogs;
    }
    
    public void setPropertyLogs(List<PropertyLog> propertyLogs) {
        this.propertyLogs = propertyLogs;
    }
    
    public void addUser(User user) {
        user.getProperties().add(this);
        this.setUser(user);
    }
    
    public void addAddress(Address address) {
        this.setAddress(address);
        address.setProperty(this);
    }
    
    public void addImages(List<Image> images) {
        this.setImages(images);
        for(Image img: images) {
            img.setProperty(this);
        }
    }
    
    public void addAmenities(List<Amenity> amenities) {
        this.setAmenities(amenities);
        for (Amenity amenity : amenities) {
            amenity.addProperty(this);
        }
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
