package DTO;

public class PropertyHomeDTO {
    
    private int id;
    private String title;
    private String description;
    private int dailyPrice;
    
    public PropertyHomeDTO() {
    }
    
    public PropertyHomeDTO(int id, String title, String description, int dailyPrice) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dailyPrice = dailyPrice;
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
    
    public int getDailyPrice() {
        return dailyPrice;
    }
    
    public void setDailyPrice(int dailyPrice) {
        this.dailyPrice = dailyPrice;
    }
    
    @Override
    public String toString() {
        return "PropertyHomeDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dailyPrice=" + dailyPrice +
                '}';
    }
}
