package Listener;

/**
 * Created by Jeremy on 4/13/17.
 */
public interface FrameworkChangeListener {

    /**
     * Notify the box that ready to import data.
     */
    public void readyToImportData();


    /**
     * Notify the core that current data set change.
     */
    public void currentDataSetChange(String dataSetName);
}
