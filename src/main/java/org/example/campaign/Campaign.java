package org.example.campaign;

import org.example.bid.AdType;
import org.example.bid.BidRequest;
import org.example.bid.Country;
import org.example.creative.Creative;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Campaign {

    private final String id;

    private final String name;

    private BigDecimal budget;

    private final BigDecimal maxBid;

    private CampaignStatus status;

    private final Country country;

    private final AdType adType;

    private final List<Creative> creatives = new ArrayList<>();

    public Campaign(String id,
                    String name,
                    BigDecimal budget,
                    BigDecimal maxBid,
                    Country country,
                    AdType adType) {

        this.id = id;
        this.name = name;
        this.budget = budget;
        this.maxBid = maxBid;
        this.country = country;
        this.adType = adType;
        this.status = CampaignStatus.ACTIVE;
    }

    public void addCreative(Creative creative) {
        creatives.add(creative);
    }

    public List<Creative> getCreatives() {
        return List.copyOf(creatives);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public BigDecimal getMaxBid() {
        return maxBid;
    }

    public CampaignStatus getStatus() {
        return status;
    }

    public Country getCountry() {
        return country;
    }

    public AdType getAdType() {
        return adType;
    }

    public void spend(BigDecimal amount) {
        if (budget.compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient campaign budget");
        }
        budget = budget.subtract(amount);
    }

    public boolean canBid(BidRequest request) {

        if (status != CampaignStatus.ACTIVE) {
            return false;
        }

        if (budget.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }

        if (country != request.getPlacement().getCountry()) {
            return false;
        }

        if (adType != request.getPlacement().getAdType()) {
            return false;
        }

        return findCreative(request).isPresent();
    }

    public Optional<Creative> findCreative(BidRequest request) {

        return creatives.stream()
                .filter(c -> c.getWidth() == request.getPlacement().getWidth())
                .filter(c -> c.getHeight() == request.getPlacement().getHeight())
                .findFirst();
    }

    public boolean matches(BidRequest request) {
        return canBid(request)
                && country == request.getPlacement().getCountry()
                && adType == request.getPlacement().getAdType();
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", budget=" + budget +
                ", maxBid=" + maxBid +
                ", status=" + status +
                '}';
    }
}