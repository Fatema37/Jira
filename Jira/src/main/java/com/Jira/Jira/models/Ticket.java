package com.Jira.Jira.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class Ticket {
    private String id;
    private String title;
    private String description;
    private String status;
    private String owner;
    private String estimate_time;
    private String project_id;
    private TicketType ticketType;
    private List<Ticket> childTickets = new ArrayList<>();


    @Override
    public String toString() {
        return "Ticket{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", owner='" + owner + '\'' +
                ", estimate_time='" + estimate_time + '\'' +
                ", project_id=" + project_id +
                ", ticketType=" + ticketType +
                ", childTickets=" + childTickets +
                '}';
    }
}
