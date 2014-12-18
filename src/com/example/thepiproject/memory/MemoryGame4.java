package com.example.thepiproject.memory;

import java.util.Random;

import com.example.logic.OnAnswerSelectedListener;
import com.example.thepiproject.R;
import com.example.thepiproject.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MemoryGame4 extends Fragment implements OnClickListener{

	private ImageView paint1;
	private ImageView paint2;
	private ImageView paint3;
	private ImageView paint4;
	private ImageView paint5;
	private ImageView paint6;
	private ImageView paint7;
	private ImageView paint8;
	private ImageView paint9;
	private ImageView paint10;
	private ImageView paint11;
	private ImageView paint12;
	private ImageView color1;
	private ImageView color2;
	private ImageView color3;
	private boolean blue=false;
	private boolean yellow=false;
	private boolean red=false;
	private int [] buttons;
	private OnAnswerSelectedListener listener;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view= inflater.inflate(R.layout.activity_memory_game4,null);
		buttons=new int[12];
		listener = (OnAnswerSelectedListener) getActivity();
		paint1= (ImageView) view.findViewById(R.id.paintButton1);
		paint1.setOnClickListener(this);
		
		paint2= (ImageView) view.findViewById(R.id.paintButton2);
		paint2.setOnClickListener(this);
		
		paint3= (ImageView) view.findViewById(R.id.paintButton3);
		paint3.setOnClickListener(this);
		
		paint4= (ImageView) view.findViewById(R.id.paintButton4);
		paint4.setOnClickListener(this);
		
		paint5= (ImageView) view.findViewById(R.id.paintButton5);
		paint5.setOnClickListener(this);
		
		paint6= (ImageView) view.findViewById(R.id.paintButton6);
		paint6.setOnClickListener(this);
		
		paint7= (ImageView) view.findViewById(R.id.paintButton7);
		paint7.setOnClickListener(this);
		
		paint8= (ImageView) view.findViewById(R.id.paintButton8);
		paint8.setOnClickListener(this);
		
		paint9= (ImageView) view.findViewById(R.id.paintButton9);
		paint9.setOnClickListener(this);
		
		paint10= (ImageView) view.findViewById(R.id.paintButton10);
		paint10.setOnClickListener(this);
		
		paint11= (ImageView) view.findViewById(R.id.paintButton11);
		paint11.setOnClickListener(this);
		
		paint12= (ImageView) view.findViewById(R.id.paintButton12);
		paint12.setOnClickListener(this);
		initButton();
		
		color1= (ImageView) view.findViewById(R.id.game4Color1);
		color1.setOnClickListener(this);
		
		color2= (ImageView) view.findViewById(R.id.game4Color2);
		color2.setOnClickListener(this);
		
		color3 = (ImageView) view.findViewById(R.id.game4Color3);
		color3.setOnClickListener(this);
		
		 
		CountDown cd = new CountDown(2000, 50);
		cd.start();
		
		
		return view;
	}
	@Override
	public void onClick(View v) {
			int id = v.getId();
			int correct=0;
			if(id == R.id.game4Color1){
				yellow=true;
				red=false;
				blue=false;
			}
			if(id == R.id.game4Color3){
				yellow=false;
				red=true;
				blue=false;
			}
			if(id == R.id.game4Color2){
				yellow=false;
				red=false;
				blue=true;
			}
			if(id== R.id.paintButton1){
				if(yellow){
					paint1.setImageResource(R.drawable.yellow);
					if(buttons[0]==3){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(red){
					paint1.setImageResource(R.drawable.red);
					if(buttons[0]==1){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(blue){
					paint1.setImageResource(R.drawable.blue);
					if(buttons[0]==2){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}
			}
			
			if(id== R.id.paintButton2){
				if(yellow){
					paint2.setImageResource(R.drawable.yellow);
					if(buttons[1]==3){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(red){
					paint2.setImageResource(R.drawable.red);
					if(buttons[1]==1){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(blue){
					paint2.setImageResource(R.drawable.blue);
					if(buttons[1]==2){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}
			}
			
			if(id== R.id.paintButton3){
				if(yellow){
					paint3.setImageResource(R.drawable.yellow);
					if(buttons[2]==3){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(red){
					paint3.setImageResource(R.drawable.red);
					if(buttons[2]==1){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(blue){
					paint3.setImageResource(R.drawable.blue);
					if(buttons[2]==2){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}
			}
			
			if(id== R.id.paintButton4){
				if(yellow){
					paint4.setImageResource(R.drawable.yellow);
					if(buttons[3]==3){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(red){
					paint4.setImageResource(R.drawable.red);
					if(buttons[3]==1){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(blue){
					paint4.setImageResource(R.drawable.blue);
					if(buttons[3]==2){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}
			}
			
			if(id== R.id.paintButton5){
				if(yellow){
					paint5.setImageResource(R.drawable.yellow);
					if(buttons[4]==3){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(red){
					paint5.setImageResource(R.drawable.red);
					if(buttons[4]==1){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(blue){
					paint5.setImageResource(R.drawable.blue);
					if(buttons[4]==2){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}
			}
			
			if(id== R.id.paintButton6){
				if(yellow){
					paint6.setImageResource(R.drawable.yellow);
					if(buttons[5]==3){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(red){
					paint6.setImageResource(R.drawable.red);
					if(buttons[5]==1){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(blue){
					paint6.setImageResource(R.drawable.blue);
					if(buttons[5]==2){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}
			}
			
			if(id== R.id.paintButton7){
				if(yellow){
					paint7.setImageResource(R.drawable.yellow);
					if(buttons[6]==3){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(red){
					paint7.setImageResource(R.drawable.red);
					if(buttons[6]==1){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(blue){
					paint7.setImageResource(R.drawable.blue);
					if(buttons[6]==2){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}
			}
			
			if(id== R.id.paintButton8){
				if(yellow){
					paint8.setImageResource(R.drawable.yellow);
					if(buttons[7]==3){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(red){
					paint8.setImageResource(R.drawable.red);
					if(buttons[7]==1){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(blue){
					paint8.setImageResource(R.drawable.blue);
					if(buttons[7]==2){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}
			}
			
			if(id== R.id.paintButton9){
				if(yellow){
					paint9.setImageResource(R.drawable.yellow);
					if(buttons[8]==3){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(red){
					paint9.setImageResource(R.drawable.red);
					if(buttons[8]==1){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(blue){
					paint9.setImageResource(R.drawable.blue);
					if(buttons[8]==2){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}
			}
			
			if(id== R.id.paintButton10){
				if(yellow){
					paint10.setImageResource(R.drawable.yellow);
					if(buttons[9]==3){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(red){
					paint10.setImageResource(R.drawable.red);
					if(buttons[9]==1){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(blue){
					paint10.setImageResource(R.drawable.blue);
					if(buttons[9]==2){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}
			}
			
			if(id== R.id.paintButton11){
				if(yellow){
					paint11.setImageResource(R.drawable.yellow);
					if(buttons[10]==3){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(red){
					paint11.setImageResource(R.drawable.red);
					if(buttons[10]==1){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(blue){
					paint11.setImageResource(R.drawable.blue);
					if(buttons[10]==2){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}
			}
			
			if(id== R.id.paintButton12){
				if(yellow){
					paint12.setImageResource(R.drawable.yellow);
					if(buttons[11]==3){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(red){
					paint12.setImageResource(R.drawable.red);
					if(buttons[11]==1){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}else if(blue){
					paint12.setImageResource(R.drawable.blue);
					if(buttons[11]==2){
						++correct;
					}else {
						listener.wrongAnswer();
					}
				}
			}
			
			if(correct==12){
				listener.correctAnswer();
			}
	}
	
	private void allButtonGrey(){
		paint1.setImageResource(R.drawable.grey);
		paint2.setImageResource(R.drawable.grey);
		paint3.setImageResource(R.drawable.grey);
		paint4.setImageResource(R.drawable.grey);
		paint5.setImageResource(R.drawable.grey);
		paint6.setImageResource(R.drawable.grey);
		paint7.setImageResource(R.drawable.grey);
		paint8.setImageResource(R.drawable.grey);
		paint9.setImageResource(R.drawable.grey);
		paint10.setImageResource(R.drawable.grey);
		paint11.setImageResource(R.drawable.grey);
		paint12.setImageResource(R.drawable.grey);
		
	}
	private void initButton(){
		Random r = new Random();
		int index=r.nextInt(150);
		
		if(index<50){
			paint1.setImageResource(R.drawable.red);
			buttons[0]=1;
			Log.i("InitButton", "red");
		}else if(index<100){
			paint1.setImageResource(R.drawable.blue);
			Log.i("InitButton", "bluew");
			buttons[0]=2;
		}else{
			paint1.setImageResource(R.drawable.yellow);
			buttons[0]=3;
			Log.i("InitButton", "yellow");
		}
		
		index= r.nextInt(150);
		if(index<50){
				paint2.setImageResource(R.drawable.red);
				buttons[1]=1;
		}else if(index<100){
				paint2.setImageResource(R.drawable.blue);
				buttons[1]=2;
		}else{
				paint2.setImageResource(R.drawable.yellow);
				buttons[1]=3;
		}
		
		index= r.nextInt(150);
		if(index<50){
				paint3.setImageResource(R.drawable.red);
				buttons[2]=1;
		}else if(index<100){
				paint3.setImageResource(R.drawable.blue);
				buttons[2]=2;
		}else{
				paint3.setImageResource(R.drawable.yellow);
				buttons[2]=3;
		}
		
		index= r.nextInt(150);
		if(index<50){
				paint4.setImageResource(R.drawable.red);
				buttons[3]=1;
		}else if(index<100){
				paint4.setImageResource(R.drawable.blue);
				buttons[3]=2;
		}else{
				paint4.setImageResource(R.drawable.yellow);
				buttons[3]=3;
		}
		
		index= r.nextInt(150);
		if(index<50){
				paint5.setImageResource(R.drawable.red);
				buttons[4]=1;
		}else if(index<100){
				paint5.setImageResource(R.drawable.blue);
				buttons[4]=2;
		}else{
				paint5.setImageResource(R.drawable.yellow);
				buttons[4]=3;
		}
		
		index= r.nextInt(150);
		if(index<50){
				paint6.setImageResource(R.drawable.red);
				buttons[5]=1;
		}else if(index<100){
				paint6.setImageResource(R.drawable.blue);
				buttons[5]=2;
		}else{
				paint6.setImageResource(R.drawable.yellow);
				buttons[5]=3;
		}
		
		index= r.nextInt(150);
		if(index<50){
				paint7.setImageResource(R.drawable.red);
				buttons[6]=1;
		}else if(index<100){
				paint7.setImageResource(R.drawable.blue);
				buttons[6]=2;
		}else{
				paint7.setImageResource(R.drawable.yellow);
				buttons[6]=3;
		}
		
		index= r.nextInt(150);
		if(index<50){
				paint8.setImageResource(R.drawable.red);
				buttons[7]=1;
		}else if(index<100){
				paint8.setImageResource(R.drawable.blue);
				buttons[7]=2;
		}else{
				paint8.setImageResource(R.drawable.yellow);
				buttons[7]=3;
		}
		
		index= r.nextInt(150);
		if(index<50){
				paint9.setImageResource(R.drawable.red);
				buttons[8]=1;
		}else if(index<100){
				paint9.setImageResource(R.drawable.blue);
				buttons[8]=2;
		}else{
				paint9.setImageResource(R.drawable.yellow);
				buttons[8]=3;
		}
		
		index= r.nextInt(150);
		if(index<50){
				paint10.setImageResource(R.drawable.red);
				buttons[9]=1;
		}else if(index<100){
				paint10.setImageResource(R.drawable.blue);
				buttons[9]=2;
		}else{
				paint10.setImageResource(R.drawable.yellow);
				buttons[9]=3;
		}
		
		index= r.nextInt(150);
		if(index<50){
				paint11.setImageResource(R.drawable.red);
				buttons[10]=1;
		}else if(index<100){
				paint11.setImageResource(R.drawable.blue);
				buttons[10]=2;
		}else{
				paint11.setImageResource(R.drawable.yellow);
				buttons[10]=3;
		}
		
		index= r.nextInt(150);
		if(index<50){
				paint12.setImageResource(R.drawable.red);
				buttons[11]=1;
		}else if(index<100){
				paint12.setImageResource(R.drawable.blue);
				buttons[11]=2;
		}else{
				paint12.setImageResource(R.drawable.yellow);
				buttons[11]=3;
		}
	}
	private class CountDown extends CountDownTimer {

		public CountDown(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			int progres = (int) millisUntilFinished;

		}

		@Override
		public void onFinish() {
			allButtonGrey();
			
		}
	}

}
