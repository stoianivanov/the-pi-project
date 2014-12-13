package com.example.thepiproject.speed;

import com.example.thepiproject.R;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameSpeed2 extends Fragment {

	private static final String ANSWER = "10";
	private TextView  question;
	private EditText answer;
	private Button next;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_game_speed2, null);
		
		answer= (EditText) view.findViewById(R.id.answerGame2);
		
		next= (Button) view.findViewById(R.id.nextGame2);
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("Game 2", "ON click");
				GetPoints points = (GetPoints) getActivity();

				if(answer.getText().toString().equals(ANSWER)){
					points.getPoint(100);
				}else{
					points.getPoint(0);

				}
	
			}
				
			
		});
		return view;
	}
}
