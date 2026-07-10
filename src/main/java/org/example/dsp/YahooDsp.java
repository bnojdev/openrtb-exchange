package org.example.dsp;

import org.example.bid.*;
import org.example.campaign.Campaign;
import org.example.creative.Creative;

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
                AdType.BANNER
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
                .max(Comparator.comparing(Campaign::getMaxBid))
                .flatMap(campaign ->
                        campaign.findCreative(request)
                                .map(creative ->
                                        new BidResponse(
                                                this,
                                                campaign,
                                                creative,
                                                campaign.getMaxBid()
                                        )
                                ));
    }

    @Override
    public String toString() {
        return getName();
    }
}
