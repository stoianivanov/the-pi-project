package com.example.thepiproject.speed;

import com.example.thepiproject.R;
import com.example.thepiproject.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class GameSpeed5 extends Fragment implements OnClickListener {


	private Button answer1;
	private Button answer2;
	private Button answer3;
	private Button answer4;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view= inflater.inflate(R.layout.activity_game_speed5, null);
		answer1= (Button) view.findViewById(R.id.Answer1);
		answer1.setOnClickListener(this);
		
		answer2= (Button) view.findViewById(R.id.Answer2);
		answer2.setOnClickListener(this);
		
		answer3= (Button) view.findViewById(R.id.Answer3);
		answer3.setOnClickListener(this);
		
		answer4= (Button) view.findViewById(R.id.Answer4);
		answer4.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View v) {
		int id= v.getId();
		GetPoints points= (GetPoints) getActivity();
		if(id==R.id.Answer4){
			points.getPoint(100);
		}else{
			points.getPoint(0);
		}
		
	}
}
