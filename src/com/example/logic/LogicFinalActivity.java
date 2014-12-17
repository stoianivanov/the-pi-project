package com.example.logic;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.thepiproject.BackGroundMusic;
import com.example.thepiproject.BackGroundMusic.LocalBinder;
import com.example.thepiproject.MainActivity;
import com.example.thepiproject.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

public class LogicFinalActivity extends Activity implements OnClickListener{

	LineChart lineChart;
	long score;
	boolean mBound = false;
	BackGroundMusic music;
	ImageButton musicButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logic_final);
		
		long score = getIntent().getExtras().getLong("score");
		TextView tv = (TextView) findViewById(R.id.logicFinalTextView);
		tv.setText(Long.toString(MainActivity.currentPlayerID));
		
		musicButton = (ImageButton) findViewById (R.id.soundButtonLogicFinal);
		musicButton.setOnClickListener(this);
		lineChart = (LineChart) findViewById(R.id.logifFinalLineChart);
		createChart();
		
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
	private void createChart(){
		
		ArrayList<Entry> comp1 = new ArrayList<Entry>();
		ArrayList<Entry> comp2 = new ArrayList<Entry>();
		
		comp1.add(new Entry(100.00f, 0));
		comp1.add(new Entry(50.00f,1));
		
		comp2.add(new Entry(120.00f,0));
		comp2.add(new Entry(110.00f,1));
		
		LineDataSet setComp1 = new LineDataSet(comp1, "Company 1");
		LineDataSet setcomp2 = new LineDataSet(comp2, "Company 2");
		
		setComp1.setColors(new int[] {R.color.Brown,R.color.Brown,
				R.color.Brown,R.color.Brown}, getApplicationContext());
//		setcomp2.setColors(new int[] {getResources().getColor(Color.GREEN),getResources().getColor(Color.GREEN),
//				getResources().getColor(Color.GREEN), getResources().getColor(Color.GREEN)}, getApplicationContext());
		
		ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
		dataSets.add(setComp1);
		dataSets.add(setcomp2);
		
		ArrayList<String> xVals = new ArrayList<String>();
		xVals.add("1.Q");
		xVals.add("2.Q");
		xVals.add("3.Q");
		xVals.add("4.Q");
		LineData data = new LineData(xVals,dataSets);
		lineChart.animateXY(2000, 2000);
		lineChart.setData(data);
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
