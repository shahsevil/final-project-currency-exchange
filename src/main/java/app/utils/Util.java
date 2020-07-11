package app.utils;

public class Util {

  public static <T> T getOrDefault(T coming, T defValue) {
    return coming == null || "default".equals(coming) ? defValue : coming;
  }
}
