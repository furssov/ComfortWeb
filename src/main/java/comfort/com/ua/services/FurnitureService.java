package comfort.com.ua.services;

import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.models.Furniture;
import comfort.com.ua.models.FurnitureType;

import java.util.List;
import java.util.Optional;

public interface FurnitureService {
    List<Furniture> findByFurnitureTypeOfOrderId(long id) throws NoSuchFurnitureException;
    List<Furniture> findAllGallery();
    Optional<Furniture> findById(long id) throws NoSuchFurnitureException;

    void delete(Long id);
    List<Furniture> findAll();
    Furniture save(Furniture furniture);
}
