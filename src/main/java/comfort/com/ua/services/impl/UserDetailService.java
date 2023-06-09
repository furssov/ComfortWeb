package comfort.com.ua.services.impl;

import comfort.com.ua.models.User;
import comfort.com.ua.repos.UserRepository;
import comfort.com.ua.security.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user != null) return new UserDetail(user);
        else
            throw new UsernameNotFoundException("user " + username + " was not found");
    }
}
