package com.scheduler.scheduler.config;

import com.scheduler.scheduler.controller.ContextController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.LinkedList;
import java.util.Queue;

@Configuration
@EnableScheduling
public class SchedulerConfig {

    @Autowired
    ContextController contextController;

    private static Queue<ClientContextHolder.CLIENT> contextStack = new LinkedList<>();

    static {
        contextStack.add(ClientContextHolder.CLIENT.CLIENT1);
        contextStack.add(ClientContextHolder.CLIENT.CLIENT2);
        contextStack.add(ClientContextHolder.CLIENT.CLIENT3);
    }

    @Scheduled(fixedDelayString = "${fixedDelay.in.milliseconds}")
    public void executor() {
        ClientContextHolder.CLIENT peeked = contextStack.remove();
        System.out.println("-- Current context name " + peeked);
        ClientContextHolder.setContext(peeked);
        contextController.setDataSource();
        contextStack.add(peeked);
    }
}
