package logic;

import DTO.PropertyDTO;
import DTO.PropertyHomeDTO;
import mapper.LogMapper;
import mapper.PropertyMapper;
import models.Property;
import models.PropertyLog;
import models.PropertyView;
import models.User;
import repositories.PropertyRepository;
import repositories.UserRepository;
import util.MongoDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertyLogic {
    UserRepository userRepository = new UserRepository();
    UserLogic userLogic = new UserLogic();
    PropertyRepository propertyRepository = new PropertyRepository();
    PropertyMapper propertyMapper = new PropertyMapper();
    LogMapper logMapper = new LogMapper();
    
    public Property addProperty(Property property) {
        property.setDailyPrice((int) Math.ceil(property.getDailyPrice() * 1.15));
        property.addAddress(property.getAddress());
        property.addUser(property.getUser());
        property.addAmenities(property.getAmenities());
        property.setImages(property.getImages());
        propertyRepository.save(property);
        MongoDB.insertProperty(property);
        return property;
    }
    
    public List<PropertyHomeDTO> getHomeProperties() {
        List<Property> properties = MongoDB.loadFromCache();
        if (properties.isEmpty()) return null;
        ArrayList<PropertyHomeDTO> propertiesHomeDTOs = new ArrayList<>();
        for (Property p : properties) {
            propertiesHomeDTOs.add(propertyMapper.propertyHomeToDTO(Optional.ofNullable(p)));
        }
        return propertiesHomeDTOs;
    }

    public PropertyDTO getProperty(Integer id) {
        Optional<Property> property = propertyRepository.findById(id);
        if (property.isEmpty()) return null;
        
        return propertyMapper.propertyToDTO(property);
    }
    
    public ArrayList<PropertyDTO> getUserProperties(Integer id) {
        List<Property> properties = propertyRepository.findByUserId(id);
        ArrayList<PropertyDTO> propertiesDTO = new ArrayList<>();
        for (Property p : properties) {
            propertiesDTO.add(propertyMapper.propertyToDTO(Optional.ofNullable(p)));
        }
        return propertiesDTO;
    }
    
    public List<PropertyView> searchProperties(PropertyView searchResult) {
        return propertyRepository.findObjectsBySearch(searchResult.getCity(),
                searchResult.getBeds(), searchResult.getBaths(), searchResult.getGuests(), searchResult.getDailyPrice(),
                searchResult.getStartDate(), searchResult.getEndDate());
    }
    
    public void updateProperty(PropertyDTO p, Integer id) {
        Optional<Property> propertyBefore = propertyRepository.findById(id);
        Optional<User> user = userRepository.findById(propertyBefore.get().getUser().getId());
        User user1 =  userLogic.userMapper.userOptionalToUser(user);
        Property property = propertyMapper.dtoToProperty(p, propertyBefore, user1);
        PropertyLog propertyLog = logMapper.propertyToLog(propertyBefore);
        property.getPropertyLogs().add(propertyLog);
        propertyRepository.updateProperty(property);
    }
    
    
}
