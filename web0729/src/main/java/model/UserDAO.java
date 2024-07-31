package model;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;

public class UserDAO {

    private static List <User> users = new ArrayList <User> ();
    static{
         users.add(new User(100L, "Amy", "amy@gmail.com"));
         users.add(new User(101L, "Mary", "mary@gmail.com"));
         users.add(new User(102L, "Tom", "tom@gmail.com"));

    }
    public UserDAO() {
    	
    }
    public List <User> findAll() {
       
        return users;
    }

    public User fetchBy(Long id)  {
        for (User user: findAll()) {
            if (id.equals(user.getId())) {
                return user;
            } 
        }
        return null;
    }

    public boolean create(User user) {
        return users.add(user);
    }

    public boolean update(long id,User user) {
        for (User updateUser: users) {
            if (updateUser.getId().equals(id)) {
                users.remove(updateUser);
                users.add(user);
                return true;
            }
        }
        return false;
    }

    public boolean delete(Long id) {
        for (User user: users) {
            if (user.getId().equals(id)) {
                users.remove(user);
                return true;
            }
        }
        return false;
    }
}


