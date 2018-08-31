package com.ef.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ef.entity.Logger;

@Repository
public interface LoggerRepository extends JpaRepository<Logger, Integer> {
	
	@Query("SELECT l.ip FROM Logger l WHERE l.startDate >= :startDate AND l.startDate < :endDate GROUP BY l.ip HAVING COUNT(l.ip) >= :threshold")
	public List<String> find(@Param("startDate") Date startDate, @Param("endDate") Date endDate, @Param("threshold") long threshold);


	
}
