package com.jet.filter.ws.helper;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.GaData.ColumnHeaders;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jet.filter.ws.helper.ganalytic.GAnalyticImplementation;
import com.jet.filter.ws.helper.ganalytic.GAnalyticOauthHelper;
import com.jet.filter.ws.model.AnalyticOutputData;
import com.jet.filter.ws.model.CallTracker;
import com.jet.filter.ws.model.GaProperties;
import com.jet.filter.ws.model.GoogleAnalyticInput;
import com.jet.filter.ws.model.InputParamEvent;
import com.jet.filter.ws.model.MergeDTO;
import com.jet.filter.ws.model.OauthProperties;
import com.jet.filter.ws.model.session.WebtrackerSessionOutput;

public class EventHelper implements Serializable {

	private static final long serialVersionUID = -7290629471314905800L;
	private AnalyticOutputData analyticOutputData;

	private static class EventHelperHolder {
		public static final EventHelper INSTANCE = new EventHelper();
	}

	private EventHelper() {
	}

	public static EventHelper getInstance() {
		return EventHelperHolder.INSTANCE;
	}

	public OauthProperties callOProperties(String code, String TRACKING_CODE,
			GAnalyticImplementation implementation) throws IOException {

		int result = implementation.initAuthorization();
		if (result == 0) {
			System.out.println("Masuk DS");
			return null;
		}

		GAnalyticOauthHelper oauthHelper = implementation.getOauthHelper();

		GoogleAuthorizationCodeFlow codeFlow = implementation
				.getAuthorizationCodeFlow();

		return new OauthProperties(oauthHelper, codeFlow);
	}

	public GoogleAnalyticInput parseGoogleAnalyticFromEvent(String jsonParameter)
			throws JsonParseException, JsonMappingException, IOException {
		InputParamEvent paramEvent = parseParamEvent(jsonParameter);

		StringBuffer sb = new StringBuffer();
		sb.append(Constant.Dimension.EVENT_LABEL);
		sb.append(Constant.COMMA_DELIMITER);
		sb.append(Constant.Dimension.GA_SOURCE);
		sb.append(Constant.COMMA_DELIMITER);
		sb.append(Constant.Dimension.GA_KEYWORD);
		sb.append(Constant.COMMA_DELIMITER);
		sb.append(Constant.Dimension.GA_MEDIUM);
		sb.append(Constant.COMMA_DELIMITER);
		sb.append(Constant.Dimension.GA_CAMPAIGN);
		sb.append(Constant.COMMA_DELIMITER);
		sb.append(Constant.Dimension.GA_LANDING_PAGE_PATH);
		sb.append(Constant.COMMA_DELIMITER);
		sb.append(Constant.Dimension.GA_REFERRAL_PATH);

		GaProperties gaProperties = new GaProperties();
		gaProperties.setStartDate(paramEvent.getDateRange().getStartDate());
		gaProperties.setEndDate(paramEvent.getDateRange().getEndDate());
		gaProperties.setMetrics(Constant.Dimension.GA_SESSIONS);
		gaProperties.setDimension(sb.toString());

		GoogleAnalyticInput analyticInput = new GoogleAnalyticInput();
		analyticInput.setTrackingCode(paramEvent.getSiteCode());
		analyticInput.setGaproperties(gaProperties);

		return analyticInput;
	}

	public InputParamEvent parseParamEvent(String jsonParameter)
			throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		InputParamEvent paramEvent = mapper.readValue(jsonParameter,
				InputParamEvent.class);

