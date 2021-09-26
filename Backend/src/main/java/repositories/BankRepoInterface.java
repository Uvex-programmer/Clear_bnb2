package repositories;

import models.BankAccount;

import java.util.List;
import java.util.Optional;

public interface BankRepoInterface {

    Optional<BankAccount> findById(Integer id);

    List<?> findAll();

    Optional<BankAccount> save(BankAccount account);


}
