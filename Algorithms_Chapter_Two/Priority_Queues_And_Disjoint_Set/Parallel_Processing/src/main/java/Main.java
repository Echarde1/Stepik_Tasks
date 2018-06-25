  import java.io.BufferedReader;
  import java.io.FileReader;
  import java.io.IOException;
  import java.io.InputStreamReader;
  import java.util.Arrays;

  class Task implements Comparable<Task> {
    private int cpuN;
    private long processTime, endProcTime;

    public Task(int cpuN) {
      this.cpuN = cpuN;
    }

    public int getCpuN() {
      return cpuN;
    }

    public void setProcessTime(long processTime) {
      this.processTime = processTime;
    }

    public long getEndProcTime() {
      return endProcTime;
    }

    public void setEndProcTime(long endProcTimePrev) {
      this.endProcTime = processTime + endProcTimePrev;

    }

    @Override
    public int compareTo(Task o) {
      int res = Long.compare(this.getEndProcTime(), o.getEndProcTime());
      return (res == 0) ? Integer.compare(this.getCpuN(), o.getCpuN()) : res;
    }

    @Override
    public String toString() {
      return cpuN + " " + endProcTime;

    }
  }

  public class Main {
    private static int CPUNUMBERS, TASKNUMBERS;
    private static int[] processTimes;
    private static Task[] H;

    public static void main(String[] args) throws IOException {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      //BufferedReader reader = new BufferedReader(new FileReader("/home/danil/Documents/Java_Projects/Stepik_Projects/Algorithms_Chapter_Two/Priority_Queues_And_Disjoint_Set/Parallel_Processing/src/main/java/input.txt"));
      String[] tmp = reader.readLine().split("\\s");
      CPUNUMBERS = Integer.parseInt(tmp[0]);
      TASKNUMBERS = Integer.parseInt(tmp[1]);
      processTimes = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

      H = new Task[CPUNUMBERS];

      // создаем кучу.
      for (int i = 0; i < CPUNUMBERS; i++) {
        H[i] = new Task(i);
      }

      // упорядочиваем по времени окончания обработки и по номеру процессора, чтобы определить процессор для след. задачи
      for (int i = CPUNUMBERS / 2; i >= 0; i--) {
        siftDown(i);
      }

      for (int i = 0; i < TASKNUMBERS; i++) {
        System.out.println(H[0]);

        Task t = H[0];
        t.setProcessTime(processTimes[i]);
        t.setEndProcTime(H[0].getEndProcTime());
        H[0] = t;

        siftDown(0);
      }
    }

    public static void siftDown(int i) {
      while (true) {
        int leftChild = 2 * i + 1;
        int rightChild = 2 * i + 2;
        int smallestChild = i;

        if (leftChild < CPUNUMBERS && H[leftChild].compareTo(H[smallestChild]) < 0)
          smallestChild = leftChild;

        if (rightChild < CPUNUMBERS && H[rightChild].compareTo(H[smallestChild]) < 0)
          smallestChild = rightChild;

        if (smallestChild == i) return;

        Task temp = H[i];
        H[i] = H[smallestChild];
        H[smallestChild] = temp;
        i = smallestChild;
      }
    }
  }