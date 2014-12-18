package com.example.logic;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.thepiproject.R;

public class NumbersExample extends Activity implements OnClickListener {

	ImageButton okButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.origami_example);
		final int exampleNumber = getIntent().getExtras().getInt("example");
		
		if(exampleNumber == 1){
			setContentView(R.layout.activity_numbers_example);
		} else if (exampleNumber == 2){
			setContentView(R.layout.finish_the_pic_example);
		} else if (exampleNumber == 3){
			setContentView(R.layout.origami_example);
		} else if (exampleNumber == 4){
			setContentView(R.layout.solve_the_expresion_example);
		} else if (exampleNumber == 5){
			setContentView(R.layout.sqrt_number_example);
		} else if (exampleNumber == 6){
			setContentView(R.layout.remove_the_tick_example);
		} else if (exampleNumber == 7){
			setContentView(R.layout.whats_missing_example);
		} else if (exampleNumber == 8){
			setContentView(R.layout.keys_example);
		} else if (exampleNumber == 9){
			setContentView(R.layout.color_the_blocks);
		}
		
		
//		getWindow().clearFlags(
//				WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		okButton = (ImageButton) findViewById(R.id.buttonExampleResult);
		okButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.numbers_example, menu);
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

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.buttonExampleResult) {
			// setResult(RESULT_OK);
			finish();
		}
	}
}
