package com.example.thepiproject;

import java.util.Locale;

import com.example.thepiproject.BackGroundMusic.LocalBinder;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends Activity implements OnClickListener {

	public static Intent musicIntent;
	
	Button newGame;
	Button changeLanguage;
	ImageButton musicButton;
	Button category;
	String language;
	SharedPreferences pref;


	boolean mBound = false;
	public static BackGroundMusic music;
	public static boolean musicPlaying;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.e("PiProject", "OnCreate");

		pref = getPreferences(MODE_PRIVATE);

		musicPlaying = pref.getBoolean("music", true);
		language = pref.getString("language", "en");

		newGame = (Button) findViewById(R.id.randomGameButton);
		newGame.setOnClickListener(this);

		changeLanguage = (Button) findViewById(R.id.languageButton);
		changeLanguage.setOnClickListener(this);

		musicButton = (ImageButton) findViewById(R.id.soundButton);
		musicButton.setOnClickListener(this);

		category = (Button) findViewById(R.id.categoryButton);
		category.setOnClickListener(this);
		
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onStart() {
		super.onStart();
		doBindService();
		Log.e("PiProject", "OnStart");
		if (musicPlaying) {
			musicIntent = new Intent(this, BackGroundMusic.class);
			startService(musicIntent);
		}
		setLocale(language);
	}

	@Override
	protected void onResume() {
		super.onResume();

		if (musicPlaying) {
			musicButton.setImageResource(R.drawable.icon_on);
		} else {
			musicButton.setImageResource(R.drawable.icon_off);
		}
		if(MainActivity.musicPlaying){
			MainActivity.musicIntent = new Intent(this, BackGroundMusic.class);
			startService(MainActivity.musicIntent);
		}
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		// cd.cancel();
		SharedPreferences.Editor editor = pref.edit();
		editor.putBoolean("music", musicPlaying);
		editor.putString("language", language);
		editor.commit();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		music.onDestroy();
		
	}

	@Override
	protected void onStop() {
		super.onStop();
		doUnbindService();
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

	public void setLocale(String lang) {
		language = lang;
		Locale myLocale = new Locale(lang);
		Resources res = getResources();
		DisplayMetrics dm = res.getDisplayMetrics();
		Configuration conf = res.getConfiguration();
		conf.locale = myLocale;
		Locale.setDefault(myLocale);
		res.updateConfiguration(conf, dm);

		newGame.setText(R.string.start_game);
		category.setText(R.string.choose_category);
		changeLanguage.setText(R.string.language);

	}

	// Bounding Music Service with the Activity

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

	void doBindService() {
		bindService(new Intent(this, BackGroundMusic.class), sCon,
				Context.BIND_AUTO_CREATE);
		mBound = true;
	}

	void doUnbindService() {
		if (mBound) {
			unbindService(sCon);
			mBound = false;
		}
	}

	@Override
	public void onClick(View v) {

		if (v.getId() == R.id.soundButton) {
			if (musicPlaying) {
				Log.e("PiProject", "ButtonOn");
				music.onPause();
				musicButton.setImageResource(R.drawable.icon_off);
				musicPlaying = false;
			} else {
				music.resumeMusic();
				musicButton.setImageResource(R.drawable.icon_on);
				musicPlaying = true;
			}
		} else if (v.getId() == R.id.categoryButton) {
			Intent i = new Intent(getApplicationContext(),
					CategoryActivity.class);
			startActivity(i);
		} else if (v.getId() == R.id.languageButton) {
			if (Locale.getDefault().getLanguage().equals("en")) {
				setLocale("bg");
			} else if (Locale.getDefault().getLanguage().equals("bg")) {
				setLocale("en");
			}
		} else if (v.getId() == R.id.randomGameButton){
			Intent i = new Intent(getApplicationContext(),AllGames.class);
			startActivity(i);
		}
	}
}
