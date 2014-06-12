package com.jet.filter.ws.controller;

import java.util.regex.Matcher;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jet.filter.ws.helper.Medium;
import com.jet.filter.ws.helper.Pattern_J;
import com.jet.filter.ws.helper.Pattern_S;
import com.jet.filter.ws.helper.RegexMatcher;
import com.jet.filter.ws.model.AnalyticData;
import com.jet.filter.ws.model.InputData;
/*
 * @author:Gemuruh Geo Pratama
 * */
@Controller
public class FilterController {
	
	
	@ResponseBody
	@RequestMapping(value="/filter",method=RequestMethod.POST)
	public String filterData(@RequestBody String json_input){
		
		
		/*
		 * Check if input JSON must have a valid field
		 * --siteCode
		 * --view
		 * --analyticData
		 * --source
		 * --medium
		 * --campaign
		 * --keywords
		 * 
		 * if not do nothing
		 * else format to new JSON
		 * */
		
		
		//if input contain all valid field
		if(Pattern_J.ANALYTIC_DATA.patternMatch(json_input)&&
				Pattern_J.CAMPAIGN.patternMatch(json_input)&&
				Pattern_J.KEYWORDS.patternMatch(json_input)&&
				Pattern_J.MEDIUM.patternMatch(json_input)&&
				Pattern_J.SITE_CODE.patternMatch(json_input)&&
				Pattern_J.SOURCE.patternMatch(json_input)&&
				Pattern_J.VIEW.patternMatch(json_input)){
			
			
			
			//initialize GSON
			Gson gson = new GsonBuilder().setDateFormat("MM/dd/yyyy").create();
			
			
			InputData i_data = gson.fromJson(json_input, InputData.class);
			AnalyticData an_data = i_data.getAnalyticData();
			
			//medium transformation
			//for medium = "organic","cpc","ppc"
			String medium = an_data.getMedium();
			String temp = medium;
			
			//Treat if data from medium contain special character
			String regex = "[^a-zA-Z0-9]";
			
			String regex2 = "[\\{\\}\\[\\]\\)\\(]";
			
			//Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE); //JUST for sure
			//Matcher matcher = pattern.matcher(temp);
			
			Matcher matcher = RegexMatcher.getRegexMatcher(regex, temp);
			
			temp = matcher.replaceAll("").toLowerCase().trim();//remove special character if any
			
			
			if(temp.equalsIgnoreCase(Medium.ORGANIC.medium)
					||temp.equalsIgnoreCase(Medium.CPC.medium)
					||temp.equalsIgnoreCase(Medium.PPC.medium)
					||temp.equalsIgnoreCase(Medium.REFERRAL.medium)){
				temp = temp.toLowerCase().trim();
				if(temp.equalsIgnoreCase(Medium.PPC.medium)){
					temp = Medium.CPC.medium; //change medium to cpc
				}
				medium = temp;
			}
			
			
			//source transformation
			String source = an_data.getSource();
			
			String temp_s = source;
			
			
			if(!Pattern_S.DIRECT.patternMatch(temp_s) && !Pattern_S.NONE.patternMatch(temp_s) && !Pattern_S.NOT_SET.patternMatch(temp_s)){
				//Do the Transformation
				Matcher matcher2 = RegexMatcher.getRegexMatcher(regex2, temp_s);
				temp_s = matcher2.replaceAll("").toLowerCase().trim();
				source = temp_s;
				
			}			
			an_data.setMedium(medium);//set new medium
			an_data.setSource(source);//set new source
			
			
			i_data.setAnalyticData(an_data); //Set new analytic data, if not match the condition set as its
			
			
			String output_json = gson.toJson(i_data);
			
			return output_json;
			
		}
		else{
			return json_input;
		}
		
	}
}
