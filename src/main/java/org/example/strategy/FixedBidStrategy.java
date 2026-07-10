package org.example.strategy;

import org.example.bid.BidRequest;
import org.example.campaign.Campaign;

import java.math.BigDecimal;

public class FixedBidStrategy implements BidStrategy{
    @Override
    public BigDecimal calculateBid(BidRequest request, Campaign campaign) {
        return campaign.getMaxBid();
    }
}
