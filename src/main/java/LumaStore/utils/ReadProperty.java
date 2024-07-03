package LumaStore.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import LumaStore.utilConstants.ConfigProp;

public final class ReadProperty {

	private ReadProperty() {
		// Private constructor to prevent instantiation
	}

	private static Properties prop = new Properties();

	static {
		try {
			FileInputStream propfile = new FileInputStream("./src/test/resources/config.properties");
			prop.load(propfile);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static String getValue(ConfigProp congfigprop) throws Exception {
		String key = congfigprop.getKey();
		String value = prop.getProperty(key);
		
		if (Objects.isNull(value)) {
			throw new Exception("Property name "+key+" is not found");
		}
		return value;
	}
}