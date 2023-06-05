package comfort.com.ua.unit.jpa;


import comfort.com.ua.models.User;
import comfort.com.ua.repos.UserRepository;
import org.h2.engine.Role;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application-test.properties")
public class UserRepoTest {

    @Autowired
    private UserRepository userRepository;

    //The app assumes that there ll be only one user - admin, that's why there is no sense to testing db with a lot of data
    @Test
    public void loadByUsername(){
        for(int i = 0; i < 100; i++){
            User user = new User();
            user.setUsername("User" + i);
            user.setPassword("123456789");
            userRepository.save(user);
        }
        Assertions.assertEquals(100, userRepository.findAll().size());
        for(int i = 0; i < 100; i++){
            User user = new User();
            user.setId(Long.valueOf(i + 1));
            user.setUsername("User" + i);
            user.setPassword("123456789");
            Assertions.assertEquals(user, userRepository.findUserByUsername(user.getUsername()));
        }

    }
}
