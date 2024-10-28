import java.io.*;
import java.util.*;

// Loads data from the text file and creates player objects for each
// Player that is listed in the file. Creates a list of player objects
// To store every player and their stats to use for the stats application
public class DataLoader {
    public List<Player> loadData(String fileName) {
        List<Player> players = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            Player player = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Player:")) {
                    player = new Player();
                    String[] parts = line.split(": ");
                    if (parts.length > 1) {
                        player.setName(parts[1].trim());
                    }
                } else if (line.startsWith("Team:") && player != null) {
                    String[] parts = line.split(": ");
                    if (parts.length > 1) {
                        player.setTeam(parts[1].trim());
                    }
                } else if (line.startsWith("Games Played:") && player != null) {
                    String[] parts = line.split(": ");
                    if (parts.length > 1) {
                        player.setGamesPlayed(Integer.parseInt(parts[1].trim()));
                    }
                } else if (line.startsWith("Total Points:") && player != null) {
                    String[] parts = line.split(": ");
                    if (parts.length > 1) {
                        player.setPoints(Integer.parseInt(parts[1].trim()));
                    }
                } else if (line.startsWith("Total Assists:") && player != null) {
                    String[] parts = line.split(": ");
                    if (parts.length > 1) {
                        player.setAssists(Integer.parseInt(parts[1].trim()));
                    }
                } else if (line.startsWith("Total Rebounds:") && player != null) {
                    String[] parts = line.split(": ");
                    if (parts.length > 1) {
                        player.setRebounds(Integer.parseInt(parts[1].trim()));
                    }
                } else if (line.startsWith("Total Steals:") && player != null) {
                    String[] parts = line.split(": ");
                    if (parts.length > 1) {
                        player.setSteals(Integer.parseInt(parts[1].trim()));
                    }
                } else if (line.startsWith("Total Blocks:") && player != null) {
                    String[] parts = line.split(": ");
                    if (parts.length > 1) {
                        player.setBlocks(Integer.parseInt(parts[1].trim()));
                    }
                } else if (line.startsWith("------------")) {
                    if (player != null) {
                        players.add(player);
                        player = null; // Reset player for the next entry
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return players;
    }
}
