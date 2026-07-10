package org.example.dsp;

import org.example.bid.AdType;
import org.example.bid.Bid;
import org.example.bid.BidRequest;
import org.example.bid.Country;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class GoogleDsp implements Dsp{
    @Override
    public String getId() {
        return "2";
    }

    @Override
    public String getName() {
        return "Google";
    }

    @Override
    public Optional<Bid> submitBid(BidRequest request) {
        BigDecimal bidPrice = BigDecimal.valueOf(ThreadLocalRandom.current()
                .nextInt(1, 11));

        if (bidPrice.compareTo(request.getPlacement().getFloorPrice()) < 0) {
            return Optional.empty();
        }

        Bid bid = new Bid(
                this,
                bidPrice,
                Instant.now(),
                "google-creative-1"
        );

        return Optional.of(bid);
    }

    @Override
    public Boolean supports(BidRequest bidRequest) {
        return null;
    }

    @Override
    public Set<Country> getSupportedCountries() {
        return Set.of(Country.IN, Country.US, Country.UK);
    }

    @Override
    public Set<AdType> getSupportedAdTypes() {
        return Set.of(
                AdType.BANNER,
                AdType.VIDEO,
                AdType.NATIVE
        );
    }

    @Override
    public String toString() {
        return getName();
    }
}
