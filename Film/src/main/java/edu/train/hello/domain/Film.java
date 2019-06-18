package edu.train.hello.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Film")
public class Film implements Serializable {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="Id")
    private  Integer id;

    @Column(name="fName")
    private String Name;

    @Temporal(TemporalType.DATE)
    @Column(name="fDate")
    private Date fDate;

    @Column(name="Image")
    private String imageId;

    @Column(name="Genre")
    private String genreFilm;

    @Column(name="About")
    private String aboutFilm;

    @Column(name="averageRate")
    private String averageRate;

    public Film()
    {

    }

    public Film(Integer Id,String Name, Date fDate, String imageId, String GenreFilm, String aboutFilm, String averageRate)
    {
        this.id = Id;
        this.Name = Name;
        this.fDate = fDate;
        this.imageId = imageId;
        this.genreFilm = GenreFilm;
        this.aboutFilm = aboutFilm;
        this.averageRate = averageRate;
    }

    //Get
    public Integer getId()
    {
        return id;
    }

    public String getName()
    {
        return Name;
    }

    public Date getfDate()
    {
        return fDate;
    }

    public String getImageId()
    {
        return imageId;
    }

    public String getGenreFilm()
    {
        return genreFilm;
    }

    public String getAboutFilm() {
        return aboutFilm;
    }

    public String getAverageRate() {
        return averageRate;
    }

    //Set
    public void setId(Integer id)
    {
        this.id = id;
    }

    public void setName(String Name)
    {
        this.Name = Name;
    }

    public void setfDate(Date fDate)
    {
        this.fDate = fDate;
    }

    public void setImageId(String imageId)
    {
        this.imageId = imageId;
    }

    public void setGenreFilm(String GenreFilm)
    {
        this.genreFilm = GenreFilm;
    }

    public void setAboutFilm(String aboutFilm) {
        this.aboutFilm = aboutFilm;
    }

    public void setAverageRate(String averageRate) {
        this.averageRate = averageRate;
    }
}
