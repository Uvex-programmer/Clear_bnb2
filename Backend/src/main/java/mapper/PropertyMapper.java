package mapper;

import DTO.PropertyDTO;
import DTO.PropertyHomeDTO;
import models.*;
import java.util.Optional;

public class PropertyMapper {

    public PropertyDTO propertyToDTO(Optional<Property> property) {
        var p = property.get();
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setId(p.getId());
        propertyDTO.setUserId(p.getUser().getId());
        propertyDTO.setDescription(p.getDescription());
        propertyDTO.setImages(p.getImages());
        propertyDTO.setStartDate(p.getStartDate());
        propertyDTO.setEndDate(p.getEndDate());
        propertyDTO.setGuests(p.getGuests());
        propertyDTO.setDailyPrice(p.getDailyPrice());
        propertyDTO.setBeds(p.getBeds());
        propertyDTO.setCreatedAt(p.getCreatedAt());
        propertyDTO.setBathrooms(p.getBathrooms());
        propertyDTO.setAddress(p.getAddress());
        propertyDTO.setTitle(p.getTitle());
        propertyDTO.setPropertyLogs(p.getPropertyLogs());
        propertyDTO.setAmenities(p.getAmenities());
        return propertyDTO;
    }
    
    public Property dtoToProperty(PropertyDTO dto, Optional<Property> property, User user) {
        System.out.println(property.get().getUser());
        Property p = new Property();
        p.setId(property.get().getId());
        p.setUser(user);
        p.setCreatedAt(dto.getCreatedAt());
        p.setGuests(dto.getGuests());
        p.setDescription(dto.getDescription());
        p.setBeds(dto.getBeds());
        p.setBathrooms(dto.getBathrooms());
        p.setDailyPrice(dto.getDailyPrice());
        p.setEndDate(dto.getEndDate());
        p.setStartDate(dto.getStartDate());
        p.setTitle(dto.getTitle());
        p.addAmenities(dto.getAmenities());
        p.addAddress(dto.getAddress());
        p.setPropertyLogs(property.get().getPropertyLogs());
        p.addImages(dto.getImages());
        return p;
    }
    
    public PropertyHomeDTO propertyHomeToDTO(Optional<Property> property) {
        var p = property.get();
        PropertyHomeDTO propertyHomeDTO = new PropertyHomeDTO();
        propertyHomeDTO.setId(p.getId());
        propertyHomeDTO.setDailyPrice(p.getDailyPrice());
        propertyHomeDTO.setTitle(p.getTitle());
        propertyHomeDTO.setDescription(p.getDescription());
        return propertyHomeDTO;
    }
}
