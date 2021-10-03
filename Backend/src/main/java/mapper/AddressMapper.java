package mapper;

import DTO.AddressDTO;
import DTO.PropertyDTO;
import models.Address;
import models.Property;

import java.util.Optional;

public class AddressMapper {

    public AddressDTO addressToDTO(Optional<Address> a) {
        return new AddressDTO(a.get().getId(), a.get().getStreet(), a.get().getZipcode(), a.get().getCity(), a.get().getProperty());
    }

    public Address dtoToAddress(AddressDTO dto, Optional<Address> address){
        Address a = new Address();
        a.setId(address.get().getId());
        a.setZipcode(dto.getZipcode());
        a.setStreet(dto.getStreet());
        a.setCity(dto.getCity());
        a.setProperty(address.get().getProperty());
        return a;
    }
}