		return paramEvent;
	}

	public String getOutputGaData(GaData gaData) {
		List<ColumnHeaders> columnHeaders = gaData.getColumnHeaders();

		Map<String, List<String>> mapgadata = new LinkedHashMap<String, List<String>>();

		for (ColumnHeaders ch : columnHeaders) {
			mapgadata.put(ch.getName().split(":")[1], new ArrayList<String>());
		}

		for (int i = 0; i < gaData.getRows().size(); i++) {
			for (int j = 0; j < gaData.getRows().get(i).size(); j++) {
				mapgadata.get(new ArrayList<String>(mapgadata.keySet()).get(j))
				.add(gaData.getRows().get(i).get(j));
			}
		}

		List<String> googleSessionID = new ArrayList<String>(); // FIXME, temporary
		List<String> googleUserID = new ArrayList<String>();// FIXME, temporary

		List<String> callRefno = new ArrayList<String>();
		List<String> dateEvent = new ArrayList<String>();
		List<String> timeEvent = new ArrayList<String>();

		for (String eventLabel : mapgadata.get("eventLabel")) {
			String[] strings = eventLabel.split(",");
			if (strings.length > 1) {
				callRefno.add((strings[0].split("=")[1].trim()));
				dateEvent.add((strings[3].split("=")[1].split(" ")[0]));
				timeEvent.add((strings[3].split("=")[1].split(" ")[1]));
			} else {
				callRefno.add("");
				dateEvent.add("");
				timeEvent.add("");
			}
			googleSessionID.add("");
			googleUserID.add("");
		}

		List<String> source = mapgadata.get("source");
		List<String> medium = mapgadata.get("medium");
		List<String> keywords = mapgadata.get("keyword");
		List<String> campaign = mapgadata.get("campaign");
		List<String> landingPagePath = mapgadata.get("landingPagePath");
		List<String> referralPath = mapgadata.get("referralPath");

		analyticOutputData = new AnalyticOutputData(googleSessionID,
				googleUserID, source, medium, keywords, campaign, callRefno,
				dateEvent, timeEvent, landingPagePath, referralPath);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String jsonResult = gson.toJson(analyticOutputData);

		return jsonResult;
	}

	public GoogleAnalyticInput parseGoogleAnalyticInput(String jsonInput)
			throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		GoogleAnalyticInput gaInput = mapper.readValue(jsonInput,
				GoogleAnalyticInput.class);

		DateFormat dateFormat = new SimpleDateFormat(Constant.DATE_FORMAT);
		dateFormat.setLenient(false);
		Pattern pattern = Pattern.compile("\\d{2}");
		
		// validate date format must equal "yyyy-MM-dd"
		if (pattern.matcher(gaInput.getGaproperties().getStartDate().trim().split("-")[2]).matches()
				&& pattern.matcher(gaInput.getGaproperties().getEndDate().trim().split("-")[2]).matches()) {
			try {
				Date startDate = dateFormat.parse(gaInput.getGaproperties().getStartDate());
				Date endDate = dateFormat.parse(gaInput.getGaproperties().getEndDate());

				if (endDate.before(startDate)) { // validate if enddate before startdate
					return null;
				}

			} catch (ParseException e) {
				return null;
			}
		} else {
			return null;
		}
		
		return gaInput;
	}

	public List<Map<String, MergeDTO>> getListMergeData(AnalyticOutputData auoEvent, WebtrackerSessionOutput wso, CallTracker callTracker) {

		int tmpIndex = -1;

		MergeDTO mergeDTO = null;
		Map<String, MergeDTO> map = new HashMap<String, MergeDTO>();
		List<Map<String, MergeDTO>> listMergeDTO = new ArrayList<Map<String, MergeDTO>>();

		/**
		 * Google Analytic Data
		 */
		if (auoEvent != null && auoEvent.getCallRefno() != null) {

			for (int i = 0; i < auoEvent.getCallRefno().size(); i++) {

				mergeDTO = new MergeDTO();

				
				/**
				 * Google analytic itself
				 */
				if (auoEvent.getSource() != null && auoEvent.getSource().get(i) != null) {
					mergeDTO.setGa_source(auoEvent.getSource().get(i));
				}

				if (auoEvent.getCampaign() != null && auoEvent.getCampaign().get(i) != null) {
					mergeDTO.setGa_campaign(auoEvent.getCampaign().get(i));
				}

				if (auoEvent.getMedium() != null
						&& auoEvent.getMedium().get(i) != null) {

					mergeDTO.setGa_medium(
							auoEvent.getMedium().get(i));
				}

				if (auoEvent.getKeyword() != null
						&& auoEvent.getKeyword().get(i) != null) {
					mergeDTO.setGa_keyword(
							auoEvent.getKeyword().get(i));
				}

				/**
				 * Google analytic with webtracker
				 */
				if ((wso.getCallRefNo() != null && wso.getCallRefNo().contains(auoEvent.getCallRefno().get(i)))) {
					// Webtracker
					tmpIndex = wso.getCallRefNo().indexOf(auoEvent.getCallRefno().get(i));

					if (wso.getSource() != null
							&& wso.getSource().get(tmpIndex) != null) {

						mergeDTO.setWt_source(
								wso.getSource().get(tmpIndex));
					}

					if (wso.getCampaign() != null
							&& wso.getCampaign().get(tmpIndex) != null) {

						mergeDTO.setWt_campaign(
								wso.getCampaign().get(tmpIndex));
					}

					if (wso.getMedium() != null
							&& wso.getMedium().get(tmpIndex) != null) {

						mergeDTO.setWt_medium(
								wso.getMedium().get(tmpIndex));
					}

					if (wso.getKeywords() != null
							&& wso.getKeywords().get(i) != null) {

						mergeDTO.setWt_keyword(
								wso.getKeywords().get(tmpIndex));
					}
				}

				/**
				 * Google analytic with calltracker 
				 */
				if ((callTracker.getCall_ref_no() != null && callTracker.getCall_ref_no().contains(auoEvent.getCallRefno().get(i)))) {
					tmpIndex = callTracker.getCall_ref_no().indexOf(auoEvent.getCallRefno().get(i));

					if (callTracker.getSource() != null
							&& callTracker.getSource().get(tmpIndex) != null) {

						mergeDTO.setCt_source(callTracker.getSource().get(tmpIndex));
					}

					if (callTracker.getCampaign() != null
							&& callTracker.getCampaign().get(tmpIndex) != null) {

						mergeDTO.setCt_campaign(callTracker.getCampaign().get(tmpIndex));
					}

					if (callTracker.getMedium() != null
							&& callTracker.getMedium().get(tmpIndex) != null) {

						mergeDTO.setCt_medium(callTracker.getMedium().get(tmpIndex));
					}

					if (callTracker.getKeywords() != null
							&& callTracker.getKeywords().get(i) != null) {

						mergeDTO.setCt_keyword(callTracker.getKeywords().get(tmpIndex));
					}
				}

				map.put(auoEvent.getCallRefno().get(i), mergeDTO);
			}
		}

		/**
		 * Webtracker data
		 */
		if (wso != null && wso.getCallRefNo() != null) {

			for (int i = 0; i < wso.getCallRefNo().size(); i++) {

				mergeDTO = new MergeDTO();

				/**
				 * Webtracker with google analytic
				 */
				if ((auoEvent != null && !auoEvent.getCallRefno().contains(wso.getCallRefNo().get(i)))) {
					
					if (wso.getSource() != null && wso.getSource().get(i) != null) {
						mergeDTO.setWt_source(wso.getSource().get(i));
					}

					if (wso.getCampaign() != null
							&& wso.getCampaign().get(i) != null) {

						mergeDTO.setWt_campaign(wso.getCampaign().get(i));
					}

					if (wso.getMedium() != null
							&& wso.getMedium().get(i) != null) {

						mergeDTO.setWt_medium(wso.getMedium().get(i));
					}

					if (wso.getKeywords() != null
							&& wso.getKeywords().get(i) != null) {

						mergeDTO.setWt_keyword(wso.getKeywords().get(i));
					}
				}
				
				/**
				 * Webtracker with calltracker
				 */

				if ((callTracker.getCall_ref_no() != null && callTracker.getCall_ref_no().contains(wso.getCallRefNo().get(i)))) {
					tmpIndex = callTracker.getCall_ref_no().indexOf(wso.getCallRefNo().get(i));

					if (callTracker.getSource() != null 
							&& callTracker.getSource().get(tmpIndex) != null) {

						mergeDTO.setCt_source(callTracker.getSource().get(tmpIndex));
					}

					if (callTracker.getCampaign() != null
							&& callTracker.getCampaign().get(tmpIndex) != null) {

						mergeDTO.setCt_campaign(callTracker.getCampaign().get(tmpIndex));
					}

					if (callTracker.getMedium() != null
							&& callTracker.getMedium().get(tmpIndex) != null) {

						mergeDTO.setCt_medium(callTracker.getMedium().get(tmpIndex));
					}

					if (callTracker.getKeywords() != null
							&& callTracker.getKeywords().get(i) != null) {

						mergeDTO.setCt_keyword(callTracker.getKeywords().get(tmpIndex));
					}
				}

				map.put(wso.getCallRefNo().get(i), mergeDTO);
			}
		}

		/**
		 * Calltracker Data
		 */
		if (callTracker != null && callTracker.getCall_ref_no() != null) {

			for (int i = 0; i < callTracker.getCall_ref_no().size(); i++) {

				mergeDTO = new MergeDTO();

				if ((auoEvent != null && !auoEvent.getCallRefno().contains(callTracker.getCall_ref_no().get(i))) 
						&& (wso != null && !wso.getCallRefNo().contains(callTracker.getCall_ref_no().get(i)))) {

					/**
					 * Calltracker itself
					 */
					if (callTracker.getSource() != null
							&& callTracker.getSource().get(i) != null) {

						mergeDTO.setCt_source(callTracker.getSource().get(i));
					}

					if (callTracker.getCampaign() != null
							&& callTracker.getCampaign().get(i) != null) {

						mergeDTO.setCt_campaign(callTracker.getCampaign().get(i));
					}

					if (callTracker.getMedium() != null
							&& callTracker.getMedium().get(i) != null) {

						mergeDTO.setCt_medium(callTracker.getMedium().get(i));
					}

					if (callTracker.getKeywords() != null
							&& callTracker.getKeywords().get(i) != null) {

						mergeDTO.setCt_keyword(callTracker.getKeywords().get(i));
					}
					
					map.put(callTracker.getCall_ref_no().get(i), mergeDTO);
				}
			}
		}
		
		listMergeDTO.add(map);
		
		return listMergeDTO;
	}

	public AnalyticOutputData getAnalyticOutputData() {
		return analyticOutputData;
	}

}
