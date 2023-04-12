package comfort.com.ua.services.impl;

import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.models.Furniture;
import comfort.com.ua.models.FurnitureType;
import comfort.com.ua.models.Priority;
import comfort.com.ua.repos.FurnitureRepo;
import comfort.com.ua.services.FurnitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FurnitureServiceImpl implements FurnitureService {

    @Autowired
    private FurnitureRepo furnitureRepo;

    @Override
    public List<Furniture> findByFurnitureTypeOfOrderId(long id) throws NoSuchFurnitureException {
        List<Furniture> furniture = furnitureRepo.findAll()
                .stream().filter(furniture1 -> furniture1.getFurnitureTypeOfOrderId().getId() == id).collect(Collectors.toList());
        if (!furniture.isEmpty()) {
            sortByPriority(furniture);
            return furniture;
        }
        else
            throw new NoSuchFurnitureException("Пробачте, тимчасова ця категорія недоступна!");
    }


    @Override
    public List<Furniture> findAllGallery() {
        List<Furniture> furnitures = furnitureRepo.findAll();
        return furnitures.stream().filter(furniture -> furniture.getType() != null).collect(Collectors.toList());
    }

    @Override
    public Optional<Furniture> findById(long id) throws NoSuchFurnitureException {
        Optional<Furniture> furniture = furnitureRepo.findById(id);
        if (furniture.isPresent()) return furniture;
        else throw new NoSuchFurnitureException("there is no such furniture");
    }

    @Override
    public void delete(Long id) {
        furnitureRepo.deleteById(id);
    }

    @Override
    public List<Furniture> findAll() {
        List<Furniture> furnitures  = furnitureRepo.findAll();
        furnitures.sort(new Comparator<Furniture>() {
            @Override
            public int compare(Furniture o1, Furniture o2) {
                if(o1.getId()>o2.getId()){
                    return 1;
                }
                else if (o2.getId()== o1.getId()){
                    return 0;
                }
                else {
                    return -1;
                }
            }
        });
        return furnitures;
    }

    @Override
    public Furniture save(Furniture furniture) {
        return furnitureRepo.save(furniture);
    }


    private List<Furniture> sortByPriority(List<Furniture> furnitures) {
        furnitures.sort(new Comparator<Furniture>() {
            @Override
            public int compare(Furniture o1, Furniture o2) {
                if (o1.getPriority() != null && o1.getPriority().equals(Priority.HIGH))
                {
                    return -1;
                }
                else return 1;
            }
        });
        return furnitures;
    }


}
