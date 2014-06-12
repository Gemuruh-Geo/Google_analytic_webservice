	package com.jet.filter.ws.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
/*
 * @author:Gemuruh Geo Pratama
 * */
@Controller
public class HelloController {

	@RequestMapping(value = "/client")
    public ModelAndView Client(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	return new ModelAndView("index");
    }
    @ResponseBody
    @RequestMapping(value = "/")
    public String Opening() throws IOException {
    	String legend = "http://ec2-54-253-45-215.ap-southeast-2.compute.amazonaws.com/jet-filter-ws-v3/client //simple client to pull REST GA</br>"+
"http://ec2-54-253-45-215.ap-southeast-2.compute.amazonaws.com/jet-filter-ws-v3/pull // to pull data from google analytic</br>"+
"http://ec2-54-253-45-215.ap-southeast-2.compute.amazonaws.com/jet-filter-ws-v3/callback // to accept data properties for Google Analytic</br>"+
"http://ec2-54-253-45-215.ap-southeast-2.compute.amazonaws.com/jet-filter-ws-v3/filter // to filter</br>"+
"http://ec2-54-253-45-215.ap-southeast-2.compute.amazonaws.com/jet-filter-ws-v3/navigation //to get session navigtion</br>"+
"http://ec2-54-253-45-215.ap-southeast-2.compute.amazonaws.com/jet-filter-ws-v3/session_history // to get session histroy</br>"+
"http://ec2-54-253-45-215.ap-southeast-2.compute.amazonaws.com/jet-filter-ws-v3/websession // to get websession";
    	return legend;
    }

}