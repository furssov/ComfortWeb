package comfort.com.ua.controllers;

import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.models.FurnitureTypeOfOrder;
import comfort.com.ua.services.FurnitureTypeOfOrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(FurnitureTypeOfOrderController.class)
class FurnitureTypeOfOrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FurnitureTypeOfOrderService furnitureTypeOfOrderService;

    @Test
    void pullingPage() throws Exception {
        Mockito.when(furnitureTypeOfOrderService.findByOrderId(1l)).thenThrow(new NoSuchFurnitureException("no"));
        Mockito.when(furnitureTypeOfOrderService.findByOrderId(2l)).thenReturn(List.of(new FurnitureTypeOfOrder()));
        mockMvc
                .perform(get("/types/1"))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(view().name("orderOk"))
                .andExpect(model().attributeExists("mes"));

        mockMvc.perform(get("/types/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("furniture-types-of-order"))
                .andExpect(model().attribute("furnitureOrders", furnitureTypeOfOrderService.findByOrderId(2l)));
    }
}