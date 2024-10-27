import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main extends JFrame {
    private static final int APP_WIDTH = 800; // Application width constant
    private static final int APP_HEIGHT = 600; // Application height constant

    private TablePanel tablePanel;
    private StatsPanel statsPanel;
    private List<Player> players;

    public Main() {
        setTitle("NBA Player Data Visualization - Table and Stats");
        setSize(APP_WIDTH, APP_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Load player data
        DataLoader dataLoader = new DataLoader();
        players = dataLoader.loadData("NBA Stats/nba_23_24_player_stats.txt");

        // Initialize panels
        tablePanel = new TablePanel(players);
        statsPanel = new StatsPanel(players);

        // Set layout and add panels
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(tablePanel, BorderLayout.CENTER);
        getContentPane().add(statsPanel, BorderLayout.NORTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.setVisible(true);
        });
    }
}
