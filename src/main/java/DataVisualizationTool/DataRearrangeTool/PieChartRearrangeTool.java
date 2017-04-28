package DataVisualizationTool.DataRearrangeTool;
import Data.Data;
import java.util.*;

/**
 * Created by Jeremy on 4/24/17.
 */
public class PieChartRearrangeTool implements DataRearrangeTool {
    // Name of the bar chart data rearrange tool
    private final String name = "Pie Chart Data Rearrange Tool";

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
        double sum = 0;
        for (int i = 0; i < tempData.get(1).size(); i++) {
            sum += Double.parseDouble(tempData.get(1).get(i).toString());
        }

        for (int j = 0; j < tempData.get(1).size(); j++) {
            Double element = Double.parseDouble(tempData.get(1).get(j).toString()) / sum;
            tempData.get(1).set(j, element);
        }
        dest.importData(tempData);
    }

    @Override
    public String getName() {
        return name;
    }

}
