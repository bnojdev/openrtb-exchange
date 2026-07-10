package org.example.strategy;

import org.example.bid.BidRequest;
import org.example.campaign.Campaign;

import java.math.BigDecimal;

public interface BidStrategy {
    BigDecimal calculateBid(
            BidRequest request,
            Campaign campaign
    );
}
