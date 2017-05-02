package UserAPI;

import Data.Data;
import DataReader.DataReader;
import DataReader.ExternalAPI.ExternalAPI;
import DataVisualizationTool.DataRearrangeTool.DataRearrangeTool;
import DataVisualizationTool.DataRearrangeTool.DataRearrangeToolFactory;
import DataVisualizationTool.DrawAPI.DrawDataAPI;
import DataVisualizationTool.DrawAPI.DrawDataAPIFactory;
import DataVisualizationTool.Plot.Plot;
import DataVisualizationTool.Plot.PlotFactory;
import Framework.core.DataVisualizationBox;
import Listener.FrameworkChangeListener;
import com.sun.tools.doclets.internal.toolkit.util.Extern;

/**
 * This class represents a user api.
 * Created by Jeremy on 5/1/17.
 */
public interface UserAPI {

    /**
     * Get data visualization box which is the core of the
     * data visualization application.
     * @param name : name of the new created data visualization box.
     */
    public DataVisualizationBox createDataVisualizationBox(String name);

    /**
     * Set data reader to a data visualization box.
     * @param box : the box to which a reader will be added.
     * @param dataReader: data reader that will be added to the data visualization box.
     */
    public void addDataReaderToBox(DataVisualizationBox box, DataReader dataReader);

    /**
     * Set plot factory to a box.
     * @param box : the box to which the plot factory will be added.
     * @param plotFactory : plot factory that will be added to the box.
     */
    public void addPlotFactoryToBox(DataVisualizationBox box, PlotFactory plotFactory);

    /**
     * Set draw data api factory to a box.
     * @param box: the box to which the draw data api will be added.
     * @param apiFactory: the draw data api factory will be added to the box.
     */
    public void addDrawDataAPIToBox(DataVisualizationBox box, DrawDataAPIFactory apiFactory);

    /**
     * Add data rearrange tool to a box.
     * @param box: the box to which the data rearrange tool factory will be added.
     * @param dataRearrangeToolFactory : the data rearrange tool factory that will be added to the box.
     */
    public void addDataRearrangeToolFactory(DataVisualizationBox box, DataRearrangeToolFactory dataRearrangeToolFactory);

    /**
     * Add framework listener to a box.
     * @param box : the box to which a framework listener will be added.
     * @param listener : a framework listener that will be added to the box.
     */
    public void addFrameworkListenerToBox(DataVisualizationBox box, FrameworkChangeListener listener);

    /**
     * add new external api for a data visualization box.
     * (User can define the external api by themselves)
     * @param box : the data visualization box to which the external api will be added.
     * @param name : name of the newly added external api.
     * @param api : the external api added to the visualization box.
     */
    public void addExternalAPIToBox(DataVisualizationBox box, String name, ExternalAPI api);

    /**
     * remove an external api from a box by name.
     * @param box : box who owns the external api.
     * @param name : name of the external api expected remove from the box.
     */
    public void removeExternalAPIFromBox(DataVisualizationBox box, String name);

    /**
     * get external api from a box by name.
     * @param box : box who owns the external api.
     * @param name : name of the api expected to extract.
     * @return the expected api.
     */
    public ExternalAPI getExternalAPIFromBox(DataVisualizationBox box, String name);

    /**
     * Get data reader from a data visualization box.
     *  @param box : the box from which the data reader is extracted.
     */
    public DataReader getDataReaderFromBox(DataVisualizationBox box);

    /**
     * Get plot factory from a data visualization box.
     *  @param box : the box from which the plot factory is extracted.
     */
    public PlotFactory getPlotFactoryFromBox(DataVisualizationBox box);

    /**
     * Read data from a data source to a box.
     * @param box : the box to which data will be imported.
     * @param api : the external api will be deployed to read data from data source.
     * @param type : type of the reader will be deployed to read data to the box.
     * @param source : data source.
     */
    public void readDataToBox(DataVisualizationBox box, ExternalAPI api, String type, String source);

    /**
     * Visualize data under a data visualization box.
     * @param box : the box that will be deployed to visualize data.
     * @param type : the way to visualize data (bar chart, pie chart, xy plot)
     * @param data : data set that contains the data to be visualized.
     */
    public void visualizeDataFromBox(DataVisualizationBox box, String type, Data data);

    /**
     * Change current data set of a box.
     * @param box : the box whose current data set is going to be changed.
     * @param name : name of data set.
     */
    public void changeBoxCurrentDataSet(String name, DataVisualizationBox box);

    /**
     * Get current data set from a box.
     * @param box : the box whose current data set will be extracted.
     * @return the data set will be extracted from the data set.
     */
    public Data getCurrentDataSetFromBox(DataVisualizationBox box);

    /**
     * Create new data set.
     * @param name : name of the data set.
     * @return the new created data set whose name equals to the argument value.
     */
    public Data createDataSet(String name);

    /**
     * Get data from a box by name.
     * @param box : the box whose attached data sets will be extracted by name.
     * @param name : name of the data set expected to extract.
     * @return data set whose name is the second argument's value.
     */
    public Data getDataFromBoxByName(DataVisualizationBox box, String name);

    /**
     * Get the plot that will be plotted.
     * @param type : type of the plot.
     * @param name : name of this plot.
     * @param data : data container which contains data for this plot.
     * @return a plot whose type and name are respectively equal to the first two arguments' value.
     * the plot visualize the data set which equals to the third argument.
     */
    public Plot getPlot(String type, String name, Data data);

    /**
     *  Get data rearrange tool factory.
     *  @param type : type of the data rearrange tool
     */
    public DataRearrangeTool getDataRearrangeTool(String type);

    /**
     * Get draw data api.
     * @param type : type of the draw data api. (Bar chart, Pie chart, XY Plot, Histogram and so on)
     * @param plot : the plot that will be drew by this draw data api.
     */
    public DrawDataAPI getDrawDataAPI(String type, Plot plot);
}
