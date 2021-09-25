package models;

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
        @Filter(name = "freeSearchFilter", condition = "city LIKE '%' :city '%' OR description LIKE '%' :description '%' OR street LIKE '%' :street '%'")
    
})
public class PropertyView {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
    @Column(name = "user_id")
    private int userId;
    @Column
    private String title;
    @Column
    private String description;
    @Column(name = "bed_count")
    private int beds;
    @Column(name = "bathroom_count")
    private int baths;
    @Column(name = "guest_max")
    private int guests;
    @Column(name = "daily_price")
    private int dailyPrice;
    @Column(name = "start_date")
    private java.sql.Timestamp startDate;
    @Column(name = "end_date")
    private java.sql.Timestamp endDate;
    @Column
    private String street;
    @Column
    private String zipcode;
    @Column
    private String city;
    
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
    
    public Timestamp getStartDate() {
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
