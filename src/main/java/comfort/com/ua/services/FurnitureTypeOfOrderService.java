package comfort.com.ua.services;

import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.models.Furniture;
import comfort.com.ua.models.FurnitureTypeOfOrder;

import java.util.List;

public interface FurnitureTypeOfOrderService {
    List<FurnitureTypeOfOrder> findByOrderId(Long id) throws NoSuchFurnitureException;
    FurnitureTypeOfOrder findById(long id);
}
