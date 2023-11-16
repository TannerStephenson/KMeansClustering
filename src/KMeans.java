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
    public static ArrayList<ArrayList<Double>> clusterOne = new ArrayList<>();
    public static ArrayList<ArrayList<Double>> clusterTwo = new ArrayList<>();
    public static ArrayList<ArrayList<Double>> clusterThree = new ArrayList<>();
    public static ArrayList<ArrayList<Double>> clusterFour = new ArrayList<>();
    public static ArrayList<ArrayList<Double>> clusterFive = new ArrayList<>();
    public static ArrayList<ArrayList<Double>> clusterSix = new ArrayList<>();
    public static ArrayList<Double> centroidOne;
    public static ArrayList<Double> centroidTwo;
    public static ArrayList<Double> centroidThree;
    public static ArrayList<Double> centroidFour;
    public static ArrayList<Double> centroidFive;
    public static ArrayList<Double> centroidSix;
    public static final int MAX_CENTROIDS = 6;
    public static boolean finished = false;

    public static void main(String [] args) {
        setData();
        setRandomSeed();

        for (int i = 0; i < 75; i++) {
            clearAllClusters();
            kMeans(data);
            updateCentroid();
        }

        writeToFile(clusterOne, "ClusterOne");
        writeToFile(clusterTwo, "ClusterTwo");
        writeToFile(clusterThree, "ClusterThree");
        writeToFile(clusterFour, "ClusterFour");
        writeToFile(clusterFive, "ClusterFive");
        writeToFile(clusterSix, "ClusterSix");
    }

    /**
     * Description - Calculates the k-means algorithm.
     * @param data - The entire list of data points from synthetic control data.
     */
    public static void kMeans(ArrayList<ArrayList<Double>> data) {
        double smallestDistance;
        double[] distances = new double[MAX_CENTROIDS];

        for (int i = 0; i < data.size(); i++) {
            int index = 0;
            smallestDistance = 1000000000;
            ArrayList<Double> dataToCompare = data.get(i);
            double dataMean = mean(dataToCompare);
            distances[0] = Math.abs(dataMean - mean(centroidOne));
            distances[1] = Math.abs(dataMean - mean(centroidTwo));
            distances[2] = Math.abs(dataMean - mean(centroidThree));
            distances[3] = Math.abs(dataMean - mean(centroidFour));
            distances[4] = Math.abs(dataMean - mean(centroidFive));
            distances[5] = Math.abs(dataMean - mean(centroidSix));

            for (int j = 0; j < MAX_CENTROIDS; j++) {
                if (distances[j] < smallestDistance) {
                    smallestDistance = distances[j];
                    index = j;
                }
            }
            assignToCluster(index, dataToCompare);
        }
    }

    /**
     * Description - Calculates the mean of an individual data point.
     * @param cluster - The cluster of 60 data points.
     * @return - The mean of the individual data point.
     */
    public static double mean(ArrayList<Double> cluster) {
        double total = 0;
        for (int i = 0; i < cluster.size(); i++) {
            total += cluster.get(i);
        }
        return total / cluster.size();
    }

    /**
     * Description - Returns the new centroid array list.
     * @param cluster - The individual cluster.
     * @return - The new calculated centroid.
     */
    public static ArrayList<Double> calculateNewCentroid(ArrayList<ArrayList<Double>> cluster) {
        int size = cluster.size();
        if (size == 0) {
            return new ArrayList<>();
        }

        int dimension = cluster.get(0).size();
        ArrayList<Double> mean = new ArrayList<>(Collections.nCopies(dimension, 0.0));

        for (ArrayList<Double> dataPoint : cluster) {
            for (int i = 0; i < dimension; i++) {
                mean.set(i, mean.get(i) + dataPoint.get(i));
            }
        }

        for (int i = 0; i < dimension; i++) {
            mean.set(i, mean.get(i) / size);
        }

        return mean;
    }

    /**
     * Description - Assigns each datapoint array to a cluster.
     * @param clusterIndex - The clusters index to be assigned.
     * @param dataPoint - The array of data points.
     */
    public static void assignToCluster(int clusterIndex, ArrayList<Double> dataPoint) {
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

    /**
     * Description - Updates the new centroids.
     */
    public static void updateCentroid() {
        centroidOne = calculateNewCentroid(clusterOne);
        centroidTwo = calculateNewCentroid(clusterTwo);
        centroidThree = calculateNewCentroid(clusterThree);
        centroidFour = calculateNewCentroid(clusterFour);
        centroidFive = calculateNewCentroid(clusterFive);
        centroidSix = calculateNewCentroid(clusterSix);
    }

    /**
     * - Refreshes the clusters.
     */
    public static void clearAllClusters() {
        clusterOne.clear();
        clusterTwo.clear();
        clusterThree.clear();
        clusterFour.clear();
        clusterFive.clear();
        clusterSix.clear();
    }

    /**
     * Description - Writes to our six output files.
     * @param cluster - The cluster to be written.
     * @param filename - The name for the file.
     */
    public static void writeToFile(ArrayList<ArrayList<Double>> cluster, String filename) {
        try (FileWriter writer = new FileWriter(filename + "Output.txt")) {
            for (int i = 0; i < cluster.size(); i++) {
                for (int j = 0; j < 60; j++) {
                    writer.write(String.format("%.4f ", cluster.get(i).get(j)));
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Description - This method is designed to pick 6 random data points to start.
     */
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

    /**
     * Description - Adds all the data points from synthetic_control_data.txt to our arraylist.
     */
    public static void setData(){
        File file = new File("synthetic_control_data.txt");
        try {
            Scanner scan = new Scanner(file);
            while(scan.hasNext()) {
                ArrayList<Double> list = new ArrayList<>();
                for (int i = 0; i < 60; i++) {
                    list.add(scan.nextDouble());
                }
                data.add(list);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Writing to file is complete!");
    }
}
