package DataVisualizationTool.DataRearrangeTool;

import Data.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jeremy on 4/23/17.
 */
public class HistogramRearrangeTool implements DataRearrangeTool {

    // Name of the bar chart data rearrange tool
    private final String name = "Histogram Data Rearrange Tool";

    @Override
    public void reArrange(List<String> attributes, Data target, Data dest) {
        // Set parameters of the destination data set.
        dest.setSize(target.getSize());
        dest.setDim(attributes.size());
        String[] destAttributes = new String[2];
        destAttributes[0] = attributes.get(0);
        destAttributes[1] = "Count";
        dest.setAttributes(destAttributes);

        // Calculate counts of different values of the attribute.
        List<Object> values = target.getFeatures(attributes.get(0));
        Map<Object, Integer> pairs = new HashMap<>();
        for (Object value : values) {
            if (!pairs.containsKey(value)) {
                pairs.put(value, 1);
            } else {
                pairs.put(value, pairs.get(value) + 1);
            }
        }

        // Copy the value into the data
        List<List<Object>> tempData = new ArrayList<>();
        for (int i = 0; i < destAttributes.length; i++) {
            tempData.add(new ArrayList<Object>());
        }

        for (Map.Entry<Object, Integer> entry : pairs.entrySet()) {
            tempData.get(0).add(entry.getKey());
            tempData.get(1).add(entry.getValue());
        }

        dest.importData(tempData);
    }

    @Override
    public String getName() {
        return name;
    }
}
