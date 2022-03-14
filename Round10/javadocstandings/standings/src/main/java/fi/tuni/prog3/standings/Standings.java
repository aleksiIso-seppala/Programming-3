/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package fi.tuni.prog3.standings;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 *
 * A class for maintaining team statistics and standings. Team standings are determined by the following rules:
 * <ul>
 *   <li>Primary rule: points total. Higher points come first.</li>
 *   <li>Secondary rule: goal difference (scored minus allowed). Higher difference comes first.</li>
 *   <li>Tertiary rule: number of goals scored. Higher number comes first.</li>
 *   <li>Last rule: natural String order of team names.</li>
 * </ul>
 */
public class Standings {
    
    private TreeMap<String, Team> teams_ = new TreeMap<>();
    
    /**
     * Constructs an empty Standings object.
     */
    public Standings(){
        this.teams_ = new TreeMap<>();
    }
    
    /**
     * Constructs a Standings object that is initialized with the game data 
     * read from the specified file. The result is identical to first 
     * constructing an empty Standing object and then calling {@link #readMatchData(filename) readMatchData(filename)}.
     * @param filename the name of the game data file to read.
     * @throws IOException if there is some kind of an IO error (e.g. if the specified file does not exist).
     */
    public Standings(String filename) throws IOException{
        this.teams_ = new TreeMap<>();
        readMatchData(filename);
    }
    
    
    /**
     * <p>Reads game data from the specified file and updates the team statistics and standings accordingly.</p>
     * <p>The match data file is expected to contain lines of form 
     * "teamNameA\tgoalsA-goalsB\tteamNameB". Note that the '\t' are tabulator characters.</p>
     * <p>E.g. the line "Iceland\t3-2\tFinland" would describe a match 
     * between Iceland and Finland where Iceland scored 3 and Finland 2 goals.</p>
     * @param filename the name of the game data file to read.
     * @throws IOException if there is some kind of an IO error (e.g. if the specified file does not exist).
     */
    public final void readMatchData(String filename) throws IOException{
       
        try(var file = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = file.readLine()) != null){
                String[] split = line.split("\\t");
                String team1 = split[0];
                String team2 = split[2];
                String[] score = split[1].split("-");
                int teamAGoal = Integer.parseInt(score[0]);
                int teamBGoal = Integer.parseInt(score[1]);
                addMatchResult(team1,teamAGoal,teamBGoal,team2);
                
            }
       }
    }
    
    /**
     * Updates the team statistics and standings according to the match result described by the parameters.
     * @param teamNameA the name of the first ("home") team.
     * @param goalsA the number of goals scored by the first team.
     * @param goalsB the number of goals scored by the second team.
     * @param teamNameB the name of the second ("away") team.
     */
    public void addMatchResult(String teamNameA, int goalsA, int goalsB, String teamNameB){
        
        
        if(!teams_.containsKey(teamNameA)){
            Team newTeam = new Team(teamNameA);
            this.teams_.put(teamNameA, newTeam);
        }
        if(!teams_.containsKey(teamNameB)){
            Team newTeam = new Team(teamNameB);
            this.teams_.put(teamNameB, newTeam);
        }

        Team teamA = teams_.get(teamNameA);
        Team teamB = teams_.get(teamNameB);
        
        teamA.games_ += 1;
        teamB.games_ += 1;
        
        teamA.scored_ += goalsA;
        teamA.allowed_ += goalsB;

        teamB.scored_ += goalsB;
        teamB.allowed_ += goalsA;

        if(goalsA > goalsB){
            teamA.wins_ += 1;
            teamA.points_ += 3;

            teamB.losses_ += 1;
        }
        else if(goalsA == goalsB){
            teamA.ties_ += 1;
            teamA.points_ += 1;

            teamB.ties_ += 1;
            teamB.points_ += 1;
        }
        else{
            teamB.wins_ += 1;
            teamB.points_ += 3;

            teamA.losses_ += 1;            
        }
    }
    
    /**
     * Returns a list of the teams in the same order as they would appear in a standings table.
     * @return a list of the teams in the same order as they would appear in a standings table.
     */
    public List<Team> getTeams(){
        
        ArrayList<Team> listOfTeams = new ArrayList<>();
        
        for(var team : teams_.values()){
           listOfTeams.add(team);
        }
        
        Comparator<Team> compare = Comparator
                .comparing(Team::getPoints)
                .thenComparing(Team::getDifference)
                .thenComparing(Team::getScored)
                .thenComparing(Team::getName);
        
        List<Team> sorted = listOfTeams.stream().sorted(compare).collect(Collectors.toList());
        
        ArrayList<Team> returnable = new ArrayList<>();
        for(var team : sorted){
            returnable.add(team);
        }
        Collections.reverse(returnable);
        return returnable;
        
    }
    
    /**
     * Prints a formatted standings table to the provided output stream.
     * @param out the output stream to use when printing the standings table.
     */
    public void printStandings(PrintStream out){
        
        List<Team> teams = getTeams();
        
        Comparator<Team> compare = Comparator.comparing(Team::getNameLength);
        List<Team> names = teams.stream().sorted(compare).collect(Collectors.toList());
        int longest = names.get(names.size()-1).getNameLength();
        
        for(int i=0;i<teams.size();i++){
            
            Team current = teams.get(i);
            System.out.format("%s",current.getName());
            for(int j=0;j<(longest-teams.get(i).getNameLength());j++){
                System.out.format(" ");
            }
            System.out.format(" ");
            System.out.format("%3s ",current.getGames());
            System.out.format("%3s ",current.getWins());
            System.out.format("%3s ",current.getTies());
            System.out.format("%3s ",current.getLosses());
            
            String goals = current.getScored() + "-" + current.getAllowed();
            System.out.format("%6s ",goals);
            System.out.format("%3s%n",current.getPoints());
        }
        
        
    }
    
    /**
     * A class for storing statistics of a single team. The class offers only 
     * public getter functions. The enclosing class Standings is responsible 
     * for setting and updating team statistics.
     */
    public static class Team {
        
        private String name_;
        private int wins_ = 0;
        private int ties_ = 0;
        private int losses_ = 0;
        private int scored_ = 0;
        private int allowed_ = 0;
        private int points_ = 0;
        private int games_ = 0;
        
        /**
         * Constructs a Team object for storing statistics of the named team.
         * @param name the name of the team whose statistics the new team object stores. 
         */
        public Team(String name){
            this.name_ = name;
            
        }
        
        /**
         * Returns the name of the team.
         * @return the name of the team.
         */
        public String getName(){
            return name_;
        }
        
        /**
         * Returns the number of wins of the team.
         * @return the number of wins of the team.
         */
        public int getWins(){
            return wins_;
        }
        
        /**
         * Returns the number of ties of the team.
         * @return the number of ties of the team.
         */
        public int getTies(){
            return ties_;
        }
        
        /**
         * Returns the number of losses of the team.
         * @return the number of losses of the team.
         */
        public int getLosses(){
            return losses_;
        }
        
        /**
         * Returns the number of goals scored by the team.
         * @return the number of goals scored by the team.
         */
        public int getScored(){
            return scored_;
        }
        
        /**
         * Returns the number of goals allowed (conceded) by the team.
         * @return the number of goals allowed (conceded) by the team.
         */
        public int getAllowed(){
            return allowed_;
        }
        
        /**
         * Returns the overall number of points of the team.
         * @return the overall number of points of the team.
         */
        public int getPoints(){
            return points_;
        }
        
        
        
        private int getGames(){
            return games_;
        }

        private int getDifference(){
            return (scored_ - allowed_);
        }
        
        private int getNameLength(){
            return name_.length();
        }
        
    }

}
