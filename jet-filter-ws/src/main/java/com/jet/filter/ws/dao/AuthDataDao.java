package com.jet.filter.ws.dao;

import java.util.List;

import com.jet.filter.ws.model.TrackingSite;



/*
 * @author:Gemuruh Geo Pratama
 * */
public interface AuthDataDao {
	public TrackingSite getTrackingSiteByTrackingCode(String trackingCode);
	
	public int updateConfigJson(String newConfigJson,String trackingCode);
	
	public List<String> getTrackingCode();
	
	public int updateAttributJSON(String attJson,String tracking_code);
	
	
}
