package org.example.exchange;

import org.example.auction.Auction;
import org.example.bid.Bid;
import org.example.bid.BidCollector;
import org.example.bid.BidRequest;
import org.example.dsp.Dsp;
import org.example.publisher.Placement;
import org.example.selector.DefaultDspSelector;
import org.example.selector.DspSelector;

import java.util.*;

public class Exchange {
    private final Map<String, Auction> activeAuctions = new HashMap<>();
    private final List<Dsp> registeredDsps = new ArrayList<>();
    private final BidCollector bidCollector = new BidCollector();
    private final DspSelector dspSelector;

    public Exchange(DspSelector dspSelector) {
        this.dspSelector = dspSelector;
    }

    public Auction createAuction(Placement placement) {

        BidRequest request = new BidRequest(
                UUID.randomUUID().toString(),
                placement
        );

        String auctionId = UUID.randomUUID().toString();

        Auction auction = new Auction(auctionId, request);

        activeAuctions.put(auctionId, auction);

        List<Dsp> eligibleDsps =
                dspSelector.select(request, registeredDsps);

        bidCollector.collectBidsAsync(auction, eligibleDsps);

        return auction;
    }

//    private void notifyDsp(Auction auction) {
//        for (Dsp dsp : registeredDsps) {
//            Optional<Bid> bid = dsp.submitBid(auction.getBidRequest());
//            bid.ifPresent(auction::acceptBid);
//        }
//    }

    public void registerDsp(Dsp dsp) {
        if (dsp == null){
            throw new IllegalArgumentException("DSP cannot be null");
        }

        boolean exists = registeredDsps.stream().anyMatch(t -> t.getId().equals(dsp.getId()));

        if (exists) {
            throw new IllegalArgumentException("DSP should be unique");
        }

        registeredDsps.add(dsp);
    }

    public void receiveBid(String auctionId, Bid bid) {
    }

    public void closeAuction(String auctionId) {
    }

    public Auction getAuction(String auctionId) {
        return this.activeAuctions.get(auctionId);
    }
}
