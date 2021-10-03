package logic;

import DTO.AddressDTO;
import mapper.AddressMapper;
import models.Address;
import repositories.AddressRepository;

import java.util.Optional;

public class AddressLogic {

    AddressRepository addressRepository = new AddressRepository();
    AddressMapper addressMapper = new AddressMapper();

    public Optional<Address> updateAddress(AddressDTO addressDTO, Integer id){
        var address2 = addressRepository.findById(id);
        var address1 = addressMapper.dtoToAddress(addressDTO, address2);

        return addressRepository.updateAddress(address1);
    }
}
