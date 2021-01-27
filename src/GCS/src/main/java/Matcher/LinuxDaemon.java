package Matcher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LinuxDaemon {
    private Set<String> relevantPrograms;
    private Set<String> activePrograms;
    private boolean kill;

    public LinuxDaemon(List<String> programs) {
        this.relevantPrograms = new HashSet<>();
        this.activePrograms = new HashSet<>();

        relevantPrograms.addAll(programs);
        this.kill = false;
    }

    private void loop() throws IOException {
        BufferedReader reader;
        InputStream stream;
        Process proc;
        String line;

        Set<String> newPrograms = new HashSet<>();

        while (kill = false) {
            proc = Runtime.getRuntime().exec("ps -eo command");
            stream = proc.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));

            while ((line = reader.readLine()) != null)
                newPrograms.add(line);

            this.activePrograms = new HashSet<>(relevantPrograms);
            activePrograms.retainAll(newPrograms);
        }
    }

    private void addProgram(String name) {
        this.activePrograms.add(name);
    }

    public void endProcess() {
        this.kill = true;
    }
}
