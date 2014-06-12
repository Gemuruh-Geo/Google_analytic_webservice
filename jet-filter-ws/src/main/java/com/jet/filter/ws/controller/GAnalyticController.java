package com.jet.filter.ws.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.model.GaData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jet.filter.ws.helper.Constant;
import com.jet.filter.ws.helper.EventHelper;
import com.jet.filter.ws.helper.ganalytic.GAnalyticImplementation;
import com.jet.filter.ws.helper.ganalytic.GaDataProperties;
import com.jet.filter.ws.model.AnalyticOutputData;
import com.jet.filter.ws.model.CallTracker;
import com.jet.filter.ws.model.GaProperties;
import com.jet.filter.ws.model.GoogleAnalyticInput;
import com.jet.filter.ws.model.InputParamEvent;
import com.jet.filter.ws.model.MergeDTO;
import com.jet.filter.ws.model.OauthProperties;
import com.jet.filter.ws.model.session.WebtrackerSessionOutput;

/*
 * @author:Gemuruh Geo Pratama
 * 
 * */

@Controller
public class GAnalyticController {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HttpHeaders httpHeaders;
	
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();
	private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	private static final String APPLICATION_NAME = "JET INTERACTIVE WEBSERVICE";
	private boolean isEvent;
	private GaData eventGaData;


	@ResponseBody
	@RequestMapping(value="/ganalytic",method=RequestMethod.POST)
	public String pullDataFromGoogleAnalytics(HttpServletRequest request, HttpServletResponse response,@RequestBody String jsonData) throws JsonProcessingException, IOException{



		GoogleAnalyticInput gaInput = EventHelper.getInstance().parseGoogleAnalyticInput(jsonData);
		if(gaInput==null){
			return "{\"error\":\"Invalid Input data\"}";
		}
		String TRACKING_CODE = gaInput.getTrackingCode();

		GaProperties gaProperties = gaInput.getGaproperties();
		GAnalyticImplementation implementation = new GAnalyticImplementation.Builder(JSON_FACTORY, HTTP_TRANSPORT, APPLICATION_NAME).loadTrackingCode(TRACKING_CODE).build();

		if(new GAnalyticImplementation().getTrackingSite(TRACKING_CODE)==null){
			return "{\"error\":\"No Account Or Data Format Wrong\"}";
		}
		
		OauthProperties oauthPorperties = EventHelper.getInstance().callOProperties(null, TRACKING_CODE,implementation);
		if(oauthPorperties==null){
			return "{\"error\":\"no access token\"}";
		}
		//GAnalyticOauthHelper helper = new GAnalyticOauthHelper.Builder(JSON_FACTORY, HTTP_TRANSPORT).getBClientSecret(TRACKING_CODE, attributes_json);
		//if(helper.getClientSecret()==null){
			//return "{\"error\":\"no access token\"}";
		//}
		


		Credential credential = implementation.getCredential(oauthPorperties.getCodeFlow(), null);


		GaDataProperties gaDataProperties = new GaDataProperties.Builder()
		.setStartDate(gaProperties.getStartDate())
		.setEndDate(gaProperties.getEndDate())
		.setMetrics(gaProperties.getMetrics())
		.setDimension(gaProperties.getDimension())
		.build();

		Analytics analytics = implementation.getAnalytics(null,credential);
		GaData gaData = implementation.executeGaData(gaDataProperties, analytics);

		if (isEvent) {
			eventGaData = implementation.executeGaDataFilterBy(
					gaDataProperties, analytics, Constant.FILTER_CATEGORY);
			isEvent = false;
		}

		//System.out.println(gaData.toPrettyString());

		//return getOutputGaData(gaData);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();

		return gson.toJson(gaData);

	}

	@ResponseBody
	@RequestMapping(value = "/pullevent", method = RequestMethod.POST)
	public String pullEventData(HttpServletRequest request, @RequestBody String jsonParam)
			throws JsonParseException, JsonMappingException, IOException {

		Gson gson = new GsonBuilder().setDateFormat(Constant.DATE_FORMAT).create();

		GoogleAnalyticInput googleAnalyticInput = EventHelper.getInstance().parseGoogleAnalyticFromEvent(jsonParam);

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<String>(
				gson.toJson(googleAnalyticInput), httpHeaders);

		isEvent = true;
		ResponseEntity<String> restResult = restTemplate.exchange(request.getRequestURL().toString()
				.replaceAll(Constant.PULL_EVENT_SERVICE, Constant.GA_SERVICE), HttpMethod.POST, entity, String.class);
		
		if (restResult.getBody().equals(Constant.ErrorMessage.INVALID_INPUT_DATA)) {
			return Constant.ErrorMessage.INVALID_INPUT_DATA;
		} else if (restResult.getBody().equals(Constant.ErrorMessage.NO_ACCOUNT_OR_DATA_FORMAT_WRONG)) {
			return Constant.ErrorMessage.NO_ACCOUNT_OR_DATA_FORMAT_WRONG;
		} else if (restResult.getBody().equals(Constant.ErrorMessage.NO_ACCESS_TOKEN)) {
			return Constant.ErrorMessage.NO_ACCESS_TOKEN;
		}
		
		if (eventGaData != null && eventGaData.getRows() != null) {
			String result = EventHelper.getInstance().getOutputGaData(eventGaData);
			return result;
		} else {
			return Constant.ErrorMessage.NO_DATA;
		}
	}

