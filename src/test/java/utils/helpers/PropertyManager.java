package utils.helpers;

import java.io.FileReader;
import java.util.Properties;

public class PropertyManager {

    private static PropertyManager propertyManager;
    private static Properties properties;

    public static PropertyManager getInstance() {
        if(propertyManager == null){
            propertyManager = new PropertyManager();
            properties = new Properties();
            try {
                properties.load(new FileReader(ClassLoader.getSystemResource("settings.properties").getPath()));
            } catch(Exception e) {

            }
        }
        return propertyManager;
    }

    public String getProperty(String key) {
        if (properties != null) {
            return properties.getProperty(key);
        }
        return null;
    }
}
