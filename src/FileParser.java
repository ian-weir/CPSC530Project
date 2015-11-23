import javafx.util.Pair;

import java.io.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class FileParser {
    int startIndex = 0;
    int endIndex = 0;
    int totalClicks = 0;

    Map<ClickInstance, Integer> clickCounter = new HashMap<>();


    public void setupData(String filename, boolean includeFrequency) { //This will get changed to return all our data set up
        String currentData;
        InputStreamReader fileReader;
        ClickInstance clickInstance;

        try {
            InputStream file = new FileInputStream(filename);
            fileReader = new InputStreamReader(file, Charset.defaultCharset());
            BufferedReader lineReader = new BufferedReader(fileReader);
            int count;
            while ((currentData = lineReader.readLine()) != null) {
                clickInstance = getNextClick(currentData, includeFrequency);
                boolean exists = false;

                for (ClickInstance iterator : clickCounter.keySet()) {
                    if (clickInstance.getxCoord() == iterator.getxCoord() &&
                            clickInstance.getyCoord() == iterator.getyCoord() &&
                            clickInstance.getMilliSinceLastClick() == iterator.getMilliSinceLastClick()) {
                        clickInstance = iterator;
                        exists = true;
                        break;
                    }
                }
                if(exists) {
                    clickCounter.put(clickInstance, clickCounter.get(clickInstance) + 1);
                } else {
                    clickCounter.put(clickInstance, 1);
                }
                totalClicks++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private ClickInstance getNextClick(String currentData, boolean includeFrequenc) {
        startIndex = 0;

        endIndex = currentData.indexOf(';');
        int xCoord = Integer.parseInt(currentData.substring(startIndex, endIndex).trim());
        int yCoord = Integer.parseInt(getNextString(';', currentData, false).trim());
        double milliSinceLastClick = (includeFrequenc == true ? Double.parseDouble(getNextString(';', currentData, true).trim()) : -1);

        return new ClickInstance(xCoord, yCoord, milliSinceLastClick);
    }


    private String getNextString(char seperator, String currentData, boolean isLastString) {
        startIndex = currentData.indexOf(seperator, endIndex);
        endIndex = currentData.indexOf(seperator, startIndex + 1);
        if (isLastString) {
            endIndex = currentData.length();
        }
        return currentData.substring(startIndex + 1, endIndex);
    }

}