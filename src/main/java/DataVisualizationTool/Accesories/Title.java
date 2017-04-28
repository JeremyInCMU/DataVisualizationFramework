package DataVisualizationTool.Accesories;

import DataVisualizationTool.DrawAPI.DrawAPI;
import DataVisualizationTool.Plot.Plot;

/**
 * Created by Jeremy on 4/10/17.
 */
public class Title extends Item {

    /** Name of the item */
    private final static String name = "Title";

    /** Plot to which this item belong */
    private Plot plot;

    /** DrawAPI of this item */
    private DrawAPI drawAPI;

    /** Content of title */
    private String content;

    /**
     * Constructor.
     * @param drawAPI;
     */
    public Title(DrawAPI drawAPI, String title) {
        super(drawAPI);
        content = title;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Plot getPlot() {
        return plot;
    }

    @Override
    public void draw() {
        drawAPI.draw();
    }

    @Override
    public void attach2(Plot plot) {
        this.plot = plot;
    }
}
