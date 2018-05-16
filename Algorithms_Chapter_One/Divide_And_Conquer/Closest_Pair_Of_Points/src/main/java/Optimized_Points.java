import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class Optimized_Points {

    public static void main(String[] args) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(input.readLine());
        ArrayList<Point> P = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] temp = input.readLine().split(" ");
            long x = Long.parseLong(temp[0]);
            long y = Long.parseLong(temp[1]);
            P.add( new Point(x, y) );
        }

        System.out.printf("%.9f\n", closest(P, n));
    }

    private static double stripClosest(ArrayList<Point> strip, int size, double d) {
        double min = d;

        for (int i = 0; i < size; ++i) {
            for (int j = i + 1; j < size && (strip.get(j).y - strip.get(i).y) < min; ++j) {
                min = Math.min(distance(strip.get(i), strip.get(j)), min);
            }
        }

        return min;
    }

    private static double findMinDistance(ArrayList<Point> P, int n) {
        double min = Double.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                min = Math.min(distance(P.get(i), P.get(j)), min);
            }
        }
        return min;
    }

    private static double closestUtil(ArrayList<Point> Px, ArrayList<Point> Py, int n) {
        if (n <= 3) return findMinDistance(Px, n);

        ArrayList<Point> Pxr = new ArrayList<>();
        ArrayList<Point> Pxl = new ArrayList<>();

        int mid = n / 2;
        Point midPoint = Px.get(mid);

        Pxl.addAll(Px.subList(0, mid));
        Pxr.addAll(Px.subList(mid + 1, n));

        ArrayList<Point> Pyl = new ArrayList<>();
        ArrayList<Point> Pyr = new ArrayList<>();


        //O(n^2) =(
        for (int i = 0; i < n; i++) {
          if (Pxl.contains(Py.get(i))) {
            Pyl.add( Py.get(i) );
          } else if (Pxr.contains( Py.get(i) )){
            Pyr.add(Py.get(i));
          }
        }
        //

        double dl = closestUtil(Pxl, Pyl, Pyl.size());
        double dr = closestUtil(Pxr, Pyr, Pyr.size());

        double d = Math.min(dl, dr);

        ArrayList<Point> strip = new ArrayList<>();

        int j = 0;
        for (int i = 0; i < Py.size(); i++) {
          if (Math.abs(Py.get(i).x - midPoint.x) < d) {
            strip.add(j++, Py.get(i));
          }
        }

        return Math.min(d, stripClosest(strip, j, d) );
    }

    static double closest(ArrayList<Point> P, int n) {
        ArrayList<Point> Px = new ArrayList<>(P);
        ArrayList<Point> Py = new ArrayList<>(P);
        Px.sort(Comparator.comparingLong(o -> o.x));
        Py.sort(Comparator.comparingLong(o -> o.y));

        return closestUtil(Px, Py, n);
    }

    private static double distance(Point p1, Point p2) {
        return Math.sqrt( (p1.x - p2.x) * (p1.x - p2.x) +
                (p1.y - p2.y) * (p1.y - p2.y)
        );
    }

    private static void printPoints(ArrayList<Point> P, int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(P.get(i).x + " " + P.get(i).y);
        }
        System.out.println();
    }
}

class Point {
    long x;
    long y;

    Point(long x, long y) {
        this.x = x;
        this.y = y;
    }
}