package comfort.com.ua.services;

import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.models.Furniture;

import java.util.List;
import java.util.Optional;

public interface FurnitureService {
    List<Furniture> findByFurnitureTypeOfOrderId(long id);
    Optional<Furniture> findById(long id) throws NoSuchFurnitureException;
}
