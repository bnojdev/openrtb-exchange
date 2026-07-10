package org.example.bid;

import org.example.auction.Auction;
import org.example.dsp.Dsp;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BidCollector {
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    public void collectBidsAsync(Auction auction, List<Dsp> regiDsps) {

        scheduler.schedule(
                auction::close,
                100,
                TimeUnit.MILLISECONDS
        );

        for (Dsp dsp : regiDsps) {
            CompletableFuture.runAsync(() -> {
                dsp.bid(auction.getBidRequest())
                        .ifPresent(response -> {

                            Bid bid = new Bid(
                                    response.getDsp(),
                                    response.getBidPrice(),
                                    Instant.now(),
                                    response.getCreative().getId()
                            );

                            auction.acceptBid(bid);
                        });
            }, executorService);
        }

    }
}
