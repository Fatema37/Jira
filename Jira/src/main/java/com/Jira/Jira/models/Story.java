package com.Jira.Jira.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Story extends Ticket{

      private List<Ticket> taskList;




}
