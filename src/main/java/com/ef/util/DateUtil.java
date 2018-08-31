package com.ef.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.ef.enums.EnumDuration;

public class DateUtil {
	
	public final static SimpleDateFormat DT_FORMAT_LOG = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US); 
	
	public static Date addTime(Date startDate, EnumDuration enumDuration) {
		Calendar cal = Calendar.getInstance();
	    
		cal.setTime(startDate);
		if (enumDuration == EnumDuration.hourly) {
			cal.add(Calendar.HOUR_OF_DAY, 1);
		} else if (enumDuration == EnumDuration.daily) {
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}
		
		return cal.getTime();
	}

}
