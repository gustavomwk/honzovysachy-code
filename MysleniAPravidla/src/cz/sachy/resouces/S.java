package cz.sachy.resouces;

import java.net.URL;
import java.util.Properties;

public class S {
	private static Properties strings;

	public static void init(String language) {
		URL url = S.class.getResource("strings_" + language + ".txt");
		System.out.println(url);
		strings = new Properties();
		try {
			strings.load(url.openStream());
		} catch (Exception e) {
			e.printStackTrace();
		};
	}
	
	public static String g(String key) {
		try {
			String ret = strings.getProperty(key);
			if (ret == null) return key;
			return ret;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
