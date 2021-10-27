package com.broadcastinfochecker.sm.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetInfoRegisterRepository extends JpaRepository<TweetInfoRegister, Long> {

    @Query("select p from TweetInfoRegister p order by p.id desc")
    List<TweetInfoRegister> findAllDesc();
}
