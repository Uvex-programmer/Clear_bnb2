package mapper;

import DTO.PropertyDTO;
import models.Property;
import java.util.Optional;

public class PropertyMapper {

    public PropertyDTO propertyToDTO(Optional<Property> p) {
        return new PropertyDTO(p.get().getId(), p.get().getTitle(), p.get().getDescription(), p.get().getBeds(),
                p.get().getBathrooms(), p.get().getGuests(), p.get().getCreatedAt(), p.get().getStartDate(),
                p.get().getEndDate(), p.get().getDailyPrice(), p.get().getAddress(), p.get().getImages());
    }

}
