package models;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "properties")
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int user_id;
    private String title;
    private String description;
    @Column(name = "bed_count")
    private int beds;
    @Column(name = "bathroom_count")
    private int bathrooms;
    @Column(name = "guest_max")
    private int guests;
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "daily_price")
    private int dailyPrice;
    @OneToOne(mappedBy = "address")
    private Address address;
    @OneToOne(mappedBy = "images")
    private Images images;
    @OneToMany
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private List<Review> reviews;
    @OneToMany
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private List<Booking> bookings;
    @ManyToMany
    @JoinTable(
            name = "properties_x_amenities",
            joinColumns = @JoinColumn(name = "property_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "amenities_id", referencedColumnName = "id")
    )
    private List<Amenity> amenities;
    
    public Property() {
    }
    
    public Property(int id, int user_id, String title, String description, int beds, int bathrooms, int guests, Date createdAt, Date startDate, Date endDate, int dailyPrice) {
        this.id = id;
        this.user_id = user_id;
        this.title = title;
        this.description = description;
        this.beds = beds;
        this.bathrooms = bathrooms;
        this.guests = guests;
        this.createdAt = createdAt;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyPrice = dailyPrice;
    }
    
    public Address getAddress() {
        return address;
    }
    
    public void setAddress(Address address) {
        this.address = address;
    }
    
    public Images getImages() {
        return images;
    }
    
    public void setImages(Images images) {
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
    
    public int getUser_id() {
        return user_id;
    }
    
    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
                ", user_id=" + user_id +
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
