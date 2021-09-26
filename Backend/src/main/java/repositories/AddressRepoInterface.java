package repositories;

import models.Address;

import java.util.List;
import java.util.Optional;

public interface AddressRepoInterface {

    Optional<Address> save(Address address);

    Optional<Address> findById(Integer id);

    List findAll();
}
