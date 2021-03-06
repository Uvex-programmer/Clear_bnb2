package interfaces;

import models.Property;
import models.PropertyView;

import java.util.List;
import java.util.Optional;

public interface PropertyRepoInterface {
    
    Optional<Property> findById(Integer id);
    
    List<PropertyView> findAll();
    
    List<?> findAvailableObjects();
    
    List<?> findObjectsBySearch(String freeSearch, int beds, int bathrooms, int minGuests,
                                int maxPrice, java.sql.Timestamp startDate, java.sql.Timestamp endDate);
    
    List<?> findByUserId(Integer id);
    
    Optional<Property> save(Property property);
}
