import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class KMeans {

    public static ArrayList<Double> data = new ArrayList<>();
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

    public static void main(String [] args) {
        setData();
        setRandomSeed();
        kMeans(data);
        System.out.println("Cluster 1's size: " + clusterOne.size() + "\n It's Centroid: " + centroidOne);
        System.out.println("Cluster 2's size: " + clusterTwo.size() + "\n It's Centroid: " + centroidTwo);
        System.out.println("Cluster 3's size: " + clusterThree.size() + "\n It's Centroid: " + centroidThree);
        System.out.println("Cluster 4's size: " + clusterFour.size() + "\n It's Centroid: " + centroidFour);
        System.out.println("Cluster 5's size: " + clusterFive.size() + "\n It's Centroid: " + centroidFive);
        System.out.println("Cluster 6's size: " + clusterSix.size() + "\n It's Centroid: " + centroidSix);
    }

    public static void kMeans(ArrayList<Double> data) {
        double smallestDistance;
        double[] distances = new double[MAX_CENTROIDS];

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
    }

    public static void updateCentroid() {
        centroidOne = mean(clusterOne);
        centroidTwo = mean(clusterTwo);
        centroidThree = mean(clusterThree);
        centroidFour = mean(clusterFour);
        centroidFive = mean(clusterFive);
        centroidSix = mean(clusterSix);
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

    // This method is designed to pick 6 random data points to start.
    public static void setRandomSeed() {
        int listSize = data.size();
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
        centroidSix = data.get(randomIndex);
    }

    // Adds all the data points from synthetic_control_data.txt to our arraylist.
    public static void setData(){
        ArrayList<Double> list = new ArrayList<>();
        File file = new File("synthetic_control_data.txt");
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNext()) {
                list.add(scan.nextDouble());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        data = list;
    }
}
