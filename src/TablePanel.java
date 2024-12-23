import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.util.stream.Collectors;

// This panels contains the table that displays every single player that played
// In the 2023-2024 NBA Season. It contains their name, what team they played for,
// And the total amount of points they scored that season
public class TablePanel extends JPanel {
    private static final String[] COLUMNS = {"Name", "Team", "Points"}; // Updated columns
    private static final int[] COLUMN_WIDTHS = {100, 90, 60}; // Set column widths

    private JTable table;
    private TableModel tableModel;

    public TablePanel(List<Player> players) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Sort players by name initially
        List<Player> sortedPlayers = players.stream()
                .sorted(Comparator.comparing(Player::getName))
                .collect(Collectors.toList());

        tableModel = new TableModel(sortedPlayers);
        table = new JTable(tableModel);
        table.setAutoCreateRowSorter(true); // Enable sorting

        // Adjust column widths
        for (int i = 0; i < COLUMN_WIDTHS.length; i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(COLUMN_WIDTHS[i]);
        }

        // Left-align the "Points" column
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        table.getColumnModel().getColumn(2).setCellRenderer(leftRenderer); // Align points to the left

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    public JTable getTable() {
        return table;
    }

    public TableModel getTableModel() {
        return tableModel;
    }

    // Method to update table data based on filtered players
    public void updateTable(List<Player> filteredPlayers) {
        tableModel.updateData(filteredPlayers);
        table.revalidate(); // Refresh the table
        table.repaint();
    }

    // Custom TableModel class for managing table data
    class TableModel extends AbstractTableModel {
        private List<Player> players;

        public TableModel(List<Player> players) {
            this.players = new ArrayList<>(players);
        }

        @Override
        public int getRowCount() {
            return players.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Player player = players.get(rowIndex);
            switch (columnIndex) {
                case 0: return player.getName();
                case 1: return player.getTeam();
                case 2: return player.getPoints(); // Points column
                default: return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (columnIndex == 2) {
                return Integer.class; // Points column for sorting as integers
            }
            return String.class;
        }

        public Player getPlayerAt(int row) {
            return players.get(row);
        }

        // Method to update data based on filters
        public void updateData(List<Player> newPlayers) {
            this.players = new ArrayList<>(newPlayers);
            fireTableDataChanged(); // Notify the table about the data change
        }
    }
}
