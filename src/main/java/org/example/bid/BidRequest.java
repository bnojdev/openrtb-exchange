package org.example.bid;

import java.math.BigDecimal;

public class BidRequest {

    private final String publisherId;
    private final String placementId;

    private final int width;
    private final int height;

    private final String country;

    private final BigDecimal floorPrice;

    public BidRequest(String publisherId,
                      String placementId,
                      int width,
                      int height,
                      String country,
                      BigDecimal floorPrice) {

        this.publisherId = publisherId;
        this.placementId = placementId;
        this.width = width;
        this.height = height;
        this.country = country;
        this.floorPrice = floorPrice;
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

    public String getCountry() {
        return country;
    }

    public BigDecimal getFloorPrice() {
        return floorPrice;
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