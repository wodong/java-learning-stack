package com.guyuesoft.java8.newdatetime;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class Demo {

	public static void main(String[] args) {
		System.out.println("=================日期处理================");
		//不同方式生成日期
		LocalDate date1= LocalDate.now();
		System.out.println("date1: "+date1);
		//date1: 2017-11-23
		
		LocalDate date2 = LocalDate.parse("2013-10-23");
		System.out.println("date2: "+date2);
		//date2: 2013-10-23
		
		LocalDate date3= LocalDate.of(2013, 11, 22);
		System.out.println("date3: "+date3);
		//date3: 2013-11-22
		
		//日期细节的获取
		System.out.println(date3.getYear()+"-"+date3.get(ChronoField.MONTH_OF_YEAR)+"-"+date3.get(ChronoField.DAY_OF_MONTH));
		// 2013-11-22
		
		//月末
		LocalDate date4= date3.with(TemporalAdjusters.lastDayOfMonth());
		System.out.println("date4: "+date4);
		//date4: 2013-11-30
		
		System.out.println("=================时间处理================");
		//不同方式生成时间
		LocalTime time1= LocalTime.now();
		System.out.println("time1: "+time1);
		//time1: 13:22:04.363056100
		
		LocalTime time2 = LocalTime.parse("21:10:22");
		System.out.println("time2: "+time2);
		//time2: 21:10:22
		
		LocalTime time3 = LocalTime.of(0, 11, 22);
		System.out.println("time3: "+time3);
		// time3: 00:11:22
		
		//时间细节的获取
		System.out.println("time3(2): "+time3.getHour()+":"+time3.get(ChronoField.MINUTE_OF_HOUR)+":"+time3.get(ChronoField.SECOND_OF_MINUTE));
		// time3(2): 0:11:22
		
		System.out.println("=================日期时间处理================");
		//日期和时间
		LocalDateTime dateTime1= LocalDateTime.parse("2015-10-10T15:15:00");
		System.out.println("dateTime1: "+dateTime1);
		//dateTime1: 2015-10-10T15:15
		
		//DateTimeFormatter是线程安全的		
		LocalDateTime dateTime2= LocalDateTime.parse("2015-10-10 15:15:01",DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println("dateTime2: "+dateTime2.format(DateTimeFormatter.ISO_DATE_TIME));
		//dateTime2: 2015-10-10T15:15:01
		//
		ZonedDateTime zonedDateTime= dateTime1.atZone(TimeZone.getDefault().toZoneId());
		System.out.println("zonedDateTime: "+zonedDateTime);
		System.out.println("=================Instant处理================");
		//zonedDateTime: 2015-10-10T15:15+08:00[Asia/Shanghai]
		
		// Instant
		Instant instant1 = Instant.now();
		System.out.println("instant1: "+instant1);
		//instant1: 2017-11-23T05:22:04.372058Z
		
		//仅仅是一个测试用例，不用这样使用
		Instant instant2 =Instant.ofEpochMilli((new Date()).getTime());
		System.out.println("instant2: "+instant2);
		//instant2: 2017-11-23T05:22:04.373Z
		
		//解析时间
		Instant instant3 = Instant.parse("2012-10-10T10:20:21Z");
		System.out.println("instant3: "+instant3);
		//instant3: 2012-10-10T10:20:21Z
		
		// 日期操作
		Instant instant4 = instant3.plus(10, ChronoUnit.DAYS);
		System.out.println("instant4: "+instant4);
		//instant4: 2012-10-20T10:20:21Z
		
		Instant instant5 = dateTime1.toInstant(ZoneOffset.UTC);
		System.out.println("instant5: "+instant5);
		//instant5: 2015-10-10T15:15:00Z
		
		//
		Duration d1= Duration.between(instant3, instant4);
		System.out.println("Duration1: "+d1);
		//Duration1: PT240H
		
		Duration d2= Duration.between(dateTime2, dateTime1);
		System.out.println("Duration2: "+d2);
		// Duration2: PT-1S
		
		Duration d3= Duration.between(instant3, zonedDateTime);
		System.out.println("Duration3: "+d1);
		//Duration3: PT240H 
		
		//
	}
	
}
