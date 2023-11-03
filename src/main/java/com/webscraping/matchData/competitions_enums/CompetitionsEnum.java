package com.webscraping.matchData.competitions_enums;


public enum CompetitionsEnum {
    PREMIER_LEAGUE("premier_league"),
    LALIGA("laliga"),
    SERIEA("seria"),
    LEAGUE1("league1");

    private String url;

    CompetitionsEnum(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
