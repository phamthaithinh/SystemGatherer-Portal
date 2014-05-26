package io.github.systemgatherer.scripts;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CheckDiskTest {

    @Test
    public void runScriptTest() {
        String[] cmd = {
                "/bin/bash",
                "-c",
                "echo output | scripts/check_disk.py / 40 20"
        };

        try {
            String line;
            Process p = Runtime.getRuntime().exec(cmd);
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((line = input.readLine()) != null) {
                System.out.println(line);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
