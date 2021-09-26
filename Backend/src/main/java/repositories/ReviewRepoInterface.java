package repositories;

import java.util.List;
import java.util.Optional;

public interface ReviewRepoInterface {
    
    
    Optional<?> findById(Integer id);
    
    List<?> findAll();

    List<?> findAllReviewsOnUserId(Integer id);

//    Optional<?> save();
//
//    void delete();
}
