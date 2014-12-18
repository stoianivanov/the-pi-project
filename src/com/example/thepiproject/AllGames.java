package com.example.thepiproject;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.playerdatabase.Player;
import com.example.playerdatabase.PlayerHelper;
import com.example.thepiproject.BackGroundMusic.LocalBinder;
import com.example.thepiproject.memory.MemoryActivity;
import com.example.thepiproject.speed.SpeedActivity;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.YLabels;

public class AllGames extends Activity implements OnClickListener{

	Dialog dialog;
	
	boolean maxLogic = false;
	boolean maxSpeed = false;
	boolean maxMemory = false;
	boolean minMemory = false;
	boolean minSpeed = false;
	boolean minLogic = false;
	
	ImageButton musicButton;
	boolean mBound = false;
	BackGroundMusic music;

	TextView finalText;
	
	long totalScore;
	long lScore;
	long sScore;
	long mScore;
	LineChart lineChart;

	Player player;
	PlayerHelper ph;
	
	ImageButton logicButton;
	ImageButton memoryButton;
	ImageButton speedButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_games);

		ph = new PlayerHelper(this);
		player = ph.getPlayer(MainActivity.currentPlayerID);

		musicButton = (ImageButton) findViewById(R.id.soundButtonAllFinal);
		musicButton.setOnClickListener(this);
		
		lineChart = (LineChart) findViewById(R.id.AllGamesLineChart);

		finalText = (TextView) findViewById(R.id.allGamesFinalText);
		
		logicButton = (ImageButton)findViewById(R.id.allGamesLogicButton);
		memoryButton = (ImageButton) findViewById(R.id.allGamesMemoryButton);
		speedButton = (ImageButton) findViewById(R.id.allGamesSpeedButton);
		logicButton.setVisibility(View.INVISIBLE);
		memoryButton.setVisibility(View.INVISIBLE);
		speedButton.setVisibility(View.INVISIBLE);
		logicButton.setOnClickListener(this);
		speedButton.setOnClickListener(this);
		memoryButton.setOnClickListener(this);
		
		totalScore = 0;
		lScore = 0;
		sScore = 0;
		mScore = 0;

		Intent intentLogic = new Intent(getApplicationContext(),
				com.example.logic.LogicMain.class);
		intentLogic.putExtra("caller", 1);
		startActivityForResult(intentLogic, 1);


	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.e("AllGames", "onActivityResult");

		if (requestCode == 1) {
			Log.e("AllGames", "requestCode == 1");
			if (resultCode == RESULT_OK) {

				lScore = data.getLongExtra("score", 0);
				totalScore += lScore;

				Intent intentSpeed = new Intent(getApplicationContext(),
						com.example.thepiproject.speed.SpeedActivity.class);
				intentSpeed.putExtra("caller", 2);
				startActivityForResult(intentSpeed, 2);
			}
		} else if (requestCode == 2) {
			Log.e("AllGames", "requestCode == 2");
			if (resultCode == RESULT_OK) {

				Log.e("AllGames", "Code:1 ResultOK");
				sScore = data.getLongExtra("score", 0);
				totalScore += sScore;

				Intent intentMemory = new Intent(getApplicationContext(),
						MemoryActivity.class);
				intentMemory.putExtra("caller", 3);
				startActivityForResult(intentMemory, 3);
			}
		} else if (requestCode == 3) {
			Log.e("AllGames", "requestCode == 3");
			if (resultCode == RESULT_OK) {

				Log.e("AllGames", "Code:1 ResultOK");
				mScore = data.getLongExtra("score", 0);
				totalScore += mScore;
				createChart();
				setTextAndButton();
			}

		}
	}

	@SuppressLint("ResourceAsColor")
	private void createChart() {

		ArrayList<Entry> comp1 = new ArrayList<Entry>();

		final float bestScore = player.getPlayerTotalPointBest();
		final float lastScore = player.getPlayerTotalPointCurrent();

		if (totalScore > bestScore) {
			showDialog();
		}

		comp1.add(new Entry(lastScore, 1));
		comp1.add(new Entry(bestScore, 2));
		comp1.add(new Entry((float) totalScore, 3));

		LineDataSet setComp1 = new LineDataSet(comp1, "Total");

		setComp1.setColors(ColorTemplate.COLORFUL_COLORS);

		ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
		dataSets.add(setComp1);

		ArrayList<String> xVals = new ArrayList<String>();
		xVals.add("0");
		xVals.add("Previous Score");
		xVals.add("Best Score");
		xVals.add("Current Score");
		xVals.add("");

		LineData data = new LineData(xVals, dataSets);

		lineChart.animateXY(2000, 2000);
		lineChart.setTouchEnabled(false);
		lineChart.setDescription("");
		lineChart.setDrawYLabels(true);
		lineChart.setValueTextSize(22f);
		lineChart.setValueTextColor(Color.YELLOW);
		lineChart.setBackgroundColor(Color.TRANSPARENT);
		lineChart.setData(data);
		lineChart.setDrawGridBackground(false);
		lineChart.setGridColor(Color.BLACK);

		Legend legend = lineChart.getLegend();
		legend.setTextSize(13f);

		XLabels xLabels = lineChart.getXLabels();
		xLabels.setTextSize(20f);
		xLabels.setTextColor(Color.GREEN);

		YLabels yLabels = lineChart.getYLabels();
		yLabels.setTextSize(12f);
		yLabels.setTextColor(Color.CYAN);
		yLabels.setLabelCount(4);
	}

	private void showDialog() {
		dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawableResource(R.drawable.high_score);
		dialog.show();
		dialog.getWindow().clearFlags(
				WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		new CountDownTimer(2000, 100) {

			@Override
			public void onTick(long millisUntilFinished) {
			}

			@Override
			public void onFinish() {
				dialog.cancel();
			}
		}.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.all_games, menu);
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
	protected void onResume() {
		super.onResume();

		setMusicButton();
		
		if (MainActivity.musicPlaying) {
			MainActivity.musicIntent = new Intent(this, BackGroundMusic.class);
			startService(MainActivity.musicIntent);
		}
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

	private void setTextAndButton(){
		
		if(lScore > mScore){
			if(lScore > sScore){
				maxLogic = true;
				if(sScore > mScore){
					minMemory = true;
				} else {
					minSpeed = true;
				}
			} else if (lScore < sScore){
				maxSpeed = true;
				minMemory = true;
			}
		} else {
			if (mScore > sScore){
				maxMemory = true;
				if(sScore > lScore){
					minLogic = true;
				} else {
					minSpeed = true;
				}
			} else {
				maxSpeed = true;
				minLogic = true;
			}
		}
		
		Log.e("settext", maxLogic +"" + minLogic);
		Log.e("settext", maxSpeed +"" + minSpeed);
		Log.e("settext", maxMemory +"" + minMemory);
		
		if(maxSpeed && minLogic){
			finalText.setText(R.string.max_speed_min_logic);
			logicButton.setVisibility(View.VISIBLE);
		} 
		if (maxSpeed && minMemory){
			memoryButton.setVisibility(View.VISIBLE);
			finalText.setText(R.string.max_speed_min_memory);
		} 
		if (maxLogic && minSpeed){
			finalText.setText(R.string.max_logic_min_speed);
			speedButton.setVisibility(View.VISIBLE);
		} 
		if (maxLogic && minMemory){
			finalText.setText(R.string.max_logic_min_memory);
			memoryButton.setVisibility(View.VISIBLE);
		} 
		if (maxMemory && minLogic){
			finalText.setText(R.string.max_memory_min_logic);
			logicButton.setVisibility(View.VISIBLE);
		} 
		if (maxMemory && minSpeed){
			finalText.setText(R.string.max_memory_min_speed);
			speedButton.setVisibility(View.VISIBLE);
		}
		
		player.setPlayerTotalPointCurrent((int) totalScore);
		ph.updateScore(MainActivity.currentPlayerID, player);
		player.checkBest();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		doBindService();
		
		
		
	}

	@Override
	public void onClick(View v) {
		Intent i = null;
		if(v.getId() == R.id.allGamesLogicButton){
			i = new Intent(getApplicationContext(),LogicActivity.class);
			i.putExtra("caller", 0);
			startActivity(i);
			finish();
			return;
		} else if (v.getId() == R.id.allGamesMemoryButton){
			i = new Intent(getApplicationContext(),MemoryActivity.class);
			i.putExtra("caller", 0);
			startActivity(i);
			finish();
			return;
		} else if (v.getId() == R.id.allGamesSpeedButton){
			i = new Intent(getApplicationContext(),SpeedActivity.class);
			i.putExtra("caller", 0);
			startActivity(i);
			finish();
			return;
		} else if (v.getId() == R.id.soundButtonAllFinal){
			if(MainActivity.musicPlaying){
				Log.e("PiProject", "ButtonOn");
				MainActivity.music.onPause();
				musicButton.setImageResource(R.drawable.icon_off);
				MainActivity.musicPlaying = false;
			} else {
				MainActivity.music.resumeMusic();
				musicButton.setImageResource(R.drawable.icon_on);
				MainActivity.musicPlaying = true;
			}
		}
	}

}
