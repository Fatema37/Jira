package com.Jira.Jira.repositories;

import com.Jira.Jira.models.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Optional;

@Repository
public class UserRepo {
    HashMap<String, User> userHashMap = new HashMap<>();
    Long id = 0L;

    public User save(User user){
        if(user.getId()==null){
        id++;
        user.setId(id.toString());
        }
        userHashMap.put(user.getId(), user);
        return user;
    }

    public Optional<User> findById(String id){
        if(userHashMap.containsKey(id)){
            return Optional.of(userHashMap.get(id));
        }
        else {
            return Optional.empty();
        }
    }
    public  Optional<User> findByEmail(String email){
        for(User user : userHashMap.values()){
            if(user.getEmail().equals(email)){
                return Optional.of(user);
            }
        }
        return Optional.empty();
    }

}
