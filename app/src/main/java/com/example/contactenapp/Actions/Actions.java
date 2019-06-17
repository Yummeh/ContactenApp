package com.example.contactenapp.Actions;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Actions {
	
	public static final int REQUEST_CALL = 1;
	private static boolean initialized = false;
	private static Context mContext;
	private static Activity mActivity;
	private static String mPhoneNumber;
	private static String mEmailAdress;
	
	public static void initialize(Context context, Activity activity) {
		mContext = context;
		mActivity = activity;
		initialized = true;
	}
	
	public static void setPhoneNumber(String phoneNumber) {
		mPhoneNumber = phoneNumber;
	}
	
	public static void setEmilAdress(String emailAdress) {
		mEmailAdress = emailAdress;
	}
	
	public static void call() {
		if (initialized || mPhoneNumber.trim().length() > 0) {
			if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
				ActivityCompat.requestPermissions(mActivity, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
			} else {
				String dial = "tel: " + mPhoneNumber;
				mContext.startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
			}
		} else {
			Log.d("Initialize", "Please initialize phonecall before use");
		}
	}
	
	public static void mail() {
		if (initialized || mEmailAdress.trim().length() > 0) {
			Intent intent = new Intent(Intent.ACTION_SEND);
			intent.putExtra(Intent.EXTRA_EMAIL, mEmailAdress);
			intent.setType("message/rfc822");
			mContext.startActivity(Intent.createChooser(intent, "Choose an email client"));
		} else {
			Log.d("Initialize", "Please initialize phonecall before use");
		}
	}
	
	public static void openGallery() {
		
	}
}

