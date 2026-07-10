package org.example.auction;

import org.example.bid.Bid;

import java.time.Duration;
import java.time.Instant;

public class AuctionResult {

    private final String auctionId;

    private final Bid winningBid;

    private final int totalBids;

    private final Instant startTime;

    private final Instant endTime;

    private final Duration duration;

    public AuctionResult(
            String auctionId,
            Bid winningBid,
            int totalBids,
            Instant startTime,
            Instant endTime) {

        this.auctionId = auctionId;
        this.winningBid = winningBid;
        this.totalBids = totalBids;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = Duration.between(startTime, endTime);
    }

    public String getAuctionId() {
        return auctionId;
    }

    public Bid getWinningBid() {
        return winningBid;
    }

    public int getTotalBids() {
        return totalBids;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public Duration getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "AuctionResult{" +
                "auctionId='" + auctionId + '\'' +
                ", winningBid=" + winningBid +
                ", totalBids=" + totalBids +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                '}';
    }
}
