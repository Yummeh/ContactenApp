package com.example.contactenapp.Controller;

import com.example.contactenapp.QuotesAPI.Contents;
import com.example.contactenapp.QuotesAPI.Quote;
import com.example.contactenapp.QuotesAPI.QuoteApiService;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuoteRepository {
	private static final String BASE_URL = "https://api.myjson.com";
	
	private QuoteApiService mQuoteApiService;
	
	public QuoteRepository() {
		mQuoteApiService = createApiService();
	}
	
	private QuoteApiService createApiService() {
		OkHttpClient okHttpClient = new OkHttpClient.Builder()
				.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
				.build();
		
		// Create the Retrofit instance
		Retrofit quoteApi = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.client(okHttpClient)
				.addConverterFactory(GsonConverterFactory.create())
				.build();
		
		// Return the Retrofit MovieDatabaseApiService
		return quoteApi.create(QuoteApiService.class);
	}
	
	public Call<Quote> getQuote() {
		return mQuoteApiService.getQuote();
	}
}
