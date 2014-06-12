package com.jet.filter.ws.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jet.filter.ws.model.session.Websession;
import com.jet.filter.ws.model.session.WebtrackerSession;
import com.jet.filter.ws.model.session.WebtrackerSessionOutput;


public class SessionDaoHelper {
	
	private static final ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	private static final SessionDao sessionDao = (SessionDao)appContext.getBean("sessionDao");
	
	public static String getNavigation(UUID sessionid) {
		return sessionDao.getNavigation(sessionid);
	}
	public static String getSessionHistory(UUID originSessionid) {
		// TODO Auto-generated method stub
		return sessionDao.getSessionHistory(originSessionid);
	}
	
	public static List<Websession> getWebSession(final String startdate, final String enddate,
			final String[] tracking_code){
		return sessionDao.getWebSession(startdate, enddate, tracking_code);
	}
	
	public static WebtrackerSessionOutput getWebtrackingSession(final String startdate,final String enddate,final String[] tracking_codes){
		return sessionDao.getWebtrackerSession(startdate, enddate, tracking_codes);
	}
	
}
