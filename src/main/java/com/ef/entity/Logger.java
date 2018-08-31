package com.ef.entity;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.ef.util.DateUtil;

@Entity
public class Logger {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private Date startDate;
	
	private String ip;
	
	private String httpMethod;
	private String httpCode;
	private String httpFrom;

	public Logger(String line) throws ParseException {
		super();
		String[] split = line.split("\\|");
		setStartDate(DateUtil.DT_FORMAT_LOG.parse(split[0]));
		setIp(split[1]);
		setHttpMethod(split[2]);
		setHttpCode(split[3]);
		setHttpFrom(split[4]);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	public void setHttpMethod(String httpMethod) {
		this.httpMethod = httpMethod;
	}

	public String getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(String httpCode) {
		this.httpCode = httpCode;
	}

	public String getHttpFrom() {
		return httpFrom;
	}

	public void setHttpFrom(String httpFrom) {
		this.httpFrom = httpFrom;
	}
	
	
	
} 	
