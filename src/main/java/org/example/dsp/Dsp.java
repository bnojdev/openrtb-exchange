package org.example.dsp;

import org.example.bid.Bid;
import org.example.bid.BidRequest;

import java.util.Optional;

public interface Dsp {

    String getId();

    String getName();

    Optional<Bid> submitBid(BidRequest request);

}
