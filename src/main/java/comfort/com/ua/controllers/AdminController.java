package comfort.com.ua.controllers;

import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.models.Furniture;
import comfort.com.ua.models.ImageDB;
import comfort.com.ua.repos.ImageRepo;
import comfort.com.ua.services.FurnitureService;
import comfort.com.ua.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private FurnitureService furnitureService;

    @Autowired
    private ImageRepo imageRepo;

    @Value("${upload.path}")
    private String uploadPath;
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

    @GetMapping("/furniture/create")
    public String getFurnitureForm(Model model) {
        model.addAttribute("furniture", new Furniture());
        return "furnitureForm";
    }

    @PostMapping("/furniture/create")
    public String postFurniture(@RequestParam("file") MultipartFile file, @ModelAttribute Furniture furniture) {
        furnitureService.save(furniture);
        if (file != null)
        {
            File dir = new File(uploadPath);
            if (dir.exists()) {

                ImageDB imageDB = new ImageDB();

                imageDB.setPath("/images/furnitures/"  + file.getOriginalFilename());
                imageDB.setFurniture(furniture);
                imageRepo.save(imageDB);
            }
            else {
                dir.mkdir();
            }
        }
        return "redirect:/admin/furniture";
    }

    @GetMapping("/furniture")
    public String furnitureList(Model model) {
        model.addAttribute("furnitures",furnitureService.findAll());
        return "admin_furniture";
    }

    @GetMapping("/furniture/{id}")
    public String getFurniture(@PathVariable long id, Model model) throws NoSuchFurnitureException {
        model.addAttribute("furniture", furnitureService.findById(id).get());
        return "admin_furniture_id";
    }

    @DeleteMapping("/furniture/{id}")
    public String deleteFurniture(@PathVariable("id") long id)
    {
        furnitureService.delete(id);
        return "redirect:/admin/furniture";
    }

    @PatchMapping("/furniture/{id}")
    public String changeFurniture(@PathVariable long id, @ModelAttribute Furniture furniture) throws NoSuchFurnitureException {
        Optional<Furniture> f = furnitureService.findById(id);
        if (f.isPresent())
        {
            f.get().setName(furniture.getName());
            f.get().setDescription(furniture.getDescription());
            f.get().setType(furniture.getType());
            f.get().setPrice(furniture.getPrice());
            f.get().setPriority(furniture.getPriority());
            furnitureService.save(f.get());

        }
        return "redirect:/admin/furniture";
    }
}
