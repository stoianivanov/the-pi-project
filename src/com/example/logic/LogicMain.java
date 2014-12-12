package com.example.logic;

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
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.thepiproject.BackGroundMusic;
import com.example.thepiproject.BackGroundMusic.LocalBinder;
import com.example.thepiproject.MainActivity;
import com.example.thepiproject.R;

public class LogicMain extends Activity implements OnClickListener , NumbersFragment.OnAnswerSelectedListener {

	
	private static final long INITIAL_SERIES_NUMBERS_TIME = 10000;
	
	NumbersFragment numbersFragment;
	ImageButton musicButton;

	boolean mBound = false;
	BackGroundMusic music;
	ProgressBar pb;
	long timeLeft;
	CountDown cd;
	TextView result;
	Dialog dialog;
	long score;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logic_main);

		result = (TextView) findViewById(R.id.logicScore);
		
		musicButton = (ImageButton) findViewById(R.id.soundButtonLogicMain);
		musicButton.setOnClickListener(this);

		pb = (ProgressBar) findViewById(R.id.progressBar1);

		Drawable draw = getResources().getDrawable(R.drawable.progress_bar);
		pb.setProgressDrawable(draw);
		timeLeft = INITIAL_SERIES_NUMBERS_TIME;

		numbersFragment = new NumbersFragment();
		getFragmentManager().beginTransaction().replace(R.id.fragment_container,numbersFragment).commit();
		score = 0;
		doBindService();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
			if(MainActivity.musicPlaying){
				music.wrongSound();
			}
			pb.setProgress(0);
			numbersFragment.nextQuestion();
			cd = new CountDown(INITIAL_SERIES_NUMBERS_TIME, 50);
			cd.start();
		}

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

	@Override
	protected void onPause() {
		super.onPause();
		cd.cancel();
	}

	@Override
	protected void onResume() {
		super.onResume();
		setMusicButton();
		result.setText(Long.toString(score));
		if (timeLeft < 100) {
			timeLeft = INITIAL_SERIES_NUMBERS_TIME;
		}
		cd = new CountDown(timeLeft, 50);
		cd.start();
	}

	private void setMusicButton() {
		if (MainActivity.musicPlaying) {
			musicButton.setImageResource(R.drawable.icon_on);
		} else {
			musicButton.setImageResource(R.drawable.icon_off);
		}
	}

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
	protected void onStop() {
		super.onStop();
		doUnbindService();
	}

	private void showDialog() {
		dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawableResource(
				R.drawable.congratulations);
		dialog.show();
		dialog.getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		CountDownTimer timer = new CountDownTimer(1500, 100) {

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
	public void onClick(View v) {
		if (v.getId() == R.id.soundButtonLogicMain) {
			if (MainActivity.musicPlaying) {
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
	public void correctAnswer() {
		if(MainActivity.musicPlaying){
			music.correctSound();
		}
		showDialog();
		cd.cancel();
		score += timeLeft;
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
		result.setText(Long.toString(score));
		cd = new CountDown(INITIAL_SERIES_NUMBERS_TIME, 50);
		cd.start();
	}
}
