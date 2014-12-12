package com.example.thepiproject;

import com.example.thepiproject.BackGroundMusic.LocalBinder;

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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

public class LogicActivity extends Activity implements OnClickListener {

	ImageButton musicButton;

	boolean mBound = false;
	private BackGroundMusic music;
	ProgressBar pb;
	long timeLeft;
	CountDown cd;
	TextView result;
	Dialog dialog;
	
	Button correct;
	Button wrong;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logic);

		musicButton = (ImageButton) findViewById(R.id.soundButtonLogicActivity);
		musicButton.setOnClickListener(this);

		result = (TextView) findViewById(R.id.result);
		correct = (Button) findViewById(R.id.correct);
		wrong = (Button) findViewById(R.id.wrong);
		
		correct.setOnClickListener(this);
		wrong.setOnClickListener(this);
		
		pb = (ProgressBar) findViewById(R.id.progressBar1);
		
		Drawable draw = getResources().getDrawable(R.drawable.progress_bar);
		pb.setProgressDrawable(draw);
		timeLeft=10000;
		
		doBindService();
		

	}

	@Override
	protected void onPause() {
		super.onPause();
		cd.cancel();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logic, menu);
		return true;
	}

	@Override
	protected void onStart() {
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
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
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
		super.onStop();
		doUnbindService();
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
		if (v.getId() == R.id.soundButtonLogicActivity) {
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
		} else if (v.getId() == R.id.correct){
			cd.cancel();
			if (timeLeft > 200){
				result.setText(Long.toString(timeLeft));
				showDialog();
			} else {
				result.setText("0");
			}
			if(MainActivity.musicPlaying){
				music.correctSound();
			}
		} else if (v.getId() == R.id.wrong){
			if(MainActivity.musicPlaying){
				music.wrongSound();
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
