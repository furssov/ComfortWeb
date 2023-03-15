package comfort.com.ua.unit.services;

import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.models.FurnitureTypeOfOrder;
import comfort.com.ua.models.TypeOfOrder;
import comfort.com.ua.repos.FurnitureTypeOfOrderRepository;
import comfort.com.ua.services.FurnitureTypeOfOrderService;
import comfort.com.ua.services.impl.FurnitureTypeOfOrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class FurnitureTypeOfOrderServiceImplTest {


    @TestConfiguration
    static class FurnitureTypeOfOrderServiceTest
    {
        @Bean
        public FurnitureTypeOfOrderService test()
        {
            return new FurnitureTypeOfOrderServiceImpl();
        }
    }

    @Autowired
    private FurnitureTypeOfOrderService service;

    @MockBean
    private FurnitureTypeOfOrderRepository repository;

    @Test()
    void findByOrderId()
    {
        Mockito.when(repository.findAll()).thenReturn(List.of(new FurnitureTypeOfOrder(1L, "a", new TypeOfOrder(2L, "A", Collections.emptyList(), "BB"), Collections.emptyList(),"1.jpg")
        ,new FurnitureTypeOfOrder(12l, "b", new TypeOfOrder(1L, "A", Collections.emptyList(), "aa"), Collections.emptyList(),"12.jpg")));
        try {
            List<FurnitureTypeOfOrder> f = service.findByOrderId(1L);
            NoSuchFurnitureException e = assertThrows(NoSuchFurnitureException.class, () -> {
                service.findByOrderId(3L);
            });
            Assertions.assertEquals("NO SUCH TYPE", e.getMessage());

            Assertions.assertEquals(f, repository.findAll().stream().filter(furnitureTypeOfOrder -> furnitureTypeOfOrder.getOrderId().getId() == 1L).collect(Collectors.toList()));

            f = service.findByOrderId(2L);

            Assertions.assertEquals(f, repository.findAll().stream().filter(furnitureTypeOfOrder -> furnitureTypeOfOrder.getOrderId().getId() == 2L).collect(Collectors.toList()));
        } catch (NoSuchFurnitureException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void findById() {
        FurnitureTypeOfOrder f = new FurnitureTypeOfOrder();
        f.setId(1L);
        FurnitureTypeOfOrder f1 = new FurnitureTypeOfOrder();
        f1.setId(54L);
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(f));
        Mockito.when(repository.findById(54L)).thenReturn(Optional.of(f1));
        Assertions.assertEquals(f, service.findById(1L));
        Assertions.assertEquals(f1, service.findById(54L));

        NoSuchFurnitureException e = assertThrows(NoSuchFurnitureException.class, () ->
        {
           service.findByOrderId(745L);
        });

        Assertions.assertEquals("NO SUCH TYPE", e.getMessage());

    }

}