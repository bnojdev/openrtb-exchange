package org.example.strategy;

import org.example.bid.BidRequest;
import org.example.campaign.Campaign;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;

public class RandomBidStrategy implements BidStrategy {

    @Override
    public BigDecimal calculateBid(
            BidRequest request,
            Campaign campaign) {

        int max = campaign.getMaxBid().intValue();

        return BigDecimal.valueOf(
                ThreadLocalRandom.current().nextInt(1, max + 1)
        );
    }
}
