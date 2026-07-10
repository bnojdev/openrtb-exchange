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

public class YahooDsp implements Dsp{

    private final List<Campaign> campaigns = new ArrayList<>();

    @Override
    public String getId() {
        return "3";
    }

    @Override
    public String getName() {
        return "Yahoo";
    }

    public YahooDsp() {

        Campaign campaign = new Campaign(
                "y-c1",
                "Yahoo News",
                BigDecimal.valueOf(800),
                BigDecimal.valueOf(5),
                Country.IN,
                AdType.BANNER,
                new RandomBidStrategy()
        );

        campaign.addCreative(
                new Creative(
                        "yahoo-creative-1",
                        250,
                        260,
                        AdType.BANNER,
                        "https://news.yahoo.com"
                )
        );

        campaigns.add(campaign);
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
