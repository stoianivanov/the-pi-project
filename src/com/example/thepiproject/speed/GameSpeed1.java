package com.example.thepiproject.speed;

import com.example.thepiproject.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameSpeed1 extends Fragment {

	private static final String ANSWER = "4";
	private TextView  question;
	private EditText answer;
	private Button next;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_game_speed1, null);
		
		answer= (EditText) view.findViewById(R.id.answerGame1);
		
		next= (Button) view.findViewById(R.id.nextGame1);
		next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.i("Game 1", "Onclick");
				GetPoints points = (GetPoints) getActivity();
				Log.i("Game 1", "Onclick");
				if(answer.getText().toString().equals(ANSWER)){
					Log.i("Game 1", "Onclick");
					points.getPoint(100);
				}else{
					points.getPoint(0);
					Log.i("Game 1", "Onclick");
				}
		
				Log.i("Game 1", "Onclick");
			}
		});
		return view;
	}
}
