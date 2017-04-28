package DataVisualizationTool.DataRearrangeTool;

import Data.Data;

import java.util.List;

/**
 * Created by Jeremy on 4/23/17.
 */
public interface DataRearrangeTool {

    /**
     * Rearrange original data set to a data set deployed to plot bar chart.
     * @param target: target data set.
     * @param dest: destination data set.
     */
    public void reArrange(List<String> attributes, Data target, Data dest);

    /**
     * Get name of the data rearrange tool.
     * @return name of the data rearrange tool.
     */
    public String getName();
}
