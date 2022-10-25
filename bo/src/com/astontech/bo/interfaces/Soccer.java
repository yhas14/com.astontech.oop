package com.astontech.bo.interfaces;

public class Soccer implements Sports{
    private String TeamName;
    private String Score;
    private String Winners;

    //region CONSTRUCTOR
        public Soccer(){}

        public Soccer(String teamName, String score, String winner){
            this.setTeamName(teamName);
            this.setScore(score);
            this.setWinners(winner);
        }
    //endregion

    //region GETTERS/SETTERS
    public String getTeamName() {
        return TeamName;
    }

    public void setTeamName(String teamName) {
        TeamName = teamName;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getWinners() {
        return Winners;
    }

    public void setWinners(String winners) {
        Winners = winners;
    }
    //endregion

    @Override
    public String Score() {
        return this.getScore();
    }

    @Override
    public String TeamName() {
        return this.getTeamName();
    }

    @Override
    public String Winners() {
        return this.getWinners();
    }

    @Override
    public int Quarters() {
        return 2;
    }

    @Override
    public int NumberOfPlayers() {
        return 22;
    }

    @Override
    public String Fouls() {
        return "Take penalty";
    }


}
