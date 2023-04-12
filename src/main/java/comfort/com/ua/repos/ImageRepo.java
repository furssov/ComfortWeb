package comfort.com.ua.repos;

import comfort.com.ua.models.ImageDB;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepo extends JpaRepository<ImageDB, Long> {
}
