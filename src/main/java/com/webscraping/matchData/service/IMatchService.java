package com.webscraping.matchData.service;

import com.webscraping.matchData.entities.Team;

import java.util.List;

public interface IMatchService {

    public List<Team> getAll(String competitionName);


}
