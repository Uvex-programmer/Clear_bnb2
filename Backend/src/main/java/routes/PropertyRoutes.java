package routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import logic.PropertyLogic;
import models.Property;
import models.PropertyView;
import repositories.PropertyRepository;
import util.MongoDB;

import java.util.List;
import java.util.Optional;

public class PropertyRoutes {

    private final Express app;
    private final PropertyRepository propertyRepository;
    private final ObjectMapper mapper;
    PropertyLogic propertyLogic = new PropertyLogic();
    
    public PropertyRoutes(Express app, ObjectMapper mapper, PropertyRepository propertyRepository) {
        this.app = app;
        this.propertyRepository = propertyRepository;
        this.mapper = mapper;
        this.propertyMethods();
    }

    public void propertyMethods() {
        app.post("/api/add-property", (req, res) -> {
            propertyLogic.addProperty(req.body(Property.class));
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
