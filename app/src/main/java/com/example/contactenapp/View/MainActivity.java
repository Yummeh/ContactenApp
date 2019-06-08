package com.example.contactenapp.View;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;

import com.example.contactenapp.Controller.MainViewModel;
import com.example.contactenapp.Model.Contact;
import com.example.contactenapp.R;

import java.util.List;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {
	
	private MainViewModel mMainViewModel;
	
	private ContactsAdapter mContactsAdapter;
	private RecyclerView mRecyclerView;
	
	private List<Contact> mContacts;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initViewModel();
		
		
	}
	
	private void initViewModel() {
		mMainViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainViewModel.class);
		mMainViewModel.getContacts().observe(this, new Observer<List<Contact>>() {
			@Override
			public void onChanged(@Nullable List<Contact> contacts) {
				mContacts = contacts;
				UpdateUI();
			}
		});
	}
	
	private void initRecylerView() {
		mRecyclerView = findViewById(R.id.contacts);
		mContactsAdapter = new ContactsAdapter(mContacts, this);
		mRecyclerView.setAdapter(mContactsAdapter);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
	}
	
	
	private void UpdateUI() {
		mContactsAdapter.setContacts(mContacts);
	}
	
}
