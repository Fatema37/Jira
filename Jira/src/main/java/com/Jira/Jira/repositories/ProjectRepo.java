package com.Jira.Jira.repositories;

import com.Jira.Jira.models.Project;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class ProjectRepo {
    HashMap<String, Project> projectHashMap = new HashMap<>();
    Long id = 0L;

    public Project save(Project project){
        if(project.getId()==null) {
            id++;
            project.setId(id.toString());
        }
        projectHashMap.put(project.getId(), project);
        return project;
    }


    public Optional<Project> findById(String id){
        if(projectHashMap.containsKey(id)){
            return Optional.of(projectHashMap.get(id));
        }
        else {
            return Optional.empty();
        }
    }

    public Optional<Project> findByName(String name){
        for (Project project : projectHashMap.values()) {
            if(project.getName().equals(name)){
                return Optional.of(project);
            }
        }
        return Optional.empty();
    }

public Optional<Project> findByOwner(String owner){
        for (Project project : projectHashMap.values()) {
            if(project.getOwner().equals(owner)){
                return Optional.of(project);
            }
        }
        return Optional.empty();
    }

            public Optional<List> findTicketsByProjectId(Project id) {
                for (Project project : projectHashMap.values()) {
                    if (project.getId().equals(id)) {
                        return Optional.of(project.getTickets());
                    }

                }
                return Optional.empty();
            }
}
