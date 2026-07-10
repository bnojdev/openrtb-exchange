package org.example.publisher;

import org.example.bid.AdType;
import org.example.bid.Country;

import java.math.BigDecimal;

public class Placement {

    private final String placementId;

    private Publisher publisher;

    private final int width;

    private final int height;

    private final Country country;

    private final AdType adType;

    private final BigDecimal floorPrice;

    public Placement(String placementId,
                     int width,
                     int height,
                     Country country,
                     AdType adType,
                     BigDecimal floorPrice) {

        this.placementId = placementId;
        this.width = width;
        this.height = height;
        this.country = country;
        this.adType = adType;
        this.floorPrice = floorPrice;
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

    public Country getCountry() {
        return country;
    }

    public AdType getAdType() {
        return adType;
    }

    public BigDecimal getFloorPrice() {
        return floorPrice;
    }

    void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    @Override
    public String toString() {
        return "Placement{" +
                "placementId='" + placementId + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", country=" + country +
                ", adType=" + adType +
                ", floorPrice=" + floorPrice +
                '}';
    }
}
