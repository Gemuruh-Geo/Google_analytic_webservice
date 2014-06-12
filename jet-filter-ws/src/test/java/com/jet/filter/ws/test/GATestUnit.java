package com.jet.filter.ws.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration("file:src/main/webapp/WEB-INF/mvc-dispatcher-servlet.xml")
public class GATestUnit {
	
	@Autowired
	protected WebApplicationContext context;
	protected MockMvc mockMvc;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void testNoAccount() throws Exception {
		mockMvc.perform(get("/noaccount")).andDo(print())
		.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void testNoAccessToken() throws Exception {
		mockMvc.perform(get("/noaccesstoken")).andDo(print())
		.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void testWrongInputData() throws Exception {
		mockMvc.perform(get("/wronginputdata")).andDo(print())
		.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void testGanalytic() {
		try {
			String inputJson ="{\"trackingCode\": \"WT1234\",\"gaproperties\": {\"startDate\":\"2014-05-01\",\"endDate\":\"2014-05-01\",\"metrics\":\"ga:sessions\",\"dimension\":\"ga:eventLabel,ga:source,ga:keyword,ga:medium,ga:campaign,ga:landingPagePath,ga:referralPath\"}}";
			mockMvc.perform(
					post("/ganalytic")
							.content(inputJson)
							.contentType(MediaType.APPLICATION_JSON)
							.accept(MediaType.APPLICATION_JSON)
			).andDo(print()).andExpect(status().isOk()).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testPullEvent() throws Exception {
		mockMvc.perform(get("/testpullevent")).andDo(print())
				.andExpect(status().isOk()).andReturn();
	}

	@Test
	public void testMerge() throws Exception {
		mockMvc.perform(get("/testmerge")).andDo(print())
				.andExpect(status().isOk()).andReturn();
	}

}
