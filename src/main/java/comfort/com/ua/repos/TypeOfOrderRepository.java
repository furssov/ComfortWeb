package comfort.com.ua.repos;

import comfort.com.ua.models.TypeOfOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeOfOrderRepository extends JpaRepository<TypeOfOrder, Long> {
}
