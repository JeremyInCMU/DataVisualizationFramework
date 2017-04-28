package DataVisualizationTool.DrawAPI;

import java.awt.*;

/**
 * Created by Jeremy on 4/11/17.
 */
public interface DrawAPI {
    /**
     * Draw the every details of the item.
     */
    public void draw();

    /**
     * Set name of the plot.
     *
     * @param title: new title of a plot.
     */
    public void setTitle(String title);

    /**
     * Set height of the plot.
     *
     * @param height: height of a plot.
     */
    public void setHeight(int height);

    /**
     * Set width of the plot.
     *
     * @param width: width of a plot.
     */
    public void setWidth(int width);

    /**
     * Set color of the plot.
     *
     * @param color: color of the plot.
     */
    public void setColor(Color color);
}
