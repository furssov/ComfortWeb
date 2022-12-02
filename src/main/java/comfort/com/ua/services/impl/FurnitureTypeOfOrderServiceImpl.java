package comfort.com.ua.services.impl;

import comfort.com.ua.models.FurnitureTypeOfOrder;
import comfort.com.ua.repos.FurnitureTypeOfOrderRepository;
import comfort.com.ua.services.FurnitureTypeOfOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FurnitureTypeOfOrderServiceImpl implements FurnitureTypeOfOrderService {
    @Autowired
    private FurnitureTypeOfOrderRepository furnitureTypeOfOrderRepository;
    @Override
    public List<FurnitureTypeOfOrder> findByOrderId(Long id) {
        List<FurnitureTypeOfOrder> furnitureTypeOfOrders = furnitureTypeOfOrderRepository.findAll();
        return furnitureTypeOfOrders.stream().filter(furnitureTypeOfOrder -> furnitureTypeOfOrder.getOrderId().getId() == id).collect(Collectors.toList());
    }
}
