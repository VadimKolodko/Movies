package edu.train.hello.Service;

import edu.train.hello.domain.Film;
import edu.train.hello.domain.Rate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static edu.train.hello.Requests.GetAllFilms;

@Service("rateService")
@Transactional
public class RateService {

    @Autowired
    private SessionFactory sessionFactory;

    public RateService()
    {

    }

    public Rate getFilmRatebyName(String filmName, String UserName)
    {
        Session session = sessionFactory.getCurrentSession();
        Query getQuery = session.createQuery("FROM Rate as f WHERE f.rateFilmName = :name and f.rateUserLogin = :login");
        getQuery.setParameter("name", filmName);
        getQuery.setParameter("login", UserName);
        if(getQuery.list().size() == 0)
        {
            return null;
        }
        return (Rate)getQuery.uniqueResult();
    }


    public List<Rate> getUserRate(String userLogin)
    {
        Session session = sessionFactory.getCurrentSession();
        Query getQuery = session.createQuery("FROM Rate as f WHERE f.rateUserLogin = :name");
        getQuery.setParameter("name", userLogin);
        return getQuery.list();
    }

    public List<Rate> getFilmRate(String filmName)
    {
        Session session = sessionFactory.getCurrentSession();
        Query getQuery = session.createQuery("FROM Rate as f WHERE f.rateFilmName = :name");
        getQuery.setParameter("name", filmName);
        return getQuery.list();
    }

    public void addRate(Rate rate)
    {
        Session session = sessionFactory.getCurrentSession();
        session.save(rate);
    }

    public void editRate(Rate rate, String rateString)
    {
        Session session = sessionFactory.getCurrentSession();
        Rate existingFilm = (Rate)session.get(Rate.class, rate.getRateId());
        existingFilm.setRateId(rate.getRateId());
        existingFilm.setRateFilmName(rate.getRateFilmName());
        existingFilm.setRateUserLogin(rate.getRateUserLogin());
        existingFilm.setRateRateFilm(rateString);
        session.update(existingFilm);
    }

    public void deleteRate(Rate rate)
    {
        Session session = sessionFactory.getCurrentSession();
        session.delete(rate);
    }
}
