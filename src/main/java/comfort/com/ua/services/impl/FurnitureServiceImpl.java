package comfort.com.ua.services.impl;

import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.models.Furniture;
import comfort.com.ua.repos.FurnitureRepo;
import comfort.com.ua.services.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FurnitureServiceImpl implements FurnitureService {

    @Autowired
    private FurnitureRepo furnitureRepo;

    @Override
    public List<Furniture> findByFurnitureTypeOfOrderId(long id) {
        List<Furniture> furniture = furnitureRepo.findAll();
        return furniture.stream().filter(furniture1 -> furniture1.getFurnitureTypeOfOrderId().getId() == id).collect(Collectors.toList());
    }

    @Override
    public Optional<Furniture> findById(long id) throws NoSuchFurnitureException {
        Optional<Furniture> furniture = furnitureRepo.findById(id);
        if (furniture.isPresent()) return furniture;
        else throw new NoSuchFurnitureException("there is no such furniture");
    }
}
