package com.ef.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ef.entity.BlockedIp;

@Repository
public interface BlockedLogRepository extends JpaRepository<BlockedIp, Integer> {

}
