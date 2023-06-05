package comfort.com.ua.unit.jpa;

import comfort.com.ua.models.FurnitureTypeOfOrder;
import comfort.com.ua.models.TypeOfOrder;
import comfort.com.ua.repos.FurnitureTypeOfOrderRepository;
import comfort.com.ua.repos.TypeOfOrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.properties")
public class TypeOfOrderRepoTest {
    @Autowired
    private TypeOfOrderRepository typeOfOrderRepository;

    @Autowired
    private FurnitureTypeOfOrderRepository furnitureTypeOfOrderRepository;

    @BeforeEach
    public void setUp(){
        for (int i = 0; i < 10000; i++) {
            TypeOfOrder typeOfOrder = new TypeOfOrder();
            FurnitureTypeOfOrder f1 = new FurnitureTypeOfOrder();
            f1.setName("Name1." + i);
            furnitureTypeOfOrderRepository.save(f1);
            typeOfOrder.setType("Matrazy" + i);
            typeOfOrder.setImage("/image/image" + i + ".jpg");
            typeOfOrder.setList(List.of(f1));
            typeOfOrderRepository.save(typeOfOrder);
        }
    }

    @Test
    public void saveEntity(){
        Assertions.assertEquals(10000, typeOfOrderRepository.findAll().size());
        Assertions.assertEquals("Matrazy5434", typeOfOrderRepository.findAll().get(5434).getType());
        Assertions.assertEquals("Matrazy9999", typeOfOrderRepository.findAll().get(9999).getType());
        Assertions.assertEquals("Matrazy1", typeOfOrderRepository.findAll().get(1).getType());
        Assertions.assertEquals("Matrazy543", typeOfOrderRepository.findAll().get(543).getType());
        Assertions.assertEquals("Matrazy2813", typeOfOrderRepository.findAll().get(2813).getType());
    }

    @Test
    public void updateEntity(){
        List<TypeOfOrder> typeOfOrders = new LinkedList<>(typeOfOrderRepository.findAll());
        Assertions.assertEquals(10000, typeOfOrders.size());
        for (int i = 0; i < 100; i++) {
            TypeOfOrder type = new TypeOfOrder();
            type.setId(typeOfOrders.get(i).getId());
            type.setImage(typeOfOrders.get(i).getImage());
            type.setType("Matrazy" + i + "Changed");
            typeOfOrderRepository.save(type);
            Assertions.assertEquals(type, typeOfOrderRepository.findAll().get(i));
        }
    }

    @Test
    public void deleteEntity(){
        List<TypeOfOrder> typeOfOrders = typeOfOrderRepository.findAll();
        Assertions.assertEquals(10000, typeOfOrders.size());

        typeOfOrderRepository.delete(typeOfOrders.get(34));
        Assertions.assertEquals(9999, typeOfOrderRepository.findAll().size());
        Assertions.assertEquals(Optional.empty(),typeOfOrderRepository.findById(typeOfOrders.get(34).getId()));

        typeOfOrderRepository.delete(typeOfOrders.get(1395));
        Assertions.assertEquals(9998, typeOfOrderRepository.findAll().size());
        Assertions.assertEquals(Optional.empty(),typeOfOrderRepository.findById(typeOfOrders.get(1395).getId()));

        typeOfOrderRepository.delete(typeOfOrders.get(7845));
        Assertions.assertEquals(9997, typeOfOrderRepository.findAll().size());
        Assertions.assertEquals(Optional.empty(),typeOfOrderRepository.findById(typeOfOrders.get(7845).getId()));

        Assertions.assertEquals(9997, furnitureTypeOfOrderRepository.findAll().size());
    }

}
