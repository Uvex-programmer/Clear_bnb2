package models;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "property_id")
    private int propertyId;
    private String url;
    private boolean primary;
    @OneToOne
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private Property property;
    
    public Images() {
    }
    
    public Images(int id, int propertyId, String url, boolean primary) {
        this.id = id;
        this.propertyId = propertyId;
        this.url = url;
        this.primary = primary;
    }
    
    public Property getProperty() {
        return property;
    }
    
    public void setProperty(Property property) {
        this.property = property;
    }
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getPropertyId() {
        return propertyId;
    }
    
    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
    }
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }
    
    public boolean isPrimary() {
        return primary;
    }
    
    public void setPrimary(boolean primary) {
        this.primary = primary;
    }
}