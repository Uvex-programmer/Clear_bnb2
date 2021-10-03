package mapper;

import DTO.PropertyDTO;
import models.*;
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
}
