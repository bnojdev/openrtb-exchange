package org.example.dsp;

import org.example.bid.*;
import org.example.campaign.Campaign;
import org.example.creative.Creative;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class AmazonDsp implements Dsp{

    private final List<Campaign> campaigns = new ArrayList<>();

    @Override
    public String getId() {
        return "1";
    }

    @Override
    public String getName() {
        return "Amazon";
    }

    public AmazonDsp() {

        Campaign primeCampaign = new Campaign(
                "amazon-c1",
                "Amazon Prime",
                BigDecimal.valueOf(1000),
                BigDecimal.valueOf(7),
                Country.IN,
                AdType.BANNER
        );

        primeCampaign.addCreative(
                new Creative(
                        "amazon-creative-1",
                        250,
                        260,
                        AdType.BANNER,
                        "https://amazon.in/prime"
                )
        );

        Campaign shoppingCampaign = new Campaign(
                "amazon-c2",
                "Amazon Shopping",
                BigDecimal.valueOf(2000),
                BigDecimal.valueOf(9),
                Country.IN,
                AdType.BANNER
        );

        shoppingCampaign.addCreative(
                new Creative(
                        "amazon-creative-2",
                        250,
                        260,
                        AdType.BANNER,
                        "https://amazon.in"
                )
        );

        campaigns.add(primeCampaign);
        campaigns.add(shoppingCampaign);
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
