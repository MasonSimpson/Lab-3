
// Player object that stores stats
public class Player {
    //Private variable declaration
    private String name;
    private String team;
    private int gamesPlayed;
    private int points;
    private int rebounds;
    private int assists;
    private int steals;
    private int blocks;
    //Default constructor
    public Player() {
        this.name = "";
        this.team = "";
        this.gamesPlayed = 0;
        this.points = 0;
        this.rebounds = 0;
        this.assists = 0;
        this.steals = 0;
        this.blocks = 0;
    }
    //Constructor
    public Player(String name, String team, int gamesPlayed, int points, int rebounds, int assists, int steals, int blocks) {
        this.name = name;
        this.team = team;
        this.gamesPlayed = gamesPlayed;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
        this.steals = steals;
        this.blocks = blocks;
    }
    //Getters and setters for each player's name, team, and stats
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTeam() {
        return team;
    }
    public void setTeam(String team) {
        this.team = team;
    }
    public int getGamesPlayed() {
        return gamesPlayed;
    }
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public int getRebounds() {
        return rebounds;
    }
    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }
    public int getAssists() {
        return assists;
    }
    public void setAssists(int assists) {
        this.assists = assists;
    }
    public int getSteals() {
        return steals;
    }
    public void setSteals(int steals) {
        this.steals = steals;
    }
    public int getBlocks() {
        return blocks;
    }
    public void setBlocks(int blocks) {
        this.blocks = blocks;
    }
    //Takes in the total season stat and returns the
    //Per game stat by dividing the total season stat
    //By the number of games the player played that season
    public double calculatePerGame(int stat) {
        return (double) (stat / gamesPlayed);
    }
    @Override
    //overriding the tostring method
    public String toString() {
        return "Player: " + name + "\nTeam: " + team + "\nGames Played: " + gamesPlayed + "\nPoints: " + points +
                "\nRebounds: " + rebounds + "\nAssists: " + assists + "\nSteals: " + steals + "\nBlocks: " + blocks;
    }
}
