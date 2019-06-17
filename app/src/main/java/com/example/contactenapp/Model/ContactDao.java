package com.example.contactenapp.Model;

import androidx.lifecycle.LiveData;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ContactDao {
	
	@Query("SELECT * FROM contacts ORDER BY name")
	LiveData<List<Contact>> getAllContacts();
	
	@Insert
	void insertContacts(Contact... contact);
	
	@Update
	void updateContacts(Contact... contact);
	
	@Delete
	void deleteContacts(Contact... contacts);
	
	@Delete
	void deleteAllContacts(List<Contact> contacts);
}
