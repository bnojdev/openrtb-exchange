package org.example.dsp;

import org.example.bid.Bid;
import org.example.bid.BidRequest;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

public class GoogleDsp implements Dsp{
    @Override
    public String getId() {
        return "2";
    }

    @Override
    public String getName() {
        return "Google";
    }

    @Override
    public Optional<Bid> submitBid(BidRequest request) {
        BigDecimal bidPrice = BigDecimal.valueOf(ThreadLocalRandom.current()
                .nextInt(1, 11));

        if (bidPrice.compareTo(request.getFloorPrice()) < 0) {
            return Optional.empty();
        }

        Bid bid = new Bid(
                this,
                bidPrice,
                Instant.now(),
                "google-creative-1"
        );

        return Optional.of(bid);
    }

    @Override
    public String toString() {
        return getName();
    }
}
