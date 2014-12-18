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
	private ImageView currentColor;
	private boolean blue=false;
	private boolean yellow=true;
	private boolean red=false;
	private int [] buttons;
	private int correct=0;
	
	private OnAnswerSelectedListener listener;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view= inflater.inflate(R.layout.activity_memory_game4,null);
		currentColor= (ImageView) view.findViewById(R.id.game4_current_color);
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
	private void clickButton(ImageView image, int pos){
		if(yellow){
			image.setImageResource(R.drawable.yellow);
			if(buttons[pos]==3){
				++correct;
			}else {
				listener.wrongAnswer();
			}
		}else if(red){
			image.setImageResource(R.drawable.red);
			if(buttons[pos]==1){
				++correct;
			}else {
				listener.wrongAnswer();
			}
		}else if(blue){
			image.setImageResource(R.drawable.blue);
			if(buttons[pos]==2){
				++correct;
			}else {
				listener.wrongAnswer();
			}
		}
	}
	@Override
	public void onClick(View v) {
			int id = v.getId();
			
			if(id == R.id.game4Color1){
				yellow=true;
				currentColor.setImageResource(R.drawable.yellow);
				red=false;
				blue=false;
			}
			if(id == R.id.game4Color3){
				yellow=false;
				red=true;
				currentColor.setImageResource(R.drawable.red);
				blue=false;
			}
			if(id == R.id.game4Color2){
				yellow=false;
				red=false;
				blue=true;
				currentColor.setImageResource(R.drawable.blue);
			}
			if(id== R.id.paintButton1){
				clickButton(paint1, 0);
			}
			
			if(id== R.id.paintButton2){
				clickButton(paint2, 1);
			}
			
			if(id== R.id.paintButton3){
				clickButton(paint3 , 2 );
			}
			
			if(id== R.id.paintButton4){
				clickButton(paint4, 3);
			}
			
			if(id== R.id.paintButton5){
				clickButton(paint5, 4);
			}
			
			if(id== R.id.paintButton6){
				clickButton(paint6, 5);
			}
			
			if(id== R.id.paintButton7){
				clickButton(paint7, 6);
			}
			
			if(id== R.id.paintButton8){
				clickButton(paint8, 7);
			}
			
			if(id== R.id.paintButton9){
				clickButton(paint9, 8);
			}
			
			if(id== R.id.paintButton10){
				clickButton(paint10, 9);
			}
			
			if(id== R.id.paintButton11){
				clickButton(paint11, 10);
			}
			
			if(id== R.id.paintButton12){
				clickButton(paint12, 11);
			}
			
			if(correct == 12){
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
	private void creatButton(ImageView image , int pos){
		
		Random r = new Random();
		int index=r.nextInt(150);
		
		if(index<50){
			image.setImageResource(R.drawable.red);
			buttons[pos]=1;
			Log.i("InitButton", "red");
		}else if(index<100){
			image.setImageResource(R.drawable.blue);
			Log.i("InitButton", "bluew");
			buttons[pos]=2;
		}else{
			image.setImageResource(R.drawable.yellow);
			buttons[pos]=3;
			Log.i("InitButton", "yellow");
		}
	}
	private void initButton(){
		creatButton(paint1, 0);
		creatButton(paint2, 1);
		creatButton(paint3, 2);
		creatButton(paint4, 3);
		creatButton(paint5, 4);
		creatButton(paint6, 5);
		creatButton(paint7, 6);
		creatButton(paint8,7);
		creatButton(paint9, 8);
		creatButton(paint10,9);
		creatButton(paint11, 10);
		creatButton(paint12, 11);
		
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
