package Data;

import java.util.List;

/**
 * This class represents a data set.
 * Created by Jeremy on 4/11/17.
 */
public interface DataInterface {

    /**
     * Get name of this data.
     * @reutrn name of this data set.
     */
    public String getName();

    /**
     * Set size (number of features) of this data
     * @param size: size of this data set.
     */
    public void setSize(int size);

    /**
     * Get size (number of features) of this data
     * @return size of this data set.
     */
    public int getSize();

    /**
     * Set dimension of ths data set.
     * @param dim: dimension of the data set.
     */
    public void setDim(int dim);

    /**
     * Get dimension of the data set.
     * @return dimension of the data set.
     */
    public int getDim();


    /**
     * Set attributes of the data set.
     * @param attributes: attributes of the data set.
     */
    public void setAttributes(String[] attributes);

    /**
     * Get attributes of the data set.
     * @return a list of attributes of this data set.
     */
    public List<String> getAttributes();

    /**
     * Get attribute type of the data set
     * @return a list contains attribute type of the data set.
     */
    public List<String> getDataType();

    /**
     * Import data contents into this data set.
     * @param data: data contents to be imported.
     */
    public void importData(List<List<Object>> data);


    /**
     * Get a column of data belongs to an attribute
     * @param attribute: an attribute of the data set.
     * @return a list of data belongs to this attribute.
     */
    public List<Object> getFeatures(String attribute);
}
