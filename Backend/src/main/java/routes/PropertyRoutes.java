package routes;

import express.Express;
import models.Property;
import repositories.PropertyRepository;

public class PropertyRoutes {
    
    private final Express app;
    private final PropertyRepository propertyRepository;
    
    public PropertyRoutes(Express app, PropertyRepository propertyRepository) {
        this.app = app;
        this.propertyRepository = propertyRepository;
        this.propertyMethods();
    }
    
    public void propertyMethods() {
        app.post("/api/addProperty", (req, res) -> {
            Property property = req.body(Property.class);
            property.addAddress(property.getAddress());
            property.addUser(property.getUser());
            propertyRepository.save(property);
            System.out.println(property);
        });
    }
}
