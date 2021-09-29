package interfaces;

import models.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionRepoInterface {

    Optional<Transaction> findById(Integer id);

    List<?> findAll();

    Optional<Transaction> save(Transaction transaction);

}
