import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.util.stream.Collectors;

public class TablePanel extends JPanel {
    private JTable table;
    private TableModel tableModel;

    public TablePanel(List<Player> players) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Sort players by name using streams
        List<Player> sortedPlayers = players.stream()
                .sorted(Comparator.comparing(Player::getName))
                .collect(Collectors.toList());

        tableModel = new TableModel(sortedPlayers);
        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true); // Enable sorting
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public Player getSelectedPlayer() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            return tableModel.getPlayerAt(table.convertRowIndexToModel(selectedRow));
        }
        return null;
    }
}

// Helper class to manage table data
class TableModel extends AbstractTableModel {
    private List<Player> players;
    private String[] columns = {"Name", "Team"};

    public TableModel(List<Player> players) {
        this.players = players;
    }

    @Override
    public int getRowCount() {
        return players.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Player player = players.get(rowIndex);
        switch (columnIndex) {
            case 0: return player.getName();
            case 1: return player.getTeam();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    public Player getPlayerAt(int row) {
        return players.get(row);
    }
}
