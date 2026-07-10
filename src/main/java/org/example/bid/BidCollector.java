package org.example.bid;

import org.example.auction.Auction;
import org.example.dsp.Dsp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class BidCollector {
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private final ScheduledExecutorService scheduler =
            Executors.newSingleThreadScheduledExecutor();

    public void collectBids(Auction auction, List<Dsp> registeredDsps) {

        for (Dsp dsp : registeredDsps) {
            dsp.submitBid(auction.getBidRequest())
                    .ifPresent(auction::acceptBid);
        }
    }

    public void collectBidsAsync(Auction auction, List<Dsp> regiDsps) {
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        scheduler.schedule(
                auction::close,
                100,
                TimeUnit.MILLISECONDS
        );

        for (Dsp dsp : regiDsps) {
            CompletableFuture.runAsync(() -> {
                dsp.submitBid(auction.getBidRequest())
                        .ifPresent(auction::acceptBid);
            }, executorService);
        }

//        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
//                .thenRun(auction::close);
    }
}
