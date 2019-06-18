package edu.train.hello.controller;

import edu.train.hello.Service.FilmService;
import edu.train.hello.Service.RateService;
import edu.train.hello.Service.UserService;
import edu.train.hello.domain.Film;

import edu.train.hello.domain.SearchSettings;
import edu.train.hello.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@SessionAttributes("film")
@RequestMapping("/film")
public class FilmController {

    private String UserLogin = "";

    @Autowired
    FilmService filmService;

    @Autowired
    RateService rateService;

    @Autowired
    UserService userService;

    @GetMapping("/film")
    @RequestMapping(method = RequestMethod.GET)
    public String getFilm(Model model){
        model.addAttribute("allFilm", filmService.getAll());
        return "mainLoginUser";
    }

    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String getFilmInfo(@RequestParam("id") Integer filmId, @SessionAttribute("SessionUser") User user, Model model){
        Film existFilm = filmService.get(filmId);
        model.addAttribute("filmInfo", existFilm);
        model.addAttribute("SessionUser", user);
        model.addAttribute("rateInfoFilm", rateService.getFilmRatebyName(existFilm.getName(), user.getUserName()));
        return "filmInfo";
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getFilmAdd(@SessionAttribute("SessionUser") User user, Model model){
        if(!user.getUserRole().equals("Admin"))
            return "redirect:/film";
        model.addAttribute("filmInfo", new Film());
        return "filmAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String putFilmAdd(@ModelAttribute("filmInfo") Film film, @SessionAttribute("SessionUser") User user){
        filmService.add(film);
        return "redirect:/film";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteFilmPage(@RequestParam("id") Integer filmId, @SessionAttribute("SessionUser") User user, Model model){
        Film existFilm = filmService.get(filmId);
        model.addAttribute("filmInfo", existFilm);
        model.addAttribute("id", filmId);
        model.addAttribute("delFilm", "Delete");
        return "filmDelete";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteFilm(@RequestParam("id") Integer id, @SessionAttribute("SessionUser") User user){
        filmService.delete(id);
        return "redirect:/film";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam("id") Integer filmId, @SessionAttribute("SessionUser") User user, Model model){
        Film existFilm = filmService.get(filmId);
        model.addAttribute("filmInfo", existFilm);
        return "filmEdit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String putEdit(@ModelAttribute("filmToChange") Film film, @SessionAttribute("SessionUser") User user){
        filmService.edit(film);
        return "redirect:/film";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String getSearch(@SessionAttribute("SessionUser") User user, Model model){
        model.addAttribute("searchSettings", new SearchSettings());
        return "filmSearch";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String getSearchPost(@ModelAttribute("searchSettings") SearchSettings searchSettings, @SessionAttribute("SessionUser") User user, Model model){
        model.addAttribute("allFilm", filmService.getAllBySortAndFilter(searchSettings.getFiltr(), searchSettings.getSort(), searchSettings.getRat(), user.getUserName()));
        model.addAttribute("SessionUser", user);
        return "filmInfoSearch";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getIndex(Model model){
        return "index";
    }
}
