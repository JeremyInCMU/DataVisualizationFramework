package Framework.core;

import DataReader.DataReader;
import DataReader.ExternalAPI.ExternalAPI;
import DataVisualizationTool.DataRearrangeTool.DataRearrangeToolFactory;
import DataVisualizationTool.DrawAPI.DrawDataAPIFactory;
import DataVisualizationTool.Plot.PlotFactory;
import Listener.FrameworkChangeListener;
import Data.Data;
/**
 * Created by Jeremy on 4/10/17.
 */
public interface DataVisualizationFramework<E> {

    /**
     * Set data reader for the framework.
     *
     * @param reader: data reader.
     */
    public void setDataReader(DataReader reader);

    /**
     * Set plot factory for the framework.
     *
     * @param factory: plot factory deployed to create different kinds of plots.
     */
    public void setPlotFactory(PlotFactory factory);

    /**
     * Set draw data api for the framework.
     *
     * @param factory: draw data api deployed by the framework to visualize data.
     */
    public void setDrawDataAPIFactory(DrawDataAPIFactory factory);

    /**
     * Set data rearrange tool factory.
     *
     * @param factory: data rearrange tool factory deployed to create data rearrange tool for different plots.
     */
    public void setDataRearrangeTool(DataRearrangeToolFactory factory);

    /**
     * Set framework change listener.
     *
     * @param listener: listener which monitors the framework's change.
     */
    public void setFrameworkListener(FrameworkChangeListener listener);

    /**
     * Get data reader from the framework.
     *
     * @return data reader of the framework.
     */
    public DataReader getDataReader();

    /**
     * Get plot factory of the framework.
     *
     * @return plot factory of the framework.
     */
    public PlotFactory getPlotFactory();

    /**
     * Read data from a data source.
     *
     * @param type: the type of data source (local file, web, api)
     * @param source: data source
     * @param api: api deployed to extract data.
     */
    public void readData(ExternalAPI api, String type, String source);

    /**
     * Visualize data read from data source.
     *
     * @param type: the type of visualization (bar chat, pie chat or x-y plot)
     * @param data: data needs to be visualized
     */
    public void visualizeData(String type, Data data);

    /**
     * Set current selected data set.
     * @param dataSetName : currently selected data set name.
     */
    public void setCurrentData(String dataSetName);

    /**
     * Get current selected data set.
     * @return currently selected data set.
     */
    public Data getCurrentData();

    /**
     * Get data set by name.
     * @param name: name of the data set searched.
     */
    public Data getDataByName(String name);
}
