package mapper;
import DTO.AddressDTO;
import models.Address;

import java.util.Optional;

public class AddressMapper {

    public AddressDTO addressToDTO(Optional<Address> address) {
        var a = address.get();
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setId(a.getId());
        addressDTO.setCity(a.getCity());
        addressDTO.setProperty(a.getProperty());
        addressDTO.setStreet(a.getStreet());
        addressDTO.setZipcode(a.getZipcode());
        return addressDTO;
    }

    public Address dtoToAddress(AddressDTO dto, Optional<Address> address){
        var a = address.get();
        Address addressNew = new Address();
        a.setId(a.getId());
        a.setZipcode(dto.getZipcode());
        a.setStreet(dto.getStreet());
        a.setCity(dto.getCity());
        a.setProperty(a.getProperty());
        return addressNew;
    }
}
