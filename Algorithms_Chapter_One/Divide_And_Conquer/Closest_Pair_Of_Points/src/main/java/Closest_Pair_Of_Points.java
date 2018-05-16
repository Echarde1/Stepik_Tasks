import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Closest_Pair_Of_Points {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new FileReader("//home/danil/Documents/Java_Projects/Stepik_Projects/Algorithms_Chapter_One/Divide_And_Conquer/Closest_Pair_Of_Points/src/main/java/input.txt"));
        //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        PointG[] P = new PointG[n];
        for (int i = 0; i < n; i++) {
            String[] temp = input.readLine().split(" ");
            P[i] = new PointG(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }

        System.out.printf("%.9f\n", closest(P, n));
    }

    private static double findMinDistance(PointG[] P, int low,int high) {
        double min = Double.MAX_VALUE;
        for (int i = low; i < high/*- 1*/; ++i) {
            for (int j = i + 1; j < high; ++j) {
                min = Math.min(distance(P[i], P[j]), min);
            }
        }
        return min;
    }

    private static double stripClosest(PointG[] strip, int size, double d) {
        double min = d;

        Arrays.sort(strip, Comparator.comparingLong(o -> o.y));

        for (int i = 0; i < size; ++i) {
            for (int j = i + 1; j < size && (strip[j].y - strip[i].y) < min; ++j) {
                min = Math.min(distance(strip[i], strip[j]), min);
            }
        }

        return min;
    }

    private static double closestUtil(PointG[] P, int low, int high) {
        if (high - low <= 3) return findMinDistance(P, low, high);

        int mid = (high + low) / 2;
        PointG midPoint = P[mid];

        PointG[] Pl = new PointG[mid];
        PointG[] Pr = new PointG[high - mid];

        System.arraycopy(P, 0, Pl, low, mid);
        System.arraycopy(P, mid, Pr, low, high - mid);

        double dl = closestUtil(Pl, 0, Pl.length);
        double dr = closestUtil(Pr, 0, Pr.length);

        double d = Math.min(dl, dr);

        ArrayList<PointG> stripTemp = new ArrayList<>();

        int j = 0;
        for (int i = 0; i < high; i++) {
            if (Math.abs(P[i].x - midPoint.x) < d) {
                stripTemp.add(j, P[i]);
                j++;
            }
        }

        PointG[] strip = new PointG[stripTemp.size()];
        strip = stripTemp.toArray(strip);

        return Math.min(d, stripClosest(strip, j ,d));
    }

    static double closest(PointG[] P, int n) {
        Arrays.sort(P, Comparator.comparingLong(o -> o.x));

        return closestUtil(P, 0, n);
    }

    private static double distance(PointG p1, PointG p2) {
        return Math.sqrt( (p1.x - p2.x) * (p1.x - p2.x) +
                (p1.y - p2.y) * (p1.y - p2.y)
        );
    }

    private static void printPoints(PointG[] P, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(P[i].x + " " + P[i].y);
        }
        System.out.println();
    }
}

class PointG {
    long x;
    long y;

    PointG(long x, long y) {
        this.x = x;
        this.y = y;
    }
}