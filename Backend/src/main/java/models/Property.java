package models;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "properties")
public class Property {
    @Audited
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Audited
    private String title;
    @Audited
    private String description;
    @Column(name = "bed_count")
    @Audited
    private int beds;
    @Column(name = "bathroom_count")
    @Audited
    private int bathrooms;
    @Audited
    @Column(name = "guest_max")
    private int guests;
    @Audited
    @UpdateTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    @Audited
    @Column(name = "start_date")
    private Date startDate;
    @Audited
    @Column(name = "end_date")
    private Date endDate;
    @Audited
    @Column(name = "daily_price")
    private int dailyPrice;
    @Audited
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
    @JsonBackReference(value = "User - Properties")
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Audited
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Properties_Amenities",
            joinColumns = {@JoinColumn(name = "property_id")},
            inverseJoinColumns = {@JoinColumn(name = "amenities_id")}
    )
    private List<Amenity> amenities = new ArrayList<>();
    
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
        this.dailyPrice = dailyPrice;
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
    
    public void addAmenities(List<Amenity> amenities) {
        this.setAmenities(amenities);
        for(Amenity amenity: amenities) {
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
