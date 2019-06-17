package com.example.contactenapp.Model;

import android.content.Context;
import android.graphics.Rect;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(version = 1, entities = {Contact.class}, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
	
	private static final String NAME_DATABASE = "contact_database";
	
	public abstract ContactDao contactDao();
	
	private static volatile AppDataBase INSTANCE;
	
	public static AppDataBase getDataBase(final Context context) {
		if (INSTANCE == null) {
			synchronized (AppDataBase.class) {
				if (INSTANCE == null) {
					INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDataBase.class, NAME_DATABASE).build();
				}
			}
		}
		return INSTANCE;
	}
}