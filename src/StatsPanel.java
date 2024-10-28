import javax.swing.*;
import java.awt.*;
import java.util.List;

// Displays the total stats from every player in the 23-24 season
// Updates depending on what filters are selected
public class StatsPanel extends JPanel {
    private final int WIDTH = 300;
    private final int HEIGHT = 150;

    // Initialize labels at the time of declaration
    private JLabel totalPlayersLabel = new JLabel();
    private JLabel totalPointsLabel = new JLabel();
    private JLabel avgPointsLabel = new JLabel();
    private JLabel totalAssistsLabel = new JLabel();
    private JLabel avgAssistsLabel = new JLabel();
    private JLabel totalReboundsLabel = new JLabel();
    private JLabel avgReboundsLabel = new JLabel();
    private JLabel totalStealsLabel = new JLabel();
    private JLabel avgStealsLabel = new JLabel();
    private JLabel totalBlocksLabel = new JLabel();
    private JLabel avgBlocksLabel = new JLabel();

    public StatsPanel(List<Player> players) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        // Add title label
        add(new JLabel("Statistics for 2023-2024 NBA Season"));
        add(totalPlayersLabel);
        add(totalPointsLabel);
        add(avgPointsLabel);
        add(totalAssistsLabel);
        add(avgAssistsLabel);
        add(totalReboundsLabel);
        add(avgReboundsLabel);
        add(totalStealsLabel);
        add(avgStealsLabel);
        add(totalBlocksLabel);
        add(avgBlocksLabel);

        // Update stats with initial list of players
        updateStats(players);
    }

    // Method to update statistics when filters are applied
    public void updateStats(List<Player> filteredPlayers) {
        int totalPlayers = filteredPlayers.size();
        int totalPoints = filteredPlayers.stream().mapToInt(Player::getPoints).sum();
        int totalAssists = filteredPlayers.stream().mapToInt(Player::getAssists).sum();
        int totalRebounds = filteredPlayers.stream().mapToInt(Player::getRebounds).sum();
        int totalSteals = filteredPlayers.stream().mapToInt(Player::getSteals).sum();
        int totalBlocks = filteredPlayers.stream().mapToInt(Player::getBlocks).sum();

        // Calculate averages and round to nearest integer
        double avgPoints = totalPlayers > 0 ? Math.round(filteredPlayers.stream().mapToInt(Player::getPoints).average().orElse(0)) : 0;
        double avgAssists = totalPlayers > 0 ? Math.round(filteredPlayers.stream().mapToInt(Player::getAssists).average().orElse(0)) : 0;
        double avgRebounds = totalPlayers > 0 ? Math.round(filteredPlayers.stream().mapToInt(Player::getRebounds).average().orElse(0)) : 0;
        double avgSteals = totalPlayers > 0 ? Math.round(filteredPlayers.stream().mapToInt(Player::getSteals).average().orElse(0)) : 0;
        double avgBlocks = totalPlayers > 0 ? Math.round(filteredPlayers.stream().mapToInt(Player::getBlocks).average().orElse(0)) : 0;

        // Update label texts
        totalPlayersLabel.setText("Total Players: " + totalPlayers);
        totalPointsLabel.setText("Total Points: " + totalPoints);
        avgPointsLabel.setText("Average Points (per player): " + (int) avgPoints);
        totalAssistsLabel.setText("Total Assists: " + totalAssists);
        avgAssistsLabel.setText("Average Assists (per player): " + (int) avgAssists);
        totalReboundsLabel.setText("Total Rebounds: " + totalRebounds);
        avgReboundsLabel.setText("Average Rebounds (per player): " + (int) avgRebounds);
        totalStealsLabel.setText("Total Steals: " + totalSteals);
        avgStealsLabel.setText("Average Steals (per player): " + (int) avgSteals);
        totalBlocksLabel.setText("Total Blocks: " + totalBlocks);
        avgBlocksLabel.setText("Average Blocks (per player): " + (int) avgBlocks);
    }
}
