package UserAPI;

import Data.Data;
import Framework.core.DataVisualizationBox;
import Plugin.DataVisualizationPlugin;

/**
 * Created by Jeremy on 5/2/17.
 */
public class PluginSample1 implements DataVisualizationPlugin {
    @Override
    public String getGameName() {
        return null;
    }

    @Override
    public Data getData() {
        return null;
    }

    @Override
    public void onRegister(DataVisualizationBox framework) {

    }

    @Override
    public void onNewBox() {

    }

    @Override
    public boolean isDataSourceAndTypeValid(String source, String type) {
        return false;
    }

    @Override
    public boolean isDataImportSuccessful() {
        return false;
    }

    @Override
    public void dataVisualization() {

    }

    @Override
    public void boxClosed() {

    }
}
