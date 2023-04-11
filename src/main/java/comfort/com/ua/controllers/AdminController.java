package comfort.com.ua.controllers;

import comfort.com.ua.models.Furniture;
import comfort.com.ua.services.FurnitureService;
import comfort.com.ua.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private FurnitureService furnitureService;

    @GetMapping
    public String adminPage()
    {
        return "admin_opportunities";
    }

    @GetMapping("/orders")
    public String getOrders(Model model)
    {
        model.addAttribute("orders", orderService.findAll());
        return "orders";
    }

    @GetMapping("/furniture")
    public String getFurnitureForm(Model model)
    {
        model.addAttribute("furniture", new Furniture());
        return "furnitureForm";
    }

    @PostMapping("/furniture")
    public String postFurniture(@ModelAttribute Furniture furniture)
    {
        furnitureService.save(furniture);
        return "redirect: /admin";
    }
}
