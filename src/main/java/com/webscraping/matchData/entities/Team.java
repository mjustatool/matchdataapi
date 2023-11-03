package com.webscraping.matchData.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Team implements Comparable<Team>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String teamName;
    private int classment;
    private int matchPlayed;
    private int wins;
    private int matchDraw;
    private int defeat;
    private int points;

    @Override
    public int compareTo(Team o) {
        return Integer.compare(this.getClassment(),o.getClassment());
    }
}
