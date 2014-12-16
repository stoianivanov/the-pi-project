package com.example.thepiproject.memory;

import java.util.ArrayList;
import java.util.Random;

import com.example.logic.OnAnswerSelectedListener;
import com.example.thepiproject.R;
import com.example.thepiproject.R.layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MemoryGame2 extends Fragment implements OnClickListener{

	private OnAnswerSelectedListener listener;
	private ImageButton button1;
	private ImageButton button2;
	private ImageButton button3;
	private ImageButton button4;
	private ImageButton goButton;
	private int  closeimage;
	private ImageView image1;
	private ImageView image2;
	private ImageView image3;
	private ImageView image4;
	private ArrayList<Integer> list;
	private boolean buttonGoClick=false;
	private int[] arr={1,1,1,1};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view= inflater.inflate(R.layout.activity_memory_game2, null);
		list= new ArrayList<Integer>();
		initList();
		listener= (OnAnswerSelectedListener) getActivity();
		image1=(ImageView) view.findViewById(R.id.memoryImage1Game2);
		
		image2=(ImageView) view.findViewById(R.id.memoryImage2Game2);
		
		image3=(ImageView) view.findViewById(R.id.memoryImage3Game2);
		
		image4=(ImageView) view.findViewById(R.id.memoryImage4Game2);
		
		getQuoestion();
		
		button1= (ImageButton) view.findViewById(R.id.memoryGame2Button1);
		button1.setOnClickListener(this);
		
		button2= (ImageButton) view.findViewById(R.id.memoryGame2Button2);
		button2.setOnClickListener(this);
		
		button3= (ImageButton) view.findViewById(R.id.memoryGame2Button3);
		button3.setOnClickListener(this);
		
		button4= (ImageButton) view.findViewById(R.id.memoryGame2Button4);
		button4.setOnClickListener(this);
		
		goButton= (ImageButton) view.findViewById(R.id.imageButtonGoGame2);
		goButton.setOnClickListener(this);
		return view;
	}
	private void getQuoestion(){
		Random r=new Random();
		int index=r.nextInt(list.size());
		image1.setImageResource(list.get(index));
		list.remove(index);
		index=r.nextInt(list.size());
		image2.setImageResource(list.get(index));
		list.remove(index);
	    index=r.nextInt(list.size());
		image4.setImageResource(list.get(index));
		list.remove(index);
		index=r.nextInt(list.size());
		image3.setImageResource(list.get(index));
		closeimage=list.get(index);
		list.remove(index);	
	}
	private void initButton(){
		Random r = new Random();
		int index=r.nextInt(200);
		
		if(index<50){
			button1.setImageResource(closeimage);
			arr[0]=0;
		}else if(index <100){
			button2.setImageResource(closeimage);
			arr[1]=0;
		}else if(index<150){
			button3.setImageResource(closeimage);
			arr[2]=0;
		}else if( index <200){
			button4.setImageResource(closeimage);
			arr[3]=0;
		}
		if(arr[0]==1){
			index=r.nextInt(list.size());
			button1.setImageResource(list.get(index));
			list.remove(index);
		}
		if(arr[1]==1){
			index=r.nextInt(list.size());
			button2.setImageResource(list.get(index));
			list.remove(index);
		}
		if(arr[2]==1){
			index=r.nextInt(list.size());
			button3.setImageResource(list.get(index));
			list.remove(index);
		}
		if(arr[3]==1){
			index=r.nextInt(list.size());
			button4.setImageResource(list.get(index));
			list.remove(index);
		}
	}
	private void initList(){
		list.add(R.drawable.memory_game_2_1);
		list.add(R.drawable.memory_game_2_2);
		list.add(R.drawable.memory_game_2_3);
		list.add(R.drawable.memory_game_2_4);
		list.add(R.drawable.memory_game_2_5);
		list.add(R.drawable.memory_game_2_6);
		list.add(R.drawable.memory_game_2_7);
		list.add(R.drawable.memory_game_2_8);
		list.add(R.drawable.memory_game_2_9);
		list.add(R.drawable.memory_game_2_10);
		
	}
	@Override
	public void onClick(View v) {
		int id=v.getId();
		int index=-1;
		if(id==goButton.getId()){
			buttonGoClick=true;
			initButton();
		}
		if(buttonGoClick==true){
			
			for (int i = 0; i < arr.length; i++) {
				if(arr[i]==0){
					index=i;
				}
			}
			if(id==button1.getId()){
				if(index==0){
					listener.correctAnswer();
				}else{
					listener.wrongAnswer();
				}
			}
			if(id==button2.getId()){
				if(index==0){
					listener.correctAnswer();
				}else{
					listener.wrongAnswer();
				}
			}
			if(id==button3.getId()){
				if(index==0){
					listener.correctAnswer();
				}else{
					listener.wrongAnswer();
				}
			}
			if(id==button4.getId()){
				if(index==0){
					listener.correctAnswer();
				}else{
					listener.wrongAnswer();
				}
			}
		}
		
	}
}
