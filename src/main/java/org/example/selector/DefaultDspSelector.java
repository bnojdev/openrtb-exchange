package org.example.selector;

import org.example.bid.BidRequest;
import org.example.dsp.Dsp;

import java.util.List;

public class DefaultDspSelector implements DspSelector{
    @Override
    public List<Dsp> select(BidRequest bidRequest, List<Dsp> dspRegister) {
        return dspRegister.stream()
                .filter(dsp -> dsp.getSupportedAdTypes().contains(bidRequest.getAdType()))
                .filter(dsp -> dsp.getSupportedCountries().contains(bidRequest.getCountry()))
                .toList();
    }
}
