import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

  private static int[] parent;
  private static int[] rank;

  public static void main(String[] args) throws IOException {
    //BufferedReader reader = new BufferedReader(new FileReader("/home/danil/Documents/Java_Projects/Stepik_Projects/Algorithms_Chapter_Two/Priority_Queues_And_Disjoint_Set/Auto_Tas_Checking/src/main/java/input.txt"));
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    int[] number_eq_noteq = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
    int numbers = number_eq_noteq[0];
    int equals = number_eq_noteq[1];
    int notEquals = number_eq_noteq[2];
    parent = new int[numbers];
    rank = new int[numbers];

    for (int i = 0; i < numbers; i++) {
      parent[i] = i;
    }

    for (int i = 0; i < equals; i++) {
      int[] tasks = (Arrays.stream(reader.readLine().split("\\s")).mapToInt(s -> Integer.valueOf(s) - 1).toArray());
      int src = find(tasks[0]);
      int dest = find(tasks[1]);
      union(src, dest);
    }

    for (int i = 0; i < notEquals; i++) {
      int[] tasks = (Arrays.stream(reader.readLine().split("\\s")).mapToInt(s -> Integer.valueOf(s) - 1).toArray());
      int src = find(tasks[0]);
      int dest = find(tasks[1]);
      if (src == dest) {
        System.out.println(0);
        return;
      }
    }
    System.out.println(1);
  }

  private static void union(int i_id, int j_id) {

    if (i_id == j_id) return;
    if (rank[i_id] > rank[j_id]) {
      parent[j_id] = i_id;
    } else {
      parent[i_id] = j_id;
      if (rank[i_id] == rank[j_id]) {
        rank[i_id]++;
      }
    }
  }

  private static int find(int i) {
    if (i != parent[i]) {
      parent[i] = find(parent[i]);
    }
    return parent[i];
  }

}
