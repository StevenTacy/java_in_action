package com.java_inaction.virtual_thread;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

public class VirtualThreadImpl {
    public static void main(String[] args) {
        System.out.println(getSystemCores());
        executeMultiVirtualThread();
    }

    private static void executeMultiVirtualThread() {
        final ThreadFactory factory = Thread.ofVirtual().name("routine-", 0).factory();
        try (var executer = Executors.newThreadPerTaskExecutor(factory)) {
            IntStream.range(0, getSystemCores() + 5).forEach(i -> {
                executer.submit(() -> {
                    System.out.println("executing thread routine " + i);
                    try {
                        sleep(Duration.ofSeconds(1));
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                });
            });
        }
    }

    private static void sleep(Duration duration) throws InterruptedException {
        Thread.sleep(duration);
    }

    private static int getSystemCores() {
        return Runtime.getRuntime().availableProcessors();
    }
}
