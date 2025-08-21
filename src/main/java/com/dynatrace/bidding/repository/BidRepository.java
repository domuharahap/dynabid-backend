package com.dynatrace.bidding.repository;

import com.dynatrace.bidding.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {
    List<Bid> findByBiddingNumberOrderByAmountDesc(String biddingNumber);

    List<Bid> findByUserId(Long userId);

    Bid findTopByUserIdOrderByTimestampDesc(Long userId);

}