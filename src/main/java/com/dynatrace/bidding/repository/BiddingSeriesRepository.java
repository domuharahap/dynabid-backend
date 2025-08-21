package com.dynatrace.bidding.repository;

import com.dynatrace.bidding.model.BiddingSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BiddingSeriesRepository extends JpaRepository<BiddingSeries, Long> {
    List<BiddingSeries> findByStatus(String status);

    Optional<BiddingSeries> findBySeriesName(String seriesName);
}
