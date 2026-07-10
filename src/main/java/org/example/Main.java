package org.example;

import org.example.auction.Auction;
import org.example.bid.AdType;
import org.example.bid.BidRequest;
import org.example.bid.Country;
import org.example.dsp.AmazonDsp;
import org.example.dsp.GoogleDsp;
import org.example.dsp.YahooDsp;
import org.example.exchange.Exchange;
import org.example.publisher.Placement;
import org.example.publisher.Publisher;
import org.example.selector.DefaultDspSelector;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        Exchange exchange = new Exchange(new DefaultDspSelector());

        exchange.registerDsp(new GoogleDsp());
        exchange.registerDsp(new YahooDsp());
        exchange.registerDsp(new AmazonDsp());

        Publisher publisher = new Publisher("pub1", "News18");

        Placement placement = new Placement(
                "placement1",
                250,
                260,
                Country.IN,
                AdType.BANNER,
                BigDecimal.valueOf(
                        ThreadLocalRandom.current().nextInt(1, 4))
        );

        publisher.addPlacement(placement);

        Auction auction = exchange.createAuction(placement);

        Instant now = Instant.now();

        Thread.sleep(200);
        System.out.println("Placement Details: " + placement);
        System.out.println("Auction Details: " + auction.getResult());
        System.out.println(Duration.between(now, Instant.now()).toMillis() + " ms");

    }
}