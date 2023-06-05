package comfort.com.ua.unit.jpa;

import comfort.com.ua.models.Furniture;
import comfort.com.ua.models.FurnitureTypeOfOrder;
import comfort.com.ua.models.TypeOfOrder;
import comfort.com.ua.repos.FurnitureRepo;
import comfort.com.ua.repos.FurnitureTypeOfOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.properties")
public class FurnitureTypeOfOrderTest {

    @Autowired
    private FurnitureTypeOfOrderRepository furnitureTypeOfOrderRepository;

    @Autowired
    private FurnitureRepo furnitureRepo;

    @Test
    public void saveEntity(){
        for (int i = 0; i < 1000; i++){
            Furniture furniture = new Furniture();
            furniture.setName("Furniture" + i);
            furnitureRepo.save(furniture);
        }

        List<Furniture> furnitures = furnitureRepo.findAll();
        for (int i = 0; i < 1000; i++){
            furnitureTypeOfOrderRepository.save(new FurnitureTypeOfOrder(Long.valueOf(i + 1), "Name" + i, new TypeOfOrder(), List.of(furnitures.get(i)), "img"));
        }
        Assertions.assertEquals(1000, furnitureTypeOfOrderRepository.findAll().size());
        Assertions.assertEquals("Furniture0", furnitureTypeOfOrderRepository.findAll().get(0).getFurniture().get(0).getName());
        Assertions.assertEquals("Furniture1", furnitureTypeOfOrderRepository.findAll().get(1).getFurniture().get(0).getName());
        Assertions.assertEquals("Furniture2", furnitureTypeOfOrderRepository.findAll().get(2).getFurniture().get(0).getName());
    }

}
