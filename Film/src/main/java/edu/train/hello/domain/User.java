package edu.train.hello.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Integer userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userRole")
    private String userRole;

    public User()
    {

    }

    public User(String userName, String userRole){
        this.userName = userName;
        this.userRole = userRole;
    }

    //Get
    public String getUserName(){
        return this.userName;
    }

    public Integer getUserId(){
        return userId;
    }

    public String getUserRole(){
        return userRole;
    }

    //Set
    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserRole(String userRole){
        this.userRole = userRole;
    }
}
