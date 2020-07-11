package app.utils;

public class MathUtils {

  public static double round_till(double num, int till) {
    return Math.round(num * till) / (double) till;
  }

}
