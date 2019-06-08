package com.example.contactenapp.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.contactenapp.Model.Contact;
import com.example.contactenapp.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
	
	private List<Contact> contacts;
	private Context context;
	
	public List<Contact> getContacts() {
		return contacts;
	}
	
	public void setContacts(List<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public ContactsAdapter(List<Contact> contacts, Context context) {
		this.contacts = contacts;
		this.context = context;
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
		Contact contact = contacts.get(position);
		Bitmap bitmap = contact.getProfilePhoto();
		
		if (bitmap != null) {
			Glide.with(context).asBitmap().load(contact.getProfilePhoto()).into(holder.profilePhote);
		} else {
			Glide.with(context).asDrawable().load(R.drawable.sharp_account_circle_white_24dp).into(holder.profilePhote);
		}
		
		holder.phoneNumber.setText(contact.getPhoneNumber());
		holder.phoneNumber.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO PHONE CALL
			}
		});
		holder.name.setText(contact.getName());
		holder.email.setText(contact.getEmail());
		holder.email.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//TODO OPEN GMAIL
			}
		});
	}
	
	@Override
	public int getItemCount() {
		return contacts.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder {
		
		ImageView profilePhote;
		TextView name;
		TextView email;
		TextView phoneNumber;
		
		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			profilePhote = itemView.findViewById(R.id.profilePhoto);
			name = itemView.findViewById(R.id.name);
			email = itemView.findViewById(R.id.email);
			phoneNumber = itemView.findViewById(R.id.phoneNumber);
		}
	}
}
