package org.example.creative;

import org.example.bid.AdType;

import java.util.Objects;

public class Creative {

    private final String id;

    private final int width;

    private final int height;

    private final AdType adType;

    private final String landingUrl;

    public Creative(String id,
                    int width,
                    int height,
                    AdType adType,
                    String landingUrl) {

        this.id = id;
        this.width = width;
        this.height = height;
        this.adType = adType;
        this.landingUrl = landingUrl;
    }

    public String getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public AdType getAdType() {
        return adType;
    }

    public String getLandingUrl() {
        return landingUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Creative creative)) return false;
        return Objects.equals(id, creative.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Creative{" +
                "id='" + id + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", adType=" + adType +
                ", landingUrl='" + landingUrl + '\'' +
                '}';
    }
}
