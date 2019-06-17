package com.example.contactenapp.Controller;

import android.app.Application;
import android.os.Debug;
import android.util.Log;

import com.example.contactenapp.QuotesAPI.Contents;
import com.example.contactenapp.QuotesAPI.Quote;
import com.example.contactenapp.QuotesAPI.Quote_;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuoteApiViewModel extends AndroidViewModel implements Callback<Quote> {
	private QuoteRepository mQuoteRepository;
	private MutableLiveData<List<Quote_>> quote = new MutableLiveData<>();
	private MutableLiveData<String> error = new MutableLiveData<>();
	
	public QuoteApiViewModel(@NonNull Application application) {
		super(application);
		mQuoteRepository = new QuoteRepository();
		mQuoteRepository.getQuote().enqueue(this);
	}
	
	public LiveData<List<Quote_>> getQuote() {
		return quote;
	}
	
	public LiveData<String> getError() {
		return error;
	}
	
	@Override
	public void onResponse(Call<Quote> call, Response<Quote> response) {
		if (response.isSuccessful() && response.body() != null) {
			if (response.body().getContents() != null) {
				quote.setValue(response.body().getContents().getQuotes());
			}
		} else {
			error.setValue("Api Error: " + response.message());
		}
	}
	
	@Override
	public void onFailure(Call<Quote> call, Throwable t) {
		
	}
}
