package DataReader.ExternalAPI;
import Data.Data;
/**
 * Created by Jeremy on 4/17/17.
 */
public interface ExternalAPI {

    /**
     * Read data from this external api.
     *
     * @param source: deploy this api to read data from the source.
     * @param data: a data structure contains extracted data.
     */
    public void extract(String source, Data data);
}
