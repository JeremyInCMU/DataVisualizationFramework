package DataReader;
import Data.Data;
import DataReader.ExternalAPI.ExternalAPI;
import twitter4j.TwitterException;

import java.io.FileNotFoundException;
/**
 * Created by Jeremy on 4/10/17.
 */
public interface Reader {
    /**
     * Read information from outside.
     *
     * @param  api : deployed to extract data
     * @param  type: data type.
     * @param  source: read from source
     * @param  data: where input data is going to be stored
     */
    public void read(ExternalAPI api, String type, String source, Data data) throws FileNotFoundException;
}
