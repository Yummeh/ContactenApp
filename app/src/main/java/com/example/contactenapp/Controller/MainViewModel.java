package com.example.contactenapp.Controller;

import android.app.Application;
import android.app.KeyguardManager;
import android.util.Log;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.contactenapp.Model.Contact;
import com.example.contactenapp.QuotesAPI.Quote;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
	private AppRepository mAppRepository;
	private LiveData<List<Contact>> mContacts;
	
	public MainViewModel(@NonNull Application application) {
		super(application);
		mAppRepository = new AppRepository(application.getApplicationContext());
		mContacts = mAppRepository.getAllContacts();
		
	}
	
	public LiveData<List<Contact>> getContacts() {
		return mContacts;
	}
	
	public void insert(Contact contact) {
		mAppRepository.insert(contact);
	}
	
	public void update(Contact contact) {
		mAppRepository.update(contact);
	}
	
	public void delete(Contact contact) {
		mAppRepository.delete(contact);
	}
	
	public void deleteAll(List<Contact> contacts) {
		mAppRepository.deleteAll(contacts);
	}
}
