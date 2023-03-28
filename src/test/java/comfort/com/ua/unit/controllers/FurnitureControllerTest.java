package comfort.com.ua.unit.controllers;

import comfort.com.ua.controllers.FurnitureController;
import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.models.Furniture;
import comfort.com.ua.models.FurnitureType;
import comfort.com.ua.models.ImageDB;
import comfort.com.ua.models.Order;
import comfort.com.ua.services.FurnitureService;
import comfort.com.ua.services.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class FurnitureControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FurnitureService furnitureService;

    @MockBean
    private OrderService orderService;

    @Test
    void getAllFurnitures() throws Exception {
        Furniture f = new Furniture();
        f.setImages(List.of(new ImageDB()));
        f.setType(FurnitureType.BED);
        Mockito.when(furnitureService.findByFurnitureTypeOfOrderId(1l)).thenReturn(List.of(f));
        mockMvc.perform(get("/furniture/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("furnitures"))
                .andExpect(model().attribute("furniture", furnitureService.findByFurnitureTypeOfOrderId(1l)));
        Mockito.when(furnitureService.findByFurnitureTypeOfOrderId(2l)).thenThrow(new NoSuchFurnitureException("no"));
        mockMvc.perform(get("/furniture/2"))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(view().name("error"))
                .andExpect(model().attribute("mes", "no"));
    }

    @Test
    void getConcreteFurniture()
    {
        Furniture f = new Furniture();
        f.setImages(List.of(new ImageDB()));
        f.setType(FurnitureType.BED);
        try {
            Mockito.when(furnitureService.findById(1l)).thenReturn(Optional.of(f));
            mockMvc
                    .perform(get("/furniture/f/1"))
                    .andExpect(status().isOk())
                    .andExpect(view().name("furniture"))
                    .andExpect(model().attribute("furniture", furnitureService.findById(1l).get()))
                    .andExpect(model().attribute("order", new Order()));
            Mockito.when(furnitureService.findById(2l)).thenThrow(new NoSuchFurnitureException("mes"));
            mockMvc
                    .perform(get("/furniture/f/2"))
                    .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                    .andExpect(view().name("error"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }






 ;

}