	@ResponseBody
	@RequestMapping(value = "/merge", method = RequestMethod.POST)
	public String merge(HttpServletRequest request, @RequestBody String jsonParameter)
			throws JsonParseException, JsonMappingException, IOException, ParseException {

		Gson gson = new GsonBuilder().serializeNulls().create();

		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		InputParamEvent inputParamEvent = EventHelper.getInstance().parseParamEvent(jsonParameter);

		HttpEntity<String> entityEvent = new HttpEntity<String>(
				gson.toJson(inputParamEvent), httpHeaders);

		// start GA Event Webservice, return value in eventGaData field
		ResponseEntity<String> restResultPullEvent = restTemplate.exchange(request.getRequestURL().toString()
				.replaceAll(Constant.MERGE_SERVICE, Constant.PULL_EVENT_SERVICE), HttpMethod.POST, entityEvent, String.class);

		if (restResultPullEvent.getBody().equals(Constant.ErrorMessage.INVALID_INPUT_DATA)) {
			return Constant.ErrorMessage.INVALID_INPUT_DATA;
		} else if (restResultPullEvent.getBody().equals(Constant.ErrorMessage.NO_ACCOUNT_OR_DATA_FORMAT_WRONG)) {
			return Constant.ErrorMessage.NO_ACCOUNT_OR_DATA_FORMAT_WRONG;
		} else if (restResultPullEvent.getBody().equals(Constant.ErrorMessage.NO_ACCESS_TOKEN)) {
			return Constant.ErrorMessage.NO_ACCESS_TOKEN;
		}
		
		// start webtracking webservice
		String webtrackerUri = request.getRequestURL().toString()
				.replaceAll(Constant.MERGE_SERVICE, Constant.WT_SERVICE);
		DateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateFormat.parse(inputParamEvent.getDateRange().getStartDate()));
		calendar.add(Calendar.DAY_OF_MONTH, 3);
		String wsoEndDate = dateFormat.format(calendar.getTime());
		

		String wsoString = restTemplate
				.getForObject(
						webtrackerUri
						+ "?tracking_codes={tracking_codes}&startdate={startdate}&enddate={enddate}",
						String.class, inputParamEvent.getSiteCode(),
						inputParamEvent.getDateRange().getStartDate(),
						wsoEndDate);

		WebtrackerSessionOutput wso = gson.fromJson(wsoString, WebtrackerSessionOutput.class);

		// start call_tracking webservice
		String callTrackerUri = request.getRequestURL().toString()
				.replaceAll(Constant.MERGE_SERVICE, Constant.CT_SERVICE);
		String callTrackingString = restTemplate.getForObject(callTrackerUri + "?startdate={startdate}&enddate={enddate}&accountNumber={accountNumber}", String.class,
				inputParamEvent.getDateRange().getStartDate(), 
				inputParamEvent.getDateRange().getEndDate(), 
				inputParamEvent.getSiteCode().substring(2).trim());
		CallTracker callTracker = gson.fromJson(callTrackingString, CallTracker.class);

		List<Map<String, MergeDTO>> listMerge = null;

		AnalyticOutputData auoEvent = EventHelper.getInstance().getAnalyticOutputData();

		listMerge = EventHelper.getInstance().getListMergeData(auoEvent, wso, callTracker);
		
		if (listMerge != null && listMerge.size() > 0) {
			Map<String, List<Map<String, MergeDTO>>> map = new HashMap<String, List<Map<String,MergeDTO>>>();
			map.put("call_ref", listMerge);

			return gson.toJson(map);
		} else {
			return Constant.ErrorMessage.NO_DATA;
		}
	}


	/*
	 * 
	 * [URL]/pull?TRACKING_CODE=WT1234
	 * 
	 * */

	@RequestMapping(value="/pull",method=RequestMethod.GET)
	public ModelAndView saveCredential(HttpServletRequest request,HttpServletResponse response) throws JsonProcessingException, IOException{

		String code=request.getParameter("code");

		String TrackingCode = request.getParameter("TRACKING_CODE");
		HttpSession httpSession = request.getSession();
		if(httpSession.getAttribute("trackingcode")==null && TrackingCode!=null){
			httpSession.setAttribute("trackingcode", TrackingCode);
		}else{
			TrackingCode = (String) httpSession.getAttribute("trackingcode");
		}


		GAnalyticImplementation implementation = 
				new GAnalyticImplementation.Builder(JSON_FACTORY, HTTP_TRANSPORT, APPLICATION_NAME).loadTrackingCode(TrackingCode).build();
		
		
		OauthProperties oauthPorperties = EventHelper.getInstance().callOProperties(code, TrackingCode,implementation);
		Credential credential = implementation.getCredential(oauthPorperties.getCodeFlow(), code);

		//condition to callback
		if(credential==null){
			String redirectUrl = oauthPorperties.getHelper().buildCallBackURI(oauthPorperties.getCodeFlow());

			//return "redirect:"+redirectUrl;
			response.sendRedirect(redirectUrl);
			return null;
		}
		return new ModelAndView("test");


		//String url = request.getRequestURL().toString().replace("/pull", "/webpage");
		//System.out.println("========================= "+url);
		//return "redirect:"+url;

	}


	@RequestMapping(value="/webpage",method=RequestMethod.GET)
	public ModelAndView test(){
		return new ModelAndView("test");
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public void setHttpHeaders(HttpHeaders httpHeaders) {
		this.httpHeaders = httpHeaders;
	}

}
