package DataVisualizationTool.DataRearrangeTool;

import Data.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeremy on 4/23/17.
 */
public class BarChartRearrangeTool implements DataRearrangeTool{
    // Name of the bar chart data rearrange tool
    private final String name = "Bar Chart Data Rearrange Tool";

    @Override
    public void reArrange(List<String> attributes, Data target, Data dest) {
        // Set parameters of the destination data set.
        dest.setSize(target.getSize());
        dest.setDim(attributes.size());
        String[] destAttributes = new String[2];
        destAttributes[0] = attributes.get(0);
        destAttributes[1] = attributes.get(1);
        dest.setAttributes(destAttributes);

        // Copy the value into the data
        List<List<Object>> tempData = new ArrayList<>();

        tempData.add(target.getFeatures(attributes.get(0)));
        tempData.add(target.getFeatures(attributes.get(1)));
        dest.importData(tempData);
    }

    @Override
    public String getName() {
        return name;
    }
}
