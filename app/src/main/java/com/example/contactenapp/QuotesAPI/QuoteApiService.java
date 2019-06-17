package com.example.contactenapp.QuotesAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuoteApiService {
	@GET("/bins/wtno5")
	Call<Quote> getQuote();
}
