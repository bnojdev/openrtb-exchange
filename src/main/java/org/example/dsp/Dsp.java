package org.example.dsp;

import org.example.bid.*;

import java.util.Optional;
import java.util.Set;

public interface Dsp {

    String getId();

    String getName();

//    Optional<Bid> submitBid(BidRequest request);

    Boolean supports(BidRequest bidRequest);

    Set<Country> getSupportedCountries();

    Set<AdType> getSupportedAdTypes();

    Optional<BidResponse> bid(BidRequest request);

}
