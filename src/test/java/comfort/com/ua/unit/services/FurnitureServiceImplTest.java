package comfort.com.ua.unit.services;

import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.models.Furniture;
import comfort.com.ua.models.FurnitureType;
import comfort.com.ua.models.FurnitureTypeOfOrder;
import comfort.com.ua.models.Priority;
import comfort.com.ua.repos.FurnitureRepo;
import comfort.com.ua.services.FurnitureService;
import comfort.com.ua.services.impl.FurnitureServiceImpl;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith({SpringExtension.class})
class FurnitureServiceImplTest {

    @TestConfiguration
    static class FurnitureBeanTest
    {
        @Bean
        public FurnitureService furnitureService(){
            return new FurnitureServiceImpl();
        }
    }

    @Autowired
    private FurnitureService service;

    @MockBean
    private FurnitureRepo repository;

    @Test
    void findByFurnitureTypeOfOrderId() {
        FurnitureTypeOfOrder f = new FurnitureTypeOfOrder();
        f.setId(1L);
        FurnitureTypeOfOrder f1 = new FurnitureTypeOfOrder();
        f1.setId(2L);
        Furniture furniture1 = new Furniture(1L,"A","bbb","dfsdf",
                f, Collections.emptyList(), FurnitureType.BED, Collections.emptyList(), Priority.LOW);
        Furniture furniture2 = new Furniture(2L,"A","bbb","dfsdf",
                f1, Collections.emptyList(), FurnitureType.BED, Collections.emptyList(), Priority.LOW);
        Furniture furniture3 =   new Furniture(1L,"A","bbb","dfsdf",
                f, Collections.emptyList(), FurnitureType.CHAIR, Collections.emptyList(), Priority.LOW);

        Mockito.when(repository.findAll()).thenReturn(List.of(furniture1, furniture2,
              furniture3));

        try {
            assertEquals(List.of(furniture1, furniture3), service.findByFurnitureTypeOfOrderId(1L));
        } catch (NoSuchFurnitureException e) {
            throw new RuntimeException(e);
        }

        NoSuchFurnitureException e = assertThrows(NoSuchFurnitureException.class, () ->
        {
            service.findById(54L);
        });
        assertEquals("there is no such furniture", e.getMessage());


    }

    @Test
    void findAllGallery() {
        Furniture f1 = new Furniture();
        f1.setType(FurnitureType.CHAIR);
        Furniture f2 = new Furniture();
        f2.setType(FurnitureType.BED);
        Furniture f3 = new Furniture();
        Mockito.when(repository.findAll()).thenReturn(List.of(f1, f2, f3));

        assertEquals(List.of(f1, f2), service.findAllGallery());

    }

    @Test
    void findById() {
        Furniture f1 = new Furniture();
        f1.setId(1L);
        Furniture f2 = new Furniture();
        f2.setId(2L);
        Furniture f3 = new Furniture();
        Mockito.when(repository.findById(1L)).thenReturn(Optional.of(f1));
        Mockito.when(repository.findById(2L)).thenReturn(Optional.of(f2));

        try {
            assertEquals(Optional.of(f1), service.findById(1L));
            assertEquals(Optional.of(f2), service.findById(2L));

            NoSuchFurnitureException e = assertThrows(NoSuchFurnitureException.class, () ->
            {
                service.findById(3L);
            });
            assertEquals("there is no such furniture", e.getMessage());
        } catch (NoSuchFurnitureException e) {
            throw new RuntimeException(e);
        }
    }
}