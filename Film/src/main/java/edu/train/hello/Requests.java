package edu.train.hello;

public class Requests {
    public static final String GetAllFilms = "FROM Film f ORDER BY f.fDate";
    public static final String GetFilmtoID = "FROM Film as f WHERE f.id = ";
    public static final String hqlGetAllUsers = "FROM Users";
    public static final String hqlGetUser = "FROM Users as i WHERE i.userId =";
    public static  String hqlGetUserByName = "FROM Users as i WHERE i.userName = ";
}
