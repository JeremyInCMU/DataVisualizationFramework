package DataVisualizationTool.DataRearrangeTool;

/**
 * Created by Jeremy on 4/23/17.
 */
public class DataRearrangeToolFactory {

    // Data rearrange tool factory is signelton
    private static DataRearrangeToolFactory ourInstance = new DataRearrangeToolFactory();

    /**
     * Initialize data rearrange tool factory in a singleton way.
     * @return instance of data rearrange tool factory.
     */
    public static DataRearrangeToolFactory getInstance() {
        if (ourInstance != null) { return ourInstance; }
        return new DataRearrangeToolFactory();
    }

    /**
     * Constructor.
     */
    private DataRearrangeToolFactory() { }

    /**
     * Build different kinds of data rearrange tools.
     * @param type: the plot with type which will deploy the data rearrange tool.
     * @return data rearrange tool.
     */
    public DataRearrangeTool build(String type) {
        if (type.equals("Histogram")) {
            return new HistogramRearrangeTool();
        } else if (type.equals("Bar Chart")) {
            return new BarChartRearrangeTool();
        } else if (type.equals("XY Plot")) {
            return new XYPlotRearrangeTool();
        } else if (type.equals("Pie Chart")) {
            return new PieChartRearrangeTool();
        }
        return null;
    }
}
