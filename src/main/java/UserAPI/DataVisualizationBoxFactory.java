package UserAPI;
import DataReader.DataReader;
import DataReader.ExternalAPI.ExternalAPI;
import DataVisualizationTool.DataRearrangeTool.DataRearrangeTool;
import DataVisualizationTool.DataRearrangeTool.DataRearrangeToolFactory;
import DataVisualizationTool.DrawAPI.DrawDataAPI;
import DataVisualizationTool.DrawAPI.DrawDataAPIFactory;
import DataVisualizationTool.Plot.Plot;
import DataVisualizationTool.Plot.PlotFactory;
import Framework.core.DataVisualizationBox;
import Data.Data;
import Listener.FrameworkChangeListener;

/**
 * Implement the user api.
 * Created by Jeremy on 5/2/17.
 */
public class DataVisualizationBoxFactory implements UserAPI {

    @Override
    public DataVisualizationBox createDataVisualizationBox(String name) {
        return new DataVisualizationBox(name);
    }

    @Override
    public void addPlotFactoryToBox(DataVisualizationBox box, PlotFactory plotFactory) {
        box.setPlotFactory(plotFactory);
    }

    @Override
    public void addDrawDataAPIToBox(DataVisualizationBox box, DrawDataAPIFactory apiFactory) {
        box.setDrawDataAPIFactory(apiFactory);
    }

    @Override
    public void addDataRearrangeToolFactory(DataVisualizationBox box, DataRearrangeToolFactory dataRearrangeToolFactory) {
        box.setDataRearrangeToolFactory(dataRearrangeToolFactory);
    }

    @Override
    public void addFrameworkListenerToBox(DataVisualizationBox box, FrameworkChangeListener listener) {
        box.setFrameworkListener(listener);
    }

    @Override
    public void addExternalAPIToBox(DataVisualizationBox box, String name, ExternalAPI api) {
        box.addAPI(name, api);
    }

    @Override
    public void removeExternalAPIFromBox(DataVisualizationBox box, String name) {
        box.removeAPI(name);
    }

    @Override
    public ExternalAPI getExternalAPIFromBox(DataVisualizationBox box, String name) {
        return box.getAPI(name);
    }

    @Override
    public DataReader getDataReaderFromBox(DataVisualizationBox box) {
        return box.getDataReader();
    }

    @Override
    public PlotFactory getPlotFactoryFromBox(DataVisualizationBox box) {
        return box.getPlotFactory();
    }

    @Override
    public void readDataToBox(DataVisualizationBox box, ExternalAPI api, String type, String source) {
        box.readData(api, type, source);
    }

    @Override
    public void changeBoxCurrentDataSet(String name, DataVisualizationBox box) {
        box.setCurrentData(name);
    }

    @Override
    public Data getCurrentDataSetFromBox(DataVisualizationBox box) {
        return box.getCurrentData();
    }

    @Override
    public Data createDataSet(String name) {
        return new Data(name);
    }

    @Override
    public Data getDataFromBoxByName(DataVisualizationBox box, String name) {
        return box.getDataByName(name);
    }

    @Override
    public void visualizeDataFromBox(DataVisualizationBox box, String type, Data data) {
        box.visualizeData(type, data);
    }

    @Override
    public void addDataReaderToBox(DataVisualizationBox box, DataReader dataReader) {
        box.setDataReader(dataReader);
    }

    @Override
    public Plot getPlot(String type, String name, Data data) {
        return PlotFactory.getInstance().build(type, name, data);
    }

    @Override
    public DataRearrangeTool getDataRearrangeTool(String type) {
        return DataRearrangeToolFactory.getInstance().build(type);
    }

    @Override
    public DrawDataAPI getDrawDataAPI(String type, Plot plot) {
        return DrawDataAPIFactory.getInstance().build(type, plot);
    }
}
