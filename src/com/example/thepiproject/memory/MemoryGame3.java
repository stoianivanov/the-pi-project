package com.example.thepiproject.memory;

import java.util.Random;

import com.example.thepiproject.R;
import com.example.thepiproject.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MemoryGame3 extends Fragment implements OnClickListener{


	private long timeLeft;
	private ImageView key1;
	private ImageView key2;
	private ImageView key3;
	private ImageView key4;
	private ImageView key5;
	private ImageView key6;
	private ImageView key7;
	private ImageView key8;
	private ImageView key9;
	private ImageView key10;
	private ImageView key11;
	private ImageView key12;
	private ImageView backgroud;
	private int[] keys;
	private View view;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		view= inflater.inflate(R.layout.activity_memory_game3, null);
		keys=new int[12];
		initKeys();
		backgroud= (ImageView) view.findViewById(R.id.backgroud_game_3);
		key1= (ImageView) view.findViewById(R.id.key1);
		key1.setOnClickListener(this);
		createImage(key1, 1);
		
		key2= (ImageView) view.findViewById(R.id.key2);
		key2.setOnClickListener(this);
		createImage(key2, 2);
		
		key3= (ImageView) view.findViewById(R.id.key3);
		key3.setOnClickListener(this);
		createImage(key3,3);
		
		key4= (ImageView) view.findViewById(R.id.key4);
		key4.setOnClickListener(this);
		createImage(key4, 4);
		
		key5= (ImageView) view.findViewById(R.id.key5);
		key5.setOnClickListener(this);
		createImage(key5, 5);
		
		key6= (ImageView) view.findViewById(R.id.key6);
		key6.setOnClickListener(this);
		createImage(key6, 6);
		
		key7= (ImageView) view.findViewById(R.id.key7);
		key7.setOnClickListener(this);
		createImage(key7, 7);
		
		key8= (ImageView) view.findViewById(R.id.key8);
		key8.setOnClickListener(this);
		createImage(key8, 8);
		
		key9= (ImageView) view.findViewById(R.id.key9);
		key9.setOnClickListener(this);
		createImage(key9, 9);
		
		key10= (ImageView) view.findViewById(R.id.key10);
		key10.setOnClickListener(this);
		createImage(key10, 10);
		
		key11= (ImageView) view.findViewById(R.id.key11);
		key11.setOnClickListener(this);
		createImage(key11, 11);
		
		key12= (ImageView) view.findViewById(R.id.key12);
		key12.setOnClickListener(this);
		createImage(key12, 12);
		CountDown cd =new CountDown(3000, 50);
		return view;
	}
	private void initKeys(){
		Random r= new Random();
		int index=r.nextInt(100);
		for (int i = 0; i < keys.length; i++) {
			if(index<30){
				keys[i]=0;
			}else{
				keys[i]=1;
			}
			
		}
	}
	private void createImage(ImageView image, int pos){
		if(keys[pos-1]==1){
			image.setVisibility(View.INVISIBLE);
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	
	private class CountDown extends CountDownTimer {

		public CountDown(long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
		}

		@Override
		public void onTick(long millisUntilFinished) {
			int progres = (int) millisUntilFinished;
			timeLeft = millisUntilFinished;
		}

		@Override
		public void onFinish() {
			backgroud.setVisibility(View.VISIBLE);
			
			if(keys[0]==0){
				key1.setVisibility(View.INVISIBLE);
			}
			if(keys[1]==0){
				key2.setVisibility(View.INVISIBLE);
			}
			if(keys[2]==0){
				key3.setVisibility(View.INVISIBLE);
			}
			if(keys[3]==0){
				key4.setVisibility(View.INVISIBLE);
			}
			if(keys[4]==0){
				key5.setVisibility(View.INVISIBLE);
			}
			if(keys[5]==0){
				key6.setVisibility(View.INVISIBLE);
			}
			if(keys[6]==0){
				key7.setVisibility(View.INVISIBLE);
			}			
			if(keys[7]==0){
				key8.setVisibility(View.INVISIBLE);
			}
			if(keys[8]==0){
				key9.setVisibility(View.INVISIBLE);
			}
			if(keys[9]==0){
				key10.setVisibility(View.INVISIBLE);
			}
			if(keys[10]==0){
				key11.setVisibility(View.INVISIBLE);
			}
			if(keys[11]==0){
				key12.setVisibility(View.INVISIBLE);
			}
			
		}



	}
}
