package org.quarkus;

import io.quarkus.runtime.Startup;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@ApplicationScoped
public class ExampleService {

    private final ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor(x -> new Thread(x, "ExampleServiceThread"));

    private final Database database;

    public ExampleService(Database database) {
        this.database = database;
    }

    @Startup
    void startup() {
        worker.scheduleWithFixedDelay(this::scan, 2000, 10000, TimeUnit.MILLISECONDS);
    }

    void shutdown() {
        System.out.println("Shutdown");
    }

    void scan() {
        database.init();
    }


}
