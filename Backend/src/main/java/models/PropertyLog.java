package models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "property_log")
public class PropertyLog {
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
    @UpdateTimestamp
    @Column(name = "created_at")
    private Date createdAt;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "daily_price")
    private int dailyPrice;
    @JsonBackReference(value = "property-propertyLogs")
    @ManyToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private Property property;
    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    private AddressLog addressLog;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "Properties_Amenities_Log",
            joinColumns = @JoinColumn(name = "property_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "amenities_id", referencedColumnName = "id")
    )
    private List<AmenityLog> amenities = new ArrayList<>();

    public void addAddress(AddressLog address) {
        this.setAddressLog(address);
        address.setProperty(this);
    }
    public void addAmenities(List<AmenityLog> amenities) {
        this.setAmenities(amenities);
        for(AmenityLog amenity: amenities) {
            amenity.addProperty(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void addProperty(Property property) {
        this.setProperty(property);
        property.getPropertyLogs().add(this);
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public AddressLog getAddressLog() {
        return addressLog;
    }

    public void setAddressLog(AddressLog addressLog) {
        this.addressLog = addressLog;
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

    public List<AmenityLog> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<AmenityLog> amenities) {
        this.amenities = amenities;
    }

    @Override
    public String toString() {
        return "PropertyLog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", beds=" + beds +
                ", bathrooms=" + bathrooms +
                ", guests=" + guests +
                ", createdAt=" + createdAt +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", dailyPrice=" + dailyPrice +
                '}';
    }
}
