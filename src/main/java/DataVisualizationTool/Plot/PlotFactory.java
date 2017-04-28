package DataVisualizationTool.Plot;
import Data.Data;

/**
 * Created by Jeremy on 4/10/17.
 */
public class PlotFactory {

    private static PlotFactory ourInstance = new PlotFactory();

    public static PlotFactory getInstance() {
        return ourInstance;
    }

    /**
     * Private constructor to make sure this class can not be instantiated.
     */
    private PlotFactory() {}

    /**
     * Build a new plot.
     * @param type: type of plot.
     * @return a new created plot.
     */
    public Plot build(String type, String name, Data data) {
        if (type.equals("Bar Chart")) {
            return new BarChart(name, data);
        } else if (type.equals("Pie Chart")) {
            return new PieChart(name, data);
        } else if (type.equals("XY Plot")) {
            return new XYPlot(name, data);
        } else if (type.equals("Histogram")) {
            return new Histogram(name, data);
        }
        return null;
    }
}
