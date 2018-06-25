import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

class Table {
  private int parentId;
  private int records;

  Table(int parentId, int records) {
    this.parentId = parentId;
    this.records = records;
  }

  int getParentId() {
    return parentId;
  }

  void setParentId(int parentId) {
    this.parentId = parentId;
  }

  int getRecords() {
    return records;
  }

  void setRecords(int records) {
    this.records += records;
  }

  void clearRecords() {
    this.records = 0;
  }
}

public class Main  {
  private static int MAX = 0;
  private static Table1[] tables;

  private static int find(int i) {
    if (tables[i].getParentId() != i) {
      tables[i].setParentId(find(tables[i].getParentId()));
    }
    return tables[i].getParentId();
  }

  public static void main(String[] args) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader("/home/danil/Documents/Java_Projects/Stepik_Projects/Algorithms_Chapter_Two/Priority_Queues_And_Disjoint_Set/Table_Unioning/src/main/java/input.txt"));
    //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    String[] temp = reader.readLine().split(" ");
    int tableNumber = Integer.parseInt(temp[0]);
    int unionNumber = Integer.parseInt(temp[1]);
    int[] tableSize = Arrays.stream(reader.readLine().split("\\s")).mapToInt(Integer::parseInt).toArray();

    tables = new Table1[tableNumber];
    for (int i = 0; i < tableNumber; i++) {
      tables[i] = new Table1(i, tableSize[i]);
      MAX = Math.max(MAX, tableSize[i]);
    }

    for (int i = 0; i < unionNumber; i++) {
      int[] dest_src = (Arrays.stream(reader.readLine().split("\\s")).mapToInt(s -> Integer.valueOf(s) - 1).toArray());

      Table1 dest = tables[dest_src[0]];
      Table1 src = tables[dest_src[1]];

      int destId = find(dest.getParentId());
      int srcId = find(src.getParentId());
      dest = tables[destId];
      src = tables[srcId];

      if (destId != srcId) {
        dest.setRecords(src.getRecords());
        src.clearRecords();
        src.setParentId(destId);
        if (dest.getRecords() > MAX) MAX = dest.getRecords();
        tables[destId] = dest;
        tables[srcId] = src;
      }

      System.out.println(MAX);
    }
  }
}
