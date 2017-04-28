package DataReader;
import Data.Data;
import DataReader.ExternalAPI.ExternalAPI;
import twitter4j.TwitterException;

import java.io.FileNotFoundException;

/**
 * This class implements data reader reading data from
 * outside data sources. And then store those into a dataset.
 * Created by Jeremy on 4/10/17.
 */
public class DataReader implements Reader {

    // Reader apdater
    private ReaderAdapter adapter;

    @Override
    public void read(ExternalAPI api, String type, String source, Data data) {
        adapter = new ReaderAdapter(type);
        try {
            adapter.read(api, type, source, data);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
