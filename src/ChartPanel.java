import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class ChartPanel extends JPanel {
    private static final int MARGIN = 50;
    private static final int CHART_DIAMETER = 250; // Set a smaller diameter for better fitting
    private Map<String, Integer> teamPointsMap;

    public ChartPanel(List<Player> players) {
        // Aggregate total points by team
        teamPointsMap = players.stream()
                .collect(Collectors.groupingBy(Player::getTeam,
                        Collectors.summingInt(Player::getPoints)));

        setPreferredSize(new Dimension(500, 500)); // Set panel size
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the title
        drawTitle(g);

        // Draw the pie chart
        drawPieChart(g);
    }

    private void drawTitle(Graphics g) {
        // Set the font to bold and draw the title closer to the chart
        g.setFont(new Font("SansSerif", Font.BOLD, 16));
        g.setColor(Color.BLACK);
        String title = "Total Points by Team";
        int titleWidth = g.getFontMetrics().stringWidth(title);
        g.drawString(title, (getWidth() - titleWidth) / 2, 40); // Moved closer to the chart
    }

    private void drawPieChart(Graphics g) {
        if (teamPointsMap.isEmpty()) return;

        // Calculate total points
        int totalPoints = teamPointsMap.values().stream().mapToInt(Integer::intValue).sum();

        // Set initial values for drawing the pie chart
        int startAngle = 0;
        int x = (getWidth() - CHART_DIAMETER) / 2;
        int y = (getHeight() - CHART_DIAMETER) / 2 + 20; // Adjusted for title

        // Generate colors for each team
        List<Color> teamColors = generateColors(teamPointsMap.size());
        int colorIndex = 0;

        // Draw each team's pie slice
        for (Map.Entry<String, Integer> entry : teamPointsMap.entrySet()) {
            String team = entry.getKey();
            int points = entry.getValue();

            // Calculate the angle for the current team
            int arcAngle = (int) Math.round((double) points / totalPoints * 360);

            // Set color and draw the arc
            g.setColor(teamColors.get(colorIndex++));
            g.fillArc(x, y, CHART_DIAMETER, CHART_DIAMETER, startAngle, arcAngle);

            // Draw the team label
            drawLabel(g, x, y, CHART_DIAMETER, startAngle, arcAngle, team, points);

            // Update start angle for the next slice
            startAngle += arcAngle;
        }
    }

    private void drawLabel(Graphics g, int x, int y, int diameter, int startAngle, int arcAngle, String team, int points) {
        // Calculate label position at the middle of the arc
        int labelAngle = startAngle + arcAngle / 2;
        double radians = Math.toRadians(labelAngle);

        // Set base radius for label placement
        int labelRadius = diameter / 2 + 50;

        // Calculate label coordinates
        int labelX = x + diameter / 2 + (int) ((labelRadius + 10) * Math.cos(radians));
        int labelY = y + diameter / 2 - (int) ((labelRadius + 10) * Math.sin(radians));

        // Draw line from slice to label
        int lineStartX = x + diameter / 2 + (int) ((diameter / 2) * Math.cos(radians));
        int lineStartY = y + diameter / 2 - (int) ((diameter / 2) * Math.sin(radians));
        g.setColor(Color.BLACK);
        g.drawLine(lineStartX, lineStartY, labelX, labelY); // Proper line to label

        // Draw the label text
        g.setColor(Color.BLACK);
        g.setFont(new Font("SansSerif", Font.PLAIN, 10));
        g.drawString(team + " (" + points + ")", labelX - 30, labelY + 5);
    }

    // Generate distinct colors for the teams
    private List<Color> generateColors(int numberOfColors) {
        List<Color> colors = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < numberOfColors; i++) {
            colors.add(new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256)));
        }
        return colors;
    }
}
