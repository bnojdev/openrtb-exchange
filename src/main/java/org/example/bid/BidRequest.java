package org.example.bid;

import org.example.publisher.Placement;

import java.math.BigDecimal;
import java.util.List;

public class BidRequest {

    private final String requestId;

    private final Placement placement;


    public BidRequest(String requestId, Placement placement) {
        this.requestId = requestId;
        this.placement = placement;
    }

    public String getRequestId() {
        return requestId;
    }

    public Placement getPlacement() {
        return placement;
    }

    @Override
    public String toString() {
        return "BidRequest{" +
                "requestId='" + requestId + '\'' +
                ", placement=" + placement +
                '}';
    }
}