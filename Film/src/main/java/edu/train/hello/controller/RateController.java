package edu.train.hello.controller;

import edu.train.hello.Service.FilmService;
import edu.train.hello.Service.RateService;
import edu.train.hello.domain.Film;
import edu.train.hello.domain.Rate;
import edu.train.hello.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@SessionAttributes("rate")
@RequestMapping("/rate")
public class RateController {

    @Autowired
    RateService rateService;

    @Autowired
    FilmService filmService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getFilmInfo(@RequestParam("nameFilm") String nameFilm, @RequestParam("nameUser") String userLogin, @RequestParam("rate") String userRate, @SessionAttribute("SessionUser") User user, Model model){
        Rate rate = new Rate(nameFilm, userLogin, userRate);
        rateService.addRate(rate);
        List<Rate> nameFilms = rateService.getFilmRate(nameFilm);
        String averateString = "";
        Integer buff = 0;
        for (Rate rateFilm:nameFilms) {
            buff = buff + Integer.parseInt(rateFilm.getRateRateFilm());
        }
        averateString = Integer.toString(buff / nameFilms.size());
        Film film = filmService.get(nameFilm);
        filmService.editAverageRate(film, averateString);
        return "redirect:/film/info?id=" + film.getId();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String getEditFilmInfo(@RequestParam("nameFilm") String nameFilm, @RequestParam("nameUser") String userLogin, @RequestParam("rate") String userRate, @SessionAttribute("SessionUser") User user, Model model){
        if(!userRate.equals("0")) {
            Rate rate = rateService.getFilmRatebyName(nameFilm, userLogin);
            rateService.editRate(rate, userRate);
            List<Rate> nameFilms = rateService.getFilmRate(nameFilm);
            String averateString = "";
            Integer buff = 0;
            for (Rate rateFilm : nameFilms) {
                buff = buff + Integer.parseInt(rateFilm.getRateRateFilm());
            }
            averateString = Integer.toString(buff / nameFilms.size());
            Film film = filmService.get(nameFilm);
            filmService.editAverageRate(film, averateString);
            return "redirect:/film/info?id=" + film.getId();
        }
        if(userRate.equals("0")) {
            Rate rate = rateService.getFilmRatebyName(nameFilm, userLogin);
            rateService.deleteRate(rate);
            List<Rate> nameFilms = rateService.getFilmRate(nameFilm);
            String averateString = "0";
            Integer buff = 0;

            if(nameFilms.size() != 0) {
                for (Rate rateFilm : nameFilms) {
                    buff = buff + Integer.parseInt(rateFilm.getRateRateFilm());
                }
                averateString = Integer.toString(buff / nameFilms.size());
            }

            Film film = filmService.get(nameFilm);
            filmService.editAverageRate(film, averateString);
            return "redirect:/film/info?id=" + film.getId();
        }
        return "redirect:/film";
    }
}
