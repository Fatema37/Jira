package com.Jira.Jira.repositories;

import com.Jira.Jira.models.Ticket;
import com.Jira.Jira.models.TicketType;

import java.util.HashMap;
import java.util.Optional;

public class TicketRepo {
    HashMap<String, Ticket> ticketHashMap = new HashMap<>();
    Long id = 0L;

    public Optional<Ticket> save(Ticket ticket){
        if(ticket.getId()==null){
            id++;
            ticket.setId(id.toString());
        }
        ticketHashMap.put(ticket.getId(), ticket);
        return Optional.of(ticket);
    }

    public Optional<Ticket> findById(String id){
        if(ticketHashMap.containsKey(id)){
            return Optional.of(ticketHashMap.get(id));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional deleteById(String id){
        if(ticketHashMap.containsKey(id)){
            ticketHashMap.remove(id);
            return Optional.of("Ticket deleted successfully");
        }
        else {
            return Optional.empty();
        }
    }






}
