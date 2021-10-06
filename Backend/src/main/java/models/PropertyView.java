package models;

import org.bson.codecs.pojo.annotations.BsonId;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.Filters;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Immutable
@Filters({
        @Filter(name = "bedroomFilter", condition = "bed_count >= :minBeds"),
        @Filter(name = "bathroomFilter", condition = "bathroom_count >= :minBath"),
        @Filter(name = "dateFilter", condition = "start_date <= :startDate and end_date >= :endDate"),
        @Filter(name = "guestFilter", condition = "guest_max >= :minGuests"),
        @Filter(name = "priceFilter", condition = "daily_price <= :maxPrice"),
        @Filter(name = "cityFilter", condition = "city LIKE '%' :city '%'")
    
})
public class PropertyView {
    @Id
    @BsonId
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    public int id;
    @Column(name = "user_id")
    public int userId;
    @Column
    public String title;
    @Column
    public String description;
    @Column(name = "bed_count")
    public int beds;
    @Column(name = "bathroom_count")
    public int baths;
    @Column(name = "guest_max")
    public int guests;
    @Column(name = "daily_price")
    public int dailyPrice;
    @Column
    public String street;
    @Column
    public String zipcode;
    @Column
    public String city;
    @Column(name = "start_date")
    public java.sql.Timestamp startDate;
    @Column(name = "end_date")
    private java.sql.Timestamp endDate;
    
    @Override
    public String toString() {
        return "PropertyView{" +
                "id=" + id +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", beds=" + beds +
                ", baths=" + baths +
                ", guests=" + guests +
                ", dailyPrice=" + dailyPrice +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", street='" + street + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
    
    public int getId() {
        return id;
    }
    
    public int getUserId() {
        return userId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getDescription() {
        return description;
    }
    
    public int getBeds() {
        return beds;
    }
    
    public int getBaths() {
        return baths;
    }
    
    public int getGuests() {
        return guests;
    }
    
    public int getDailyPrice() {
        return dailyPrice;
    }
    
    public java.sql.Timestamp getStartDate() {
        return startDate;
    }
    
    public Timestamp getEndDate() {
        return endDate;
    }
    
    public String getStreet() {
        return street;
    }
    
    public String getZipcode() {
        return zipcode;
    }
    
    public String getCity() {
        return city;
    }
}
