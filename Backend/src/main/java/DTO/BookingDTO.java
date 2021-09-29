package DTO;

import java.io.Serializable;
import java.sql.Date;



public class BookingDTO implements Serializable {
    private int propertyId;
    private Date startDate;
    private Date endDate;
    private int userId;
    private int propertyPrice;

    public BookingDTO() {
    }

    public BookingDTO(int propertyId, Date startDate, Date endDate, int userId) {
        this.propertyId = propertyId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    public int getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(int propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId = propertyId;
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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "propertyId=" + propertyId +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", propertyPrice=" + propertyPrice +
                ", userId=" + userId +
                '}';
    }
}