package be.ninedocteur.apareruntime;

import org.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ApareRuntime extends JFrame {
    public double VERSION;
    public String VERSION_URL = "https://raw.githubusercontent.com/9e-Docteur/ApareProject/master/version.json";
    public ApareRuntime() throws IOException {
        URL url = new URL(VERSION_URL);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        String jsonStr = "";
        String line;
        while ((line = reader.readLine()) != null) {
            jsonStr += line;
        }
        reader.close();

        JSONObject jsonObj = new JSONObject(jsonStr);
        ApareConfig config = new ApareConfig();
        if(config.isConfigFilePresent()){
            VERSION = Double.valueOf(config.getValue("version"));
            String latestVersionStr = jsonObj.getString("version");
            double version = Double.valueOf(latestVersionStr);
            if (version > VERSION) {
                try {
                    ProcessBuilder pb = new ProcessBuilder("java", "-jar", "C:/ApareProject/Runtime/ApareProject " + VERSION + ".jar");

                    Process p = pb.start();

                    int exitCode = p.waitFor();

                    System.out.println("Code de sortie : " + exitCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    ProcessBuilder pb = new ProcessBuilder("java", "-jar", "C:/ApareProject/Runtime/ApareInstaller.jar");

                    Process p = pb.start();

                    int exitCode = p.waitFor();

                    System.out.println("Code de sortie : " + exitCode);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ApareRuntime();
    }
}
