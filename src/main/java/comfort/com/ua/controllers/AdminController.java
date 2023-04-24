package comfort.com.ua.controllers;

import comfort.com.ua.exceptions.NoSuchFurnitureException;
import comfort.com.ua.models.Furniture;
import comfort.com.ua.models.ImageDB;
import comfort.com.ua.repos.ImageRepo;
import comfort.com.ua.services.FurnitureService;
import comfort.com.ua.services.OrderService;
import comfort.com.ua.services.TypeOfOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private FurnitureService furnitureService;

    @Autowired
    private ImageRepo imageRepo;

    @Autowired
    private TypeOfOrderService typeOfOrderService;

    @Value("${upload.path}")
    private String uploadPath;
    @GetMapping
    public String adminPage()
    {
        return "admin_opportunities";
    }

    @GetMapping("/types")
    public String getTypeOfOrders(Model model){
        model.addAttribute("types",typeOfOrderService.getAll());
        return "admin-types";
    }

    @GetMapping("/types/{id}")
    public String getConcreteTypeOfOrder(@PathVariable Long id, Model model) throws NoSuchFurnitureException {
        model.addAttribute("type", typeOfOrderService.getById(id));
        return "admin-type-id";
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
    public String postFurniture(@RequestParam("file") MultipartFile file, @ModelAttribute Furniture furniture) throws IOException {
        furnitureService.save(furniture);
        if (file != null)
        {
            UUID uuid = UUID.randomUUID();
            String img = "furniture" + uuid + ".jpg";
            File dir = new File(uploadPath + img);
            if (dir.createNewFile()) {
                file.transferTo(dir);
                ImageDB imageDB = new ImageDB();

                imageDB.setPath("/images/furnitures/" + img);
                imageDB.setFurniture(furniture);
                imageRepo.save(imageDB);
            }
            else {
                return "furnitureForm";
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
        try {
            List<ImageDB> images = furnitureService.findById(id).get().getImages();
            String path = "/home/user/projects/idea/ComfortWeb/src/main/resources/static";
            for (ImageDB i : images){
                 path = path + i.getPath();
                File file = new File(path);
                file.delete();
            }
        }catch (NoSuchFurnitureException e){
            System.out.println(e.getMessage());
        }
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
            f.get().setFurnitureTypeOfOrderId(furniture.getFurnitureTypeOfOrderId());
            furnitureService.save(f.get());

        }
        return "redirect:/admin/furniture";
    }
}
