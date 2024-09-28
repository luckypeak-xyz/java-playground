import java.io.*;

public class CustomClassLoader extends ClassLoader {
	private String classpath;

	public CustomClassLoader(String classpath) {
		this.classpath = this.classpath;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			byte[] classByte = getClass(name);
			if (classByte != null) {
				return defineClass(name, classByte, 0, classByte.length);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.findClass(name);
	}

	private byte[] getClass(String className) throws IOException {
		String path = classpath
				+ File.pathSeparator
				+ className.replace(".", File.pathSeparator);
		try (
				InputStream in = new FileInputStream(path);
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				) {
			byte[] buf = new byte[2048];
			int len = 0;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			return  out.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
