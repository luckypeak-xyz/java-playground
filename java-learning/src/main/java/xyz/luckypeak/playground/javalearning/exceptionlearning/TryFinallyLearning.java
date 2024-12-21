package xyz.luckypeak.playground.javalearning.exceptionlearning;

public class TryFinallyLearning {

  public static void main(String[] args) {
    testTryFinally();
  }

  public static void testTryFinally() {
    try {
      foo();
    } finally {
      System.out.println("finally");
    }
  }

  private static void foo() {
    throw new RuntimeException("test");
  }
}
