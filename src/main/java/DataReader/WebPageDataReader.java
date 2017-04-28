package DataReader;
import Data.Data;
import DataReader.ExternalAPI.ExternalAPI;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Jeremy on 4/10/17.
 */
public class WebPageDataReader implements AdvanedDataReader {
    @Override
    public void readLocalFile(String source, Data data) {
        return;
    }

    @Override
    public void readWebPage(String source, Data data) {
        // Read from web page with address of url.
        try {
            URL url = new URL(source);
            Scanner scanner = new Scanner(url.openStream());
            String temp = scanner.nextLine();
            String[] attributes;
            String regex;
            if (temp.contains(",")) {
                attributes = temp.split(",");
                regex = ",";
            } else {
                attributes = temp.split(" ");
                regex = " ";
            }
            data.setAttributes(attributes);
            List<List<Object>> contents = new ArrayList<>();
            for (int i = 0; i < attributes.length; i++) {
                contents.add(new ArrayList<>());
            }

            while (scanner.hasNextLine()) {
                String tempString = scanner.nextLine();
                String[] tempArray = tempString.split(regex);
                for (int j = 0; j < tempArray.length; j++) {
                    try {
                        contents.get(j).add(Double.parseDouble(tempArray[j]));
                    } catch (Exception e) {
                        contents.get(j).add(tempArray[j]);
                    }
                }
            }
            data.setSize(contents.get(0).size());
            data.importData(contents);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void readFromAPI(ExternalAPI api, String source, Data data) {
        return;
    }

    @Override
    public void readFromSensor(String source, Data data) {return; }
}
