package com.example.contactenapp.Controller;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.example.contactenapp.Model.Contact;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
	private AppRepository mAppRepository;
	private LiveData<List<Contact>> mContacts;
	
	
	public MainViewModel(@NonNull Application application) {
		super(application);
		mAppRepository = new AppRepository(application.getApplicationContext());
		mContacts = mAppRepository.getAllProducts();
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
