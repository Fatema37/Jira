package com.Jira.Jira.models;

import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Project {
    private String name;
    private String description;
    private String id;
    private String status;
    private String owner;
    private String[] members;
    private List<Ticket> tickets;


    @Override
    public String toString() {
        return "Project{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", owner='" + owner + '\'' +
                ", members=" + Arrays.toString(members) +
                ", tickets=" + tickets +
                '}';
    }
}
