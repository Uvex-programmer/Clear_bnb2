package mapper;

import DTO.PropertyDTO;
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
    
    public Property dtoToProperty(PropertyDTO dto, Optional<Property> property) {
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
        p.addAmenities(dto.getAmenities());
        p.addAddress(dto.getAddress());
        p.setPropertyLogs(property.get().getPropertyLogs());
        p.addImages(dto.getImages());
        return p;
    }
    
    public PropertyHomeDTO propertyHomeToDTO(Optional<Property> p) {
        return new PropertyHomeDTO(
                p.get().getId(),
                p.get().getTitle(),
                p.get().getDescription(),
                p.get().getBeds(),
                p.get().getBathrooms(),
                p.get().getGuests(),
                p.get().getCreatedAt(),
                p.get().getStartDate(),
                p.get().getEndDate(),
                p.get().getDailyPrice());
    }
    
    public Property dtoToPropertyHome(PropertyDTO dto, Optional<Property> property) {
        Property p = new Property();
        p.setId(property.get().getId());
        p.setCreatedAt(dto.getCreatedAt());
        p.setGuests(dto.getGuests());
        p.setDescription(dto.getDescription());
        p.setBeds(dto.getBeds());
        p.setBathrooms(dto.getBathrooms());
        p.setDailyPrice(dto.getDailyPrice());
        p.setEndDate(dto.getEndDate());
        p.setStartDate(dto.getStartDate());
        p.setTitle(dto.getTitle());
        return p;
    }
}
