package com.jet.filter.ws.helper.ganalytic;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jet.filter.ws.dao.AuthDataDao;
import com.jet.filter.ws.model.TrackingSite;


/*
 * @author:Gemuruh Geo Pratama
 * */

public class GaDbHelper {
	private static final ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	
	private static final AuthDataDao authDao = (AuthDataDao)appContext.getBean("authDataDao");
	
	public static TrackingSite getTrackingSiteByTrackingCode(String trackingCode) {
		
		return authDao.getTrackingSiteByTrackingCode(trackingCode);
	}
	
	public static int updateConfigJson(String newConfigJson, String trackingCode) {
		
		return authDao.updateConfigJson(newConfigJson, trackingCode);
		
	}
	
	
	public static int updateAttributJSON(String attJson,String tracking_code){
		return authDao.updateAttributJSON(attJson, tracking_code);
	}
	public static List<String> getTrackingCode(){
		return authDao.getTrackingCode();
	}
	
	
}
