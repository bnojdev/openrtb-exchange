package org.example.dsp;

import org.example.campaign.Campaign;

import java.math.BigDecimal;

public class CampaignBid {

    private final Campaign campaign;

    private final BigDecimal bidPrice;

    public CampaignBid(Campaign campaign,
                       BigDecimal bidPrice) {

        this.campaign = campaign;
        this.bidPrice = bidPrice;
    }

    public Campaign getCampaign() {
        return campaign;
    }

    public BigDecimal getBidPrice() {
        return bidPrice;
    }
}
