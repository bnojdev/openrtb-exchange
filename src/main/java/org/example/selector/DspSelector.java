package org.example.selector;

import org.example.bid.BidRequest;
import org.example.dsp.Dsp;

import java.util.List;

public interface DspSelector {

    List<Dsp> select(BidRequest bidRequest, List<Dsp> dspRegister);
}
