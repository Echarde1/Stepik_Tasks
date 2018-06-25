import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.LongStream;

public class PythonRemastering {
  static long MAX = 0;
  private static long[] lines;
  private static long[] rank;
  private static long[] parent;

  public static void main(String[] args) throws IOException {
    //BufferedReader reader = new BufferedReader(new FileReader("/home/danil/Documents/Java_Projects/Stepik_Projects/Algorithms_Chapter_Two/Priority_Queues_And_Disjoint_Set/Table_Unioning/src/main/java/input.txt"));
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int[] temp = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
    int n = temp[0];
    int m = temp[1];
    lines = Arrays.stream(reader.readLine().split("\\s")).mapToLong(Long::parseLong).toArray();
    rank =  new long[n];
    parent = LongStream.range(0, n).toArray();
    MAX = Arrays.stream(lines).max().getAsLong();

    for (int i = 0; i < m; i++) {
      temp = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
      int destination = temp[0];
      int source = temp[1];
      merge(destination - 1, source - 1);
    }
  }

  private static long getParent(long table) {
    if (table!= parent[(int) table]) {
      parent[(int) table] = getParent(parent[(int) table]);
    } return parent[(int) table];
  }

  private static void merge(int destination, int source) {
    long realDestination = getParent(destination);
    long realSource = getParent(source);

    if (realDestination == realSource) {
      System.out.println(MAX);
      return;
    }

    if (rank[(int) realDestination] > rank[(int) realSource]) {
      parent[(int) realSource] = realDestination;
      lines[(int) realDestination] = lines[(int) realSource] + lines[(int) realDestination];
      lines[(int) realSource] = 0;
      MAX = Math.max(MAX, lines[(int) realDestination]);
    } else {
      parent[(int) realDestination] = realSource;
      lines[(int) realSource] = lines[(int) realSource] + lines[(int) realDestination];
      lines[(int) realDestination] = 0;
      MAX = Math.max(MAX, lines[(int) realSource]);
      if (rank[(int) realDestination] == rank[(int) realSource]) {
        rank[(int) realSource] += 1;
      }
    }

    System.out.println(MAX);
  }
}
