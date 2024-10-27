import javax.swing.*;
import java.util.List;

public class StatsPanel extends JPanel {
    public StatsPanel(List<Player> players) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        int totalPlayers = players.size();
        int totalPoints = players.stream().mapToInt(Player::getPoints).sum();
        int totalAssists = players.stream().mapToInt(Player::getAssists).sum();
        int totalRebounds = players.stream().mapToInt(Player::getRebounds).sum();
        int totalSteals = players.stream().mapToInt(Player::getSteals).sum();
        int totalBlocks = players.stream().mapToInt(Player::getBlocks).sum();

        // Calculate averages and round to nearest integer
        double avgPoints = totalPlayers > 0 ? Math.round(players.stream().mapToInt(Player::getPoints).average().orElse(0)) : 0;
        double avgAssists = totalPlayers > 0 ? Math.round(players.stream().mapToInt(Player::getAssists).average().orElse(0)) : 0;
        double avgRebounds = totalPlayers > 0 ? Math.round(players.stream().mapToInt(Player::getRebounds).average().orElse(0)) : 0;
        double avgSteals = totalPlayers > 0 ? Math.round(players.stream().mapToInt(Player::getSteals).average().orElse(0)) : 0;
        double avgBlocks = totalPlayers > 0 ? Math.round(players.stream().mapToInt(Player::getBlocks).average().orElse(0)) : 0;

        add(new JLabel("Statistics for 2023-2024 NBA Season"));
        add(new JLabel("Total Players: " + totalPlayers));
        add(new JLabel("Total Points: " + totalPoints));
        add(new JLabel("Average Points (per player): " + (int) avgPoints));
        add(new JLabel("Total Assists: " + totalAssists));
        add(new JLabel("Average Assists (per player): " + (int) avgAssists));
        add(new JLabel("Total Rebounds: " + totalRebounds));
        add(new JLabel("Average Rebounds (per player): " + (int) avgRebounds));
        add(new JLabel("Total Steals: " + totalSteals));
        add(new JLabel("Average Steals (per player): " + (int) avgSteals));
        add(new JLabel("Total Blocks: " + totalBlocks));
        add(new JLabel("Average Blocks (per player): " + (int) avgBlocks));
    }
}
