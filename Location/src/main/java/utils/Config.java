package utils;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Config {
	private static Config con;
	private static final Properties p=new Properties();
	
	public  Config() throws IOException {
		FileReader reader= new FileReader(System.getProperty("user.dir")+"\\src\\main\\resources\\config.properties");
		p.load(reader);	
	}
	
	public static Config getInstance() {
		if(con==null) {
			synchronized (Config.class) {
				try {
					con=new Config();
				}
				catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		return con;
	}
	
	
	
	public String getValue(String key) {
		return System.getProperty(key, p.getProperty(key));
	}

}
