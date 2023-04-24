package comfort.com.ua.services.impl;

import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.models.TypeOfOrder;
import comfort.com.ua.repos.TypeOfOrderRepository;
import comfort.com.ua.services.TypeOfOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TypeOfOrderServiceImpl implements TypeOfOrderService {

    @Autowired
    private TypeOfOrderRepository repository;

    @Override
    public TypeOfOrder save(TypeOfOrder typeOfOrder) {
        return save(typeOfOrder);
    }

    @Override
    public TypeOfOrder change(Long id) throws NoSuchFurnitureException {
        Optional<TypeOfOrder> typeOfOrder = repository.findById(id);
        if (typeOfOrder.isPresent()){
            return typeOfOrder.get();
        }
        else {
            throw new NoSuchFurnitureException("no such type of order");
        }

    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public List<TypeOfOrder> getAll() {
        return repository.findAll();
    }

    @Override
    public TypeOfOrder getById(Long id) throws NoSuchFurnitureException {
        Optional<TypeOfOrder> typeOfOrder = repository.findById(id);
        if (typeOfOrder.isPresent()){
            return typeOfOrder.get();
        }
        else{
            throw new NoSuchFurnitureException("no such type");
        }
    }
}
