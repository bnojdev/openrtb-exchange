package org.example.auction;

import org.example.bid.Bid;
import org.example.bid.BidRequest;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Auction {

    private final String auctionId;
    private final BidRequest bidRequest;

    private AuctionStatus status;

    private final List<Bid> bids;

    private Bid winningBid;

    private final Instant startTime;
    private Instant endTime;

    public Auction(String auctionId, BidRequest bidRequest) {
        this.auctionId = auctionId;
        this.bidRequest = bidRequest;
        this.status = AuctionStatus.OPEN;
        this.bids = new ArrayList<>();
        this.startTime = Instant.now();
    }

    public String getAuctionId() {
        return auctionId;
    }

    public BidRequest getBidRequest() {
        return bidRequest;
    }

    public AuctionStatus getStatus() {
        return status;
    }

    public void setStatus(AuctionStatus status) {
        this.status = status;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public Bid getWinningBid() {
        return winningBid;
    }

    public void setWinningBid(Bid winningBid) {
        this.winningBid = winningBid;
    }

    public synchronized void acceptBid(Bid bid) {
        boolean valid = validateBid(bid);
        if (!valid) {
            throw new IllegalArgumentException("Bid is not in valid state");
        }

        bids.add(bid);
        updateWinningBid(bid);
    }

    public boolean validateBid(Bid bid) {

        if (status != AuctionStatus.OPEN) {
            return false;
        }

        if (bid == null) {
            return false;
        }

        return bid.getPrice().compareTo(bidRequest.getPlacement().getFloorPrice()) >= 0;
    }

    public void updateWinningBid(Bid bid) {
        if (winningBid == null) {
            winningBid = bid;
            return;
        }

        int priceComparison = bid.getPrice().compareTo(winningBid.getPrice());

        if (priceComparison > 0) {
            winningBid = bid;
            return;
        }

        if (priceComparison == 0 &&
                bid.getBidTime().isBefore(winningBid.getBidTime())) {

            winningBid = bid;
        }
    }

    @Override
    public String toString() {
        return "Auction{" +
                "auctionId='" + auctionId + '\'' +
                ", bidRequest=" + bidRequest +
                ", status=" + status +
                ", bids=" + bids +
                ", winningBid=" + winningBid +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration= " + getAuctionDuration().toString() +
                '}';
    }

    public synchronized void close() {

        if (status == AuctionStatus.CLOSED) {
            return;
        }

        status = AuctionStatus.CLOSED;
        endTime = Instant.now();
    }

    public Duration getAuctionDuration() {

        if (endTime == null) {
            return Duration.between(startTime, Instant.now());
        }

        return Duration.between(startTime, endTime);
    }
}