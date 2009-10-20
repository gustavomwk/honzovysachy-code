package cz.honzovysachy.resouces;

import java.net.URL;
import java.util.Properties;

public class S {
	private static Properties strings;

	public static boolean init(String language) {
		URL url = S.class.getResource("strings_" + language + ".txt");
		strings = new Properties();
		try {
			strings.load(url.openStream());
		} catch (Exception e) {
			String def = "en";
			if (language.equals(def)) return false;
			return init(def);
		};
		return true;
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
