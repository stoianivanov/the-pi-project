package com.example.thepiproject.speed;

import java.util.Random;

import com.example.thepiproject.R;
import com.example.thepiproject.R.layout;

import android.R.drawable;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class GameSpeed10 extends Fragment implements OnClickListener {

	private ImageView image1;
	private ImageView image2;
	private ImageView image3;
	private ImageView image4;
	private ImageView image5;
	private ImageView image6;
	private ImageView image7;
	private ImageView image8;
	private ImageView image9;
	private ImageView image10;
	private ImageView image11;
	private ImageView image12;
	private int[] images= new int[12];
	private int[] imageID={R.id.ImageViewSpeed01,R.id.ImageViewSpeed02,
			R.id.ImageViewSpeed03,R.id.ImageViewSpeed04,R.id.ImageViewSpeed05,
			R.id.ImageViewSpeed06,R.id.ImageViewSpeed07,R.id.ImageViewSpeed08,
			R.id.ImageViewSpeed09,R.id.ImageViewSpeed10,R.id.ImageViewSpeed11,
			R.id.ImageViewSpeed12};
	private int counter=0;
	private int currentClick=0;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_game_speed10, null);
		
		initImages();
		initCounter();
		image1= (ImageView) view.findViewById(R.id.ImageViewSpeed01);
		image1.setOnClickListener(this);
		changeImage(image1, 0);
		
		image2= (ImageView) view.findViewById(R.id.ImageViewSpeed02);
		image2.setOnClickListener(this);
		changeImage(image2, 1);
		
		image3= (ImageView) view.findViewById(R.id.ImageViewSpeed03);
		image3.setOnClickListener(this);
		changeImage(image3, 2);
		
		image4= (ImageView) view.findViewById(R.id.ImageViewSpeed04);
		image4.setOnClickListener(this);
		changeImage(image4, 3);
		
		image5= (ImageView) view.findViewById(R.id.ImageViewSpeed05);
		image5.setOnClickListener(this);
		changeImage(image5, 4);
		
		image6= (ImageView) view.findViewById(R.id.ImageViewSpeed06);
		image6.setOnClickListener(this);
		changeImage(image6, 5);
		
		image7= (ImageView) view.findViewById(R.id.ImageViewSpeed07);
		image7.setOnClickListener(this);
		changeImage(image7, 6);
		
		image8= (ImageView) view.findViewById(R.id.ImageViewSpeed08);
		image8.setOnClickListener(this);
		changeImage(image8, 7);
		
		image9= (ImageView) view.findViewById(R.id.ImageViewSpeed09);
		image9.setOnClickListener(this);
		changeImage(image9, 8);
		
		image10= (ImageView) view.findViewById(R.id.ImageViewSpeed10);
		image10.setOnClickListener(this);
		changeImage(image10, 9);
		
		image11= (ImageView) view.findViewById(R.id.ImageViewSpeed11);
		image11.setOnClickListener(this);
		changeImage(image11, 10);
		
		image12= (ImageView) view.findViewById(R.id.ImageViewSpeed12);
		image12.setOnClickListener(this);
		changeImage(image12, 11);
		return view;
	}
	private void changeImage(ImageView image, int pos){

		if(images[pos]==1){
			image.setImageResource(R.drawable.corect_answer);
		}
	}
	private void initImages(){
		for (int i = 0; i < images.length; i++) {
			Random r= new Random();
			int num = r.nextInt(100);
			Log.i("Random", num+"");
			if(num<50){
				images[i]= 0;
			}else{
				images[i]=1;
			}
			
		}

		
	}
	@Override
	public void onClick(View v) {
		GetPoints point = (GetPoints) getActivity();
		
		for (int i = 0; i < imageID.length; i++) {
			if(v.getId()== imageID[i] ){
				if(images[i]==1){
					++currentClick;
					Log.i("CurrentClick", ""+currentClick);
					if(currentClick==counter){
						Log.i("Enter in if", "get 100 point");
						point.getPoint(100);
					}
					
				}else{
					point.getPoint(0);
				}
			}
		}


	}
	private void  initCounter(){
		for (int i = 0; i < images.length; i++) {
			if( images[i]==1){
				++counter;
			}
		}
		Log.i("Counter size", ""+counter);
	}
}
