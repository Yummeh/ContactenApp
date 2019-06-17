package com.example.contactenapp.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.contactenapp.R;

import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
	public static final int ADD_CONTACT_FRAGMENT = 2;
	public static final int CONTACTS_OVERVIEW_FRAGMENT = 1;
	public static final int PERSONAL_INFORMATION_FRAGMENT = 0;
	private FragmentStateManager mSectionStatePagerAdapter;
	private ViewPager mViewPager;
	private Toolbar mToolbar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		initViewPager();
		
		mToolbar = findViewById(R.id.toolbar);
		setSupportActionBar(mToolbar);
		mToolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
		
		mViewPager.setCurrentItem(1);
	}
	
	private void initViewPager() {
		mViewPager = findViewById(R.id.container);
		mSectionStatePagerAdapter = new FragmentStateManager(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
		mSectionStatePagerAdapter.addFragment(new PersonalInfoFragment(), "Fragment1");
		mSectionStatePagerAdapter.addFragment(new ContactsFragment(), "Fragment2");
		mSectionStatePagerAdapter.addFragment(new AddContactFragment(), "Fragment3");
		mViewPager.setAdapter(mSectionStatePagerAdapter);
	}
	
	public void setViewPage(int fragmentNumber) {
		mViewPager.setCurrentItem(fragmentNumber);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.toolbar_menu, menu);
		MenuItem personalInfoItem = menu.findItem(R.id.personalInfoItem);
		personalInfoItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				mViewPager.setCurrentItem(0);
				return false;
			}
		});
		MenuItem homeItem = menu.findItem(R.id.home);
		homeItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
			@Override
			public boolean onMenuItemClick(MenuItem item) {
				mViewPager.setCurrentItem(1);
				return false;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}
}
