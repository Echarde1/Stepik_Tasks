import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;
class Main {
  public static int cntr = 0;
  public static Deque<String> deque = new ArrayDeque<>();
  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    int[] H = new int[Integer.parseInt(reader.readLine())];
    String[] s = reader.readLine().split(" ");
    for (int i = 0; i < H.length; i++) {
      H[i] = Integer.parseInt(s[i]);
    }

    for (int i = H.length / 2; i >= 0; i--) {
      //SiftUp(i, H);
      SiftDown(i, H);
    }

    System.out.println(cntr);

    while(deque.size() != 0) {
      System.out.println(deque.pollLast());
    }
  }

  public static int parent(int i) {
    return (int) Math.floor(i / 2);
  }
  public static int leftChild(int i) {
    return 2 * i + 1;
  }
  public static int rightChild(int i) {
    return 2 * i + 2;
  }
  public static void SiftUp(int i, int[] H) {
    while (i > 1 && H[parent(i)] < H[i]) {
      swap(H, parent(i), i);
      i = parent(i);
    }
  }
  public static void swap(int[] H, int i, int j) {
    int temp = H[i];
    H[i] = H[j];
    H[j] = temp;
    String s = String.valueOf(i) + " " + String.valueOf(j);
    deque.push(s);
  }
  public static void SiftDown(int i, int[] H) {
    int maxIndex = i;
    int l = leftChild(i);
    if (l < H.length && H[l] < H[maxIndex]) maxIndex = l;
    int r = rightChild(i);
    if (r < H.length && H[r] < H[maxIndex]) maxIndex = r;
    if (i != maxIndex) {
      swap(H, i, maxIndex);
      cntr++;
      SiftDown(maxIndex, H);
    }
  }
}