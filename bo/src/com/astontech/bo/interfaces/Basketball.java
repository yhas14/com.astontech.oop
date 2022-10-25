package com.astontech.bo.interfaces;

import common.helpers.StringHelper;

public class Basketball implements Sports {
    private int Players;
    private String TeamName;
    private String Score;
    private String Winners;


    //region GETTERS/SETTERS

    public String getWinners(){
        return Winners;
    }
    public void setWinners(String winners){
        Winners = winners;
    }
    public String getScore(){
        return Score;
    }
    public void setScore(String score){
        Score = score;
    }
    public int getPlayers() {
        return Players;
    }

    public void setPlayers(int players) {
        Players = players;
    }

    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }
    //endregion




    @Override
    public String Score() {
        return Score;
    }

    @Override
    public String TeamName() {
        return TeamName;
    }

    @Override
    public String Winners() {
        return Winners;
    }

    @Override
    public int Quarters() {
        return 4;
    }

    @Override
    public int NumberOfPlayers() {
        return 10;
    }

    @Override
    public String Fouls() {
        return "Shoot freethrow";
    }
}
