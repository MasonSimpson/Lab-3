import javax.swing.*;
import java.awt.*;

public class DetailsPanel extends JPanel {
    private JLabel nameLabel, teamLabel, gamesPlayedLabel, totalPointsLabel,
            totalAssistsLabel, totalReboundsLabel, totalStealsLabel, totalBlocksLabel;

    public DetailsPanel() {
        setLayout(new GridLayout(8, 1));
        nameLabel = new JLabel();
        teamLabel = new JLabel();
        gamesPlayedLabel = new JLabel();
        totalPointsLabel = new JLabel();
        totalAssistsLabel = new JLabel();
        totalReboundsLabel = new JLabel();
        totalStealsLabel = new JLabel();
        totalBlocksLabel = new JLabel();

        add(nameLabel);
        add(teamLabel);
        add(gamesPlayedLabel);
        add(totalPointsLabel);
        add(totalAssistsLabel);
        add(totalReboundsLabel);
        add(totalStealsLabel);
        add(totalBlocksLabel);
    }

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
