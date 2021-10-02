package mapper;

import DTO.PropertyDTO;
import models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertyMapper {

    public PropertyDTO propertyToDTO(Optional<Property> p) {
        return new PropertyDTO(p.get().getId(), p.get().getUser().getId(), p.get().getTitle(), p.get().getDescription(), p.get().getBeds(),
                p.get().getBathrooms(), p.get().getGuests(), p.get().getCreatedAt(), p.get().getStartDate(),
                p.get().getEndDate(), p.get().getDailyPrice(), p.get().getAddress(), p.get().getImages(), p.get().getAmenities(), p.get().getPropertyLogs());
    }

    public Property dtoToProperty(PropertyDTO dto, Optional<Property> property){
        Property p = new Property();
        p.setId(property.get().getId());
        p.setUser(property.get().getUser());
        p.setCreatedAt(dto.getCreatedAt());
        p.setGuests(dto.getGuests());
        p.setDescription(dto.getDescription());
        p.setBeds(dto.getBeds());
        p.setBathrooms(dto.getBathrooms());
        p.setDailyPrice(dto.getDailyPrice());
        p.setEndDate(dto.getEndDate());
        p.setStartDate(dto.getStartDate());
        p.setTitle(dto.getTitle());
        p.setUser(property.get().getUser());
        p.addAmenities(dto.getAmenities());
        p.addAddress(dto.getAddress());
        p.setPropertyLogs(property.get().getPropertyLogs());
        return p;
    }

    public PropertyLog logPorperty(Optional<Property> property){
        var address = logAdress(property.get().getAddress());
        var amenity = logAmenity(property.get().getAmenities());
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

    public AddressLog logAdress(Address address){
        AddressLog addressLog = new AddressLog();
        addressLog.setCity(address.getCity());
        addressLog.setStreet(address.getStreet());
        addressLog.setZipcode(address.getZipcode());
        return addressLog;
    }

    public ArrayList<AmenityLog> logAmenity(List<Amenity> amenity){
        ArrayList<AmenityLog> aList = new ArrayList<>();
        for(Amenity a : amenity){
            aList.add(test(a));
        }
        return aList;
    }

    public AmenityLog test(Amenity amenity){
        AmenityLog amenity1 = new AmenityLog();
        amenity1.setAmenity(amenity.getAmenity());
        return amenity1;
    }

}
