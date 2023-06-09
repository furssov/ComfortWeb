package comfort.com.ua.unit.services;

import comfort.com.ua.models.Role;
import comfort.com.ua.models.User;
import comfort.com.ua.repos.UserRepository;
import comfort.com.ua.security.UserDetail;
import comfort.com.ua.services.impl.UserDetailService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class UserDetailsServiceTest {

    @TestConfiguration
    static class UserTest{
        @Bean
        public UserDetailsService getUserService(){
            return new UserDetailService();
        }
    }
    @Autowired
    @Qualifier("getUserService")
    private UserDetailService userDetailService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void loadUser(){
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 100; i++){
            users.add(new User(Long.valueOf(i), "user" + i, "passw" + i, Role.ADMIN));
        }
        Mockito.when(userRepository.findUserByUsername("user54")).thenReturn(users.get(53));
        Mockito.when(userRepository.findUserByUsername("user23")).thenReturn(users.get(23));
        Mockito.when(userRepository.findUserByUsername("user87")).thenReturn(users.get(87));
        Mockito.when(userRepository.findUserByUsername("user1")).thenReturn(users.get(1));
        Mockito.when(userRepository.findUserByUsername("user34")).thenReturn(users.get(34));
        Mockito.when(userRepository.findUserByUsername("user1543")).thenReturn(null);
        Mockito.when(userRepository.findUserByUsername("user999")).thenReturn(null);
        Assertions.assertEquals(new UserDetail(users.get(53)), userDetailService.loadUserByUsername("user54"));
        Assertions.assertEquals(new UserDetail(users.get(23)), userDetailService.loadUserByUsername("user23"));
        Assertions.assertEquals(new UserDetail(users.get(87)), userDetailService.loadUserByUsername("user87"));
        Assertions.assertEquals(new UserDetail(users.get(1)), userDetailService.loadUserByUsername("user1"));
        Assertions.assertEquals(new UserDetail(users.get(34)), userDetailService.loadUserByUsername("user34"));

        UsernameNotFoundException e = Assertions.assertThrows(UsernameNotFoundException.class, () ->{
           userDetailService.loadUserByUsername("user1543");
        });

        Assertions.assertNotNull(e);

        UsernameNotFoundException e1 = Assertions.assertThrows(UsernameNotFoundException.class, () ->{
            userDetailService.loadUserByUsername("user999");
        });

        Assertions.assertNotNull(e1);

    }
}
