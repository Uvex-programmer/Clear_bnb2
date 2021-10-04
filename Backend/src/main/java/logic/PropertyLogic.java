package logic;

import DTO.PropertyDTO;
import mapper.LogMapper;
import mapper.PropertyMapper;
import models.Property;
import models.PropertyLog;
import models.PropertyView;
import repositories.PropertyRepository;
import util.MongoDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertyLogic {
    
    PropertyRepository propertyRepository = new PropertyRepository();
    PropertyMapper propertyMapper = new PropertyMapper();
    LogMapper logMapper = new LogMapper();
    
    public Property addProperty(Property property) {
        property.setDailyPrice((int) Math.ceil(property.getDailyPrice() * 1.15));
        property.addAddress(property.getAddress());
        property.addUser(property.getUser());
        property.addAmenities(property.getAmenities());
        System.out.println(property);
        propertyRepository.save(property);
        MongoDB.insertProperty(property);
        return property;
    }
    
    public List<PropertyDTO> getProperties() {
        //mongo db
        System.out.println(propertyRepository.findAvailableObjects());
        MongoDB.checkIfCached(propertyRepository.findAvailableObjects());
        List<Property> properties = propertyRepository.findAvailableObjects();
        if (properties.isEmpty()) return null;
        ArrayList<PropertyDTO> propertiesDTOs = new ArrayList<>();
        for (Property p : properties) {
            propertiesDTOs.add(propertyMapper.propertyToDTO(Optional.ofNullable(p)));
        }
        return propertiesDTOs;
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
    
    public List<?> searchProperties(PropertyView searchResult) {
        return propertyRepository.findObjectsBySearch(searchResult.getCity(),
                searchResult.getBeds(), searchResult.getBaths(), searchResult.getGuests(), searchResult.getDailyPrice(),
                searchResult.getStartDate(), searchResult.getEndDate());
    }
    
    public void updateProperty(PropertyDTO p, Integer id) {
        Optional<Property> propertyBefore = propertyRepository.findById(id);
        Property property = propertyMapper.dtoToProperty(p, propertyBefore);
        PropertyLog propertyLog = logMapper.propertyToLog(propertyBefore);
        property.getPropertyLogs().add(propertyLog);
        propertyRepository.updateProperty(property);
    }
    
    
}
