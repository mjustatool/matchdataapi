package com.webscraping.matchData.service;

import com.webscraping.matchData.competitions_enums.CompetitionsEnum;
import com.webscraping.matchData.entities.Team;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.*;

@Service
public class MatchService implements  IMatchService {

    private final String permierURL = "https://www.yallakora.com/epl/2829/standing/%d8%a7%d9%84%d8%af%d9%88%d8%b1%d9%8a-%d8%a7%d9%84%d8%a5%d9%86%d8%ac%d9%84%d9%8a%d8%b2%d9%8a#tour-groupstandingclip";
    private final String laligaURL = "https://www.yallakora.com/la-liga/2833/standing/%d8%a7%d9%84%d8%af%d9%88%d8%b1%d9%8a-%d8%a7%d9%84%d8%a5%d8%b3%d8%a8%d8%a7%d9%86%d9%8a#tour-groupstandingclip";
    private final String league1URL = "https://www.yallakora.com/ligue1/2838/standing/%d8%a7%d9%84%d8%af%d9%88%d8%b1%d9%8a-%d8%a7%d9%84%d9%81%d8%b1%d9%86%d8%b3%d9%8a#tour-groupstandingclip";
    private final String seriaURL = "https://www.yallakora.com/serie-a/2836/standing/%d8%a7%d9%84%d8%af%d9%88%d8%b1%d9%8a-%d8%a7%d9%84%d8%a5%d9%8a%d8%b7%d8%a7%d9%84%d9%8a#tour-groupstandingclip";


    @Override
    public List<Team> getAll(String competitionName) {
        Map<String, String> competitionToUrlMap = new HashMap<>();
        competitionToUrlMap.put(CompetitionsEnum.PREMIER_LEAGUE.getUrl(), this.permierURL);
        competitionToUrlMap.put(CompetitionsEnum.LALIGA.getUrl(), this.laligaURL);
        competitionToUrlMap.put(CompetitionsEnum.LEAGUE1.getUrl(), this.league1URL);
        competitionToUrlMap.put(CompetitionsEnum.SERIEA.getUrl(), this.seriaURL);

        return classmentOfCompetition(competitionToUrlMap.get(competitionName));
    }


    public List<Team> classmentOfCompetition(String url){
        int classment = 0;
        String teamName = null;
        Set<Team> teams = new HashSet<>();
        try {
            final Document document = Jsoup.connect(url).get();
            for (Element row : document.select("div.table div.wRow")
            ) {
                classment = Integer.parseInt(row.getElementsByClass("item arrng").text());
                teamName = row.getElementsByClass("item team").tagName("a").tagName("p").text();
                Elements elements = row.getElementsByClass("item dtls");

                for(int i = 0 ; i < elements.size() ; i++){
                    teams.add(Team.builder().teamName(teamName).classment(classment).matchPlayed(Integer.parseInt(elements.get(0).text())).wins(Integer.parseInt(elements.get(0+1).text()))
                            .matchDraw(Integer.parseInt(elements.get(2).text()))
                            .defeat(Integer.parseInt(elements.get(3).text()))
                            .points(Integer.parseInt(elements.get(4).text())).build());
                }
            }

            return teams.stream().toList();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



}
