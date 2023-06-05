package comfort.com.ua.unit.jpa;

import comfort.com.ua.models.Furniture;
import comfort.com.ua.models.ImageDB;
import comfort.com.ua.repos.FurnitureRepo;
import comfort.com.ua.repos.ImageRepo;
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
public class ImageRepoTest {
    @Autowired
    private ImageRepo imageRepo;

    @Autowired
    private FurnitureRepo furnitureRepo;

    @Test
    public void postImage(){
        for (int i = 0; i < 1000; i++){
            Furniture furniture = new Furniture();
            furniture.setName("Furniture" + i);
            furnitureRepo.save(furniture);
        }

        List<Furniture> furnitures = furnitureRepo.findAll();

        for (int i = 0; i < 1000; i++){
            imageRepo.save(new ImageDB(Long.valueOf(i+1), "12345.img", furnitures.get(i)));
        }

        Assertions.assertEquals(1000, imageRepo.findAll().size());
        Assertions.assertEquals("Furniture436", imageRepo.findAll().get(436).getFurniture().getName());
    }


}
