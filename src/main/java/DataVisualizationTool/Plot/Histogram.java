package DataVisualizationTool.Plot;
import Data.Data;
/**
 * Created by Jeremy on 4/23/17.
 */
public class Histogram extends Plot {
    /**
     * Constructor.
     *
     * @param title: title of the plot.
     * @param data: data visualized on this plot.
     */
    public Histogram(String title, Data data){
        super(title, data);
    }

    @Override
    public void draw() { drawAPI.draw(); }
}
