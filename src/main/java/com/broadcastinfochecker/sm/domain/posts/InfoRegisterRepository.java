package com.broadcastinfochecker.sm.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfoRegisterRepository extends JpaRepository<InfoRegister, Long> {

    @Query("select p from InfoRegister p order by p.id desc")
    List<InfoRegister> findAllDesc();
}
