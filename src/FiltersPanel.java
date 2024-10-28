import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FiltersPanel extends JPanel {
    private static final int PADDING = 10;
    private static final String ALL_TEAMS = "All Teams";

    private JComboBox<String> teamDropdown;
    private JCheckBox removeWestCheckbox;
    private JCheckBox removeEastCheckbox;

    private List<Player> players;
    private TablePanel tablePanel;
    private ChartPanel chartPanel;
    private StatsPanel statsPanel;

    public FiltersPanel(List<Player> players, TablePanel tablePanel, ChartPanel chartPanel, StatsPanel statsPanel) {
        this.players = players;
        this.tablePanel = tablePanel;
        this.chartPanel = chartPanel;
        this.statsPanel = statsPanel;

        setLayout(new FlowLayout(FlowLayout.LEFT, PADDING, PADDING));

        // Team Dropdown
        Set<String> teamNames = players.stream().map(Player::getTeam).collect(Collectors.toSet());
        teamNames.add(ALL_TEAMS); // Add the default "All Teams" option
        teamDropdown = new JComboBox<>(teamNames.toArray(new String[0]));
        teamDropdown.setSelectedItem(ALL_TEAMS);
        teamDropdown.addActionListener(e -> applyFilters());
        add(teamDropdown);

        // Remove West Checkbox
        removeWestCheckbox = new JCheckBox("Remove West Teams");
        removeWestCheckbox.addActionListener(e -> applyFilters());
        add(removeWestCheckbox);

        // Remove East Checkbox
        removeEastCheckbox = new JCheckBox("Remove East Teams");
        removeEastCheckbox.addActionListener(e -> applyFilters());
        add(removeEastCheckbox);
    }

    private void applyFilters() {
        String selectedTeam = (String) teamDropdown.getSelectedItem();
        boolean removeWest = removeWestCheckbox.isSelected();
        boolean removeEast = removeEastCheckbox.isSelected();

        // Filter players based on selected criteria
        List<Player> filteredPlayers = players.stream()
                .filter(player -> (selectedTeam.equals(ALL_TEAMS) || player.getTeam().equals(selectedTeam)) &&
                        (!removeWest || !isWestTeam(player.getTeam())) &&
                        (!removeEast || !isEastTeam(player.getTeam())))
                .collect(Collectors.toList());

        // Update TablePanel and ChartPanel
        tablePanel.updateTable(filteredPlayers);
        chartPanel.updateChart(filteredPlayers);
        statsPanel.updateStats(filteredPlayers);
    }

    private boolean isWestTeam(String team) {
        // List of Western Conference teams (adjust based on actual data)
        Set<String> westTeams = new HashSet<>();
        westTeams.add("Lakers");
        westTeams.add("Clippers");
        westTeams.add("Warriors");
        westTeams.add("Suns");
        westTeams.add("Kings");
        westTeams.add("Mavericks");
        westTeams.add("Rockets");
        westTeams.add("Spurs");
        westTeams.add("Grizzlies");
        westTeams.add("Pelicans");
        westTeams.add("Thunder");
        westTeams.add("Nuggets");
        westTeams.add("Jazz");
        westTeams.add("Trail Blazers");
        westTeams.add("Timberwolves");
        return westTeams.contains(team);
    }

    private boolean isEastTeam(String team) {
        // List of Eastern Conference teams (adjust based on actual data)
        Set<String> eastTeams = new HashSet<>();
        eastTeams.add("Celtics");
        eastTeams.add("Nets");
        eastTeams.add("Knicks");
        eastTeams.add("76ers");
        eastTeams.add("Raptors");
        eastTeams.add("Bulls");
        eastTeams.add("Cavaliers");
        eastTeams.add("Pistons");
        eastTeams.add("Pacers");
        eastTeams.add("Bucks");
        eastTeams.add("Hawks");
        eastTeams.add("Hornets");
        eastTeams.add("Heat");
        eastTeams.add("Magic");
        eastTeams.add("Wizards");
        return eastTeams.contains(team);
    }
}
