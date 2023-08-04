package ui;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

import static java.awt.Font.*;

// Some of following code is taken from the Bar Chart example in Java Examples from javacodex.com:
// https://www.javacodex.com/Graphics/Bar-Chart
// a bar chart showing the agent names on the x-axis and the number of games played on the y-axis
public class BarChart extends JPanel {
    private double[] values;
    private String[] labels;
    private Color[] colours;
    private String title;
    private double minValue;
    private double maxValue;
    private int panelWidth;
    private int panelHeight;
    private int barWidth;

    private File fontFile;
    private Font font;
    private FontMetrics labelMeasurements;

    private int titleWidth;
    private int labelHeight;
    private int labelWidth;
    private int top;

    // EFFECTS: constructs a new bar chart with a title, labels for axis, values for axis and colours
    public BarChart(String title, String[] labels, double[] values, Color[] colours) {
        this.title = title;
        this.labels = labels;
        this.values = values;
        this.colours = colours;
        barWidth = 120;
        minValue = 0;
        maxValue = 0;
    }

    // EFFECTS: sets the min and max values on the bar chart
    public void setValues() {
        for (double value : values) {
            if (minValue > value) {
                minValue = value;
            }
            if (maxValue < value) {
                maxValue = value;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the chart's dimensions, title/labels
    public void init(Graphics g) {
        Dimension dimension = getSize();
        panelWidth = dimension.width;
        panelHeight = dimension.height - 20;

        fontFile = new File("./Fonts/Valorant Font.ttf");
        try {
            font = Font.createFont(Font.TRUETYPE_FONT, fontFile);
        } catch (FontFormatException | IOException e) {
            System.out.println("Could not find font");
            throw new RuntimeException(e);
        }
        font = font.deriveFont(BOLD, 15);
        labelMeasurements = g.getFontMetrics(font);

        titleWidth = labelMeasurements.stringWidth(title);
        labelHeight = labelMeasurements.getAscent();
        labelWidth = (panelWidth - titleWidth) / 2;
        g.setFont(font);
        g.drawString(title, labelWidth, labelHeight);

        top = labelMeasurements.getHeight();
    }

    // MODIFIES: this
    // EFFECTS: sets the height/width and fills colours of the bars
    public void setScale(Graphics g) {
        double scale = (panelHeight - (top * 2)) / (maxValue - minValue);
        labelHeight = panelHeight - labelMeasurements.getDescent();

        int i = 0;
        for (double value: values) {
            int x = i * barWidth + 1;
            int y = top;
            int height = (int) (values[i] * scale);
            if (values[i] >= 0) {
                y += (int) ((maxValue - values[i]) * scale);
            } else {
                y += (int) (maxValue * scale);
                height = -height;
            }

            g.setColor(colours[i]);
            g.fillRect(x, y, barWidth - 3, height);
            g.setColor(Color.black);
            g.drawRect(x, y, barWidth - 3, height);

            labelWidth = labelMeasurements.stringWidth(labels[i]);
            labelWidth = i * barWidth + (barWidth - labelWidth) / 2;
            g.drawString(labels[i], labelWidth, labelHeight + 15);
            i++;
        }

    }

    // MODIFIES: this
    // EFFECTS: constructs the bar graph
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        setValues();
        init(g);
        setScale(g);
    }
}
