import java.io.FileReader;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Properties;

public class Q2 {
	public static void main(String[] args) throws Exception {
		Properties pr = new Properties();
		String path = Q2.class.getResource("config/test.properties").getPath();
//		System.out.println(path);
		path = URLDecoder.decode(path, "utf-8");
		pr.load(new FileReader(path));

		Iterator<Object> it = pr.keySet().iterator();

		while (it.hasNext()) {
			String k = (String) it.next();
			System.out.println(k + ": " + pr.getProperty(k));
		}
//		String love = pr.getProperty("love");
//		String hate = pr.getProperty("hate");

//		System.out.println("love: " + love);
//		System.out.println("hate: " + hate);
	}
}
