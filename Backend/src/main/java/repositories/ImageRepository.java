package repositories;

import models.Image;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class ImageRepository implements ImageRepoInterface {
    private final EntityManager entityManager;
    
    public ImageRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    public Optional<Image> save(Image image) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(image);
            entityManager.getTransaction().commit();
            return Optional.of(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }
    
    public Optional<Image> findById(Integer id) {
        Image image = entityManager.find(Image.class, id);
        return image != null ? Optional.of(image) : Optional.empty();
    }
    
    public List<?> findAll() {
        return entityManager.createQuery("from Images").getResultList();
    }
}
