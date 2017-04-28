package DataVisualizationTool.Plot;

import Data.Data;

/**
 * Created by Jeremy on 4/12/17.
 */
public class XYPlot extends Plot{

    /**
     * Constructor.
     *
     * @param title: title of the plot.
     * @param data: data visualized on this plot.
     */
    public XYPlot(String title, Data data){
        super(title, data);
    }

    @Override
    public void draw() { drawAPI.draw(); }
}
