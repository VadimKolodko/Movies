package edu.train.hello.domain;

import javax.persistence.*;

@Entity
@Table(name = "Rates")
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rateId")
    private Integer rateId;

    @Column(name = "rateFilmName")
    private String rateFilmName;

    @Column(name = "rateUserLogin")
    private String rateUserLogin;

    @Column(name = "rateRateFilm")
    private String rateRateFilm;

    public Rate()
    {

    }

    public Rate(String rateFilmName, String rateUserLogin, String rateRateFilm)
    {
        this.rateFilmName = rateFilmName;
        this.rateUserLogin = rateUserLogin;
        this.rateRateFilm = rateRateFilm;
    }

    public Integer getRateId() {
        return rateId;
    }

    public String getRateFilmName() {
        return rateFilmName;
    }

    public String getRateRateFilm() {
        return rateRateFilm;
    }

    public String getRateUserLogin() {
        return rateUserLogin;
    }

    public void setRateId(Integer rateId) {
        this.rateId = rateId;
    }

    public void setRateFilmName(String rateFilmName) {
        this.rateFilmName = rateFilmName;
    }

    public void setRateUserLogin(String rateUserLogin) {
        this.rateUserLogin = rateUserLogin;
    }

    public void setRateRateFilm(String rateRateFilm) {
        this.rateRateFilm = rateRateFilm;
    }
}
