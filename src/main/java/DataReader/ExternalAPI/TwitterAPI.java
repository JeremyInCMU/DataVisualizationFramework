package DataReader.ExternalAPI;
import Data.Data;
import jdk.nashorn.internal.runtime.regexp.joni.Regex;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.*;

/**
 * Created by Jeremy on 4/17/17.
 */
public class TwitterAPI implements ExternalAPI {

    // Characteristics of the twitter api
    private Twitter twitter;
    private Query query;

    @Override
    public void extract(String source, Data data) {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("3iPIcinmmVaL60RraU4PjbdDN")
                .setOAuthConsumerSecret("CcK3yZaivocyshbePuoVsBXE4wtLATjyjKrjeeMonw7Sqkg5Nz")
                .setOAuthAccessToken("857695890156523520-mOsLwdQrXI86VHstsQFQ5Dy2CMteSd7")
                .setOAuthAccessTokenSecret("Lp3u9dmM8bMMU3t0vBAYMgYgnN671nnrtQcazSDtglw4S");
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
        query = new Query(source);
        QueryResult result = null;
        try {
            result = twitter.search(query);
        } catch (TwitterException e) {
            System.out.println(e.getMessage());
        }

        wordCounts(result.getTweets(), data);
    }
        
    /**
     * Word counts.
     * @param data : data container which will contain the word count data.
     */
    private void wordCounts(List<Status> statuses, Data data) {
        String[] attributes = new String[2];
        attributes[0] = "Word";
        attributes[1] = "count";
        Map<String, Integer> map = new HashMap<>();
        String regex = "[^A-Za-z]";

        // Rearrange the text extracted through twitter.
        for (Status status : statuses) {
            String text = status.getText();
            String[] textArray = text.split(regex);
            for (int i = 0; i < textArray.length; i++) {
                if (textArray[i] == null || textArray[i].length() == 0) { continue; }
                if (map.get(textArray[i]) != null) {
                    map.put(textArray[i], map.get(textArray[i]) + 1);
                } else if (map.get(textArray[i]) == null) {
                    map.put(textArray[i], 1);
                }
            }
        }

        // Create a data object.
        data.setAttributes(attributes);
        data.setDim(attributes.length);
        List<List<Object>> contents = new ArrayList<>();

        // Initialize array list that will temporarily contains data.
        for (int i = 0; i < attributes.length; i++) {
            contents.add(new ArrayList<Object>());
        }

        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair =  (Map.Entry) it.next();
            contents.get(0).add(pair.getKey());
            contents.get(1).add(pair.getValue());
            it.remove();
        }
        data.setSize(contents.get(0).size());
        data.importData(contents);
    }
}
