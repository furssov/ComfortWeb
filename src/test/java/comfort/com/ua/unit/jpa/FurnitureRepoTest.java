package comfort.com.ua.unit.jpa;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import comfort.com.ua.models.*;
import comfort.com.ua.repos.FurnitureRepo;

import jakarta.validation.ConstraintViolationException;
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
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.properties")
public class FurnitureRepoTest {

    @Autowired
    private FurnitureRepo furnitureRepo;


    @Test
    public void saveEntity(){
        Assertions.assertEquals(79, furnitureRepo.findAll().size());
        Furniture furniture;
        for (int i = 80; i < 181; i++){
            furniture = new Furniture();
            furniture.setName("F" + i);
            furniture.setImages(List.of(new ImageDB(), new ImageDB()));
            furniture.setOrders(List.of(new Order( "Petrenko Petro1." + i, "Contact me after today",
                    LocalDate.now(), "+380731090986", "petro@gmail.com", furniture)));
            furnitureRepo.save(furniture);
        }
        Assertions.assertEquals(0, furnitureRepo.findById(73L).get().getImages().size());

        Assertions.assertEquals(2, furnitureRepo.findById(80L).get().getImages().size());
        Assertions.assertEquals(1, furnitureRepo.findById(80L).get().getOrders().size());


        Assertions.assertEquals(2, furnitureRepo.findById(100L).get().getImages().size());
        Assertions.assertEquals(1, furnitureRepo.findById(100L).get().getOrders().size());


        Assertions.assertEquals(2, furnitureRepo.findById(143L).get().getImages().size());
        Assertions.assertEquals(1, furnitureRepo.findById(143L).get().getOrders().size());

        furniture = new Furniture();
        Furniture finalFurniture = furniture;
        Assertions.assertEquals(180, furnitureRepo.findAll().size());


        ConstraintViolationException e = Assertions.assertThrows(ConstraintViolationException.class, () -> {
            finalFurniture.setOrders(List.of(new Order()));
            furnitureRepo.save(finalFurniture);

        });
        Assertions.assertNotNull(e);

    }

    @Test
    public void readByIdTest(){
        Assertions.assertEquals("Кутовий диван", furnitureRepo.findById(1l).get().getName());
        Assertions.assertEquals("Кухня blum", furnitureRepo.findById(27l).get().getName());
        Assertions.assertEquals("Стільці", furnitureRepo.findById(77l).get().getName());

    }

    @Test
    public void updateEntity()  {
        Assertions.assertEquals(79, furnitureRepo.findAll().size());
        List<Furniture> furnitures = furnitureRepo.findAll();

        Furniture furniture;
        for (int i = 0 ; i < 79; i++){
            furniture = new Furniture();
            furniture.setId(furnitures.get(i).getId());
            furniture.setName("DyvanUpdated" + i);
            furniture.setType(FurnitureType.BED);
            furniture.setPrice("5465$");
            furniture.setPriority(Priority.HIGH);
            furniture.setDescription("Bla bla bla description");
            furniture.setImages(List.of(new ImageDB(Long.valueOf(i + 1), "goodpath.img" + i, furnitures.get(i))));
            furniture.setOrders(List.of(new Order(Long.valueOf(i+1), "Petrenko Petro1." + i, "Contact me after today",
                    LocalDate.now(), "+380731090986", "petro@gmail.com", furnitures.get(i))));
            furnitureRepo.save(furniture);
        }


        Assertions.assertEquals("DyvanUpdated2", furnitureRepo.findAll().get(2).getName());
        Assertions.assertEquals("goodpath.img54", furnitureRepo.findAll().get(54).getImages().get(0).getPath());
        Assertions.assertEquals("Petrenko Petro1.77", furnitureRepo.findAll().get(77).getOrders().get(0).getName());
        Assertions.assertEquals("goodpath.img23", furnitureRepo.findAll().get(23).getImages().get(0).getPath());
    }

    @Test
    public void deleteById(){
        Assertions.assertEquals(79, furnitureRepo.findAll().size());
        Assertions.assertTrue(furnitureRepo.findById(54L).isPresent());
        furnitureRepo.deleteById(54L);
        Assertions.assertEquals(78, furnitureRepo.findAll().size());
        Assertions.assertTrue(furnitureRepo.findById(54L).isEmpty());

        Assertions.assertTrue(furnitureRepo.findById(33L).isPresent());
        furnitureRepo.deleteById(33L);
        Assertions.assertEquals(77, furnitureRepo.findAll().size());
        Assertions.assertTrue(furnitureRepo.findById(33L).isEmpty());

        Assertions.assertTrue(furnitureRepo.findById(14L).isPresent());
        furnitureRepo.deleteById(14L);
        Assertions.assertEquals(76, furnitureRepo.findAll().size());
        Assertions.assertTrue(furnitureRepo.findById(14L).isEmpty());
    }




}
