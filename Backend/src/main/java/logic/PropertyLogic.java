package logic;

import DTO.PropertyDTO;
import mapper.PropertyMapper;
import models.Property;
import models.PropertyView;
import models.User;
import repositories.PropertyRepository;
import repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertyLogic {

    UserRepository userRepository = new UserRepository();
    PropertyRepository propertyRepository = new PropertyRepository();
    PropertyMapper propertyMapper = new PropertyMapper();

    public void addProperty(Property property){
        property.addAddress(property.getAddress());
        property.addUser(property.getUser());
        propertyRepository.save(property);
    }

    public List<?> getProperties(){
        List<Property> properties = (List<Property>) propertyRepository.findAvailableObjects();
        ArrayList<PropertyDTO> propertiesDTOs = new ArrayList<>();
        for(Property p : properties){
            propertiesDTOs.add(propertyMapper.propertyToDTO(Optional.ofNullable(p)));
        }
        return propertiesDTOs;
    }

    public PropertyDTO getProperty(Integer id){
       return propertyMapper.propertyToDTO(propertyRepository.findById(id));
    }

    public ArrayList<PropertyDTO> getUserProperties(Integer id){
        List<Property> properties = (List<Property>) propertyRepository.findByUserId(id);
        ArrayList<PropertyDTO> propertiesDTO = new ArrayList<>();
        for(Property p : properties){
            propertiesDTO.add(propertyMapper.propertyToDTO(Optional.ofNullable(p)));
        }
        return propertiesDTO;
    }

    public List<?> searchProperties(PropertyView searchResult){
        return propertyRepository.findObjectsBySearch(searchResult.getCity(),
                searchResult.getBeds(), searchResult.getBaths(), searchResult.getGuests(), searchResult.getDailyPrice(),
                searchResult.getStartDate(), searchResult.getEndDate());
    }


}
