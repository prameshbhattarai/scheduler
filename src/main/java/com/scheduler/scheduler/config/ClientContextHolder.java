package com.scheduler.scheduler.config;

public class ClientContextHolder {

    public enum CLIENT {CLIENT1, CLIENT2, CLIENT3};

    private static final ThreadLocal<CLIENT> context = new ThreadLocal<>();

    // this function will be set by controller
    public static void setContext(CLIENT client) {
        context.set(client);
    }

    public static CLIENT getContext() {
        return context.get();
    }

    public static void clearContext() {
        context.remove();
    }
}
