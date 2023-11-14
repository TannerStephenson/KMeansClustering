import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class KMeans {

    public static ArrayList<Double> data = new ArrayList<>();
    public final int AMOUNT_CLUSTERS = 6;

    public static void main(String [] args) {
        setData();
        System.out.println(data);
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
        Collections.sort(list);
        data = list;
    }
}
