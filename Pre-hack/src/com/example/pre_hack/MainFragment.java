package com.example.pre_hack;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Arrays;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.RequestAsyncTask;
import com.facebook.Response;
import com.facebook.model.GraphUser;
import com.facebook.widget.LoginButton;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.facebook.Session;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.widget.ProfilePictureView;

public class MainFragment extends Fragment{
	
	
	 private String userFacebookId;
	 private ImageView profImage;
	 private String profileName;
	 private TextView likesTV;
	 private String likes;
	public View onCreateView(LayoutInflater inflater, 
	        ViewGroup container, 
	        Bundle savedInstanceState) {
	    View view = inflater.inflate(R.layout.activity_main, container, false);
	    //profilePic = (ProfilePictureView) MainFragment.findViewById(R.id.profilepic);
	    LoginButton authButton = (LoginButton) view.findViewById(R.id.authButton);
	    likesTV = (TextView)view.findViewById(R.id.likes);
	    profImage =(ImageView)view.findViewById(R.id.profile_pic);
	    authButton.setFragment(this);
	    authButton.setReadPermissions(Arrays.asList("user_likes", "read_stream","xmpp_login","user_online_presence"));
	    
	    final Session session = Session.getActiveSession();
	    if (session != null && session.isOpened()) {
	        // If the session is open, make an API call to get user data
	        // and define a new callback to handle the response
	        Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {
	            @Override
	            public void onCompleted(GraphUser user, Response response) {
	                // If the response is successful
	                if (session == Session.getActiveSession()) {
	                    if (user != null) {
	                    	userFacebookId = user.getId();//user id
	                        profileName = user.getName();//user's profile name
	                       
	                    }   
	                }   
	            }   
	        }); 
	        Bitmap profpic = getFacebookProfilePicture(userFacebookId);
	    	profImage.setImageBitmap(profpic);
	        Request.executeBatchAsync(request);
	        Request.Callback callback = new Request.Callback() {

	            @Override
	            public void onCompleted(Response response) {
	                // response should have the likes
	               likes = response.toString();
	               likesTV.setText(likes);
	            }
	        };
	       request = new Request(session, "me/likes", null, HttpMethod.GET, callback);
	        RequestAsyncTask task = new RequestAsyncTask(request);
	        task.execute();
	        
	    }  
	    
	    
	    return view;
	}
	
	public static Bitmap getFacebookProfilePicture(String userID){
	   String imageURL;

	   Bitmap bitmap = null;
	   imageURL = "http://graph.facebook.com/"+userID+"/picture?type=large";
	   InputStream in = null;
	try {
		in = (InputStream) new URL(imageURL).getContent();
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   bitmap = BitmapFactory.decodeStream(in);

	   return bitmap;
	}
	
	
	
}
