package com.Jira.Jira.services;

import com.Jira.Jira.models.*;

public class TicketFactory {

    public static Ticket getTicket(TicketType ticketType) {
        if(ticketType==TicketType.STORY){
          return new Story();
        }
        else if(ticketType==TicketType.TASK) {
            return new Task();
        }
        else if(ticketType==TicketType.SUBTASK){
            return new SubTask();
        }
        else{
            return null;
        }

    }
}