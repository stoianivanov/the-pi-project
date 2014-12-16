package com.example.thepiproject;

import com.example.thepiproject.memory.MemoryActivity;
import com.example.thepiproject.memory.MemoryGame2;
import com.example.thepiproject.speed.SpeedActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AllGames extends Activity {

	TextView scoreView;
	long score;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_all_games);

		score = 0;
		scoreView = (TextView) findViewById(R.id.allGamesScore);

		Intent intentLogic = new Intent(getApplicationContext(),
				com.example.logic.LogicMain.class);
		intentLogic.putExtra("caller", 1);
		startActivityForResult(intentLogic, 1);
		


	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.e("AllGames","onActivityResult");
		
		if (requestCode == 1) {
			Log.e("AllGames","requestCode == 1");
			if (resultCode == RESULT_OK) {

				Log.e("AllGames","Code:1 ResultOK");
				score += data.getLongExtra("score", 0);
				scoreView.setText(Long.toString(score));

				Intent intentSpeed = new Intent(getApplicationContext(),
						com.example.thepiproject.speed.SpeedActivity.class);
				intentSpeed.putExtra("caller", 2);
				startActivityForResult(intentSpeed, 2);
			}
		}else if(requestCode == 2){
			Log.e("AllGames","requestCode == 2");
			if (resultCode == RESULT_OK) {

				Log.e("AllGames","Code:1 ResultOK");
				score += data.getLongExtra("score", 0);
				scoreView.setText(Long.toString(score));

				Intent intentMemory = new Intent(getApplicationContext(),
						MemoryActivity.class);
				intentMemory.putExtra("caller", 3);
				startActivityForResult(intentMemory, 3);
			}
		}else if(requestCode == 3){
			Log.e("AllGames","requestCode == 3");
			if (resultCode == RESULT_OK) {

				Log.e("AllGames","Code:1 ResultOK");
				score += data.getLongExtra("score", 0);
				scoreView.setText(Long.toString(score));
			}

		}
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
}
