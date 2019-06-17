package com.example.contactenapp.View;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.contactenapp.Controller.MainViewModel;
import com.example.contactenapp.Model.Contact;
import com.example.contactenapp.Actions.Actions;
import com.example.contactenapp.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ContactsFragment extends Fragment {
	private static final String TAG = "Fragment1";
	
	private ContactsAdapter mContactsAdapter;
	private View view;
	private List<Contact> mContacts;
	private MainViewModel mMainViewModel;
	private RecyclerView mRecyclerView;
	private androidx.appcompat.widget.SearchView mSearchView;
	
	private boolean started = false;
	private boolean searching = false;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		setHasOptionsMenu(true);
		view = inflater.inflate(R.layout.fragment_contacts_overview, container, false);
		Button addContact = view.findViewById(R.id.addContact);
		initViewModel();
		initRecylerView();
		Actions.initialize(getContext(), getActivity());
		addContact.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				((MainActivity) getActivity()).setViewPage(2);
			}
		});
		
		return view;
	}
	
	
	private void initViewModel() {
		mContacts = new ArrayList<>();
		mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
		mMainViewModel.getContacts().observe(this, new Observer<List<Contact>>() {
			@Override
			public void onChanged(@Nullable List<Contact> contacts) {
				mContacts = contacts;
				if (!searching) {
					UpdateUI();
				}
				Log.d("Contacts", "Update");
				if (!started) {
					started = true;
				}
			}
		});
	}
	
	private void initRecylerView() {
		mRecyclerView = view.findViewById(R.id.rec);
		mContactsAdapter = new ContactsAdapter(mContacts, getActivity(), mMainViewModel);
		mRecyclerView.setAdapter(mContactsAdapter);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
	}
	
	private void UpdateUI() {
		mContactsAdapter.swapList(mContacts);
	}
	
	@Override
	public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		MenuItem searchItem = menu.findItem(R.id.searchItem);
		mSearchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();
		mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}
			
			@Override
			public boolean onQueryTextChange(String newText) {
				mContactsAdapter.setSearchText(newText);
				UpdateUI();
				return false;
				
			}
		});
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == Actions.REQUEST_CALL) {
			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				Log.d("granted", "permission granted");
				Actions.call();
			} else {
				Log.d("no permission", "no permission");
			}
		}
	}
}
