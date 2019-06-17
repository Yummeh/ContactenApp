package com.example.contactenapp.QuotesAPI;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Contents {
	
	@SerializedName("quotes")
	@Expose
	private List<Quote_> quotes = null;
	
	public List<Quote_> getQuotes() {
		return quotes;
	}
	
	public void setQuotes(List<Quote_> quotes) {
		this.quotes = quotes;
	}
	
}