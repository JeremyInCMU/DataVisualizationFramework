package DataVisualizationTool.DrawAPI;
import DataVisualizationTool.Plot.Plot;
import Data.Data;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.internal.chartpart.Chart;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jeremy on 4/10/17.
 */
public class DrawPieChartAPI extends DrawDataAPI {

    /**
     * Constructor.
     *
     * @param plot;
     */
    public DrawPieChartAPI(Plot plot) { super(plot); }

    @Override
    public Chart drawData(Data data) {
        List<String> attributes = data.getAttributes();
        Random random = new Random();

        PieChartBuilder builder = new PieChartBuilder();
        builder.width(this.width);
        builder.height(this.height);
        builder.title(this.plot.geTitle());
        PieChart chart = builder.build();

        // Series
        List<String> xAxisSeries = data.getFeatures(attributes.get(0));
        List<Double> yAxisSeries = data.getFeatures(attributes.get(1));

        for (int i = 0; i < data.getSize(); i++) {
            chart.addSeries(xAxisSeries.get(i), yAxisSeries.get(i));
        }
        return chart;
    }
}
