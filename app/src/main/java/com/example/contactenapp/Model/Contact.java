package com.example.contactenapp.Model;

import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.telephony.PhoneNumberUtils;

import java.io.File;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contacts")
public class Contact {
	
	@PrimaryKey(autoGenerate = true)
	private int id;
	
	@ColumnInfo(name = "name")
	private String name;
	
	@ColumnInfo(name = "email")
	private String email;
	
	@ColumnInfo(name = "phoneNumber")
	private String phoneNumber;
	
	@ColumnInfo(name = "profilePictureUri")
	private String profilePictureUri;
	
	public Contact(String name, String email, String phoneNumber, String profilePictureUri) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.profilePictureUri = profilePictureUri;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getProfilePictureUri() {
		return profilePictureUri;
	}
	
	public void setProfilePictureUri(String profilePictureUri) {
		this.profilePictureUri = profilePictureUri;
	}
}
