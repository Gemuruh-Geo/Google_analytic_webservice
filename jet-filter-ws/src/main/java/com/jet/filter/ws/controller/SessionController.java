package com.jet.filter.ws.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jet.filter.ws.dao.SessionDaoHelper;
import com.jet.filter.ws.model.session.SessionQueryParameter;
import com.jet.filter.ws.model.session.SessionQueryResult;
import com.jet.filter.ws.model.session.Websession;
import com.jet.filter.ws.model.session.WebtrackerSessionOutput;

@Controller
public class SessionController {
	
	private static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
	
	@ResponseBody
	@RequestMapping(value="/navigation", method=RequestMethod.POST)
	public String navigation(@RequestBody String session_id_json){
		
		SessionQueryParameter sqp = gson.fromJson(session_id_json, SessionQueryParameter.class);
		
		String session_id = sqp.getSessionid();
		if(session_id!=null){
			
			UUID uuid = UUID.fromString(session_id);
			
			
			String nav = SessionDaoHelper.getNavigation(uuid);
			SessionQueryResult sqr = new SessionQueryResult();
			sqr.setNavigation(nav);
			String json = gson.toJson(sqr);
			
			return json;
			
		}else{
			return "no session id";
		}
		
		
	}
	@ResponseBody
	@RequestMapping(value="/session_history", method=RequestMethod.POST)
	public String sessionHistory(@RequestBody String originsessionid){
		
		SessionQueryParameter sqp = gson.fromJson(originsessionid, SessionQueryParameter.class);
		String origin = sqp.getOriginsessionid();
		if(origin!=null){
			
			UUID uuid = UUID.fromString(origin);
			String sessionHistory = SessionDaoHelper.getSessionHistory(uuid);
			SessionQueryResult sqr = new SessionQueryResult();
			sqr.setSessionhistory(sessionHistory);
			
			String json = gson.toJson(sqr);
			return json;
			
			
			
		}else{
			return "no origin session id";
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/websession", method=RequestMethod.POST)
	public String websession(@RequestBody String jsonP){
		
		SessionQueryParameter sqp = gson.fromJson(jsonP, SessionQueryParameter.class);
		if(sqp!=null){
			String startDate = sqp.getStartdate();
			String endDate = sqp.getEnddate();
			String[] trackingCodes = sqp.getTracking_codes();
			
			List<Websession> ws = SessionDaoHelper.getWebSession(startDate, endDate, trackingCodes);
			
			SessionQueryResult sqr = new SessionQueryResult();
			sqr.setWebsession(ws);
			
			String json = gson.toJson(sqr);
			return json;
			
		}else{
			return "no data";
		}
		
	}
	
	@ResponseBody
	@RequestMapping(value="/webtracker",method=RequestMethod.GET)
	public String webtrackerSession(@RequestParam String[] tracking_codes,@RequestParam String startdate,@RequestParam String enddate){
		
		WebtrackerSessionOutput wos = SessionDaoHelper.getWebtrackingSession(startdate, enddate, tracking_codes);
		if((wos.getCallRefNo().isEmpty()&&
				wos.getCampaign().isEmpty()&&
				wos.getDateOfEvent().isEmpty()&&
				wos.getGoogleSessionID().isEmpty()&&
				wos.getGoogleUserid().isEmpty()&&
				wos.getJetConversionSessionID().isEmpty()&&
				wos.getJetOriginalSessionID().isEmpty()&&
				wos.getKeywords().isEmpty()&&
				wos.getLandingPathPage().isEmpty()&&
				wos.getMedium().isEmpty()&&
				wos.getReferalUrl().isEmpty()&&
				wos.getSource().isEmpty()&&
				wos.getTimeOfEvent().isEmpty()&&
				wos.getTracking_code().isEmpty())||wos==null){
			return "{\"error\":\"no data\"}";
		}
		return gson.toJson(wos);
	}
}
