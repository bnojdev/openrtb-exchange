package org.example.dsp;

import org.example.bid.AdType;
import org.example.bid.Bid;
import org.example.bid.BidRequest;
import org.example.bid.Country;

import java.util.Optional;
import java.util.Set;

public interface Dsp {

    String getId();

    String getName();

    Optional<Bid> submitBid(BidRequest request);

    Boolean supports(BidRequest bidRequest);

    Set<Country> getSupportedCountries();

    Set<AdType> getSupportedAdTypes();

}
