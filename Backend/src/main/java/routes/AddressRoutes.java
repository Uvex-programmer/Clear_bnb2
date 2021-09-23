package routes;

import express.Express;
import models.Address;
import repositories.AddressRepository;

public class AddressRoutes {
    private final Express app;
    private final AddressRepository addressRepository;
    
    public AddressRoutes(Express app, AddressRepository addressRepository) {
        this.app = app;
        this.addressRepository = addressRepository;
    }
    
    public void propertyMethods() {
        app.post("/api/add-address", (req, res) -> {
            Address address = req.body(Address.class);
            // sätta ägare här på något sätt
            addressRepository.save(address);
        });
    }
}
