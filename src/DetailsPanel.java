import javax.swing.*;
import java.awt.*;

// This panel shows all stats for a selected player from the table.
// When the program is first ran, all values are empty.
public class DetailsPanel extends JPanel {
    // Declaring constants to avoid use of magic numbers
    private JLabel nameLabel, teamLabel, gamesPlayedLabel, totalPointsLabel,
            totalAssistsLabel, totalReboundsLabel, totalStealsLabel, totalBlocksLabel;
    private final int ROWS = 8;
    private final int COLS = 1;

    // Constructor for the panel
    public DetailsPanel() {
        setLayout(new GridLayout(ROWS, COLS));
        nameLabel = new JLabel();
        teamLabel = new JLabel();
        gamesPlayedLabel = new JLabel();
        totalPointsLabel = new JLabel();
        totalAssistsLabel = new JLabel();
        totalReboundsLabel = new JLabel();
        totalStealsLabel = new JLabel();
        totalBlocksLabel = new JLabel();

        // Set the default text for all labels whenever the program is first run
        nameLabel.setText("Name: ");
        teamLabel.setText("Team: ");
        gamesPlayedLabel.setText("Games Played: ");
        totalPointsLabel.setText("Total Points: ");
        totalAssistsLabel.setText("Total Assists: ");
        totalReboundsLabel.setText("Total Rebounds: ");
        totalStealsLabel.setText("Total Steals: ");
        totalBlocksLabel.setText("Total Blocks: ");

        add(nameLabel);
        add(teamLabel);
        add(gamesPlayedLabel);
        add(totalPointsLabel);
        add(totalAssistsLabel);
        add(totalReboundsLabel);
        add(totalStealsLabel);
        add(totalBlocksLabel);
    }

    //Updates the details whenever a player in the table is selected
    public void updateDetails(Player player) {
        if (player != null) {
            nameLabel.setText("Name: " + player.getName());
            teamLabel.setText("Team: " + player.getTeam());
            gamesPlayedLabel.setText("Games Played: " + player.getGamesPlayed());
            totalPointsLabel.setText("Total Points: " + player.getPoints());
            totalAssistsLabel.setText("Total Assists: " + player.getAssists());
            totalReboundsLabel.setText("Total Rebounds: " + player.getRebounds());
            totalStealsLabel.setText("Total Steals: " + player.getSteals());
            totalBlocksLabel.setText("Total Blocks: " + player.getBlocks());
        } else {
            // Clear details if no player is selected
            nameLabel.setText("Name: ");
            teamLabel.setText("Team: ");
            gamesPlayedLabel.setText("Games Played: ");
            totalPointsLabel.setText("Total Points: ");
            totalAssistsLabel.setText("Total Assists: ");
            totalReboundsLabel.setText("Total Rebounds: ");
            totalStealsLabel.setText("Total Steals: ");
            totalBlocksLabel.setText("Total Blocks: ");
        }
    }
}
