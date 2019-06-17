package com.example.contactenapp.QuotesAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Quote {
	
	@SerializedName("success")
	@Expose
	private Success success;
	@SerializedName("contents")
	@Expose
	private Contents contents;
	
	public Success getSuccess() {
		return success;
	}
	
	public void setSuccess(Success success) {
		this.success = success;
	}
	
	public Contents getContents() {
		return contents;
	}
	
	public void setContents(Contents contents) {
		this.contents = contents;
	}
}