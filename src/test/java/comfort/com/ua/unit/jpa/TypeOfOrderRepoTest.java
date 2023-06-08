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
import org.springframework.dao.EmptyResultDataAccessException;
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

    private List<TypeOfOrder> typeOfOrders;

    @Test
    public void saveEntity(){
        typeOfOrders = typeOfOrderRepository.findAll();
        //Checking if type_of_order populated successfully
        Assertions.assertEquals(3, typeOfOrders.size());
        //Checking if furniture_type_of_order populated successfully
        Assertions.assertEquals(3, typeOfOrders.get(0).getList().size());
        Assertions.assertEquals(4, typeOfOrders.get(1).getList().size());
        Assertions.assertEquals(3, typeOfOrders.get(2).getList().size());
        //Add some new data
        TypeOfOrder type = new TypeOfOrder();
        FurnitureTypeOfOrder f1 = new FurnitureTypeOfOrder();
        f1.setName("FurnitureTypeOfOrderNew1");
        f1.setImage("newImg1.jpg");
        f1.setOrderId(type);
        type.setType("PeretyazhkaNew1");
        type.setImage("NewImage1.jpg");
        type.setList(List.of(f1));
        typeOfOrderRepository.save(type);
        //Checking if inserting into type_of_order was successfully
        typeOfOrders = typeOfOrderRepository.findAll();
        Assertions.assertEquals(4, typeOfOrders.size());
        //Checking if cascading works properly
        Assertions.assertEquals(1, typeOfOrders.get(3).getList().size());
        //Add some data again
        for (int i = 2 ; i < 102; i++){
            type = new TypeOfOrder();
            type.setType("PeretyazhkaNew" + i);
            type.setImage("NewImage"+ i +".jpg");
            f1.setName("FurnitureTypeOfOrderNew" + i);
            f1.setImage("newImg" + i + ".jpg");
            f1.setOrderId(type);
            type.setList(List.of(f1));
            typeOfOrderRepository.save(type);
        }
        //Checking if inserting into type_of_order was successfully
        typeOfOrders = typeOfOrderRepository.findAll();
        Assertions.assertEquals(104, typeOfOrders.size());
        //Checking if cascading works properly
        for (int i = 4; i < 102; i++){
            Assertions.assertEquals(1, typeOfOrders.get(i).getList().size());
        }
    }

    @Test
    public void findByIdTest(){
        Assertions.assertEquals(3, typeOfOrderRepository.findAll().size());
        typeOfOrders = typeOfOrderRepository.findAll();
        TypeOfOrder type = new TypeOfOrder();
        type.setId(1L);
        type.setImage("/images/peretyazhka.png");
        type.setType("Перетяжка меблів");
        FurnitureTypeOfOrder f1 = new FurnitureTypeOfOrder();
        FurnitureTypeOfOrder f2 = new FurnitureTypeOfOrder();
        FurnitureTypeOfOrder f3 = new FurnitureTypeOfOrder();
        f1.setId(4L);
        f1.setImage("/images/furniture_type_of_order/dyvany.jpg");
        f1.setName("Звичайні дивани");
        f1.setOrderId(type);
        f2.setId(5L);
        f2.setImage("/images/furniture_type_of_order/kutovi.jpg");
        f2.setName("Кутові Дивани");
        f2.setOrderId(type);
        f3.setId(6L);
        f3.setImage("/images/furniture_type_of_order/krisla.jpg");
        f3.setName("Крісла");
        f3.setOrderId(type);
        type.setList(List.of(f1, f2, f3));
        Assertions.assertEquals(type.getType(), typeOfOrderRepository.findById(1L).get().getType());
        Assertions.assertEquals(type.getImage(), typeOfOrderRepository.findById(1L).get().getImage());
        Assertions.assertEquals(type.getList().get(0).getName(), typeOfOrderRepository.findById(1L).get().getList().get(0).getName());


    }
    @Test
    public void updateEntity(){
        Assertions.assertEquals(3, typeOfOrderRepository.findAll().size());
        TypeOfOrder type1 = typeOfOrderRepository.findById(1L).get();
        type1.setType("peretyazhkaChanged");
        FurnitureTypeOfOrder f1 = new FurnitureTypeOfOrder();
        f1.setOrderId(type1);
        f1.setName("NewName");
        type1.getList().add(f1);
        typeOfOrderRepository.save(type1);
        TypeOfOrder typeDB = typeOfOrderRepository.findById(1l).get();
        Assertions.assertEquals(type1, typeDB);
        Assertions.assertEquals("peretyazhkaChanged", typeDB.getType());
        Assertions.assertEquals(type1.getList(), typeDB.getList());
        Assertions.assertEquals(type1.getList().get(3), typeDB.getList().get(3));
        Assertions.assertEquals("NewName", typeDB.getList().get(3).getName());

    }

    @Test
    public void deleteEntity(){
        Assertions.assertEquals(3, typeOfOrderRepository.findAll().size());
        TypeOfOrder type1 = typeOfOrderRepository.findById(1L).get();
        Assertions.assertEquals("Перетяжка меблів", type1.getType());

        typeOfOrderRepository.deleteById(1L);
        Assertions.assertEquals(2, typeOfOrderRepository.findAll().size());
        Assertions.assertTrue(typeOfOrderRepository.findById(1l).isEmpty());
        EmptyResultDataAccessException e = Assertions.assertThrows(EmptyResultDataAccessException.class, () -> typeOfOrderRepository.deleteById(453L) );

        typeOfOrderRepository.deleteById(2L);
        Assertions.assertEquals(1, typeOfOrderRepository.findAll().size());
        Assertions.assertTrue(typeOfOrderRepository.findById(2l).isEmpty());

        typeOfOrderRepository.deleteById(3L);
        Assertions.assertEquals(0, typeOfOrderRepository.findAll().size());
        Assertions.assertTrue(typeOfOrderRepository.findById(3l).isEmpty());
    }

}
