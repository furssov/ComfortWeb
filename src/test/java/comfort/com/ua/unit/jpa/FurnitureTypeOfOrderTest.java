package comfort.com.ua.unit.jpa;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import comfort.com.ua.models.Furniture;
import comfort.com.ua.models.FurnitureTypeOfOrder;
import comfort.com.ua.models.TypeOfOrder;
import comfort.com.ua.repos.FurnitureRepo;
import comfort.com.ua.repos.FurnitureTypeOfOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.properties")
public class FurnitureTypeOfOrderTest {

    @Autowired
    private FurnitureTypeOfOrderRepository furnitureTypeOfOrderRepository;

    @Autowired
    private FurnitureRepo furnitureRepo;

    private List<Furniture> furnitures;
    @BeforeEach
    public void setUp(){
        for (int i = 0; i < 1000; i++){
            Furniture furniture = new Furniture();
            furniture.setName("Furniture" + i);
            furnitureRepo.save(furniture);
        }
         furnitures = furnitureRepo.findAll();
        for (int i = 0; i < 1000; i++){
            furnitureTypeOfOrderRepository.save(new FurnitureTypeOfOrder(Long.valueOf(i + 1), "Name" + i, null, List.of(furnitures.get(i)), "img"));
        }
    }
    @Test
    public void saveEntity(){
        List<FurnitureTypeOfOrder> furnitureTypeOfOrders = furnitureTypeOfOrderRepository.findAll();
        Assertions.assertEquals(1000, furnitureTypeOfOrderRepository.findAll().size());
        for (int i = 0; i < 1000; i++){
            Assertions.assertEquals(new FurnitureTypeOfOrder(Long.valueOf(2000 + i + 1), "Name" + i, null, List.of(furnitures.get(i)), "img"),furnitureTypeOfOrders.get(i));
        }
    }

    @Test
    public void updateEntity(){
        Assertions.assertEquals(1000, furnitureTypeOfOrderRepository.findAll().size());
        List<FurnitureTypeOfOrder> furnitureTypeOfOrders = new ArrayList<>(furnitureTypeOfOrderRepository.findAll());
        for (int i = 0 ; i < 1000; i++) {
            furnitureTypeOfOrders.get(i).setName("Name1." + i);
            furnitureTypeOfOrders.get(i).setImage("img" + i);
        }

        for (int i = 0; i < 1000; i++) {
            furnitureTypeOfOrderRepository.save(furnitureTypeOfOrders.get(i));
        }

        furnitureTypeOfOrders = furnitureTypeOfOrderRepository.findAll();

        for (int i = 0; i < 1000; i++){
            Assertions.assertEquals(new FurnitureTypeOfOrder(Long.valueOf(i + 1), "Name1." + i, null, List.of(furnitures.get(i)), "img" + i),furnitureTypeOfOrders.get(i));
        }
    }

    @Test
    public void deleteEntity(){
        Assertions.assertEquals(1000, furnitureTypeOfOrderRepository.findAll().size());
        Assertions.assertEquals(1000, furnitureRepo.findAll().size());

            for(int i = 0 ; i < 1000; i++){
            furnitureTypeOfOrderRepository.delete(new FurnitureTypeOfOrder(Long.valueOf(1000 + i + 1), "Name1." + i, null, List.of(furnitures.get(i)), "img" + i));
        }
        Assertions.assertEquals(0, furnitureTypeOfOrderRepository.findAll().size());
        Assertions.assertEquals(0, furnitureRepo.findAll().size());
    }

}
