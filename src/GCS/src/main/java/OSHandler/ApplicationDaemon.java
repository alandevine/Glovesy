package OSHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApplicationDaemon implements Runnable {
    private Set<String> relevantPrograms;
    private Set<String> activePrograms;
    private boolean kill;

    public ApplicationDaemon(List<String> programs) {
        this.relevantPrograms = new HashSet<>();
        this.activePrograms = new HashSet<>();

        relevantPrograms.addAll(programs);
        this.kill = false;
    }

    private void addProgram(String name) {
        this.activePrograms.add(name);
    }

    public void endProcess() {
        this.kill = true;
    }

    private Set<String> getRunningPrograms() throws IOException {
        Set<String> newPrograms = new HashSet<>();
        Process proc = Runtime.getRuntime().exec("ps -eo command");
        InputStream is = proc.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        while ((line = reader.readLine()) != null)
            newPrograms.add(line);

        return newPrograms;
    }

    @Override
    public void run() {
        BufferedReader reader;
        InputStream stream;
        Process proc;
        String line;


        while (kill = false) {
            this.activePrograms = new HashSet<>(relevantPrograms);

            try {
                activePrograms.retainAll(getRunningPrograms());
            } catch (IOException e) {
                System.err.println(e);
            }
        }

        Thread.sleep();
    }
}
