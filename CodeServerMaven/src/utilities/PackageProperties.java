package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PackageProperties {

	public static Properties prop = new Properties();
	public static InputStream input = null;
	public PackageProperties() {
		// TODO Auto-generated constructor stub
	}
	public static String GetProperty(String key) throws IOException{
		input = new FileInputStream("config.properties");
		prop.load(input);
		return prop.getProperty(key);
	}

}
