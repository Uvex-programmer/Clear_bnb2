package routes;

import com.fasterxml.jackson.databind.ObjectMapper;
import express.Express;
import models.Property;
import models.PropertyView;
import repositories.PropertyRepository;

import java.util.List;

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
        app.post("/api/addProperty", (req, res) -> {
            Property property = req.body(Property.class);
            property.addAddress(property.getAddress());
            property.addUser(property.getUser());
            propertyRepository.save(property);
            System.out.println(property);
        });
        
        app.get("/api/getUserProperties/:id", (req, res) -> {
            List<?> properties = propertyRepository.findByUserId(Integer.parseInt(req.params("id")));
            res.json(mapper.writeValueAsString(properties));
            System.out.println(properties);
        });
        
        app.post("/api/search", (req, res) -> {
            PropertyView searchResult = req.body(PropertyView.class);
            List<?> bySearch = propertyRepository.findObjectsBySearch(searchResult.getCity(), searchResult.getBeds(), searchResult.getBaths(), searchResult.getGuests(), searchResult.getDailyPrice(),
                    searchResult.getStartDate(), searchResult.getEndDate());
            res.json(mapper.writeValueAsString(bySearch));
        });
    }
}
