package models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "img_url")
    private String url;
    @Column(name = "primary_image")
    private int primaryImage;
    @ManyToOne
    @JsonBackReference(value = "Property-Images")
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private Property property;
    
    public Image() {
    }
    
    public Image(String url, int primaryImage) {
        this.url = url;
        this.primaryImage = primaryImage;
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
    
    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public int getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(int primaryImage) {
        this.primaryImage = primaryImage;
    }
    
    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", primaryImage=" + primaryImage +
                ", property=" + property +
                '}';
    }
}