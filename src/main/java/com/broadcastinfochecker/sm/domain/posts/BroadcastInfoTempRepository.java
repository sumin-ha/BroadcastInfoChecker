package com.broadcastinfochecker.sm.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BroadcastInfoTempRepository extends JpaRepository<BroadcastInfoTemp, Long> {

    @Query("select p from BroadcastInfoTemp p order by p.id desc")
    List<BroadcastInfoTemp> findAllDesc();
}
