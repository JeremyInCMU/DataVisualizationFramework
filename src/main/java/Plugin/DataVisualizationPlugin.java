package Plugin;
import Data.Data;
import Framework.core.DataVisualizationBox;

/**
 * Data visualization plugin interface is deployed by plug-ins
 * to implement plug-ins with {@link Framework.core.DataVisualizationBox}
 * data visualization box (Framework)
 * Created by Jeremy on 4/18/17.
 */
public interface DataVisualizationPlugin {


    /**
     * Gets the name of the plug-in.
     */
    String getGameName();

    /**
     * Gets data of the plug-in.
     */
    Data getData();

    /**
     * Called (only once) when the plug-in is first registered with the
     * framework, giving the plug-in a chance to perform any initial set-up
     * before the visualization box has begun (if necessary).
     *
     * @param framework The {@link DataVisualizationBox} instance with which the plug-in
     * was registered.
     */
    void onRegister(DataVisualizationBox framework);

    /**
     * Called when a new box is about to begin.
     */
    void onNewBox();

    /**
     * Return true if data source and type are valid, false if not.
     * @param  source: data source.
     * @param  type: the type of data source (local file, webpage, api)
     * @return true if data source and type are valid, false if not.
     */
    boolean isDataSourceAndTypeValid(String source, String type);

    /**
     * Return true if data successfully imported from outside data source
     * to the visualiztion box.
     */
    boolean isDataImportSuccessful();

    /**
     * Called when visualize data.
     */
    void dataVisualization();

    /**
     * Called when the plugin is closed and allowing plugin to do any final clean up.
     */
    void boxClosed();
}
