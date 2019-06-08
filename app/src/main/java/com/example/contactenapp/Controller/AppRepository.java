package com.example.contactenapp.Controller;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import com.example.contactenapp.Model.AppDataBase;
import com.example.contactenapp.Model.Contact;
import com.example.contactenapp.Model.ContactDao;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
	private AppDataBase mAppDataBase;
	private ContactDao mContactDao;
	private LiveData<List<Contact>> mContacts;
	
	private final Executor mExecutor = Executors.newSingleThreadExecutor();
	
	public AppRepository(Context context) {
		mAppDataBase = AppDataBase.getDataBase(context);
		mContactDao = mAppDataBase.contactDao();
		mContacts = mContactDao.getAllContacts();   
	}
	
	public LiveData<List<Contact>> getAllProducts() {
		return mContacts;
	}
	
	public void insert(final Contact contact) {
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				mContactDao.insertContacts(contact);
			}
		});
	}
	
	public void update(final Contact contact) {
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				mContactDao.updateContacts(contact);
			}
		});
	}
	
	public void delete(final Contact contact) {
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				mContactDao.deleteContacts(contact);
			}
		});
	}
	
	public void deleteAll(final List<Contact> contacts) {
		mExecutor.execute(new Runnable() {
			@Override
			public void run() {
				mContactDao.deleteAllContacts(contacts);
			}
		});
	}
	
}
