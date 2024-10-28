import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main extends JFrame {
    //Constants to avoid use of magic numbers
    private static final int APP_WIDTH = 1000;
    private static final int APP_HEIGHT = 800;
    private static final int STATS_PANEL_HEIGHT = 200;
    private static final int CHART_PANEL_HEIGHT = 550;
    private static final int SUB_PANEL_WIDTH = 500;

    private TablePanel tablePanel;
    private StatsPanel statsPanel;
    private ChartPanel chartPanel;
    private DetailsPanel detailsPanel;
    private FiltersPanel filtersPanel;
    private List<Player> players;

    public Main() {
        setTitle("NBA Player Data Visualization");
        setSize(APP_WIDTH, APP_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load player data
        DataLoader dataLoader = new DataLoader();
        players = dataLoader.loadData("NBA Stats/nba_23_24_player_stats.txt");

        // Sort players by first name to match table sorting
        players.sort(Comparator.comparing(player -> player.getName().split(" ")[0]));

        // Initialize panels
        tablePanel = new TablePanel(players);
        statsPanel = new StatsPanel(players);
        chartPanel = new ChartPanel(players);
        detailsPanel = new DetailsPanel();
        filtersPanel = new FiltersPanel(players, tablePanel, chartPanel, statsPanel);

        // Add selection listener to the table to update the DetailsPanel
        tablePanel.getTable().getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = tablePanel.getTable().getSelectedRow();
                if (selectedRow != -1) {
                    // Convert the view index to the model index
                    int modelRow = tablePanel.getTable().convertRowIndexToModel(selectedRow);

                    // Access the Player object from the filtered data
                    Player selectedPlayer = tablePanel.getTableModel().getPlayerAt(modelRow);
                    if (selectedPlayer != null) {
                        detailsPanel.updateDetails(selectedPlayer);
                    }
                }
            }
        });

        // Main container panel with BorderLayout
        setLayout(new BorderLayout());

        // Add FiltersPanel at the very top of the application
        add(filtersPanel, BorderLayout.NORTH);

        // Left side panel with vertical BoxLayout
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
        leftPanel.setPreferredSize(new Dimension(SUB_PANEL_WIDTH, APP_HEIGHT));

        // Add StatsPanel to the left side
        statsPanel.setPreferredSize(new Dimension(SUB_PANEL_WIDTH, STATS_PANEL_HEIGHT));
        leftPanel.add(statsPanel);

        // Add ChartPanel to the left side
        chartPanel.setPreferredSize(new Dimension(SUB_PANEL_WIDTH, CHART_PANEL_HEIGHT));
        leftPanel.add(chartPanel);

        // Right side panel with GridBagLayout
        JPanel rightPanel = new JPanel(new GridBagLayout());
        rightPanel.setPreferredSize(new Dimension(SUB_PANEL_WIDTH, APP_HEIGHT));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        // Add TablePanel to the top-right
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.4;
        tablePanel.setPreferredSize(new Dimension(SUB_PANEL_WIDTH, 300)); // Reduced height
        rightPanel.add(tablePanel, gbc);

        // Add DetailsPanel to the bottom-right
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.6;
        rightPanel.add(detailsPanel, gbc);

        // Add left and right panels to the main frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.EAST);

        // Force revalidation to prevent initial cutoff in TablePanel
        SwingUtilities.invokeLater(() -> {
            pack();
            tablePanel.getTable().revalidate();
            tablePanel.getTable().repaint();
        });
    }

    // Method to apply filters from the FiltersPanel
    private void applyFilters(String selectedTeam, boolean removeWest, boolean removeEast) {
        List<Player> filteredPlayers = players.stream()
                .filter(player -> (selectedTeam.equals("All") || player.getTeam().equals(selectedTeam)))
                .filter(player -> !removeWest || !isWestTeam(player.getTeam()))
                .filter(player -> !removeEast || !isEastTeam(player.getTeam()))
                .collect(Collectors.toList());

        tablePanel.updateTable(filteredPlayers);
        chartPanel.updateChart(filteredPlayers);
        statsPanel.updateStats(filteredPlayers);
    }

    private boolean isWestTeam(String team) {
        // List of West Conference teams
        List<String> westTeams = Arrays.asList("Lakers", "Warriors", "Suns", "Clippers", "Kings",
                "Jazz", "Nuggets", "Thunder", "Timberwolves", "Mavericks",
                "Blazers", "Rockets", "Spurs", "Pelicans", "Grizzlies");
        return westTeams.contains(team);
    }

    private boolean isEastTeam(String team) {
        // List of East Conference teams
        List<String> eastTeams = Arrays.asList("Celtics", "Nets", "Knicks", "76ers", "Raptors",
                "Bulls", "Cavaliers", "Pistons", "Pacers", "Bucks",
                "Heat", "Hawks", "Hornets", "Magic", "Wizards");
        return eastTeams.contains(team);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}
