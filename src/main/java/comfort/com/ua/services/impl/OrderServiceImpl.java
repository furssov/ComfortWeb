package comfort.com.ua.services.impl;

import comfort.com.ua.models.Order;
import comfort.com.ua.repos.OrderRepository;
import comfort.com.ua.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Order save(Order order) {

        return orderRepository.save(order);
    }
}
