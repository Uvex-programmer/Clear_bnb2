package routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import models.Property;
import models.PropertyView;
import repositories.PropertyRepository;

import java.util.List;
import java.util.Optional;

public class PropertyRoutes {
    
    private final Express app;
    private final PropertyRepository propertyRepository;
    private final ObjectMapper mapper;
    
    public PropertyRoutes(Express app, ObjectMapper mapper, PropertyRepository propertyRepository) {
        this.app = app;
        this.propertyRepository = propertyRepository;
        this.mapper = mapper;
        this.propertyMethods();
    }
    
    public void propertyMethods() {
        app.post("/api/add-property", (req, res) -> {
            System.out.println(req.body());
            Property property = req.body(Property.class);
            System.out.println(property);
            property.addAddress(property.getAddress());
            property.addUser(property.getUser());
            propertyRepository.save(property);

            res.json(property);
        });
        
        app.get("/api/properties", (req, res) -> {
            List<PropertyView> properties = propertyRepository.findAll();
            System.out.println(properties);
            res.json(mapper.writeValueAsString(properties)).status(200);
        });
        
        app.get("/api/properties/:id", (req, res) -> {
            Optional<Property> propertyById = propertyRepository.findById(Integer.parseInt(req.params("id")));
            res.json(mapper.writeValueAsString(propertyById)).status(200);
        });
        
        app.get("/api/get-user-properties/:id", (req, res) -> {
            List<?> properties = propertyRepository.findByUserId(Integer.parseInt(req.params("id")));
            res.json(mapper.writeValueAsString(properties));
        });
        
        app.post("/api/search", (req, res) -> {
            PropertyView searchResult = req.body(PropertyView.class);
            List<?> bySearch = propertyRepository.findObjectsBySearch(searchResult.getCity(), searchResult.getBeds(), searchResult.getBaths(), searchResult.getGuests(), searchResult.getDailyPrice(),
                    searchResult.getStartDate(), searchResult.getEndDate());
            res.json(mapper.writeValueAsString(bySearch));
        });
    }
}
