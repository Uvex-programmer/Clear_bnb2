package repositories;

import java.util.List;
import java.util.Optional;

public interface ReviewRepoInterface {
    
    
    Optional<?> findById(Integer id);
    
    List<?> findAll();

    List<?> findAllByReviewUserId(Integer id);

//    Optional<?> save();
//
//    void delete();
}
