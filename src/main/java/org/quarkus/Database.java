package org.quarkus;

import io.vertx.pgclient.PgPool;
import io.vertx.sqlclient.Pool;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.concurrent.ExecutionException;

@ApplicationScoped
public class Database {

    Pool client;

    public Database(PgPool client) {
        this.client = client;
    }

    void init() {
        try {
            client.query("SELECT 1").execute().toCompletionStage().toCompletableFuture().get();
            System.out.println("test");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
