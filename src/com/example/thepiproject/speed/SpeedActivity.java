package com.example.thepiproject.speed;

import com.example.logic.LogicFinalActivity;
import com.example.logic.NumbersExample;
import com.example.logic.OnAnswerSelectedListener;
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

public class SpeedActivity extends FragmentActivity implements OnClickListener, 
												OnAnswerSelectedListener {
	
	private static final long INITIAL_SERIES_NUMBERS_TIME = 5000;
	private static final int MAX_FRAGMENT_GAME_10 = 5;
	private ImageButton musicButton;
	private boolean mBound = false;
	private BackGroundMusic music;
	private ProgressBar pb;
	private long timeLeft;
	private CountDown cd;
	private TextView result;
	private Dialog dialog;
	private long score;
	private Fragment currentFragment;
	private FrameLayout gameLayout;
	private int currentGame10=0;
	private int caller;
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
		timeLeft=INITIAL_SERIES_NUMBERS_TIME;
		
		doBindService();
		
		caller=getIntent().getExtras().getInt("caller",0);
		gameLayout= (FrameLayout) findViewById(R.id.gameSpeed);
		StartFragment(new GameSpeed1());
		
	}
	
    private void StartFragment(Fragment fr){
    	
   	 final FragmentManager fm =  getSupportFragmentManager();
   	 currentFragment= fr;
        if(fm!=null){
       	 final FragmentTransaction tr= fm.beginTransaction();
       	 tr.replace(R.id.gameSpeed, fr, null);
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
    		timeLeft = INITIAL_SERIES_NUMBERS_TIME;
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
			if(currentFragment instanceof GameSpeed1){
				((GameSpeed1) currentFragment).createQuestion();
				cd = new CountDown(INITIAL_SERIES_NUMBERS_TIME, 50);
				cd.start();
			}else if(currentFragment instanceof GameSpeed5){
				((GameSpeed5) currentFragment).createFragment();
				cd = new CountDown(INITIAL_SERIES_NUMBERS_TIME, 50);
				cd.start();
			}else if(currentGame10<MAX_FRAGMENT_GAME_10){
				StartFragment(new GameSpeed10());
				++currentGame10;
				cd = new CountDown(INITIAL_SERIES_NUMBERS_TIME, 50);
				cd.start();
			}

			
		}


	}
	
	private void showDialog(boolean correct){
		dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		if(correct){
			dialog.getWindow().setBackgroundDrawableResource(
					R.drawable.right);
		} else {
			dialog.getWindow().setBackgroundDrawableResource(
					R.drawable.wrong);
		}
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

	@Override
	public void correctAnswer() {

		if(MainActivity.musicPlaying){
			music.correctSound();
		}
		
		showDialog(true);
		cd.cancel();
		score += timeLeft*2 ;
		result.setText(Long.toString(score));
		
		cd = new CountDown(INITIAL_SERIES_NUMBERS_TIME, 50);
		cd.start();
	}

	@Override
	public void wrongAnswer() {
		if(MainActivity.musicPlaying){
			music.wrongSound();
		}
		cd.cancel();
		showDialog(false);
		cd = new CountDown(INITIAL_SERIES_NUMBERS_TIME, 50);
		cd.start();
		
	}

	@Override
	public void nextFragment() {

		if(currentFragment instanceof GameSpeed1){
			Log.i("Start", "AStart game5");
			
			StartFragment(new GameSpeed5());
		}else if( currentFragment instanceof GameSpeed5 ){
			if(currentGame10 ==0){
				Intent i = new Intent(getApplicationContext(),NumbersExample.class);
				i.putExtra("example", 6);
				startActivity(i);
			}
			StartFragment(new GameSpeed10());
			++currentGame10;
		}else if((currentFragment instanceof GameSpeed10 )&& 
					currentGame10<=MAX_FRAGMENT_GAME_10){

			StartFragment(new GameSpeed10());
			++currentGame10;
			
		}else if(currentFragment instanceof GameSpeed10){
			if(caller ==2){
				Intent i = new Intent();
				i.putExtra("score", score);
				setResult(RESULT_OK,i);
				finish();
				return;
			}else{
				Intent i = new Intent(getApplicationContext(),LogicFinalActivity.class);
				i.putExtra("score", score);
				i.putExtra("from", "speed");
				finish();
				startActivity(i);
			}
		}
	}



}
