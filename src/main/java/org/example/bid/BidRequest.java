package org.example.bid;

import java.math.BigDecimal;
import java.util.List;

public class BidRequest {

    private final String publisherId;
    private final String placementId;

    private final int width;
    private final int height;

    private final Country country;

    private final BigDecimal floorPrice;

    private final AdType adType;

    public BidRequest(String publisherId,
                      String placementId,
                      int width,
                      int height,
                      Country country,
                      BigDecimal floorPrice,
                      AdType adType) {

        this.publisherId = publisherId;
        this.placementId = placementId;
        this.width = width;
        this.height = height;
        this.country = country;
        this.floorPrice = floorPrice;
        this.adType = adType;
    }

    public String getPublisherId() {
        return publisherId;
    }

    public String getPlacementId() {
        return placementId;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BigDecimal getFloorPrice() {
        return floorPrice;
    }

    public AdType getAdType() {
        return adType;
    }

    public Country getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return "BidRequest{" +
                "publisherId='" + publisherId + '\'' +
                ", placementId='" + placementId + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", country='" + country + '\'' +
                ", floorPrice=" + floorPrice +
                '}';
    }
}