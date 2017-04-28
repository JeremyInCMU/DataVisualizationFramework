package Framework.core;

import DataReader.ExternalAPI.ExternalAPI;
import DataVisualizationTool.DataRearrangeTool.DataRearrangeTool;
import DataVisualizationTool.DataRearrangeTool.DataRearrangeToolFactory;
import DataVisualizationTool.DrawAPI.DrawDataAPI;
import DataVisualizationTool.DrawAPI.DrawDataAPIFactory;
import DataVisualizationTool.Plot.Plot;
import DataVisualizationTool.Plot.PlotFactory;
import Listener.FrameworkChangeListener;
import DataReader.DataReader;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Data.Data;

/**
 * Implement data visualization framework.
 * Created by Jeremy on 4/10/17.
 */
public class DataVisualizationBox implements DataVisualizationFramework{

    // Name of this application
    private final String name;

    // Map between source and data contents
    private Map<String, Data> map;

    // Name of each data stored in the map
    private List<String> dataName;

    // Data reader attached to this data visualization framework
    private DataReader reader;

    // Plot factory producing different kinds of plots
    private PlotFactory plotFactory;

    // Data visualization factory producing different kinds of data rearrange tools for different types of plots.
    private DataRearrangeToolFactory toolFactory;

    // Draw API for different kinds of plots
    private DrawDataAPIFactory drawAPIFactory;

    // Framework change listener
    private FrameworkChangeListener listener;

    // Current selected data
    private Data curData;

    // Containers for external apis
    private Map<String, ExternalAPI> apis;

    /**
     * Constructor.
     *
     * @param name: name of the data visualization box.
     */
    public DataVisualizationBox(String name) {
        this.name = name;               // Set name of the visualization box.
        map = new HashMap<>();          // Create hash map to contain imported datas.
        dataName = new ArrayList<>();
        apis = new HashMap<>();
    }

    @Override
    public void setDataReader(DataReader reader) {
        this.reader = reader;
    }

    @Override
    public void setPlotFactory(PlotFactory factory) { this.plotFactory = factory; }

    @Override
    public void setDrawDataAPIFactory(DrawDataAPIFactory factory) { this.drawAPIFactory = factory; }

    @Override
    public void setDataRearrangeTool(DataRearrangeToolFactory factory) { this.toolFactory = factory; }

    @Override
    public void setFrameworkListener(FrameworkChangeListener listener) { this.listener = listener; }

    @Override
    public DataReader getDataReader() {
        return reader;
    }

    @Override
    public PlotFactory getPlotFactory() {
        return plotFactory;
    }

    @Override
    public void readData(ExternalAPI api, String type, String source) {
        curData = new Data(source);
        map.put(source, curData);
        dataName.add(source);
        reader.read(api, type, source, curData);
    }

    @Override
    public void visualizeData(String type, Data data) {

        Color color = Color.RED; // Color get from the GUI
        int height = 600; // Height get from the GUI
        int width = 800; // Width get from the GUI
        Plot plot = plotFactory.build(type, data.getName(), data);
        DrawDataAPI api = drawAPIFactory.build(type, plot);
        api.setTitle(data.getName());
        api.setHeight(height);
        api.setWidth(width);
        api.setColor(color);
        api.draw();
    }

    @Override
    public void setCurrentData(String dataSetName) {
        curData = map.get(dataSetName);
    }

    @Override
    public Data getCurrentData() {
        return curData;
    }

    @Override
    public Data getDataByName(String name) {
        return map.get(name);
    }

    /**
     * Add an api to the box container.
     * @param name : name of an api.
     * @param api : external api.
     */
    public void addAPI(String name, ExternalAPI api) {
        if (!apis.containsKey(name)) {
            apis.put(name, api);
        }
    }

    /**
     * Remove an api from the box container.
     * @param name : name of an api.
     */
    public ExternalAPI removeAPI(String name) {
        if (apis.containsKey(name)) {
            return apis.remove(name);
        }
        return null;
    }

    /**
     * Get an api from the api set attached to this data visualization box.
     * @param name: the name of the api.
     */
    public ExternalAPI getAPI(String name) {
        return apis.get(name);
    }

    /**
     * Notifying the box to visualize data.
     * @param type: visualization type.
     * @param dataName: the target data set.
     */
    public void notifyDataVisualization(List<String> attributes, String type, String dataName) {
        DataRearrangeTool dataRearrangeTool= toolFactory.build(type);
        Data dest = new Data(dataName + " for " + type);
        dataRearrangeTool.reArrange(attributes, map.get(dataName), dest);
        visualizeData(type, dest);
    }
}
