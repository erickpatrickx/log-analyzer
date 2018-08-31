package com.ef;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;

import com.ef.enums.EnumDuration;
import com.ef.services.LoggerService;
import com.ef.util.DateUtil;

@SpringBootApplication
@Profile("!test")
public class Parser implements CommandLineRunner {
	
	@Autowired
	private LoggerService loggerService;

	public static void main(String[] args) {
		 SpringApplication.run(Parser.class, args);
	}
	

	public void run(String... args) throws Exception {
		
		try{
		if (args.length > 0 && args.length == 3) {
			
			if(!args[0].contains("--startDate=") && !args[1].contains("----duration=") && !args[2].contains("--threshold=")  ){
				System.err.println("#### ERROR ###### \n \n"
						+ "Usage example: java -jar parser.jar 2017-01-01.13:00:00 hourly 100 \n \n");
				System.exit(1);
			}
			Date startDate = DateUtil.DT_FORMAT_LOG.parse(args[0].split("=")[1].replaceAll("\\.", " "));
			EnumDuration duration = EnumDuration.valueOf(args[1].split("=")[1]);
			Integer threshold = Integer.valueOf(args[2].split("=")[1]);
			loggerService.execute(startDate, duration, threshold);
		}else{
			System.err.println("#### ERROR ###### \n \n"
					+ "Usage example: java -jar parser.jar 2017-01-01.13:00:00 hourly 100 \n \n");
			System.exit(-1);
		}
		
	}catch(ParseException parseException){
		System.err.println("#### ERROR ###### \n \n"
				+ "Format incorrect \n \n Usage example: java -jar parser.jar 2017-01-01.13:00:00 hourly 100 \n \n");
		System.exit(1);
	}
	}
}
