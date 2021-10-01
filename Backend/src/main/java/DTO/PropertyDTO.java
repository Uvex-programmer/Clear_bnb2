package DTO;
import models.Address;
import models.Amenity;
import models.Image;

import java.sql.Date;
import java.util.List;

public class PropertyDTO {

    private int id;
    private int userId;
    private String title;
    private String description;
    private int beds;
    private int bathrooms;
    private int guests;
    private Date createdAt;
    private Date startDate;
    private Date endDate;
    private int dailyPrice;
    private Address address;
    private List<Image> images;
    private List<Amenity> amenities;

    public PropertyDTO() {
    }
    public PropertyDTO(int id, String title, String description, int beds, int bathrooms, int guests,
                       Date createdAt, Date startDate, Date endDate, int dailyPrice,
                       Address address, List<Image> images, List<Amenity> amenities) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.beds = beds;
        this.bathrooms = bathrooms;
        this.guests = guests;
        this.createdAt = createdAt;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyPrice = dailyPrice;
        this.address = address;
        this.images = images;
        this.amenities = amenities;
    }

    public PropertyDTO(int id, int userId, String title, String description, int beds, int bathrooms, int guests,
                       Date createdAt, Date startDate, Date endDate, int dailyPrice,
                       Address address, List<Image> images, List<Amenity> amenities) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.beds = beds;
        this.bathrooms = bathrooms;
        this.guests = guests;
        this.createdAt = createdAt;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyPrice = dailyPrice;
        this.address = address;
        this.images = images;
        this.amenities = amenities;
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

    public int getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "PropertyDTO{" +
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
                ", address=" + address +
                ", images=" + images +
                '}';
    }
}
