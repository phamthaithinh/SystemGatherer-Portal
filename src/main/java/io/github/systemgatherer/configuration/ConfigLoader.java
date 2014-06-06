package io.github.systemgatherer.configuration;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public enum ConfigLoader {
    INSTANCE;

    public static ConfigLoader getInstance() {
        return INSTANCE;
    }

    public static Config getConfig() {
        return new Gson().fromJson(readFile(), Config.class);
    }

    public static String readFile() {
        StringBuffer sb = new StringBuffer();
        try {
            BufferedReader reader = new BufferedReader(
                    new FileReader(
                            new File("config/config.json")
                    )
            );

            String line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
