package edu.train.hello.controller;

import edu.train.hello.Service.RateService;
import edu.train.hello.Service.UserService;
import edu.train.hello.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("SessionUser")
@RequestMapping("/login")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RateService rateService;

    @ModelAttribute("SessionUser")
    public User getUserObject(){
        return new User();
    }

    @RequestMapping(method = RequestMethod.GET)
    public String loginRageGet(@ModelAttribute("SessionUser") User user, Model model){
        if(user.getUserId() != null)
            return "redirect: /film";
        model.addAttribute("fromUser", new User());
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String loginPagePost(@ModelAttribute("formUser") User user, @ModelAttribute("SessionUser") User sessionUser, Model model){
        User tempUser = userService.get(user.getUserName());
        if(tempUser != null)
        {
            model.addAttribute("SessionUser", tempUser);
            model.addAttribute("userRate", rateService.getUserRate(tempUser.getUserName()));
            String qwe = "";
        }
        else
        {
            user.setUserRole("User");
            userService.add(user);
            tempUser = userService.get(user.getUserName());
            model.addAttribute("SessionUser", tempUser);
            model.addAttribute("userRate", rateService.getUserRate(tempUser.getUserName()));
        }
        model.addAttribute("fromUser", null);
        return "redirect:/film";
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public User loginRead(@ModelAttribute("SessionUser") User user, Model model){
        return user;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String loginDelete(@ModelAttribute("SessionUser") User user, Model model){
        model.addAttribute("SessionUser", new User());
        return "redirect:/film";
    }
}
