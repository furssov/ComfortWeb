package comfort.com.ua.unit.jpa;

import comfort.com.ua.models.Furniture;
import comfort.com.ua.models.Order;
import comfort.com.ua.repos.FurnitureRepo;
import comfort.com.ua.repos.OrderRepository;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@ExtendWith({SpringExtension.class})
@TestPropertySource("/application-test.properties")
public class OrderRepositoryTest {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FurnitureRepo furnitureRepo;

    //User cannot do anything except create orders, so only one kind of test
    @Test
    public void addOrders(){
        for (int i = 0; i < 1000; i++){
            Furniture furniture = new Furniture();
            furniture.setName("Furniture" + i);
            furnitureRepo.save(furniture);
        }

        List<Furniture> furnitures = furnitureRepo.findAll();
        for (int i = 0; i < 1000; i++){
            Order order = new Order();
            order.setName("Order" + i);
            order.setComment("Blablabla bla bla");
            order.setEmail("fursovd70@gmail.com");
            order.setPhoneNumber("+380731090986");
            order.setDateOfOrder(LocalDate.now());
            order.setFurnitureId(furnitures.get(i));
            orderRepository.save(order);
        }
        Assertions.assertEquals(1000, orderRepository.findAll().size());
        Assertions.assertEquals("Order54", orderRepository.findAll().get(54).getName());
        Assertions.assertEquals("Order567", orderRepository.findAll().get(567).getName());
        Assertions.assertEquals("Order349", orderRepository.findAll().get(349).getName());
        Assertions.assertEquals("Order743", orderRepository.findAll().get(743).getName());
        Assertions.assertEquals(furnitures.get(1), orderRepository.findAll().get(1).getFurnitureId());
        Assertions.assertEquals(furnitures.get(111), orderRepository.findAll().get(111).getFurnitureId());
        Assertions.assertEquals(furnitures.get(231), orderRepository.findAll().get(231).getFurnitureId());
        Assertions.assertEquals(furnitures.get(781), orderRepository.findAll().get(781).getFurnitureId());
    }
}
