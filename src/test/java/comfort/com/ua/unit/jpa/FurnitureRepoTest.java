package comfort.com.ua.unit.jpa;

import comfort.com.ua.models.*;
import comfort.com.ua.repos.FurnitureRepo;
import comfort.com.ua.repos.ImageRepo;
import comfort.com.ua.repos.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ImageRepo imageRepo;

    private List<Furniture> furnitures;

    @BeforeEach
    public void setUp(){
        for (int i = 0; i < 1000; i++) {
            furnitureRepo.save(new Furniture(Long.valueOf(i + 1), "Dyvan" + i, "Description" + i, "3200uah",
                    null, null, FurnitureType.SOFA, null, Priority.LOW));
        }
         furnitures = furnitureRepo.findAll();
        for (int i = 0 ; i < 1000; i++){
            imageRepo.save(new ImageDB(Long.valueOf(i + 1), "goodpath.img" + i, furnitures.get(i)));
        }
        for (int i = 0 ; i < 1000; i++){
            orderRepository.save(new Order(Long.valueOf(i+1), "Petrenko Petro1." + i, "Contact me after today",
                    LocalDate.now(), "+380731090986", "petro@gmail.com", furnitures.get(i)));
        }
    }


    @Test
    public void saveEntity(){
        Assertions.assertEquals(1000, furnitureRepo.findAll().size());
        furnitures = furnitureRepo.findAll();
        int i = 0;
        for (Furniture f : furnitures){

            Assertions.assertEquals(new Furniture(Long.valueOf(3000 + i + 1), "Dyvan" + i, "Description" + i, "3200uah",
                    null, null, FurnitureType.SOFA, null, Priority.LOW), f);
            i++;
        }
    }

    @Test
    public void readByIdTest(){
        Assertions.assertEquals(new Furniture(Long.valueOf(2001), "Dyvan" + 0, "Description" + 0, "3200uah",
                null, null, FurnitureType.SOFA, null, Priority.LOW), furnitureRepo.findById(2001L).get());

        Assertions.assertEquals(new Furniture(Long.valueOf(2234), "Dyvan" + 233, "Description" + 233, "3200uah",
                null, null, FurnitureType.SOFA, null, Priority.LOW), furnitureRepo.findById(2234L).get());

        Assertions.assertEquals(new Furniture(Long.valueOf(2546), "Dyvan" + 545, "Description" + 545, "3200uah",
                null, null, FurnitureType.SOFA, null, Priority.LOW), furnitureRepo.findById(2546L).get());

        Assertions.assertEquals(new Furniture(Long.valueOf(2785), "Dyvan" + 784, "Description" + 784, "3200uah",
                null, null, FurnitureType.SOFA, null, Priority.LOW), furnitureRepo.findById(2785L).get());

        Assertions.assertEquals(new Furniture(Long.valueOf(2987), "Dyvan" + 986, "Description" + 986, "3200uah",
                null, null, FurnitureType.SOFA, null, Priority.LOW), furnitureRepo.findById(2987L).get());
    }

    @Test
    public void updateEntity() throws InterruptedException {
        Assertions.assertEquals(1000, furnitureRepo.findAll().size());
        furnitures = furnitureRepo.findAll();


        Furniture furniture;
        for (int i = 0 ; i < 1000; i++){
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
        Assertions.assertEquals("goodpath.img543", furnitureRepo.findAll().get(543).getImages().get(0).getPath());
        Assertions.assertEquals("Petrenko Petro1.876", furnitureRepo.findAll().get(876).getOrders().get(0).getName());
        Assertions.assertNotEquals("goodpath.img1243", furnitureRepo.findAll().get(543).getImages().get(0).getPath());
    }

    @Test
    public void delete(){
        Assertions.assertEquals(1000, furnitureRepo.findAll().size());
        Assertions.assertEquals(1000, imageRepo.findAll().size());
        Assertions.assertEquals(1000, orderRepository.findAll().size());
        Furniture furniture ;
        for (int i = 0; i < 1000; i++) {
            furniture = new Furniture();
            furniture = new Furniture();
            furniture.setId(furnitures.get(i).getId());
            furniture.setName("DyvanUpdated" + i);
            furniture.setType(FurnitureType.BED);
            furniture.setPrice("5465$");
            furniture.setPriority(Priority.HIGH);
            furniture.setDescription("Bla bla bla description");
            furniture.setImages(List.of(new ImageDB(Long.valueOf(1000 + i + 1), "goodpath.img" + i, furnitures.get(i))));
            furniture.setOrders(List.of(new Order(Long.valueOf(1000 + i + 1), "Petrenko Petro1." + i, "Contact me after today",
                    LocalDate.now(), "+380731090986", "petro@gmail.com", furnitures.get(i))));
            furnitureRepo.delete(furniture);
        }
        Assertions.assertEquals(0, furnitureRepo.findAll().size());
        Assertions.assertEquals(0, imageRepo.findAll().size());
        Assertions.assertEquals(0, orderRepository.findAll().size());
    }



}
