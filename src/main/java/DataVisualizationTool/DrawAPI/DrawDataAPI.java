package DataVisualizationTool.DrawAPI;
import DataVisualizationTool.Plot.Plot;
import Data.Data;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.internal.chartpart.Chart;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Jeremy on 4/11/17.
 */
public abstract class DrawDataAPI implements DrawAPI {

    // Layer for the drawing drew by the api
    protected JFrame layer;

    // The plot that the api will draw
    protected Plot plot;

    // Height of the plot
    protected int height;

    // Width of the plot
    protected int width;

    // Color of the plot
    protected Color color;

    /**
     * Constructor.
     *
     * @param plot;
     */
    protected DrawDataAPI (Plot plot) { this. plot = plot; }

    @Override
    public void setTitle(String title) { plot.setTitle(title); }

    @Override
    public void setHeight(int height) { this.height = height; }

    @Override
    public void setWidth(int width) { this.width = width;}

    @Override
    public void setColor(Color color) { this.color = color; }

    /**
     * Plot data to a chart.
     *
     * @param data: data which is going to be ploted on the chart.
     * @return the chart visualize those data.
     */
    protected abstract Chart drawData(Data data);

    @Override
    public void draw() {
        Chart chart = drawData(plot.getData());
        XChartPanel<Chart> chartPanel = new XChartPanel<>(chart);
        layer = new JFrame(plot.geTitle());
        layer.add(chartPanel);
        layer.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        layer.pack();
        layer.setResizable(true);
        layer.setVisible(true);
    }
}
