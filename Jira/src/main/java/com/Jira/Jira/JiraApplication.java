package com.Jira.Jira;

import com.Jira.Jira.models.*;
import com.Jira.Jira.repositories.ProjectRepo;
import com.Jira.Jira.repositories.TicketRepo;
import com.Jira.Jira.repositories.UserRepo;
import com.Jira.Jira.services.ProjectService;
import com.Jira.Jira.services.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class JiraApplication {

	public static void main(String[] args) {
		SpringApplication.run(JiraApplication.class, args);
		UserRepo userRepo = new UserRepo();
		UserService userService = new UserService(userRepo);
		userService.registerUser("fatema@gmail.com","1234","Fatema",RoleType.Admin);


		ProjectRepo projectRepo = new ProjectRepo();
		TicketRepo 	ticketRepo = new TicketRepo();
		ProjectService projectService = new ProjectService(projectRepo,ticketRepo,userService);
		projectService.createProject("Project1","This is project 1","Fatema");
		projectService.createTicket("1","Ticket1","Fatema",TicketType.STORY, null ,"fatema@gmail.com");
        projectService.createTicket("1","Ticket2","Fatema",TicketType.TASK, "1" , "fatema@gmail.com");
		userService.loginUser("fatema@gmail.com" , "1234");
		userService.getTickets("1");

		projectService.createProject("Project2","This is project 1","Hasan");
		projectService.moveTickettoAnyProject("1","2");












	}

}
