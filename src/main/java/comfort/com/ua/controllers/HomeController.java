package comfort.com.ua.controllers;

import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.models.FurnitureType;
import comfort.com.ua.repos.TypeOfOrderRepository;
import comfort.com.ua.services.FurnitureService;
import jakarta.persistence.FetchType;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@Slf4j
public class HomeController {

    @Autowired
    private TypeOfOrderRepository typeOfOrderRepository;

    @Autowired
    private FurnitureService furnitureService;

    @GetMapping("/")
    public String homePage(Model model)
    {
        log.info("Home page has been visited");
        model.addAttribute("types", typeOfOrderRepository.findAll());
        return "home";
    }


    @GetMapping("/gallery")
    public String getGallery(Model model)
    {
        model.addAttribute("furniture", furnitureService.findAllGallery());
        return "gallery";
    }








}
