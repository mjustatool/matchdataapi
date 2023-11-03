package com.webscraping.matchData.controller;

import com.webscraping.matchData.entities.Team;
import com.webscraping.matchData.service.IMatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/apiMatch")
public class MatchController {
    private final IMatchService matchService;

    public MatchController(IMatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping
    public ResponseEntity<List<Team>> getAll(@RequestParam("league") String competitionName){
        List<Team> teams = matchService.getAll(competitionName).stream()
                .sorted(Team::compareTo)
                .collect(Collectors.toList());
        return ResponseEntity.ok(teams);
    }
}
