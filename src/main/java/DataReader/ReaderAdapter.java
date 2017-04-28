package DataReader;
import Data.Data;
import DataReader.ExternalAPI.ExternalAPI;
import twitter4j.TwitterException;
import java.io.FileNotFoundException;

/**
 * This class implements an adapter for reader
 * which extends data reader to read files from
 * multiple data sources.
 * Created by Jeremy on 4/10/17.
 */
public class ReaderAdapter implements Reader {

    /** Advanced data reader for this adpater */
    private AdvanedDataReader reader;


    /**
     * Constructor.
     *
     * @param  type: reader type
     */
    public ReaderAdapter(String type) {
        if (type.equals("Local File")) {
            reader = new LocalFileDataReader();
        } else if (type.equals("Web Page")) {
            reader = new WebPageDataReader();
        } else if (type.equals("External API")) {
            reader = new WebAPIDataReader();
        } else if (type.equals("Sensor")) {
            reader = new SensorDataReader();
        }
    }

    @Override
    public void read(ExternalAPI api, String type, String source, Data data) throws FileNotFoundException {
        if (type.equals("Local File")) {
            reader.readLocalFile(source, data);
        } else if (type.equals("Web Page")) {
            reader.readWebPage(source, data);
        } else if (type.equals("External API")) {
            try {
                reader.readFromAPI(api, source, data);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else if (type.equals("Sensor")) {
            reader.readFromSensor(source, data);
        }
    }
}
