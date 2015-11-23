import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        FileParser fileParser = new FileParser();

        fileParser.setupData("IanGame6.txt", false);
        Map<ClickInstance, Integer> clickCounter = fileParser.clickCounter;
        double minEntropy;

        int currentLargest = 0;
        for(ClickInstance clickInstance : clickCounter.keySet()){
           if(clickCounter.get(clickInstance) > currentLargest){
               currentLargest = clickCounter.get(clickInstance);
           }
        }

        double largestProb = (double)currentLargest / fileParser.totalClicks;
        minEntropy = Math.log(largestProb)/Math.log(2);

        System.out.println("Min Entropy is when not including click frequency " + -minEntropy);

        fileParser.setupData("IanNormalUsage.txt", true);
        clickCounter = fileParser.clickCounter;

        for(ClickInstance clickInstance : clickCounter.keySet()){
            if(clickCounter.get(clickInstance) > currentLargest){
                currentLargest = clickCounter.get(clickInstance);
            }
        }

        largestProb = (double)currentLargest / fileParser.totalClicks;
        minEntropy = Math.log(largestProb)/Math.log(2);

        System.out.println("Min Entropy is when including click frequency " + -minEntropy);


    }
}
