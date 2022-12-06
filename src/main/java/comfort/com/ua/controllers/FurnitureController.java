package comfort.com.ua.controllers;

import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.services.FurnitureService;
import jakarta.persistence.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/furniture")
public class FurnitureController {

    @Autowired
    private FurnitureService furnitureService;

    @GetMapping("/{furnitureType}")
    public String getAllFurniture(@PathVariable long furnitureType, Model model)
    {
           model.addAttribute("furniture", furnitureService.findByFurnitureTypeOfOrderId(furnitureType));
           return "furnitures";
    }

    @GetMapping("/{furnitureType}/{id}")
    public String getFurniture(@PathVariable long furnitureType, @PathVariable long id, Model model) throws NoSuchFurnitureException {
           model.addAttribute("furniture", furnitureService.findById(id).get());
           return "furniture";
    }

}
