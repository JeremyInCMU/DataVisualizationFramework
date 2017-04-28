package DataReader;
import Data.Data;
import DataReader.ExternalAPI.ExternalAPI;

/**
 * Created by Jeremy on 4/10/17.
 */
public class SensorDataReader implements AdvanedDataReader {
    @Override
    public void readLocalFile(String source, Data data) {
        return;
    }

    @Override
    public void readWebPage(String source, Data data) {
        return;
    }

    @Override
    public void readFromAPI(ExternalAPI api, String source, Data data) {
        return;
    }

    @Override
    public void readFromSensor(String source, Data data) {
        // Read data from sensor.
        return;
    }
}
