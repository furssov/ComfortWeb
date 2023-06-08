package comfort.com.ua.unit.jpa;

import comfort.com.ua.models.Furniture;
import comfort.com.ua.models.FurnitureTypeOfOrder;
import comfort.com.ua.repos.FurnitureRepo;
import comfort.com.ua.repos.FurnitureTypeOfOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.properties")
public class FurnitureTypeOfOrderTest {

    @Autowired
    private FurnitureTypeOfOrderRepository furnitureTypeOfOrderRepository;


    @Test
    public void saveEntity(){
        Assertions.assertEquals(10, furnitureTypeOfOrderRepository.findAll().size());
        Assertions.assertTrue(furnitureTypeOfOrderRepository.findById(33L).isEmpty());
        Assertions.assertTrue(furnitureTypeOfOrderRepository.findById(66L).isEmpty());
        Assertions.assertTrue(furnitureTypeOfOrderRepository.findById(99L).isEmpty());
        FurnitureTypeOfOrder furnitureTypeOfOrder;
        for (int i = 0 ; i < 100; i++){
            furnitureTypeOfOrder = new FurnitureTypeOfOrder();
            furnitureTypeOfOrder.setName("FTOO" + i);
            furnitureTypeOfOrder.setFurniture(List.of(new Furniture(), new Furniture(), new Furniture()));
            furnitureTypeOfOrderRepository.save(furnitureTypeOfOrder);
        }
        Assertions.assertEquals(110, furnitureTypeOfOrderRepository.findAll().size());
        Assertions.assertTrue(furnitureTypeOfOrderRepository.findById(33L).isPresent());
        Assertions.assertTrue(furnitureTypeOfOrderRepository.findById(66L).isPresent());
        Assertions.assertTrue(furnitureTypeOfOrderRepository.findById(99L).isPresent());
        Assertions.assertEquals(3, furnitureTypeOfOrderRepository.findById(33L).get().getFurniture().size());
        Assertions.assertEquals(3, furnitureTypeOfOrderRepository.findById(66L).get().getFurniture().size());
        Assertions.assertEquals(3, furnitureTypeOfOrderRepository.findById(99L).get().getFurniture().size());

    }

    @Test
    public void updateEntity(){
        Assertions.assertEquals(10, furnitureTypeOfOrderRepository.findAll().size());
        List<FurnitureTypeOfOrder> furnitureTypeOfOrders = new ArrayList<>(furnitureTypeOfOrderRepository.findAll());
        List<Furniture> furnitures = new ArrayList<>();
        for (int i = 0 ; i < 10; i++) {
            furnitureTypeOfOrders.get(i).setName("Name1." + i);
            furnitureTypeOfOrders.get(i).setImage("img" + i);

        }

        for (int i = 0; i < 10; i++) {
            furnitureTypeOfOrderRepository.save(furnitureTypeOfOrders.get(i));
        }

        furnitureTypeOfOrders = furnitureTypeOfOrderRepository.findAll();

        for (int i = 0; i < 10; i++){
            furnitures.addAll(furnitureTypeOfOrderRepository.findAll().get(i).getFurniture());
            Assertions.assertEquals(new FurnitureTypeOfOrder(Long.valueOf(i + 1), "Name1." + i, furnitureTypeOfOrders.get(i).getOrderId(), furnitures, "img" + i),furnitureTypeOfOrders.get(i));
            furnitures.removeAll(furnitures);
        }
    }

    @Test
    public void deleteEntity(){
        Assertions.assertEquals(10, furnitureTypeOfOrderRepository.findAll().size());

            for(int i = 0 ; i < 10; i++){
                furnitureTypeOfOrderRepository.deleteById(Long.valueOf(i + 1));
        }
        EmptyResultDataAccessException e = Assertions.assertThrows(EmptyResultDataAccessException.class, () -> furnitureTypeOfOrderRepository.deleteById(4L) );

        Assertions.assertEquals(0, furnitureTypeOfOrderRepository.findAll().size());
    }

}
