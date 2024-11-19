package com.java.utility;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeDateUtility 
{
	public static String getCurrentTime()
	{
		String x="AM";
		LocalTime tm=LocalTime.now();
		int h=tm.getHour();
		if(h>=12)
		{
			if(h>12)
			  h-=12;
			x="PM";
		}
		String time=h+":"+tm.getMinute()+":"+tm.getSecond()+" "+x;
		return time;
	}
	public static String getCurrentDate()
	{
		String dt=LocalDate.now().toString();
		String[] x=dt.split("-");
		String date=x[2]+"-"+x[1]+"-"+x[0];
		return date;
	}
}