package comfort.com.ua.services;

import comfort.com.ua.models.Order;
import org.aspectj.weaver.ast.Or;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order save(Order order);

    List<Order> findAll();
}
