package comfort.com.ua.repos;

import comfort.com.ua.models.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Long> {

    Optional<Admin> findByEmail(String email);
}
