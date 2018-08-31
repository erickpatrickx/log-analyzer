package com.ef.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ef.dao.BlockedLogRepository;
import com.ef.dao.LoggerRepository;
import com.ef.entity.BlockedIp;
import com.ef.entity.Logger;
import com.ef.enums.EnumDuration;
import com.ef.util.DateUtil;

@Service
public class LoggerService {

	@Autowired
	private LoggerRepository logRepository;
	
	@Autowired
	private BlockedLogRepository blockedLogRepository;
	
	private String fileName = "access.log";
	

	public void execute(Date startDate, EnumDuration duration, int threshold) throws Exception {
		
		System.out.println("Parsing log file...");
		List<Logger> logs = parseLogFile();
		
		System.out.println("Saving to database, please wait...");
		saveLogFiles(logs);
		
		List<String> blockedIps = new ArrayList<String>();
		blockedIps =logRepository.find(startDate, DateUtil.addTime(startDate, duration), threshold);
		
		System.out.println("IPs blocked: "+blockedIps);
		
		System.out.println("Saving blocked ips...");
		saveBlockedIps(blockedIps, threshold);
	}
	
	private void saveLogFiles(List<Logger> logs) {
		logRepository.save(logs);
	}
	
	private void saveBlockedIps(List<String> ips, int threshold) {
		List<BlockedIp> blockedLogs = ips
				.stream()
				.map(ip -> new BlockedIp(ip, "IP blocked because of "+threshold+" threshold limit"))
				.collect(Collectors.toList());
		blockedLogRepository.save(blockedLogs);
	}
	
	private List<Logger> parseLogFile() throws Exception {
		Path path = Paths.get(fileName);
		
	    Stream<String> lines = Files.lines(path);

	    List<Logger> logs = lines
	    		.map(line -> createLog(line))
	    		.collect(Collectors.toList());
	    
	    lines.close();
	    
	    return logs;
	}
	
	private Logger createLog(String line) {
		try {
			return new Logger(line);
		} catch (ParseException ex) {
			throw new RuntimeException(ex);
		}
	}
	

}
