import java.io.*;
import java.util.*;

public class DataLoader {
    public List<Player> loadData(String fileName) {
        List<Player> players = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            Player player = null;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Player:")) {
                    player = new Player();
                    player.setName(line.split(": ")[1]);
                } else if (line.startsWith("Team:") && player != null) {
                    player.setTeam(line.split(": ")[1]);
                } else if (line.startsWith("Games Played:") && player != null) {
                    player.setGamesPlayed(Integer.parseInt(line.split(": ")[1]));
                } else if (line.startsWith("Total Points:") && player != null) {
                    player.setPoints(Integer.parseInt(line.split(": ")[1]));
                } else if (line.startsWith("Total Assists:") && player != null) {
                    player.setAssists(Integer.parseInt(line.split(": ")[1]));
                } else if (line.startsWith("Total Rebounds:") && player != null) {
                    player.setRebounds(Integer.parseInt(line.split(": ")[1]));
                } else if (line.startsWith("Total Steals:") && player != null) {
                    player.setSteals(Integer.parseInt(line.split(": ")[1]));
                } else if (line.startsWith("Total Blocks:") && player != null) {
                    player.setBlocks(Integer.parseInt(line.split(": ")[1]));
                } else if (line.startsWith("------------")) {
                    if (player != null) {
                        players.add(player);
                        player = null; // Reset player for the next entry
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return players;
    }
}
