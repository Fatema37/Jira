package com.Jira.Jira.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class User {
    private String name;
    private String email;
    private String password;
    private RoleType role;
    private String id;
    private List<Project> projects;
    private List<Ticket> tickets = new ArrayList<>();

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", id='" + id + '\'' +
                ", projects=" + projects +
                ", tickets=" + tickets +
                '}';
    }
}


