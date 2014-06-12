package com.jet.filter.ws.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jet.filter.ws.dao.CallTrackerDaoHelper;
import com.jet.filter.ws.model.CallTracker;

@Controller
public class CallTrackerController {
	
	private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	
	
	@ResponseBody
	@RequestMapping(value="/callTracker",method=RequestMethod.GET)
	public String callTrackerController(@RequestParam String startdate,@RequestParam String enddate,@RequestParam int accountNumber){
		CallTracker callTracker = CallTrackerDaoHelper.getCallTracker(startdate, enddate,accountNumber);
		if(callTracker==null ||(callTracker.getCall_ref_no().isEmpty()&&
				callTracker.getCampaign().isEmpty() &&
				callTracker.getKeywords().isEmpty() &&
				callTracker.getMedium().isEmpty() &&
				callTracker.getSource().isEmpty())){
			
			
			return "{\"error\":\"no data\"}";
			
		}
		
		
		return gson.toJson(callTracker);
	}
}
