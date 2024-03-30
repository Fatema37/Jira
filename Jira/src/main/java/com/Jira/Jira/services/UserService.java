package com.Jira.Jira.services;

import com.Jira.Jira.models.RoleType;
import com.Jira.Jira.models.Ticket;
import com.Jira.Jira.models.User;
import com.Jira.Jira.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User registerUser(String email , String password, String name, RoleType role){
        Optional<User> user1 = userRepo.findByEmail(email);
        if(user1.isPresent()){
            throw  new RuntimeException("User already exists");
        }
        User user2 = new User();
        user2.setEmail(email);
        user2.setPassword(password);
        user2.setName(name);
        user2.setRole(role);
        userRepo.save(user2);
        System.out.println(user2);
        return user2;
    }


    public  User loginUser(String email, String password) {
        Optional<User> userOptional = userRepo.findByEmail(email);
        if (userOptional.isEmpty()) {
            throw new RuntimeException("User does not exist");
        }
        User user = userOptional.get();
        if (user.getPassword().equals(password)) {
            System.out.println("User is logged in");
            return user;

        } else {
            throw new RuntimeException("Invalid Password");
        }

    }

    public User getUser(String email){
        Optional<User> user = userRepo.findByEmail(email)   ;
        if(user.isEmpty()) {
            throw new RuntimeException("User does not exist");
        }
        return user.get();
    }

    public List<Ticket> getTickets(String userId){
        Optional<User> user = userRepo.findById(userId);
        if(user.isEmpty()) {
            throw new RuntimeException("User does not exist");
        }
        if(loginUser(user.get().getEmail(), user.get().getPassword()) == null){
            throw new RuntimeException("User not logged in");
        }

        List<Ticket> tickets = user.get().getTickets();
        System.out.println("user tickets - "+tickets);
        return tickets;
    }

    public User assignTicket(String userId, Ticket ticket){
        Optional<User> user = userRepo.findById(userId);
        if(user.isEmpty()) {
            throw new RuntimeException("User does not exist");
        }
        if(loginUser(user.get().getEmail(), user.get().getPassword()) == null){
            throw new RuntimeException("User not logged in");
        }
        User user1 = user.get();
        List<Ticket> tickets = user1.getTickets();
        tickets.add(ticket);
        user1.setTickets(tickets);
        userRepo.save(user1);
        return user1;
    }


}
