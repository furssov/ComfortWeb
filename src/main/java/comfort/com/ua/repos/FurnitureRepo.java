package comfort.com.ua.repos;

import comfort.com.ua.models.Furniture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FurnitureRepo extends JpaRepository<Furniture, Long> {
}
