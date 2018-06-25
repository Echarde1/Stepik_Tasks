/*
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Random;

public class Main {

  private BigInteger lastWindowHash;
  private BigInteger patHash;
  private String pattern;

  private char[] patternChars;

  private static final BigInteger p = BigInteger.valueOf(101);
  private BigInteger x;
  */
/**
   * x^(|P|-1) % p
   **//*

  private BigInteger xp;

  private StringBuilder positions = new StringBuilder();
  private String lastWindow;

  private Main(String pattern, String text) {
    this.pattern = pattern;

    patternChars = pattern.toCharArray();

    x = BigInteger.ONE;
    //x = BigInteger.valueOf(263);
    patHash = precomputedHash(pattern);

    lastWindow = text.substring(text.length() - pattern.length());
    lastWindowHash = precomputedHash(lastWindow);

    xp = (x.pow(pattern.length() - 1).mod(p).add(p)).mod(p);
    search(pattern, text);
  }

  public static void main(String[] args) throws IOException {
    BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
//    BufferedReader input = new BufferedReader(new FileReader("/home/danil/Documents/Java_Projects/Stepik_Projects/Algorithms_Chapter_Two/Hash_Tables/Substring_Search/src/main/java/input.txt"));
    String pattern = input.readLine();
    String text = input.readLine();

    new Main(pattern, text);
  }

  private void search(String pattern, String text) {
    int k = text.length() - pattern.length();
    if (lastWindowHash.equals(patHash)) {
      if (compare(lastWindow)) {
        positions.insert(0, " ");
        positions.insert(0, k);
      }
    }

    for (int i = text.length() - pattern.length() - 1, j = 1; i >= 0; i--, j++) {
      String window = text.substring(i, text.length() - j);
      BigInteger windowHash = hash(window, lastWindowHash, lastWindow);

      if (windowHash.equals(patHash)) {
        if (compare(window)) {
          positions.insert(0, " ");
          positions.insert(0, i);
        }
      }

      lastWindowHash = windowHash;
      lastWindow = window;
    }

    System.out.println(positions);
  }

  private boolean compare(String window) {
    int i = 0;
    for (char c : window.toCharArray()) {
      if (c != patternChars[i++]) {
        return false;
      }
    }

    return true;
  }

  private BigInteger hash(String window, BigInteger prevWindowHash, String prevWindow) {
    */
/*BigInteger lastChar = BigInteger.valueOf(prevWindow.charAt(pattern.length() - 1));
    BigInteger firstChar = BigInteger.valueOf(window.codePointAt(0));

    BigInteger subtraction = prevWindowHash.subtract(lastChar.multiply(xp).mod(p)).mod(p);
    BigInteger multiply = subtraction.multiply(x).mod(p);
    BigInteger hash = multiply.add(firstChar);*//*


    //    return hash;

    BigInteger temp = prevWindowHash
        .subtract(BigInteger.valueOf(prevWindow.charAt(pattern.length() - 1))
            .multiply(xp).mod(p)).mod(p).multiply(x).mod(p)
        .add(BigInteger.valueOf(window.codePointAt(0)));

    temp = temp.add(p).mod(p);

    return temp;


  }

  private BigInteger precomputedHash(String pattern) {
//    BigInteger hash = BigInteger.ZERO;
    BigInteger pizdec = BigInteger.ZERO;

    */
/*for (int i = 0; i < pattern.length(); i++) {
      char c = pattern.charAt(i);
//      BigInteger symbol = BigInteger.valueOf(c);
//      BigInteger power = x.pow(i).mod(p);
//      BigInteger multiplication = symbol.multiply(power).mod(p);
//      hash = hash.add(multiplication).mod(p);

      pizdec = pizdec.add(BigInteger.valueOf(c).multiply(x.pow(i).mod(p)).mod(p)).mod(p);
    }*//*


    int i = 0;
    for (char c : pattern.toCharArray()) {
      pizdec = pizdec.add(BigInteger.valueOf(c).multiply((x.pow(i++).mod(p).add(p)).mod(p)).mod(p))
          .mod(p);
    }

    return pizdec;
  }

  private BigInteger randomX() {
    BigInteger prime = BigInteger.probablePrime(31, new Random());
    return prime;
  }


}
*/
