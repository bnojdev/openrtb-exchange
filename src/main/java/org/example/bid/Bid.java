package org.example.bid;

import org.example.dsp.Dsp;

import java.time.Instant;
import java.math.BigDecimal;

public class Bid {

    private final Dsp dsp;
    private final BigDecimal price;
    private Instant bidTime;
    private final String creativeId;

    public Bid(Dsp dsp, BigDecimal price, Instant bidTime, String creativeId) {
        this.dsp = dsp;
        this.price = price;
        this.bidTime = bidTime;
        this.creativeId = creativeId;
    }

    public Dsp getDsp() {
        return dsp;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Instant getBidTime() {
        return bidTime;
    }

    public String getCreativeId() {
        return creativeId;
    }

    public void setBidTime(Instant now) {
        this.bidTime = now;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "dsp=" + dsp +
                ", price=" + price +
                ", bidTime=" + bidTime +
                ", creativeId='" + creativeId + '\'' +
                '}';
    }
}