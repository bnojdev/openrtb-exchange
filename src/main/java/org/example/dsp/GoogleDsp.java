package org.example.dsp;

import org.example.bid.*;
import org.example.campaign.Campaign;
import org.example.creative.Creative;
import org.example.strategy.FixedBidStrategy;
import org.example.strategy.RandomBidStrategy;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class GoogleDsp implements Dsp{

    private final List<Campaign> campaigns = new ArrayList<>();

    @Override
    public String getId() {
        return "2";
    }

    @Override
    public String getName() {
        return "Google";
    }

    public GoogleDsp() {

        Campaign campaign1 = new Campaign(
                "g-c1",
                "Google Electronics",
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(8),
                Country.IN,
                AdType.BANNER,
                new FixedBidStrategy()
        );

        campaign1.addCreative(
                new Creative(
                        "g-creative-1",
                        250,
                        260,
                        AdType.BANNER,
                        "https://google.com/electronics"
                )
        );

        Campaign campaign2 = new Campaign(
                "g-c2",
                "Google Fashion",
                BigDecimal.valueOf(2000),
                BigDecimal.valueOf(6),
                Country.IN,
                AdType.BANNER,
                new RandomBidStrategy()
        );

        campaign2.addCreative(
                new Creative(
                        "g-creative-2",
                        250,
                        260,
                        AdType.BANNER,
                        "https://google.com/fashion"
                )
        );

        campaigns.add(campaign1);
        campaigns.add(campaign2);
    }

    @Override
    public Boolean supports(BidRequest request) {

        return getSupportedCountries().contains(
                request.getPlacement().getCountry())
                &&
                getSupportedAdTypes().contains(
                        request.getPlacement().getAdType());
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

    public Optional<BidResponse> bid(BidRequest request) {

        return campaigns.stream()
                .filter(c -> c.canBid(request))
                .map(c -> new CampaignBid(
                        c,
                        c.calculateBid(request)
                ))
                .max(Comparator.comparing(CampaignBid::getBidPrice))
                .flatMap(cb ->
                        cb.getCampaign()
                                .findCreative(request)
                                .map(creative ->
                                        new BidResponse(
                                                this,
                                                cb.getCampaign(),
                                                creative,
                                                cb.getBidPrice()
                                        )
                                ));
    }

    @Override
    public String toString() {
        return getName();
    }
}
