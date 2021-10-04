package models;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;

@Entity
@Table(name = "images_log")
public class ImageLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "img_url")
    private String url;
    @Column(name = "primary_image")
    private int primaryId;
    @ManyToOne
    @JsonBackReference(value = "PropertyLog-Images")
    @JoinColumn(name = "property_id", referencedColumnName = "id")
    private PropertyLog property;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setPrimaryId(int primaryId) {
        this.primaryId = primaryId;
    }

    public PropertyLog getProperty() {
        return property;
    }

    public void setProperty(PropertyLog property) {
        this.property = property;
    }

    @Override
    public String toString() {
        return "ImageLog{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", primaryId=" + primaryId +
                ", property=" + property +
                '}';
    }
}
