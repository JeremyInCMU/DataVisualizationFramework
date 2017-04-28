package DataReader;
import Data.Data;
import DataReader.ExternalAPI.ExternalAPI;
import twitter4j.TwitterException;

import java.io.FileNotFoundException;

/**
 * Created by Jeremy on 4/10/17.
 */
public interface AdvanedDataReader {
    /**
     * Read information from local file.
     *
     * @param source: data source.
     */
    public void readLocalFile(String source, Data data) throws FileNotFoundException;

    /**
     * Read information from a web page.
     *
     * @param source: data source.
     */
    public void readWebPage(String source, Data data);

    /**
     * Read information through an api.
     *
     * @param source: data source
     */
    public void readFromAPI(ExternalAPI api, String source, Data data) throws TwitterException;

    /**
     * Read information from a sensor.
     *
     * @param source: data source
     */
    public void readFromSensor(String source, Data data);
}
