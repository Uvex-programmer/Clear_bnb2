package repositories;

import java.util.List;
import java.util.Optional;

public interface RepositoryInterface {
    
    
    Optional<?> findById(Integer id);
    
    List<?> findAll();
    

//    Optional<?> save();
//
//    void delete();
}
