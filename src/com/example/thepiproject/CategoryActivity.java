package com.example.thepiproject;

import com.example.thepiproject.BackGroundMusic.LocalBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class CategoryActivity extends Activity implements OnClickListener{

	
	ImageButton musicButton;
	Button logicButton;
	
	boolean mBound = false;
	private BackGroundMusic music;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		
		logicButton = (Button) findViewById(R.id.categoryLogic);
		logicButton.setOnClickListener(this);
		
		musicButton = (ImageButton)findViewById(R.id.soundButtonCategoryActivity);
		musicButton.setOnClickListener(this);
		
		
		
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		doUnbindService();
	}
	private void setMusicButton(){
		if(MainActivity.musicPlaying){
			musicButton.setImageResource(R.drawable.icon_on);
		} else {
			musicButton.setImageResource(R.drawable.icon_off);
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		doBindService();
		setMusicButton();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.category, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//Bounding Music Service with the Activity

		private ServiceConnection sCon = new ServiceConnection() {

			@Override
			public void onServiceDisconnected(ComponentName name) {
				music = null;
			}

			@Override
			public void onServiceConnected(ComponentName name, IBinder service) {
				LocalBinder binder = (LocalBinder) service;
				music = binder.getService();
			}
		};
		
		  void doBindService(){
		    	bindService(new Intent(this,BackGroundMusic.class), sCon, Context.BIND_AUTO_CREATE);
		    	mBound = true;
		    }
		    
		  void doUnbindService(){
		    	if(mBound){
		    		unbindService(sCon);
		    		mBound = false;
		    	}
		    }

		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.soundButtonCategoryActivity){
				if(MainActivity.musicPlaying){
					Log.e("PiProject", "ButtonOn");
					music.onPause();
					musicButton.setImageResource(R.drawable.icon_off);
					MainActivity.musicPlaying = false;
				} else {
					music.resumeMusic();
					musicButton.setImageResource(R.drawable.icon_on);
					MainActivity.musicPlaying = true;
				}
			} else if (v.getId() == R.id.categoryLogic){
				Intent i = new Intent(getApplicationContext(),LogicActivity.class);
				startActivity(i);
			}
		}
	
}
