import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

public class TestGenerics {
  @Test
  public void test() {}

  private Map<String, ?> getMap(int kind) {
    Map<String, Integer> mapStringInt = new HashMap<>();
    Map<String, String> mapStringString = new HashMap<>();
    return switch (kind) {
      case 1 -> mapStringString;
      default -> mapStringInt;
    };
  }
}
