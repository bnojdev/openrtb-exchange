package org.example;

import org.example.auction.Auction;
import org.example.bid.BidRequest;
import org.example.dsp.AmazonDsp;
import org.example.dsp.GoogleDsp;
import org.example.dsp.YahooDsp;
import org.example.exchange.Exchange;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Exchange exchange = new Exchange();

        exchange.registerDsp(new GoogleDsp());
        exchange.registerDsp(new YahooDsp());
        exchange.registerDsp(new AmazonDsp());

        BidRequest bidRequest = new BidRequest("pub1",
                "placement1", 250, 260,
                "IN", BigDecimal.valueOf(ThreadLocalRandom.current()
                .nextInt(1, 4)));

        Auction auction = exchange.createAuction(bidRequest);
        Instant now = Instant.now();
        Thread.sleep(200);
        System.out.println("Auction details : "+auction);
        System.out.println(Duration.between(now, Instant.now()).toMillis() + " ms");
    }
}