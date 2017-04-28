package DataReader;
import Data.Data;
import DataReader.ExternalAPI.ExternalAPI;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * This class implements local file reader which reads data
 * from a local file and then store data into a data structure.
 * Created by Jeremy on 4/10/17.
 */
public class LocalFileDataReader implements  AdvanedDataReader {
    @Override
    public void readLocalFile(String source, Data data) {
        // Read loca files.
        File file = new File(source);
        try {
            Scanner scanner = new Scanner(file);
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
            data.setDim(attributes.length);
            List<List<Object>> contents = new ArrayList<>();

            // Initialize array list that will temporarily contains data.
            for (int i = 0; i < attributes.length; i++) {
                contents.add(new ArrayList<Object>());
            }

            while (scanner.hasNextLine()) {
                String[] tempArray = scanner.nextLine().split(regex);
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
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
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
        return;
    }
}
