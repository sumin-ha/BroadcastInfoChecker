package com.broadcastinfochecker.sm.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BroadcastInfoRepository extends JpaRepository<BroadcastInfo, Long> {

    @Query("select p from BroadcastInfo p order by p.id desc")
    List<BroadcastInfo> findAllDesc();
}
