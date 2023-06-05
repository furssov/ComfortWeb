package comfort.com.ua.unit.services;

import comfort.com.ua.models.Order;
import comfort.com.ua.repos.OrderRepository;
import comfort.com.ua.services.OrderService;
import comfort.com.ua.services.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
@TestPropertySource("/application-test.properties")
class OrderServiceImplTest {

    @TestConfiguration
    static class OrderServiceTest
    {
        @Bean
        public OrderService orderService()
        {
            return new OrderServiceImpl();
        }
    }

    @Autowired
    private OrderService service;

    @MockBean
    private OrderRepository repository;

    @Test
    void save() {
        for (int i = 0; i < 10000; i++) {
            Order order = new Order();
            Mockito.when(repository.save(order)).thenReturn(order);
            assertEquals(order, service.save(order));
        }
    }

    @Test
    void findAll() {
        Order o1 = new Order();
        o1.setId(1L);
        Order o2 = new Order();
        o2.setId(2L);
        Mockito.when(repository.findAll()).thenReturn(List.of(o1, o2));
        assertEquals(List.of(o1, o2), service.findAll());
    }
}