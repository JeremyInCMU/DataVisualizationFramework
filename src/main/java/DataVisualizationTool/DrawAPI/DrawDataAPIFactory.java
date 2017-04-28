package DataVisualizationTool.DrawAPI;
import DataVisualizationTool.Plot.Plot;

/**
 * Created by Jeremy on 4/17/17.
 */
public class DrawDataAPIFactory {
    // DrawAPI factory is singleton
    private static DrawDataAPIFactory ourInstance = new DrawDataAPIFactory();

    public static DrawDataAPIFactory getInstance() { return ourInstance; }

    /**
     * Constructor.
     */
    private DrawDataAPIFactory() {};

    /**
     * Build a draw data api which compatible with the plot type.
     *
     * @param type
     * @param plot
     * @return a draw data api.
     */
    public DrawDataAPI build(String type, Plot plot) {
        if (type.equals("Bar Chart")) {
            return new DrawBarChartAPI(plot);
        } else if (type.equals("Pie Chart")) {
            return new DrawPieChartAPI(plot);
        } else if (type.equals("XY Plot")) {
            return new DrawXYPlotAPI(plot);
        } else if (type.equals("Histogram")) {
            return new DrawHistogramAPI(plot);
        }
        return null;
    }
}
