import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class KMeans {

    public static ArrayList<ArrayList<Double>> data = new ArrayList<>();
    public static ArrayList<Double> clusterOne = new ArrayList<>();
    public static ArrayList<Double> clusterTwo = new ArrayList<>();
    public static ArrayList<Double> clusterThree = new ArrayList<>();
    public static ArrayList<Double> clusterFour = new ArrayList<>();
    public static ArrayList<Double> clusterFive = new ArrayList<>();
    public static ArrayList<Double> clusterSix = new ArrayList<>();
    public static double centroidOne;
    public static double centroidTwo;
    public static double centroidThree;
    public static double centroidFour;
    public static double centroidFive;
    public static double centroidSix;
    public static final int MAX_CENTROIDS = 6;
    public static boolean finished = false;

    public static void main(String [] args) {
        setData();
        System.out.println(data);
        /*System.out.println(data);
        setRandomSeed();
        do {
            clearAllClusters();
            kMeans(data);
        } while(!finished);
        writeToFile(clusterOne, "ClusterOne");
        writeToFile(clusterTwo, "ClusterTwo");
        writeToFile(clusterThree, "ClusterThree");
        writeToFile(clusterFour, "ClusterFour");
        writeToFile(clusterFive, "ClusterFive");
        writeToFile(clusterSix, "ClusterSix");*/
    }

    public static void kMeans(ArrayList<Double> data) {
        double smallestDistance;
        double[] distances = new double[MAX_CENTROIDS];
        double prevCentroidOne = centroidOne;
        double prevCentroidTwo = centroidTwo;
        double prevCentroidThree = centroidThree;
        double prevCentroidFour = centroidFour;
        double prevCentroidFive = centroidFive;
        double prevCentroidSix = centroidSix;


        for (int i = 0; i < data.size(); i++) {
            int index = 0;
            smallestDistance = 10000000;
            double dataToCompare = data.get(i);
            distances[0] = Math.abs(dataToCompare - centroidOne);
            distances[1] = Math.abs(dataToCompare - centroidTwo);
            distances[2] = Math.abs(dataToCompare - centroidThree);
            distances[3] = Math.abs(dataToCompare - centroidFour);
            distances[4] = Math.abs(dataToCompare - centroidFive);
            distances[5] = Math.abs(dataToCompare - centroidSix);

            for (int j = 0; j < MAX_CENTROIDS; j++) {
                if (distances[j] < smallestDistance) {
                    smallestDistance = distances[j];
                    index = j;
                }
            }
            assignToCluster(index, dataToCompare);
        }

        if(!clusterOne.isEmpty()){
            updateCentroid();
        }

        if (prevCentroidOne == centroidOne && prevCentroidTwo == centroidTwo &&
                prevCentroidThree == centroidThree && prevCentroidFour == centroidFour &&
                prevCentroidFive == centroidFive && prevCentroidSix == centroidSix) {
            finished = true;
        } else {
            finished = false;
        }
    }

    public static double mean(ArrayList<Double> cluster) {
        double total = 0;
        for (int i = 0; i < cluster.size(); i++) {
            total += cluster.get(i);
        }
        return total / cluster.size();
    }
    public static void assignToCluster(int clusterIndex, double dataPoint) {
        switch (clusterIndex) {
            case 0:
                clusterOne.add(dataPoint);
                break;
            case 1:
                clusterTwo.add(dataPoint);
                break;
            case 2:
                clusterThree.add(dataPoint);
                break;
            case 3:
                clusterFour.add(dataPoint);
                break;
            case 4:
                clusterFive.add(dataPoint);
                break;
            case 5:
                clusterSix.add(dataPoint);
                break;
        }
    }

    public static void updateCentroid() {
        centroidOne = mean(clusterOne);
        centroidTwo = mean(clusterTwo);
        centroidThree = mean(clusterThree);
        centroidFour = mean(clusterFour);
        centroidFive = mean(clusterFive);
        centroidSix = mean(clusterSix);
    }

    public static void clearAllClusters() {
        clusterOne.clear();
        clusterTwo.clear();
        clusterThree.clear();
        clusterFour.clear();
        clusterFive.clear();
        clusterSix.clear();
    }

    public static void writeToFile(ArrayList<Double> cluster, String filename) {
        try {
            FileWriter writer = new FileWriter(filename + "Output.txt");
            for(int i = 0; i < cluster.size(); i++) {
                writer.write(String.format("%.4f ", cluster.get(i)));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // This method is designed to pick 6 random data points to start.
    public static void setRandomSeed() {
        /*int listSize = data.size();
        int randomIndex = 0;
        Random random = new Random();

        randomIndex = random.nextInt(listSize);
        centroidOne = data.get(randomIndex);

        randomIndex = random.nextInt(listSize);
        centroidTwo = data.get(randomIndex);

        randomIndex = random.nextInt(listSize);
        centroidThree = data.get(randomIndex);

        randomIndex = random.nextInt(listSize);
        centroidFour = data.get(randomIndex);

        randomIndex = random.nextInt(listSize);
        centroidFive = data.get(randomIndex);

        randomIndex = random.nextInt(listSize);
        centroidSix = data.get(randomIndex);*/
    }

    // Adds all the data points from synthetic_control_data.txt to our arraylist.
    public static void setData(){
        ArrayList<Double> list = new ArrayList<>();
        File file = new File("synthetic_control_data.txt");
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNext()) {
                for (int i = 0; i < 60; i++) {

                }
                if (scan.hasNextDouble()) {
                    list.add(scan.nextDouble());
                } else {
                    data.add(list);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
