package comfort.com.ua.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/furniture")
public class FurnitureController {

    @GetMapping("/pulling")
    public String pullingPage()
    {
        return "furniture-pulling";
    }

    @GetMapping("/soft-to-order")
    public String softToOrderPage()
    {
        return "furniture-to-order";
    }

    @GetMapping("/case-furniture")
    public String casePage()
    {
        return "case-furniture";
    }

    @GetMapping("/dressing-rooms")
    public String dressingPage()
    {
        return "dressing-rooms";
    }

    @GetMapping("/for-business")
    public String forBusinessPage()
    {
        return "furniture-for-business";
    }

    @GetMapping("/mattresses")
    public String mattressesPage()
    {
        return "mattresses";
    }

}
