package comfort.com.ua.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class NoSuchFurnitureExceptionHandler {

    @ExceptionHandler({NoSuchFurnitureException.class})
    public ModelAndView handler(NoSuchFurnitureException e)
    {
        Map<String, String> model = new HashMap<>();
        model.put("mes", e.getMessage());
        ModelAndView modelAndView = new ModelAndView("error", model, HttpStatus.NOT_FOUND);
        return modelAndView;
    }

}
