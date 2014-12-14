package com.example.thepiproject.speed;

import com.example.thepiproject.BackGroundMusic;
import com.example.thepiproject.MainActivity;
import com.example.thepiproject.R;
import com.example.thepiproject.BackGroundMusic.LocalBinder;
import com.example.thepiproject.R.layout;

import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class SpeedActivity extends FragmentActivity implements OnClickListener, GetPoints {

	
	
	ImageButton musicButton;

	boolean mBound = false;
	private BackGroundMusic music;
	ProgressBar pb;
	long timeLeft;
	CountDown cd;
	TextView result;
	Dialog dialog;
	
	private FrameLayout gameLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_speed);
		
		musicButton= (ImageButton) findViewById(R.id.soundButtonSpeedActivity);
		musicButton.setOnClickListener(this);
		
		result= (TextView) findViewById(R.id.resultSpeed);
		
		pb = (ProgressBar) findViewById(R.id.progressBarSpeed);
		
		Drawable draw = getResources().getDrawable(R.drawable.progress_bar);
		pb.setProgressDrawable(draw);
		timeLeft=10000;
		
		doBindService();
		
		
		gameLayout= (FrameLayout) findViewById(R.id.gameSpeed);
		StartRandomFragment(new GameSpeed5());
	}
	
    private void StartRandomFragment(Fragment fr){
   	 final FragmentManager fm =  getSupportFragmentManager();
        if(fm!=null){
       	 final FragmentTransaction tr= fm.beginTransaction();
       	 tr.add(R.id.gameSpeed, fr, null);
       	 tr.commitAllowingStateLoss();
       	 
        }
        
   }
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		cd.cancel();
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.logic, menu);
		return true;

	}
	
	
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		setMusicButton();
		if(timeLeft < 100){
    		timeLeft = 10000;
    	}
    	cd = new CountDown(timeLeft, 50);
    	cd.start();
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void setMusicButton() {
		if (MainActivity.musicPlaying) {
			musicButton.setImageResource(R.drawable.icon_on);
		} else {
			musicButton.setImageResource(R.drawable.icon_off);
		}
	}
	
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		doUnbindService();
	}
	
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
		if (v.getId() == R.id.soundButtonSpeedActivity) {
			if (MainActivity.musicPlaying) {
				Log.e("PiProject", "ButtonOn");
				music.onPause();
				musicButton.setImageResource(R.drawable.icon_off);
				MainActivity.musicPlaying = false;
			} else {
				music.resumeMusic();
				musicButton.setImageResource(R.drawable.icon_on);
				MainActivity.musicPlaying = true;
			}
		} 
		
	}

	@Override
	public void getPoint(int point) {
	
		if(point>=0){
			cd.cancel();
			if (timeLeft > 200&& point >1){
				Log.i("SPEED Activity", "TIME >200");
				int points = Integer.parseInt(result.getText().toString());
				points= points+point;
				Log.i("SPEED Activity", "TIME >200");
				result.setText(""+points);
				Log.i("SPEED Activity", "TIME >200");
				showDialog();
			} else {
				if(MainActivity.musicPlaying){
					music.wrongSound();
				}
			}
		}

	}
	private class CountDown extends CountDownTimer {

		public CountDown(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			int progres = (int) millisUntilFinished;
			timeLeft = millisUntilFinished;
			pb.setProgress(progres);
		}

		@Override
		public void onFinish() {
			pb.setProgress(0);
			result.setText("0");
		}

	}
	
	private void showDialog(){
		dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawableResource(R.drawable.congratulations);
		dialog.show();
		dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		CountDownTimer timer = new CountDownTimer(1500,100) {
			
			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onFinish() {
				dialog.cancel();
			}
		}.start();
		
	}



}
