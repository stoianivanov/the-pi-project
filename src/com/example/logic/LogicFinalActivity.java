package com.example.logic;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.thepiproject.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

public class LogicFinalActivity extends Activity {

	LineChart lineChart;
	long score;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logic_final);
		
		long score = getIntent().getExtras().getLong("score");
		TextView tv = (TextView) findViewById(R.id.logicFinalTextView);
		tv.setText(Long.toString(score));
		
		lineChart = (LineChart) findViewById(R.id.logifFinalLineChart);
		createChart();
		
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
		
//		setComp1.setColors(new int[] {getResources().getColor(Color.RED),getResources().getColor(Color.RED),
//				getResources().getColor(Color.RED), getResources().getColor(Color.RED)}, getApplicationContext());
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
}
