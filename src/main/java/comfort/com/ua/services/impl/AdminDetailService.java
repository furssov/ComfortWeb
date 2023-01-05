package comfort.com.ua.services.impl;

import comfort.com.ua.models.admin.Admin;
import comfort.com.ua.repos.AdminRepo;
import comfort.com.ua.security.AdminDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminDetailService implements UserDetailsService {
    private final AdminRepo adminRepo;

    @Autowired
    public AdminDetailService(AdminRepo adminRepo) {
        this.adminRepo = adminRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Admin> admin = adminRepo.findByEmail(username);
        if (admin.isPresent()) return new AdminDetails(admin.get());

        else throw new UsernameNotFoundException("no such email");
    }
}
