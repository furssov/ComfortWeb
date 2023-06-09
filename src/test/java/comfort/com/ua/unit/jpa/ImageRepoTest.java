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



    @Test
    public void postImage(){
        Assertions.assertEquals(0, imageRepo.findAll().size());
        for (int i = 0; i < 100; i++){
            imageRepo.save(new ImageDB());
        }
        Assertions.assertEquals(100, imageRepo.findAll().size());
    }


}
