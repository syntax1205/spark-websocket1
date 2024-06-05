import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Config {
    JSONObject config;
    private String configFilePath;
    Config(String configPath){
        configFilePath = configPath;
    }
    JSONObject readConfig(){
        try {
            FileReader fileReader = new FileReader(configFilePath);
            StringBuilder context = new StringBuilder();
            int nextChar;
            while ((nextChar = fileReader.read()) != -1){
                context.append((char) nextChar);
            }
            config = new JSONObject(String.valueOf(context));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return config;
    }
}
