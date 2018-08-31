package com.ef.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BlockedIp {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String ip;

	private String message;
	
	public BlockedIp(String ip, String message) {
		this.ip = ip;
		this.message = message;
	}
} 	
