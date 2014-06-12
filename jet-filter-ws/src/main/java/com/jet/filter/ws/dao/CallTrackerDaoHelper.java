package com.jet.filter.ws.dao;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jet.filter.ws.model.CallTracker;

public class CallTrackerDaoHelper {
	private static final ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static final CallTrackerDao calltrackerDao = (CallTrackerDao)appContext.getBean("calltrackerDao");
	
	public static CallTracker getCallTracker(String startDate,String endDate,int accountNumber){
		return calltrackerDao.getCallTracker(startDate, endDate,accountNumber);
	}
	

}
