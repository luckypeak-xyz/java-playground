import java.lang.reflect.Method;
import java.util.Objects;

public class TestCustomClassLoader {
	public static void main(String[] args) throws Exception {
		CustomClassLoader customClassLoader = new CustomClassLoader(".");
		Class c = customClassLoader.loadClass("Test");

		if (Objects.nonNull(c)) {
			Object o = c.getDeclaredConstructor().newInstance();
			Method m = c.getMethod("say");
			m.invoke(o);
      System.out.println(c.getClassLoader().toString());
		}
	}
}
