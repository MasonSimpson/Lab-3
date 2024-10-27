import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main extends JFrame {
    private static final int APP_WIDTH = 1600; // Application width constant
    private static final int APP_HEIGHT = 800; // Application height constant

    private TablePanel tablePanel;
    private StatsPanel statsPanel;
    private ChartPanel chartPanel;
    private DetailsPanel detailsPanel;
    private List<Player> players;

    public Main() {
        setTitle("NBA Player Data Visualization - Table, Stats, and Chart");
        setSize(APP_WIDTH, APP_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load player data
        DataLoader dataLoader = new DataLoader();
        players = dataLoader.loadData("NBA Stats/nba_23_24_player_stats.txt");

        // Initialize panels
        tablePanel = new TablePanel(players);
        statsPanel = new StatsPanel(players); // No changes to StatsPanel
        chartPanel = new ChartPanel(players);
        detailsPanel = new DetailsPanel();

        // Add selection listener to the TablePanel's table
        tablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = tablePanel.getTable().getSelectedRow();
            if (selectedRow != -1) {
                // Get the player from the table model using getValueAt
                String playerName = (String) tablePanel.getTable().getModel().getValueAt(selectedRow, 0); // Assuming name is in the 1st column
                Player selectedPlayer = players.stream()
                        .filter(player -> player.getName().equals(playerName))
                        .findFirst()
                        .orElse(null);

                // Update details panel if player is found
                if (selectedPlayer != null) {
                    detailsPanel.updateDetails(selectedPlayer);
                }
            }
        });

        // Set layout and add panels
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(statsPanel, BorderLayout.NORTH); // StatsPanel on top
        getContentPane().add(tablePanel, BorderLayout.CENTER); // TablePanel in the center
        getContentPane().add(chartPanel, BorderLayout.SOUTH); // ChartPanel at the bottom
        getContentPane().add(detailsPanel, BorderLayout.EAST); // DetailsPanel on the right
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}
