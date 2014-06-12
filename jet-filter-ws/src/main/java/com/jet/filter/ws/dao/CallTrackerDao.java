package com.jet.filter.ws.dao;

import java.util.Date;

import com.jet.filter.ws.model.CallTracker;

public interface CallTrackerDao {
	CallTracker getCallTracker(String startDate,String endDate,int accountNumber);
}
