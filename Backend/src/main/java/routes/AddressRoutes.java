package routes;

import DTO.AddressDTO;
import express.Express;
import logic.AddressLogic;
import models.Address;
import repositories.AddressRepository;

public class AddressRoutes {
    private final Express app;
    AddressLogic addressLogic = new AddressLogic();
    
    public AddressRoutes(Express app) {
        this.app = app;

        this.addressMethods();
    }
    
    public void addressMethods() {
        app.post("/api/update-address/:id", (req, res) -> {
            res.json(addressLogic.updateAddress(req.body(AddressDTO.class), Integer.parseInt(req.params("id"))));
        });
    }
}
