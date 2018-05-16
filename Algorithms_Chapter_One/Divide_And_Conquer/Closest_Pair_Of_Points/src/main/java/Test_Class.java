import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test_Class {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scn = new Scanner(new File("/home/danil/Documents/Java_Projects/Stepik_Projects/Algorithms_Chapter_One/Divide_And_Conquer/Closest_Pair_Of_Points/src/main/java/input.txt"));
        double[] answers = new double[]{4.000000000, 1.414213562, 1.000000000, 1.000000000, 1.414213562, 5.000000000};
        int k = 0;
        while(scn.hasNext()) {
            scn.nextLine();
            int n = Integer.parseInt(scn.nextLine());
            //Point[] P = new Point[n];
            ArrayList<Point> P = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                String[] temp = scn.nextLine().split(" ");
                P.add( new Point(Integer.parseInt(temp[0]), Integer.parseInt(temp[1])) );
            }
            String s1 = String.format("%.9f", Optimized_Points/*Closest_Pair_Of_Points*/.closest(P, n));
            String s2 = String.format("%.9f", answers[k]);
            System.out.println(s1.equals(s2));
            k++;
        }
    }
}
