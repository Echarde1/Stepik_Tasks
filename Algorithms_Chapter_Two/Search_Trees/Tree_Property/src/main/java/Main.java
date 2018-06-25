  import java.io.BufferedReader;
  import java.io.FileReader;
  import java.io.IOException;
  import java.io.InputStreamReader;
  import java.util.Arrays;
  import java.util.LinkedList;
  import java.util.List;
  import java.util.stream.Collectors;

  class Node {
    long value, rightId, leftId;
    Node right, left;

    public Node(long value) {
      this.value = value;
      right = left = null;
    }
  }

  public class Main {
    private static List<Integer> list = new LinkedList<>();

    private static boolean check(Node v, long min, long max) {
      if (v == null) return true;
      if (v.value < min || max <= v.value) return false;
      return check(v.left, min, v.value) && check(v.right, v.value, max);
    }

    public static void main(String[] args) throws IOException {
      //BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
      BufferedReader input = new BufferedReader(new FileReader("/home/danil/Documents/Java_Projects/Stepik_Projects/Algorithms_Chapter_Two/Search_Trees/Tree_Property/src/main/java/input.txt"));

      int rowCnt = Integer.parseInt(input.readLine());
      Node[] nodes = new Node[rowCnt];

      //Заполняем руты
      for (int i = 0; i < rowCnt; i++) {
        int[] tmp = Arrays.stream(input.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();
        nodes[i] = new Node(tmp[0]);
        nodes[i].leftId = tmp[1];
        nodes[i].rightId = tmp[2];
      }

      //Заполняем детей
      for (int i = 0; i < rowCnt; i++) {
        Node n = nodes[i];
        n.left = (n.leftId != -1) ? nodes[(int) n.leftId] : null;
        n.right = (n.rightId != -1) ? nodes[(int) n.rightId] : null;
        nodes[i] = n;
      }

      /*if (nodes.length != 0) {
      } else {
        System.out.println("CORRECT");
        return;
      }*/

      System.out.println(nodes.length == 0 || check(nodes[0], Long.MIN_VALUE, Long.MAX_VALUE) ? "CORRECT" : "INCORRECT");
    }
  }
