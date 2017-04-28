package Data;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Data represents a data set reading from outside
 * which will be stored in the framework.
 * Created by Jeremy on 4/11/17.
 */
public class Data<E> implements DataInterface {

    // Data Name
    private final String name;

    // Data Dimension : number of attributes
    private int dim;

    // Data Size : number of features
    private int size;

    // Attributes : attributes of data
    private String[] attributes;

    // Data type : type of data
    private String[] dataType;

    // Features : features of data
    protected Object[][] features;

    /**
     * Constructor.
     *
     * @param name: name of the data.
     */
    public Data(String name) {
        this.name = name;
    }

    @Override
    public void importData(List<List<Object>> data) {
        features = new Object[data.size()][data.get(0).size()];
        for (int i = 0; i < data.size(); i++) {
            for (int j = 0; j < data.get(0).size(); j++) {
                features[i][j] = data.get(i).get(j);
            }
        }
    }

    @Override
    public String getName() { return name; }

    @Override
    public void setSize(int size) { this.size = size; }

    @Override
    public int getSize() { return size; }

    @Override
    public void setDim(int dim) { this.dim = dim; }

    @Override
    public int getDim() { return dim; }

    @Override
    public void setAttributes(String[] attributes) { this.attributes = attributes; }

    @Override
    public List<String> getAttributes() { return new ArrayList<String>(Arrays.asList(attributes)); }

    @Override
    public List<String> getDataType() { return new ArrayList<String>(Arrays.asList(dataType)); }

    @Override
    public List<Object> getFeatures(String attribute) {
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < attributes.length; i++) {
            if (attributes[i].equals(attribute)) {
                for (int j = 0; j < size; j++) {
                    result.add(features[i][j]);
                }
            }
        }
        return result;
    }


}
