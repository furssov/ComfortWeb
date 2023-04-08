package comfort.com.ua.controllers;

import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.models.Furniture;
import comfort.com.ua.models.Order;
import comfort.com.ua.models.Priority;
import comfort.com.ua.services.FurnitureService;
import comfort.com.ua.services.OrderService;


import jakarta.validation.Valid;
import lombok.EqualsAndHashCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/furniture")
@EqualsAndHashCode
public class FurnitureController {

    @Autowired
    private FurnitureService furnitureService;


    @Autowired
    private OrderService orderService;

    @GetMapping("/{furnitureType}")
    public String getAllFurniture(@PathVariable long furnitureType, Model model) throws NoSuchFurnitureException {
        List<Furniture> furnitures = furnitureService.findByFurnitureTypeOfOrderId(furnitureType);
           model.addAttribute("furniture", furnitures);
           return "furnitures";
    }

    @GetMapping("/f/{id}")
    public String getFurniture( @PathVariable long id, Model model) throws NoSuchFurnitureException {
           model.addAttribute("furniture", furnitureService.findById(id).orElseThrow());
           model.addAttribute("order", new Order());
           return "furniture";
    }

    @PostMapping("/f/{id}")
        public String postOrder(@PathVariable long id, @ModelAttribute Furniture furniture, @ModelAttribute("order") @Valid Order order, BindingResult bindingResult) throws NoSuchFurnitureException {

        if (bindingResult.hasErrors())
        {
            return "furniture";
        }

        order.setDateOfOrder(LocalDate.now());
        order.setFurnitureId(furnitureService.findById(id).orElseThrow());

        orderService.save(order);

        return "orderOk";


    }



}
