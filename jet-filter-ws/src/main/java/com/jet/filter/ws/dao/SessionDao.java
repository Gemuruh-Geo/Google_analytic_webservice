package com.jet.filter.ws.dao;

import java.util.List;
import java.util.UUID;

import com.jet.filter.ws.model.session.Websession;
import com.jet.filter.ws.model.session.WebtrackerSessionOutput;



public interface SessionDao {
	
	public String getNavigation(UUID sessionid);
	public String getSessionHistory(UUID originSessionid);
	
	public List<Websession> getWebSession(String startdate,String enddate,String[] tracking_code);
	public WebtrackerSessionOutput getWebtrackerSession(String startdate, String enddate,String[] tracking_codes);
	
}
