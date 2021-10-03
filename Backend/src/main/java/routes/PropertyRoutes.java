package routes;

import DTO.PropertyDTO;
import express.Express;
import logic.PropertyLogic;
import models.Property;
import models.PropertyView;

public class PropertyRoutes {
    
    private final Express app;
    PropertyLogic propertyLogic = new PropertyLogic();
    
    public PropertyRoutes(Express app) {
        this.app = app;
        this.propertyMethods();
    }
    
    public void propertyMethods() {
        app.post("/api/add-property", (req, res) -> {
            res.json(propertyLogic.addProperty(req.body(Property.class)));
        });

        app.post("/api/property/update/:id", (req, res) -> {
           propertyLogic.updateProperty(req.body(PropertyDTO.class), Integer.parseInt(req.params("id")));
        });
        
        app.get("/api/get-properties", (req, res) -> {
            res.json(propertyLogic.getProperties());
        });
        
        app.get("/api/get-property/:id", (req, res) -> {
            res.json(propertyLogic.getProperty(Integer.parseInt(req.params("id"))));
        });
        
        app.get("/api/get-user-properties/:id", (req, res) -> {
            res.json(propertyLogic.getUserProperties(Integer.parseInt(req.params("id"))));
        });
        
        app.post("/api/search", (req, res) -> {
            res.json(propertyLogic.searchProperties(req.body(PropertyView.class)));
        });
    }
}
