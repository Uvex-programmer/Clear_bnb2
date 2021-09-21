package routes;

import express.Express;
import models.Property;
import repositories.PropertyRepository;

public class PropertyRoutes {
    
    private final Express app;
    private PropertyRepository propertyRepository;
    
    public PropertyRoutes(Express app, PropertyRepository propertyRepository) {
        this.app = app;
        this.propertyRepository = propertyRepository;
    }
    
    public void propertyMethods() {
        app.post("/api/addProperty", (req, res) -> {
            Property property = req.body(Property.class);
            // sätta ägare här på något sätt
            propertyRepository.save(property);
        });
    }
}
