package comfort.com.ua.controllers;

import comfort.com.ua.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private OrderService orderService;

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
}
