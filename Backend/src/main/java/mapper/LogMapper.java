package mapper;

import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LogMapper {

    public AddressLog addressToLog(Address address){
        AddressLog addressLog = new AddressLog();
        addressLog.setCity(address.getCity());
        addressLog.setStreet(address.getStreet());
        addressLog.setZipcode(address.getZipcode());
        return addressLog;
    }

    public ArrayList<AmenityLog> amenityToLogList(List<Amenity> amenity){
        ArrayList<AmenityLog> aList = new ArrayList<>();
        for(Amenity a : amenity){
            aList.add(amenityToLogAmenity(a));
        }
        return aList;
    }

    public AmenityLog amenityToLogAmenity(Amenity amenity){
        AmenityLog amenity1 = new AmenityLog();
        amenity1.setAmenity(amenity.getAmenity());
        return amenity1;
    }

    public PropertyLog propertyToLog(Optional<Property> property){
        var address = addressToLog(property.get().getAddress());
        var amenity = amenityToLogList(property.get().getAmenities());
        PropertyLog propertyLog = new PropertyLog();
        propertyLog.addProperty(property.get());
        propertyLog.setBathrooms(property.get().getBathrooms());
        propertyLog.setCreatedAt(property.get().getCreatedAt());
        propertyLog.setGuests(property.get().getGuests());
        propertyLog.setDescription(property.get().getDescription());
        propertyLog.setBeds(property.get().getBeds());
        propertyLog.setDailyPrice(property.get().getDailyPrice());
        propertyLog.setEndDate(property.get().getEndDate());
        propertyLog.setStartDate(property.get().getStartDate());
        propertyLog.setTitle(property.get().getTitle());
        propertyLog.addAddress(address);
        propertyLog.addAmenities(amenity);
        return propertyLog;
    }
}
