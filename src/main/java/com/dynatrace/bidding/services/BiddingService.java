package com.dynatrace.bidding.services;


import com.dynatrace.bidding.model.Bid;
import com.dynatrace.bidding.model.BiddingSeries;
import com.dynatrace.bidding.repository.BidRepository;
import com.dynatrace.bidding.repository.BiddingSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BiddingService {
    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private BiddingSeriesRepository biddingSeriesRepository;

    public List<BiddingSeries> getActiveSeries() {
        return biddingSeriesRepository.findByStatus("ACTIVE");
    }

    public Bid placeBid(Bid bid) {
        String plateNumber = bid.getBiddingNumber();
        String seriesName = plateNumber.substring(0, 2); // Assuming series is the first two characters

        BiddingSeries series = biddingSeriesRepository.findBySeriesName(seriesName).stream()
                .filter(s -> s.getStatus().equals("ACTIVE") && LocalDateTime.now().isAfter(s.getStartDate()) && LocalDateTime.now().isBefore(s.getEndDate()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Bidding series is not active or does not exist."));

        List<Bid> existingBids = bidRepository.findByBiddingNumberOrderByAmountDesc(plateNumber);
        if (!existingBids.isEmpty() && bid.getAmount() <= existingBids.get(0).getAmount()) {
            throw new IllegalArgumentException("Bid amount must be greater than the current highest bid.");
        }
        return bidRepository.save(bid);
    }

    public Bid getHighestBidForPlate(String biddingNumber) {
        List<Bid> bids = bidRepository.findByBiddingNumberOrderByAmountDesc(biddingNumber);
        return bids.isEmpty() ? null : bids.get(0);
    }

    public List<Bid> getBidsByUserId(Long userId) {
        return bidRepository.findByUserId(userId);
    }

    public Bid getLatestBidForUser(Long userId) {
        return bidRepository.findTopByUserIdOrderByTimestampDesc(userId);
    }

    public Bid getBidsById(Long bidId) {
        return bidRepository.findById(bidId)
                .orElseThrow(() -> new IllegalArgumentException("Bid not found with ID: "+bidId));
    }

    public Bid updateBidStatus(Long bidId, String status) {
        Optional<Bid> optionalBid = bidRepository.findById(bidId);
        if (optionalBid.isPresent()) {
            Bid bid = optionalBid.get();
            bid.setStatus(status);
            return bidRepository.save(bid);
        } else {
            // Handle the case where the bid is not found
            throw new RuntimeException("Bid with ID " + bidId + " not found.");
        }
    }
}
