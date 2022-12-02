package comfort.com.ua.services;

import comfort.com.ua.models.FurnitureTypeOfOrder;

import java.util.List;

public interface FurnitureTypeOfOrderService {
    List<FurnitureTypeOfOrder> findByOrderId(Long id);
}
