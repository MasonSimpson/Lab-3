import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.util.stream.Collectors;

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

    // Custom TableModel class for managing table data
    class TableModel extends AbstractTableModel {
        private List<Player> players;

        public TableModel(List<Player> players) {
            this.players = players;
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
    }
}
