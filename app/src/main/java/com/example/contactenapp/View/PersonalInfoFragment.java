package com.example.contactenapp.View;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.contactenapp.Controller.QuoteApiViewModel;
import com.example.contactenapp.QuotesAPI.Quote;
import com.example.contactenapp.QuotesAPI.Quote_;
import com.example.contactenapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class PersonalInfoFragment extends Fragment {
	
	private View mView;
	private ImageView mPersonalPhotoView;
	private TextView mMotivatie, mRandomQuote;
	private Quote_ mQuote;
	private QuoteApiViewModel mQuoteApiViewModel;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.personal_info, container, false);
		setHasOptionsMenu(true);
		mPersonalPhotoView = mView.findViewById(R.id.personalImage);
		mPersonalPhotoView.setImageResource(R.drawable.ic_person_black_96dp);
		mMotivatie = mView.findViewById(R.id.pMotivatie);
		mMotivatie.setText(R.string.motivatie);
		mRandomQuote = mView.findViewById(R.id.pQuote);
		mRandomQuote.setText(getString(R.string.quote_placeholder));
		
		
		initQuoteVM();
		return mView;
	}
	
	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		
	}
	
	private void initQuoteVM() {
		mQuoteApiViewModel = ViewModelProviders.of(getActivity()).get(QuoteApiViewModel.class);
		mQuoteApiViewModel.getQuote().observe(this, new Observer<List<Quote_>>() {
			@Override
			public void onChanged(List<Quote_> quote_s) {
				if (quote_s != null && quote_s.get(0).getQuote() != null) {
					mQuote = quote_s.get(0);
					mRandomQuote.setText(mQuote.getQuote());
				} else {
					mRandomQuote.setText(getString(R.string.noApi));
				}
			}
		});
		mQuoteApiViewModel.getError().observe(this, new Observer<String>() {
			@Override
			public void onChanged(String s) {
				if (s != null) {
					mRandomQuote.setText(s);
				}
			}
		});
	}
}
