package com.example.logic;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.playerdatabase.Player;
import com.example.playerdatabase.PlayerHelper;
import com.example.thepiproject.BackGroundMusic;
import com.example.thepiproject.BackGroundMusic.LocalBinder;
import com.example.thepiproject.MainActivity;
import com.example.thepiproject.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.Legend;
import com.github.mikephil.charting.utils.XLabels;
import com.github.mikephil.charting.utils.YLabels;

public class LogicFinalActivity extends Activity implements OnClickListener{

	LineChart lineChart;
	long score;
	boolean mBound = false;
	BackGroundMusic music;
	ImageButton musicButton;
	
	Dialog dialog;
	
	Player player;
	PlayerHelper ph;
	
	String calledFrom;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logic_final);
		
		ph = new PlayerHelper(this);
		player = ph.getPlayer(MainActivity.currentPlayerID);
		
		score = getIntent().getExtras().getLong("score");
		calledFrom = getIntent().getExtras().getString("from");
		
		TextView tv = (TextView) findViewById(R.id.logicFinalTextView);
		
		lineChart = (LineChart) findViewById(R.id.logifFinalLineChart);
		createChart();
		
		
		if(calledFrom.equals("logic")){
			player.setPlayerLPointCurrent((int)score);
		} else if (calledFrom.equals("speed")){
			player.setPlayerSPointCurrent((int)score);
		} else if (calledFrom.equals("memory")){
			player.setPlayerMPointCurrent((int)score);
		} 
		
		ph.updateScore(MainActivity.currentPlayerID, player);
		
		musicButton = (ImageButton) findViewById (R.id.soundButtonLogicFinal);
		musicButton.setOnClickListener(this);
		
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		doUnbindService();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		doBindService();
		setMusicButton();
		if(MainActivity.musicPlaying){
			MainActivity.musicIntent = new Intent(this, BackGroundMusic.class);
			startService(MainActivity.musicIntent);
		}
	}
	
	private void showDialog(){
		dialog = new Dialog(this);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.getWindow().setBackgroundDrawableResource(
					R.drawable.high_score);
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
	
	@SuppressLint("ResourceAsColor") 
	private void createChart(){
		
		ArrayList<Entry> comp1 = new ArrayList<Entry>();
		
		float bestScore = 0;
		float lastScore = 0;
		
		
		if(calledFrom.equals("logic")){
			bestScore = (float)player.getPlayerLPointBest();
			lastScore = (float)player.getPlayerLPointCurrent();
		} else if (calledFrom.equals("speed")){
			bestScore = (float)player.getPlayerSPointBest();
			lastScore = (float)player.getPlayerSPointCurrent();
		} else if (calledFrom.equals("memory")){
			bestScore = (float)player.getPlayerMPointBest();
			lastScore = (float)player.getPlayerMPointCurrent();
		}
		
		
		if(score > bestScore){
			showDialog();
		}
		
		comp1.add(new Entry(lastScore,1));
		comp1.add(new Entry(bestScore, 2));
		comp1.add(new Entry((float) score, 3));
		
		LineDataSet setComp1 = new LineDataSet(comp1,calledFrom);
		if(calledFrom.equals("logic")){
			setComp1.setColor(Color.BLUE);
		} else if (calledFrom.equals("speed")){
			setComp1.setColor(Color.GREEN);
		} else if(calledFrom.equals("memory")){
			setComp1.setColor(Color.RED);
		}
		
		ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
		dataSets.add(setComp1);
		
		
		
		ArrayList<String> xVals = new ArrayList<String>();
		xVals.add("0");
		xVals.add("Previous Score");
		xVals.add("Best Score");
		xVals.add("Current Score");
		xVals.add("");
		
		LineData data = new LineData(xVals,dataSets);
		
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
		
		Legend legend =	lineChart.getLegend();
		legend.setTextSize(13f);
		
		XLabels xLabels = lineChart.getXLabels();
		xLabels.setTextSize(20f);
		xLabels.setTextColor(Color.GREEN);
		
		
		YLabels yLabels = lineChart.getYLabels();
		yLabels.setTextSize(12f);
		yLabels.setTextColor(Color.CYAN);
		yLabels.setLabelCount(4);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.logic_final, menu);
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
	public void onClick(View v) {
		if (v.getId() == R.id.soundButtonLogicFinal) {
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
}
