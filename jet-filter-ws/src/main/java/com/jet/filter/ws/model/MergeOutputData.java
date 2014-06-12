package com.jet.filter.ws.model;

public class MergeOutputData {
	private String callRefNo;
	private DataMerge dataMerge;

	public MergeOutputData() {
		this.dataMerge = new DataMerge();
	}

	public class DataMerge {
		private String ga_source;
		private String ga_medium;
		private String ga_campaign;
		private String ga_keyword;

		private String wt_source;
		private String wt_medium;
		private String wt_campaign;
		private String wt_keyword;

		private String ct_source;
		private String ct_medium;
		private String ct_campaign;
		private String ct_keyword;

		public String getGa_source() {
			return ga_source;
		}

		public void setGa_source(String ga_source) {
			this.ga_source = ga_source;
		}

		public String getWt_source() {
			return wt_source;
		}

		public void setWt_source(String wt_source) {
			this.wt_source = wt_source;
		}

		public String getCt_source() {
			return ct_source;
		}

		public void setCt_source(String ct_source) {
			this.ct_source = ct_source;
		}

		public String getGa_medium() {
			return ga_medium;
		}

		public void setGa_medium(String ga_medium) {
			this.ga_medium = ga_medium;
		}

		public String getWt_medium() {
			return wt_medium;
		}

		public void setWt_medium(String wt_medium) {
			this.wt_medium = wt_medium;
		}

		public String getCt_medium() {
			return ct_medium;
		}

		public void setCt_medium(String ct_medium) {
			this.ct_medium = ct_medium;
		}

		public String getGa_campaign() {
			return ga_campaign;
		}

		public void setGa_campaign(String ga_campaign) {
			this.ga_campaign = ga_campaign;
		}

		public String getWt_campaign() {
			return wt_campaign;
		}

		public void setWt_campaign(String wt_campaign) {
			this.wt_campaign = wt_campaign;
		}

		public String getCt_campaign() {
			return ct_campaign;
		}

		public void setCt_campaign(String ct_campaign) {
			this.ct_campaign = ct_campaign;
		}

		public String getGa_keyword() {
			return ga_keyword;
		}

		public void setGa_keyword(String ga_keyword) {
			this.ga_keyword = ga_keyword;
		}

		public String getWt_keyword() {
			return wt_keyword;
		}

		public void setWt_keyword(String wt_keyword) {
			this.wt_keyword = wt_keyword;
		}

		public String getCt_keyword() {
			return ct_keyword;
		}

		public void setCt_keyword(String ct_keyword) {
			this.ct_keyword = ct_keyword;
		}

	}

	public String getCallRefNo() {
		return callRefNo;
	}

	public void setCallRefNo(String callRefNo) {
		this.callRefNo = callRefNo;
	}

	public DataMerge getDataMerge() {
		return dataMerge;
	}

	public void setDataMerge(DataMerge dataMerge) {
		this.dataMerge = dataMerge;
	}

}
