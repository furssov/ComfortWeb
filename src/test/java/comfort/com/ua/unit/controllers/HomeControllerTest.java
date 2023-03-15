package comfort.com.ua.unit.controllers;

import comfort.com.ua.controllers.HomeController;
import comfort.com.ua.models.Furniture;
import comfort.com.ua.models.FurnitureType;
import comfort.com.ua.models.ImageDB;
import comfort.com.ua.models.TypeOfOrder;
import comfort.com.ua.repos.TypeOfOrderRepository;
import comfort.com.ua.services.FurnitureService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mock;

    @MockBean
    private TypeOfOrderRepository typeOfOrderRepository;

    @MockBean
    private FurnitureService furnitureService;

    @Test
    void homePage() throws Exception {
        Mockito.when(typeOfOrderRepository.findAll()).thenReturn(List.of(new TypeOfOrder()));
        mock.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("home"))
                .andExpect(model().attribute("types", typeOfOrderRepository.findAll()));
    }

    @Test
    void getGallery() throws Exception {
        Furniture f = new Furniture();
        f.setImages(List.of(new ImageDB()));
        f.setType(FurnitureType.BED);
        Furniture f1 = new Furniture();
        f1.setImages(List.of(new ImageDB()));
        f1.setType(FurnitureType.CHAIR);
        Mockito.when(furnitureService.findAllGallery()).thenReturn(List.of(f, f1));
    mock.perform(get("/gallery"))
            .andExpect(status().isOk())
            .andExpect(view().name("gallery"))
            .andExpect(model().attribute("furniture", furnitureService.findAllGallery()));
    }
}