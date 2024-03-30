package com.Jira.Jira.services;

import com.Jira.Jira.models.*;
import com.Jira.Jira.repositories.ProjectRepo;
import com.Jira.Jira.repositories.TicketRepo;
import com.Jira.Jira.repositories.UserRepo;

import java.util.List;
import java.util.Optional;

public class ProjectService {

    private ProjectRepo projectRepo;

    private TicketRepo ticketRepo;
    private UserService userService;

    public ProjectService(ProjectRepo projectRepo, TicketRepo ticketRepo, UserService userService) {
        this.projectRepo = projectRepo;
        this.ticketRepo = ticketRepo;
        this.userService = userService;
    }

    public Project createProject(String name, String description, String owner) {
       Optional<Project> project = projectRepo.findByName(name);
       if(project.isPresent()){
           return null;
       }
       else {
           Project newProject = new Project();
           newProject.setName(name);
           newProject.setDescription(description);
           newProject.setOwner(owner);
           newProject.setStatus("Active");
          projectRepo.save(newProject);
          System.out.println(newProject);
          return newProject;
       }

    }

    public Ticket createTicket(String project_id,String title,String owner, TicketType ticketType ,String parentId , String email){
        Optional<Project> project = projectRepo.findById(project_id);
        if(project.isPresent()){
            Ticket newTicket = TicketFactory.getTicket(ticketType);
            newTicket.setTitle(title);
            User user = userService.getUser(email);
            if(user == null){
                throw new RuntimeException("User not found");
            }
            newTicket.setOwner(user.getId());
            newTicket.setStatus("Open");
            newTicket.setProject_id(project_id);
            newTicket.setEstimate_time("0");
            newTicket.setDescription("No Description");
            newTicket.setTicketType(ticketType);
            ticketRepo.save(newTicket);
            userService.assignTicket(user.getId(), newTicket);
            System.out.println(newTicket);
            if(parentId == null){
                return newTicket;
            }
            Ticket parentticket = ticketRepo.findById(parentId).get();
            parentticket.getChildTickets().add(newTicket);
            return newTicket;
        }
        else {
            return null;
        }
    }

    public void moveTickettoAnyProject(String ticket_id, String project_id){
        Optional<Ticket> optionalTicket = ticketRepo.findById(ticket_id);
        Optional<Project> optionalProject = projectRepo.findById(project_id);
        if(optionalTicket.isPresent() && optionalProject.isPresent()){
            Ticket ticket = optionalTicket.get();
            List<Ticket> childs = ticket.getChildTickets();
            for(Ticket child : childs){
                child.setProject_id(project_id);
                ticketRepo.save(child);
            }
            ticket.setProject_id(project_id);
            ticketRepo.save(ticket);
            System.out.println("Ticket moved successfully");
            System.out.println(ticket);

        }
        else {
            throw new RuntimeException("Ticket or Project not found");
        }
    }

    public void deleteTicket(String ticket_id, String email){
        Optional<Ticket> ticketOptional = ticketRepo.findById(ticket_id);
        User  user  = userService.getUser(email);
        if(user == null){
            throw new RuntimeException("User not found");
        }


        if(user.getRole().equals(RoleType.Admin)){
            if(ticketOptional.isPresent()){
                Ticket ticket = ticketOptional.get();
                List<Ticket> childs = ticket.getChildTickets();
                for(Ticket child : childs){
                    ticketRepo.deleteById(child.getId());
                }
                ticketRepo.deleteById(ticket_id);
            }
            throw new RuntimeException("Ticket not found");
        }


    }




}
