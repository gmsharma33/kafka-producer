package com.example.demo.wiki;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

import java.net.URI;
import java.util.concurrent.TimeUnit;

public class WikiMediaProducer {

    public static void main(String[] args) throws InterruptedException {
//        EventHandler

        EventHandler eventHandler = new WikiMediaHandler();
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        EventSource source = builder.build();

        source.start();

        TimeUnit.MINUTES.sleep(5);
    }

}
