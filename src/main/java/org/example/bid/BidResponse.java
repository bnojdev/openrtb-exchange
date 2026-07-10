package org.example.bid;

import org.example.campaign.Campaign;
import org.example.creative.Creative;
import org.example.dsp.Dsp;

import java.math.BigDecimal;

public class BidResponse {


    private final Dsp dsp;

    private final Campaign campaign;

    private final Creative creative;

    private final BigDecimal bidPrice;

    public BidResponse(Dsp dsp,
                       Campaign campaign,
                       Creative creative,
                       BigDecimal bidPrice) {

        this.dsp = dsp;
        this.campaign = campaign;
        this.creative = creative;
        this.bidPrice = bidPrice;
    }

    public Dsp getDsp() {
        return dsp;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public Creative getCreative() {
        return creative;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }

    @Override
    public String toString() {
        return "BidResponse{" +
                "dsp=" + dsp +
                ", campaign=" + campaign +
                ", creative=" + creative +
                ", bidPrice=" + bidPrice +
                '}';
    }
}
