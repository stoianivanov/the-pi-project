package com.example.thepiproject;

import android.app.IntentService;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

public class BackGroundMusic extends IntentService {

	public BackGroundMusic() {
		super("BackGroundMusic");
	}


	private final IBinder mBinder = new LocalBinder();
	MediaPlayer backGroundMusic;
	MediaPlayer correctSFX;
	MediaPlayer wrongSFX;
	private int lenght = 0;

//	public BackGroundMusic() {
//
//	}

	public class LocalBinder extends Binder {
		BackGroundMusic getService() {
			return BackGroundMusic.this;
		}
	}

	@Override
	public IBinder onBind(Intent intent) {
		return mBinder;
	}

	public void correctSound(){
		correctSFX.start();
	}
	
	public void wrongSound(){
		wrongSFX.start();
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		backGroundMusic = MediaPlayer.create(this, R.raw.music);
		correctSFX = MediaPlayer.create(this, R.raw.correct);
		wrongSFX = MediaPlayer.create(this,R.raw.wrong);
		backGroundMusic.setLooping(true);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		return super.onStartCommand(intent, flags, startId);
	}

	public void onPause() {
		if (backGroundMusic.isPlaying()) {
			backGroundMusic.pause();
			lenght = backGroundMusic.getCurrentPosition();
		}
	}

	public void resumeMusic() {

		if (!backGroundMusic.isPlaying()) {
			backGroundMusic.seekTo(lenght);
			backGroundMusic.start();
		}
	}

	public void onStop() {
		backGroundMusic.stop();
		backGroundMusic.release();
		backGroundMusic = null;
		wrongSFX.stop();
		wrongSFX.release();
		wrongSFX = null;
		correctSFX.stop();
		correctSFX.release();
		correctSFX = null;
	}


	@Override
	public void onDestroy() {
		super.onDestroy();

		if (backGroundMusic != null) {
			try {
				backGroundMusic.stop();
				backGroundMusic.release();
				correctSFX.stop();
				correctSFX.release();
				wrongSFX.stop();
				wrongSFX.release();
				} finally {
				backGroundMusic = null;
				correctSFX = null;
				wrongSFX = null;
			}
		}
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		backGroundMusic.start();
	}
}
