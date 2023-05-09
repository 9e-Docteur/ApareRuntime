package be.ninedocteur.apareruntime;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.json.JSONObject;

public class ApareConfig {

    private static final String CONFIG_PATH = "C:\\ApareProject\\config.json";
    private JSONObject config;

    public ApareConfig() {

    }

    public void checkForConfig(){
        if (isConfigFilePresent()) {
            try {
                String configContent = new String(Files.readAllBytes(Paths.get(CONFIG_PATH)));
                config = new JSONObject(configContent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            createConfigFile();
            config = new JSONObject();
        }
    }

    public boolean isConfigFilePresent() {
        Path path = Paths.get(CONFIG_PATH);
        return Files.exists(path);
    }

    public void createConfigFile() {
        JSONObject emptyConfig = new JSONObject();
        try {
            FileWriter file = new FileWriter(CONFIG_PATH);
            file.write(emptyConfig.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createConfig(String pName, String value) {
        config.put(pName, value);
        writeConfigToFile();
    }

    public void createConfig(String pName, Boolean value) {
        config.put(pName, value);
        writeConfigToFile();
    }

    private void writeConfigToFile() {
        try {
            FileWriter file = new FileWriter(CONFIG_PATH);
            file.write(config.toString());
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String name) {
        return config.getString(name);
    }

}

