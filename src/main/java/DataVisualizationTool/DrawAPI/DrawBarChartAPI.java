package DataVisualizationTool.DrawAPI;

import Data.Data;
import DataVisualizationTool.Plot.Plot;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.internal.chartpart.Chart;
import org.knowm.xchart.style.Styler;

import java.util.List;

/**
 * Created by Jeremy on 4/23/17.
 */
public class DrawBarChartAPI extends DrawDataAPI{

    /**
     * Constructor.
     *
     * @param plot: the bar chart plot.
     */
    public DrawBarChartAPI(Plot plot) {
        super(plot);
    }

    @Override
    protected Chart drawData(Data data) {
        // Get parameters of the data set.
        List<String> attributes = data.getAttributes();
        String xAxisTitle = attributes.get(0);
        String yAxisTitle = attributes.get(1);
        // Create Chart
        CategoryChart chart = new CategoryChartBuilder().width(this.width).height(this.height).title("Bar Chart").xAxisTitle(xAxisTitle).yAxisTitle(yAxisTitle).build();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNW);
        chart.getStyler().setHasAnnotations(true);

        // Series
        List<String> xAxisSeries = data.getFeatures(xAxisTitle);
        List<Double> yAxisSeries = data.getFeatures(yAxisTitle);
        chart.addSeries(data.getName(), xAxisSeries, yAxisSeries);

        return chart;
    }
}
