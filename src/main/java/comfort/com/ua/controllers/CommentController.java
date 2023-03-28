package comfort.com.ua.controllers;

import comfort.com.ua.models.Comment;
import comfort.com.ua.repos.CommentRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentRepo commentRepo;

    @GetMapping
    public String getPage(Model model)
    {
        model.addAttribute("comments", commentRepo.findAll());
        model.addAttribute("comment", new Comment());
        return "comments";
    }


    @PostMapping
    public String postComment(@ModelAttribute @Valid Comment comment, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors())
        {
            return "comments";
        }
        comment.setDate(LocalDate.now());
        commentRepo.save(comment);
        return "redirect:/";
    }
}
