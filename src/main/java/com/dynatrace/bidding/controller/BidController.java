package com.dynatrace.bidding.controller;

import com.dynatrace.bidding.model.Bid;
import com.dynatrace.bidding.model.BiddingSeries;
import com.dynatrace.bidding.services.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bids")
public class BidController {
    @Autowired
    private BiddingService biddingService;

    @GetMapping("/series/active")
    public ResponseEntity<List<BiddingSeries>> getActiveSeries() {
        List<BiddingSeries> series = biddingService.getActiveSeries();
        //System.out.println(series.size());
        return ResponseEntity.ok(series);
    }

    @PostMapping("/place")
    public ResponseEntity<?> placeBid(@RequestBody Bid bid) {
        try {
            Bid newBid = biddingService.placeBid(bid);
            return ResponseEntity.ok(newBid);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/highest/{plateNumber}")
    public ResponseEntity<Bid> getHighestBid(@PathVariable String biddingNumber) {
        Bid highestBid = biddingService.getHighestBidForPlate(biddingNumber);
        if (highestBid != null) {
            return ResponseEntity.ok(highestBid);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Bid>> getBidsByUserId(@PathVariable Long userId) {
        List<Bid> bids = biddingService.getBidsByUserId(userId);
        return ResponseEntity.ok(bids);
    }

    @GetMapping("/{bidId}")
    public ResponseEntity<Bid> getBidsById(@PathVariable Long bidId) {
        Bid bid = biddingService.getBidsById(bidId);
        return ResponseEntity.ok(bid);
    }
}
