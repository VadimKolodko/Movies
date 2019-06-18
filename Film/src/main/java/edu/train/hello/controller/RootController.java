package edu.train.hello.controller;

import edu.train.hello.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class RootController {
    @RequestMapping(method = RequestMethod.GET)
    public String start(){
        return "redirect:/film";
    }
}
