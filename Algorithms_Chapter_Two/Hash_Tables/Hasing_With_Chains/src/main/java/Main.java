import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {
  private static BigInteger size;
  private static final BigInteger k = BigInteger.valueOf(263);
  private static final BigInteger p = BigInteger.valueOf(1_000_000_007);
  private static final BigInteger[] matrix = new BigInteger[15];
  
  static {
    matrix[0] = BigInteger.ONE;
    for (int i = 1; i < matrix.length; i++) {
      matrix[i] = matrix[i - 1].multiply(k);
    }
  }

  public static void main(String[] args) throws IOException {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    //BufferedReader input = new BufferedReader(new FileReader("/home/danil/Documents/Java_Projects/Stepik_Projects/Algorithms_Chapter_Two/Hash_Tables/Hasing_With_Chains/src/main/java/input.txt"));

    size = new BigInteger(input.readLine());
    int n = Integer.parseInt(input.readLine());

    Map<BigInteger, LinkedList<String>> map = new HashMap<>();

    LinkedList<String> list;
    BigInteger hash;
    String word;
    for (int i = 0; i < n; i++) {
      String[] command = input.readLine().split("\\s");
      word = command[1];
      switch (command[0]) {
        case "add":
          hash = hash(word);
          list = map.getOrDefault(hash, new LinkedList<>());
          if (list.isEmpty() || !list.contains(word)) list.addFirst(word);
          map.put(hash, list);
          break;
        case "del":
          hash = hash(word);
          list = map.getOrDefault(hash, null);
          if (!Objects.isNull(list)) {
            for (ListIterator<String> iter = list.listIterator(); iter.hasNext(); ) {
              String element = iter.next();
              if (element.equals(word)) {
                iter.remove();
                break;
              }
            }
            map.put(hash, list);
          }
          break;
        case "find":
          hash = hash(word);
          list = map.getOrDefault(hash, null);
          System.out.println(!Objects.isNull(list) && list.contains(word) ? "yes" : "no");
          break;
        default:
          hash = BigInteger.valueOf(Long.parseLong(word));
          list = map.getOrDefault(hash, null);
          System.out.println(!Objects.isNull(list) ? list.stream().collect(Collectors.joining(" ")) : "");
          break;
      }
    }
  }

  private static BigInteger hash(String word) {
    BigInteger hash = BigInteger.ZERO;
    for (int i = 0; i < word.length(); i++) {
      BigInteger code = BigInteger.valueOf(word.codePointAt(i));
      BigInteger temp1 = matrix[i].multiply(code).mod(p);
      hash = hash.add(temp1).add(p)
          .mod(p);
    }
    BigInteger res = hash.mod(size);
    return res;
  }}
