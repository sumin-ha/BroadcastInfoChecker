package com.broadcastinfochecker.sm.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface BroadcastInfoRepository extends JpaRepository<BroadcastInfo, Long> {

    @Query("select p from BroadcastInfo p order by p.id desc")
    List<BroadcastInfo> findAllDesc();

    @Query("select p from BroadcastInfo p where p.broadcastDate > :startDate and p.broadcastDate < :endDate order by p.broadcastDate asc")
    List<BroadcastInfo> findNext3DaysAsc(@Param(value = "startDate") LocalDateTime startDate, @Param(value = "endDate") LocalDateTime endDate);
}