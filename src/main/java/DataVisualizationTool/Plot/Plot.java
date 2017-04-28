package DataVisualizationTool.Plot;
import Data.Data;
import DataVisualizationTool.DrawAPI.DrawAPI;

/**
 * Created by Jeremy on 4/10/17.
 */
public abstract class Plot {

    /** Name of this plot */
    protected String title;

    /** Api to draw the plot */
    protected DrawAPI drawAPI;

    /** Data visualized by the plot */
    protected Data data;

    /**
     * Constructor.
     * @param title: indicate type of this plot.
     * @param data: data visualized on this plot.
     */
    protected Plot(String title, Data data) {
        this.title = title;
        this.data = data;
    }

    /**
     * Set draw api to the plot.
     * @param drawAPI: draw api for the plot.
     */
    public void setDrawAPI(DrawAPI drawAPI) { this.drawAPI = drawAPI; }

    /**
     * Get title of this plot.
     */
    public String geTitle() { return title; }

    /**
     * Set title of this plot.
     */
    public void setTitle(String title) { this.title = title; }

    /**
     * Get data shown on this plot.
     */
    public Data getData() { return data; }

    /**
     * Draw the plot.
     */
    public abstract void draw();
}
