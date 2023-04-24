package comfort.com.ua.services;

import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.models.TypeOfOrder;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TypeOfOrderService {

    TypeOfOrder save(TypeOfOrder typeOfOrder);

    TypeOfOrder change(Long id) throws NoSuchFurnitureException;

    void delete(long id);

    List<TypeOfOrder> getAll();

    TypeOfOrder getById(Long id) throws NoSuchFurnitureException;
}
