package comfort.com.ua.unit.services;


import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.models.TypeOfOrder;
import comfort.com.ua.repos.TypeOfOrderRepository;
import comfort.com.ua.services.TypeOfOrderService;
import comfort.com.ua.services.impl.TypeOfOrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class TypeOfOrderServiceTest {

    @TestConfiguration
    static class TypeOfOrderServiceBean{
        @Bean
        public TypeOfOrderService get(){
            return new TypeOfOrderServiceImpl();
        }
    }

    @Autowired
    private TypeOfOrderService typeOfOrderService;

    @MockBean
    private TypeOfOrderRepository repository;


    @Test
    public void saveEntity(){
        TypeOfOrder type;
        for (int i = 0; i < 100; i++) {
            type = new TypeOfOrder();
            Mockito.when(repository.save(type)).thenReturn(type);
            Assertions.assertEquals(type, typeOfOrderService.save(type));
        }
    }

    @Test
    public void getAllEntities(){
        Mockito.when(repository.findAll()).thenReturn(List.of(new TypeOfOrder(), new TypeOfOrder(), new TypeOfOrder()));
        Assertions.assertEquals(List.of(new TypeOfOrder(), new TypeOfOrder(), new TypeOfOrder()), typeOfOrderService.getAll());
    }

    @Test
    public void getEntityById() throws NoSuchFurnitureException {
        List<TypeOfOrder> types = new ArrayList<>();
        types.add(new TypeOfOrder(1L, "TYPE1", null, "img.jpg"));
        types.add(new TypeOfOrder(32L, "TYPE1", null, "img.jpg"));
        types.add(new TypeOfOrder(55L, "TYPE1", null, "img.jpg"));

        Mockito.when(repository.findById(1L)).thenReturn(Optional.ofNullable(types.get(0)));
        Mockito.when(repository.findById(32L)).thenReturn(Optional.ofNullable(types.get(1)));
        Mockito.when(repository.findById(55L)).thenReturn(Optional.ofNullable(types.get(2)));
        Mockito.when(repository.findById(66L)).thenReturn(Optional.empty());

        Assertions.assertEquals(types.get(0), typeOfOrderService.getById(1L));
        Assertions.assertEquals(types.get(1), typeOfOrderService.getById(32L));
        Assertions.assertEquals(types.get(2), typeOfOrderService.getById(55L));


        NoSuchFurnitureException e = Assertions.assertThrows(NoSuchFurnitureException.class, () ->{
            typeOfOrderService.getById(66l);
        });
        Assertions.assertNotNull(e);
        Assertions.assertEquals("no such type", e.getMessage());
    }
}
