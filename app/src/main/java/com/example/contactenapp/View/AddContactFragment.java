package com.example.contactenapp.View;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.contactenapp.Controller.MainViewModel;
import com.example.contactenapp.Model.Contact;
import com.example.contactenapp.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import static android.app.Activity.RESULT_OK;

public class AddContactFragment extends Fragment {
	private static final String TAG = "Fragment2";
	private static final int PICK_IMAGE = 100;
	
	private EditText mName, mEmail, mPhoneNumber;
	private ImageView mImage;
	private Button mFinishAddingContact, mChangeImage;
	private View mView;
	private MainViewModel mMainViewModel;
	private Toast mToast;
	private Uri mImageUri;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_add_contact, container, false);
		initButton();
		initEditTexts();
		mMainViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
		
		return mView;
	}
	
	private void initEditTexts() {
		mName = mView.findViewById(R.id.eName);
		mEmail = mView.findViewById(R.id.eEmail);
		mPhoneNumber = mView.findViewById(R.id.ePhoneNumber);
		mImage = mView.findViewById(R.id.eImage);
		mChangeImage = mView.findViewById(R.id.changeImage);
		mImage.setImageResource(R.drawable.ic_person_black_96dp);
		mImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openGallery();
			}
		});
		mChangeImage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				openGallery();
			}
		});
	}
	
	private void initButton() {
		mFinishAddingContact = mView.findViewById(R.id.finishAdding);
		mFinishAddingContact.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String string = "empty";
				String name = mName.getText().toString();
				String email = mEmail.getText().toString();
				String phoneNumber = mPhoneNumber.getText().toString();
				String imageUri;
				
				if (name.equals("") && email.equals("") && phoneNumber.equals("")) {
					showToast();
					return;
				}
				
				if (mImageUri == null || mImageUri.toString().length() == 0) {
					imageUri = "";
				} else {
					imageUri = mImageUri.toString();
				}
				Contact newContact = new Contact(mName.getText().toString(), mEmail.getText().toString(), mPhoneNumber.getText().toString(), imageUri);
				mMainViewModel.insert(newContact);
				
				((MainActivity) getActivity()).setViewPage(1);
			}
		});
	}
	
	private void showToast() {
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(getContext(), "Please enter something in the fields!", Toast.LENGTH_SHORT);
		mToast.show();
	}
	
	private void openGallery() {
		Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
		startActivityForResult(intent, PICK_IMAGE);
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
			mImageUri = data.getData();
			mImage.setImageURI(mImageUri);
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
}
