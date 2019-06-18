package edu.train.hello.Service;

import static edu.train.hello.Requests.*;
import edu.train.hello.domain.Film;

import edu.train.hello.domain.Rate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service("filmService")
@Transactional
public class FilmService{

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private RateService rateService;

    public FilmService()
    {
    }

    //Get all Films
    public List<Film> getAll() {
        Session session = sessionFactory.getCurrentSession();
        Query getQuery = session.createQuery(GetAllFilms);
        return getQuery.list();
    }

    public List<Film> getAllBySortAndFilter(String filter, String Sort, String Rat, String UserLogin) {
        Session session = sessionFactory.getCurrentSession();
        Query getQuery;
        List<Film> films = new ArrayList<Film>();
        List<Film> filmsOut = new ArrayList<Film>();
        List<Rate> rates = new ArrayList<Rate>();;

        if(Sort.equals("Date increase") || Sort.equals("Date descending")) {
            if (Sort.equals("Date descending")) {
                getQuery = session.createQuery("FROM Film as f WHERE f.genreFilm = :filter ORDER BY f.fDate DESC");
                getQuery.setParameter("filter", filter);
                films = getQuery.list();
            }
            if(!Sort.equals("Date descending")) {
                getQuery = session.createQuery("FROM Film as f WHERE f.genreFilm = :filter ORDER BY f.fDate");
                getQuery.setParameter("filter", filter);
                films = getQuery.list();
            }
        }

        if(Sort.equals("Average score increase") || Sort.equals("Average score decrease")) {
            if (Sort.equals("Average score decrease")) {
                getQuery = session.createQuery("FROM Film as f WHERE f.genreFilm = :filter ORDER BY f.averageRate DESC");
                getQuery.setParameter("filter", filter);
                films =  getQuery.list();
            }
            if(!Sort.equals("Average score decrease")) {
                getQuery = session.createQuery("FROM Film as f WHERE f.genreFilm = :filter ORDER BY f.averageRate");
                getQuery.setParameter("filter", filter);
                films = getQuery.list();
            }
        }

        getQuery = session.createQuery("FROM Rate as r WHERE r.rateUserLogin = :login");
        getQuery.setParameter("login", UserLogin);
        rates = getQuery.list();

        if(Rat.equals("Rated by me"))
        {
            for (Film film: films) {
                for (Rate rate: rates) {
                    if(film.getName().equals(rate.getRateFilmName()))
                    {
                        filmsOut.add(new Film(film.getId(), film.getName(), film.getfDate(), film.getImageId(), film.getGenreFilm(), film.getAboutFilm(), film.getAverageRate()));
                    }
                }
            }
        }

        if(Rat.equals("Not rated by me"))
        {
            if(rates.size() == 0)
            {
                return films;
            }

            for (Film film: films) {
                for (Rate rate: rates) {
                    if(!film.getName().equals(rate.getRateFilmName()))
                    {
                        filmsOut.add(new Film(film.getId(), film.getName(), film.getfDate(), film.getImageId(), film.getGenreFilm(), film.getAboutFilm(), film.getAverageRate()));
                        break;
                    }
                }
            }
        }
        return filmsOut;
    }

    //Get film by id
    public Film get(Integer Id){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(GetFilmtoID + Id);
        return (Film) query.uniqueResult();
    }

    public Film get(String Name){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM Film as f WHERE f.Name = :name");
        query.setParameter("name", Name);
        return (Film) query.uniqueResult();
    }

    //Add film to list
    public void add(Film film){
        Session session = sessionFactory.getCurrentSession();
        session.save(film);
    }

    //Delete film by id
    public void delete(Integer filmId){
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Film film = session.get(Film.class, filmId);
            session.delete(film);
        }
        catch (Exception  e)
        {
            //logger.fine("Error: delete()");
        }
    }

    //Edit film by id
    public void edit(Film film){
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Film existingFilm = (Film)session.get(Film.class, film.getId());
            existingFilm.setId(film.getId());
            existingFilm.setName(film.getName());
            existingFilm.setfDate(film.getfDate());
            existingFilm.setGenreFilm(film.getGenreFilm());
            existingFilm.setImageId(film.getImageId());
            existingFilm.setAverageRate(film.getAverageRate());
            session.update(existingFilm);
        }
        catch (Exception e)
        {
            //logger.fine("Error: edit()");
        }
    }

    public void editAverageRate(Film film, String averageRate){
        try
        {
            Session session = sessionFactory.getCurrentSession();
            Film existingFilm = (Film)session.get(Film.class, film.getId());
            existingFilm.setId(film.getId());
            existingFilm.setName(film.getName());
            existingFilm.setfDate(film.getfDate());
            existingFilm.setGenreFilm(film.getGenreFilm());
            existingFilm.setImageId(film.getImageId());
            existingFilm.setAverageRate(averageRate);
            session.update(existingFilm);
        }
        catch (Exception e)
        {
            //logger.fine("Error: edit()");
        }
    }
}
