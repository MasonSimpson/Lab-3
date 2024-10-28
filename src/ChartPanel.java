import javax.swing.*;
import java.awt.*;
import java.awt.geom.Arc2D;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class ChartPanel extends JPanel {
    private List<Player> players;

    public ChartPanel(List<Player> players) {
        this.players = players;
        setLayout(new BorderLayout());

        // Add padding to move the entire chart down
        setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0)); // Padding at the top

        // Title at the top, centered
        JLabel titleLabel = new JLabel("Total Points by Team", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(titleLabel, BorderLayout.NORTH);

        // Create inner panel for the chart
        JPanel chartArea = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPieChart(g);
            }
        };
        chartArea.setPreferredSize(new Dimension(450, 450)); // Chart size
        add(chartArea, BorderLayout.CENTER);
    }

    private void drawPieChart(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Map<String, Integer> teamPoints = calculateTeamPoints();
        int totalPoints = teamPoints.values().stream().mapToInt(Integer::intValue).sum();

        // Set chart properties
        int width = 350; // Chart width
        int height = 350; // Chart height
        int x = (getWidth() - width) / 2; // Center the chart horizontally
        int y = 50; // Y-coordinate to start drawing the chart, shifted down

        // Draw the pie chart
        double startAngle = 0;
        for (Map.Entry<String, Integer> entry : teamPoints.entrySet()) {
            String team = entry.getKey();
            int points = entry.getValue();
            double angle = 360.0 * points / totalPoints;

            // Set random color for each slice
            Color sliceColor = new Color((int) (Math.random() * 0x1000000));
            g2d.setColor(sliceColor);
            g2d.fill(new Arc2D.Double(x, y, width, height, startAngle, angle, Arc2D.PIE));

            // Draw labels outside each slice
            drawLabelWithLine(g2d, team, points, x, y, width, height, startAngle, angle);

            startAngle += angle;
        }
    }

    private Map<String, Integer> calculateTeamPoints() {
        Map<String, Integer> teamPoints = new HashMap<>();
        for (Player player : players) {
            teamPoints.merge(player.getTeam(), player.getPoints(), Integer::sum);
        }
        return teamPoints;
    }

    private void drawLabelWithLine(Graphics2D g2d, String team, int points, int x, int y, int width, int height, double startAngle, double angle) {
        // Calculate the middle angle of the slice
        double middleAngle = Math.toRadians(startAngle + angle / 2);

        // Calculate line start and end points
        int lineStartX = (int) (x + width / 2 + (width / 2 - 10) * Math.cos(middleAngle)); // End of slice
        int lineStartY = (int) (y + height / 2 - (height / 2 - 10) * Math.sin(middleAngle)); // End of slice
        int lineEndX = (int) (x + width / 2 + (width / 2 + 35) * Math.cos(middleAngle)); // Extended line end
        int lineEndY = (int) (y + height / 2 - (height / 2 + 35) * Math.sin(middleAngle)); // Extended line end

        // Draw the line connecting slice to label
        g2d.setColor(Color.BLACK);
        g2d.drawLine(lineStartX, lineStartY, lineEndX, lineEndY);

        // Prepare the label text
        String label = team + " (" + points + ")";

        // Adjust label position to move it further from the line
        lineEndX += (lineEndX > x + width / 2) ? 10 : -10; // Shift horizontally
        lineEndY += (lineEndY > y + height / 2) ? 10 : -10; // Shift vertically

        // Set the font size and color
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.setColor(Color.BLACK);

        // Draw the label centered
        FontMetrics metrics = g2d.getFontMetrics();
        int labelWidth = metrics.stringWidth(label);
        g2d.drawString(label, lineEndX - labelWidth / 2, lineEndY);
    }
}
