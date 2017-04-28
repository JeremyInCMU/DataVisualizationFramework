package DataVisualizationTool.Accesories;

import DataVisualizationTool.DrawAPI.DrawAPI;
import DataVisualizationTool.Plot.Plot;

/**
 * Created by Jeremy on 4/10/17.
 */
public abstract  class Item {

    /** Name of the item */
    private final static String name = "Item";

    /** Plot to which this item belong */
    private Plot plot;

    /** DrawAPI of this item */
    private DrawAPI drawAPI;

    /**
     * Constructor.
     * @param drawAPI;
     */
    public Item(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    /**
     * Get name of the item.
     * @return name of the item.
     */
    public String getName(){
        return name;
    }

    /**
     * Get plot to which the item belongs.
     * @return plot.
     */
    public Plot getPlot(){
        return plot;
    }

    /**
     * Draw the item.
     */
    public abstract void draw();

    /**
     * Attached to a plot.
     */
    public void attach2(Plot plot){
        this.plot = plot;
    }
}
