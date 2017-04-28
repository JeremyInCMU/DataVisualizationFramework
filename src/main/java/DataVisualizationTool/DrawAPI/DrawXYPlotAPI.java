package DataVisualizationTool.DrawAPI;

import DataVisualizationTool.Plot.Plot;
import Data.Data;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.internal.chartpart.Chart;
import java.util.List;

/**
 * Created by Jeremy on 4/12/17.
 */
public class DrawXYPlotAPI extends DrawDataAPI{
    /**
     * Constructor.
     *
     * @param plot: the xy plot.
     */
    public DrawXYPlotAPI(Plot plot) { super(plot); }

    @Override
    public Chart drawData(Data data) {
        List<String> attributes = data.getAttributes();
        String xAxisTitle = attributes.get(0);
        String yAxisTitle = attributes.get(1);

        XYChart chart = QuickChart.getChart(data.getName(), xAxisTitle, yAxisTitle,
                                            xAxisTitle + "-" + yAxisTitle,
                                            data.getFeatures(xAxisTitle), data.getFeatures(yAxisTitle));

        return chart;
    }
}
