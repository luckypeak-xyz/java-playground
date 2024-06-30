import org.junit.Test;

public class TryFinallyTest {
  @Test
  public void testTryFinally() {
    try {
      foo();
    } finally {
      System.out.println("finally");
    }
  }

  private void foo() {
    throw new RuntimeException("test");
  }
}
