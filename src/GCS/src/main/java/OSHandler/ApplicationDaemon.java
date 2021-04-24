package OSHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    public Set<String> getActivePrograms() {
        return this.activePrograms;
    }

    private Set<String> getRunningPrograms() throws IOException {
        Set<String> newPrograms = new HashSet<>();
        Process proc = Runtime.getRuntime().exec("ps -eo command");
        InputStream is = proc.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
            newPrograms.add(line);
        }

        notify();
        return newPrograms;
    }

    @Override
    public void run() {
        Set<String> running;

        while (kill = false) {
            this.activePrograms = new HashSet<>(relevantPrograms);

            try {
                running = getRunningPrograms();
                System.out.println(running);
                activePrograms.retainAll(running);
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<String> progs = new ArrayList<>();
        progs.add("spotify");
        progs.add("/usr/lib/firefox/firefox");
        ApplicationDaemon app = new ApplicationDaemon(progs);
        Thread t = new Thread(app);
        t.start();

        for (int i = 0; i < 1000000; i++) {
            System.out.println(app.getActivePrograms());
        }

        t.join();
    }
}
