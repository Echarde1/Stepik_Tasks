import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {


  private static long[][] calculatedMin;
  private static long[][] calculatedMax;
  private static ArrayList<Character> op;

  public static void main(String[] args) throws IOException {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    //BufferedReader input = new BufferedReader(new FileReader("/home/danil/Documents/Java_Projects/Stepik_Projects/Algorithms_Chapter_One/Dynamic_Programming/Placing_Parentheses_Problem/src/main/java/input.txt"));
    String line = input.readLine();
    ArrayList<Long> numbers = new ArrayList<Long>();
    op = new ArrayList<Character>();
    for (int i = 0; i < line.length(); i++) {
      char c = line.charAt(i);
      String ce = String.valueOf(c);
      if (i % 2 == 0) numbers.add(Long.parseLong(ce));
      else op.add(c);
    }
    int n = numbers.size();
    calculatedMin = new long[n][n];
    calculatedMax = new long[n][n];
    System.out.println(getMaxValue(numbers, n));
  }

  public static long cacl(long a, long b, char op) {
    switch (op) {
      case '+' :
        return a + b;
      case '-' :
        return a - b;
    }
    return a * b;
  }

  public static long getMaxValue(ArrayList<Long> numbers, int n) {
    for (int i = 0; i < numbers.size(); i++) {
      calculatedMin[i][i] = numbers.get(i);
      calculatedMax[i][i] = numbers.get(i);
    }
    for (int s = 0; s < n; s++) {
      for (int i = 0; i < n - s - 1; i++) {
        int j = i + s + 1;
        long[] values = minAndMaxValues(i , j);
        calculatedMin[i][j] = values[0];
        calculatedMax[i][j] = values[1];
      }
    }
    System.out.println(Arrays.deepToString(calculatedMax));
    return calculatedMax[0][n - 1];
  }

  public static long[] minAndMaxValues(int i, int j) {
    long minValue = Long.MAX_VALUE;
    long maxValue = Long.MIN_VALUE;
    for (int k = i; k < j; k++) {
      long a = cacl(calculatedMax[i][k], calculatedMax[k + 1][j], op.get(k));
      long b = cacl(calculatedMax[i][k], calculatedMin[k + 1][j], op.get(k));
      long c = cacl(calculatedMin[i][k], calculatedMax[k + 1][j], op.get(k));
      long d = cacl(calculatedMin[i][k], calculatedMin[k + 1][j], op.get(k));

      long minAB = Math.min(a, b);
      long minCD = Math.min(c, d);
      long minABCD = Math.min(minAB, minCD);
      minValue = Math.min(minValue, minABCD);
      long maxAB = Math.max(a, b);
      long maxCD = Math.max(c, d);
      long maxABCD = Math.max(maxAB, maxCD);
      maxValue = Math.max(maxValue, maxABCD);
    }

    return new long[]{minValue, maxValue};
  }
}
