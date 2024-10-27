import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main extends JFrame {
    private static final int APP_WIDTH = 1600; // Application width constant
    private static final int APP_HEIGHT = 800; // Application height constant

    private TablePanel tablePanel;
    private StatsPanel statsPanel;
    private ChartPanel chartPanel;
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
        statsPanel = new StatsPanel(players);
        chartPanel = new ChartPanel(players);

        // Set layout and add panels
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(statsPanel, BorderLayout.NORTH);
        getContentPane().add(tablePanel, BorderLayout.CENTER);
        getContentPane().add(chartPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}
