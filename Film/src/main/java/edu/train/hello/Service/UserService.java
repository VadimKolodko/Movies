package edu.train.hello.Service;

import static edu.train.hello.Requests.*;

import edu.train.hello.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.hibernate.query.Query;
import javax.transaction.Transactional;
import java.util.List;

@Service("userService")
@Transactional
public class UserService {

    @Autowired
    private SessionFactory sessionFactory;

    public List<User> getAll(){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hqlGetAllUsers);
        return query.list();
    }

    public User get(Integer userId){
       Session session = sessionFactory.getCurrentSession();
       Query query = session.createQuery(hqlGetUser + userId);
       return (User)query.uniqueResult();
    }

    public User get(String userName){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("FROM User as i WHERE i.userName = :name");
        query.setParameter("name", userName);
        if(query.uniqueResult() == null)
            return null;
        return (User)query.uniqueResult();
    }

    public void add(User user){
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    public void delete(Integer userId){
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery(hqlGetUser + userId);
        session.delete(query.uniqueResult());
    }

    public void edit(User user){
        Session session = sessionFactory.getCurrentSession();
        User existingUser = (User)session.get(User.class, user.getUserId());
        existingUser.setUserName(user.getUserName());
        existingUser.setUserRole(user.getUserRole());
        session.save(existingUser);
    }
}
