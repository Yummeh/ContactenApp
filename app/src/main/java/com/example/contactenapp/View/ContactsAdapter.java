package com.example.contactenapp.View;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.contactenapp.Controller.MainViewModel;
import com.example.contactenapp.Model.Contact;
import com.example.contactenapp.Actions.Actions;
import com.example.contactenapp.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
	
	private List<Contact> mContacts;
	private Context context;
	private Activity activity;
	private String mSearchText = "";
	private MainViewModel mMainViewModel;
	private boolean alerted;
	private AlertDialog alertDialog;
	
	public String getSearchText() {
		return mSearchText;
	}
	
	public void setSearchText(String searchText) {
		this.mSearchText = searchText;
	}
	
	public List<Contact> getmContacts() {
		return mContacts;
	}
	
	public void setmContacts(List<Contact> mContacts) {
		this.mContacts = mContacts;
	}
	
	public ContactsAdapter(List<Contact> contacts, Activity activity, MainViewModel mainViewModel) {
		this.mContacts = contacts;
		this.activity = activity;
		mMainViewModel = mainViewModel;
		context = activity.getApplicationContext();
	}
	
	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_card, parent, false);
		ViewHolder holder = new ViewHolder(view);
		return holder;
	}
	
	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		Contact contact = mContacts.get(position);
		String phoneNumber = contact.getPhoneNumber();
		String emailAdress = contact.getEmail();
		Uri imageUri = Uri.parse(contact.getProfilePictureUri());
		
		if (imageUri.toString().length() == 0) {
			Glide.with(context).asDrawable().load(R.drawable.ic_person_black_96dp).into(holder.profilePhote);
		} else {
			Glide.with(context).asDrawable().load(imageUri).into(holder.profilePhote);
		}
		
		Glide.with(context).asDrawable().load(R.drawable.ic_delete_black_24dp).into(holder.deleteItem);
		holder.deleteItem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				deleteWarning(contact);
			}
		});
		holder.phoneNumber.setText(contact.getPhoneNumber());
		holder.phoneNumber.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Actions.setPhoneNumber(phoneNumber);
				Actions.call();
			}
		});
		holder.name.setText(contact.getName());
		holder.email.setText(contact.getEmail());
		holder.email.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Actions.setEmilAdress(emailAdress);
				Actions.mail();
			}
		});
	}
	
	@Override
	public int getItemCount() {
		return mContacts.size();
	}
	
	public void swapList(List<Contact> contacts) {
		this.mContacts = contacts;
		if (contacts != null) {
			setFilter(mSearchText);
		}
	}
	
	private void setFilter(String filter) {
		String searchContact = filter.toLowerCase();
		List<Contact> newList = new ArrayList<>();
		for (Contact contact : mContacts) {
			String name = contact.getName().toLowerCase().trim();
			String email = contact.getEmail().toLowerCase().trim();
			
			if (name.contains(searchContact) || email.contains(searchContact)) {
				newList.add(contact);
			}
		}
		mContacts = new ArrayList<>();
		mContacts.addAll(newList);
		
		notifyDataSetChanged();
	}
	
	private void deleteWarning(Contact contact) {
		Log.d("yes", "hello");
		AlertDialog.Builder alert = new AlertDialog.Builder(activity);
		alert.setMessage("Do you want to remove this contact?")
				.setCancelable(false)
				.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						mMainViewModel.delete(contact);
						alerted = false;
					}
				})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						alerted = false;
					}
				});
		
		if (!alerted) {
			alerted = true;
			alertDialog = alert.create();
			alertDialog.setTitle("Removing contact!");
			alertDialog.show();
		}
	}
	
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		
		ImageView profilePhote, deleteItem;
		TextView name;
		TextView email;
		TextView phoneNumber;
		
		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			profilePhote = itemView.findViewById(R.id.profilePhoto);
			name = itemView.findViewById(R.id.name);
			email = itemView.findViewById(R.id.email);
			phoneNumber = itemView.findViewById(R.id.phoneNumber);
			deleteItem = itemView.findViewById(R.id.deleteItem);
		}
	}
}
