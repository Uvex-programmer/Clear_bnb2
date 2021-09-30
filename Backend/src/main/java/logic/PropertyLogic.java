package logic;

import DTO.PropertyDTO;
import mapper.PropertyMapper;
import models.*;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import repositories.PropertyRepository;
import repositories.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertyLogic {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("bnb");
    EntityManager em = entityManagerFactory.createEntityManager();
    AuditReader auditReader = AuditReaderFactory.get(em);
    UserRepository userRepository = new UserRepository();
    PropertyRepository propertyRepository = new PropertyRepository();
    PropertyMapper propertyMapper = new PropertyMapper();

    public Property addProperty(Property property){
        property.addAddress(property.getAddress());
        property.addUser(property.getUser());
        property.addAmenities(property.getAmenities());
        propertyRepository.save(property);
        return property;
    }

    public List<?> getProperties(){
        List<Property> properties = propertyRepository.findAvailableObjects();
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

    public ArrayList<Property> updateProperty(PropertyDTO p, Integer id){
        var prop = propertyRepository.findById(id);
        Property property = propertyMapper.dtoToProperty(p, prop);

        property.setAddress(prop.get().getAddress());
        var newP = propertyRepository.updateProperty(property);

        List revisionNumbers = auditReader.getRevisions(Property.class, property.getId());

        ArrayList<Property> prop2 = new ArrayList<>();
        for (var rev : revisionNumbers) {
            Property property1 = auditReader.find(Property.class, property.getId(), Integer.parseInt(rev.toString()));
            System.out.println(property1);
            prop2.add(property1);
        }
        //return propertyMapper.propertyToDTO(newP);
        return prop2;
    }


}
