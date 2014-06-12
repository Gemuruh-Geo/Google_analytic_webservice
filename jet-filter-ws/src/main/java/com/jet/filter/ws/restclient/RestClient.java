package com.jet.filter.ws.restclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class RestClient {

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private HttpHeaders httpHeaders;
	
	 private static final String HOST = "http://localhost:8080/jet-filter-ws";
//	private static final String HOST = "http://ec2-54-253-45-215.ap-southeast-2.compute.amazonaws.com/jet-filter-ws";
	private ResponseEntity<String> result;

	@RequestMapping(value = "/noaccount", method = RequestMethod.GET)
	@ResponseBody
	public String gaNoAccount() {
		String inputJson = "{\"trackingCode\": \"WTxxxx\",\"gaproperties\": {\"startDate\":\"2014-04-07\",\"endDate\":\"2014-04-21\",\"metrics\":\"ga:visits,ga:pageviews\",\"dimension\":\"ga:source,ga:keyword,ga:pageTitle\"}}";
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(inputJson,
				httpHeaders);

		result = restTemplate.exchange(HOST + "/ganalytic", HttpMethod.POST,
				entity, String.class);

		return result.getBody();
	}

	@RequestMapping(value = "/noaccesstoken", method = RequestMethod.GET)
	@ResponseBody
	public String gaNoAccessToken() {
		String inputJson = "{\"trackingCode\": \"WT6666\",\"gaproperties\": {\"startDate\":\"2014-04-07\",\"endDate\":\"2014-04-21\",\"metrics\":\"ga:visits,ga:pageviews\",\"dimension\":\"ga:source,ga:keyword,ga:pageTitle\"}}";
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(inputJson,
				httpHeaders);

		result = restTemplate.exchange(HOST + "/ganalytic", HttpMethod.POST,
				entity, String.class);

		return result.getBody();
	}

	@RequestMapping(value = "/wronginputdata", method = RequestMethod.GET)
	@ResponseBody
	public String gaWrongInputData() {
		String inputJson = "{\"trackingCode\": \"WT2073\",\"gaproperties\": {\"startDate\":\"2014-04-07\",\"endDate\":\"2014-04-05\",\"metrics\":\"ga:visits,ga:pageviews\",\"dimension\":\"ga:source,ga:keyword,ga:pageTitle\"}}";
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(inputJson,
				httpHeaders);

		result = restTemplate.exchange(HOST + "/ganalytic", HttpMethod.POST,
				entity, String.class);

		return result.getBody();
	}

	@RequestMapping(value = "/testpullevent", method = RequestMethod.GET)
	@ResponseBody
	public String testPullEvent() {
		String inputJson = "{ \"siteCode\" : \"WT1234\", \"view\" : \"conversionData\", \"dateRange\" : { \"startDate\" : \"2014-04-07\", \"endDate\"  : \"2014-04-21\" }}";
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(inputJson,
				httpHeaders);

		result = restTemplate.exchange(HOST + "/pullevent", HttpMethod.POST,
				entity, String.class);

		return result.getBody();
	}

	@RequestMapping(value = "/testmerge", method = RequestMethod.GET)
	@ResponseBody
	public String testMerge() {
		String inputJson = "{ \"siteCode\" : \"WT1590\", \"view\" : \"conversionData\", \"dateRange\" : { \"startDate\" : \"2014-04-07\", \"endDate\"  : \"2014-04-21\" }}";
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(inputJson,
				httpHeaders);

		result = restTemplate.exchange(HOST + "/merge", HttpMethod.POST,
				entity, String.class);

		return result.getBody();
	}

	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public void setHttpHeaders(HttpHeaders httpHeaders) {
		this.httpHeaders = httpHeaders;
	}

}
