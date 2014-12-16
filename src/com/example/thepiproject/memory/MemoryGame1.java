package com.example.thepiproject.memory;

import java.util.ArrayList;
import java.util.Random;

import com.example.thepiproject.R;
import com.example.thepiproject.R.layout;

import android.app.Activity;
import android.media.Image;
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

public class MemoryGame1 extends Fragment implements OnClickListener {

	private ArrayList<Integer> imagesList;
	private ImageView image1;
	private ImageView image2;
	private ImageView image3;
	private ImageView image4;
	private ImageView image5;
	private ImageView image6;
	private ImageView image7;
	private ImageView image8;
	private ImageView currentImage;
	private ImageView currentShadowImage;
	private ImageView shadow1;
	private ImageView shadow2;
	private ImageView shadow3;
	private ImageView shadow4;
	private ImageView shadow5;
	private ImageView shadow6;
	private ImageView shadow7;
	private ImageView shadow8;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_memory_game1,null);
		imagesList = new ArrayList<Integer>();
		initImageList();
		
		image1= (ImageView) view.findViewById(R.id.MemoryImage1);
		image1.setOnClickListener(this);
		createImage(image1);
		
		shadow1 = (ImageView) view.findViewById(R.id.MemoryImage1Shadow);
		shadow1.setOnClickListener(this);
		
		image2= (ImageView) view.findViewById(R.id.MemoryImage2);
		image2.setOnClickListener(this);
		createImage(image2);
		
		shadow2 = (ImageView) view.findViewById(R.id.MemoryImage2Shadow);
		shadow2.setOnClickListener(this);
		
		image3= (ImageView) view.findViewById(R.id.MemoryImage3);
		image3.setOnClickListener(this);
		createImage(image3);
		
		shadow3 = (ImageView) view.findViewById(R.id.MemoryImage3Shadow);
		shadow3.setOnClickListener(this);
		
		image4= (ImageView) view.findViewById(R.id.MemoryImage4);
		image4.setOnClickListener(this);
		createImage(image4);
		
		shadow4 = (ImageView) view.findViewById(R.id.MemoryImage4Shadow);
		shadow4.setOnClickListener(this);
		
		image5= (ImageView) view.findViewById(R.id.MemoryImage5);
		image5.setOnClickListener(this);
		createImage(image5);
		
		shadow5 = (ImageView) view.findViewById(R.id.MemoryImage5Shadow);
		shadow5.setOnClickListener(this);
		
		image6= (ImageView) view.findViewById(R.id.MemoryImage6);
		image6.setOnClickListener(this);
		createImage(image6);
		
		shadow6 = (ImageView) view.findViewById(R.id.MemoryImage6Shadow);
		shadow6.setOnClickListener(this);
		
		image7= (ImageView) view.findViewById(R.id.MemoryImage7);
		image7.setOnClickListener(this);
		createImage(image7);
		
		shadow7 = (ImageView) view.findViewById(R.id.MemoryImage7Shadow);
		shadow7.setOnClickListener(this);
		
		image8= (ImageView) view.findViewById(R.id.MemoryImage8);
		image8.setOnClickListener(this);
		createImage(image8);
		
		shadow8 = (ImageView) view.findViewById(R.id.MemoryImage8Shadow);
		shadow8.setOnClickListener(this);
		return view;
	}
	
	private void  initImageList(){
		for (int i = 0; i < 2; i++) {
			imagesList.add(R.drawable.androidimage);
		}
		for (int i = 0; i < 2; i++) {
			imagesList.add(R.drawable.cimage);
		}
		for (int i = 0; i < 2; i++) {
			imagesList.add(R.drawable.iosiamge);
		}
		for (int i = 0; i < 2; i++) {
			imagesList.add(R.drawable.javaimage);
		}
	}
	private void createImage( ImageView image){
		Random r = new Random();
		int index= r.nextInt(imagesList.size());
		image.setImageResource(imagesList.get(index));
		imagesList.remove(index);
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.MemoryImage1){
			Log.i("ONCLICK", "memory iamge 1");
		clickOnImage(image1, shadow1);
		}
		if(v.getId()==R.id.MemoryImage1Shadow){
			
			Log.i("ONCLICK", "memory iamge 1 shadow");
			image1.setVisibility(View.VISIBLE);
			shadow1.setVisibility(View.GONE);

		}
		if(v.getId()==R.id.MemoryImage2){
			Log.i("ONCLICK", "memory iamge 2");
		clickOnImage(image2, shadow2);
		}
		if(v.getId()==R.id.MemoryImage2Shadow){
			
			Log.i("ONCLICK", "memory iamge2 shadow");
			image2.setVisibility(View.VISIBLE);
			shadow2.setVisibility(View.GONE);

		}
		
		if(v.getId()==R.id.MemoryImage3){
			Log.i("ONCLICK", "memory iamge 3");
		clickOnImage(image3, shadow3);
		}
		if(v.getId()==R.id.MemoryImage3Shadow){
			
			Log.i("ONCLICK", "memory iamge 3 shadow");
			image3.setVisibility(View.VISIBLE);
			shadow3.setVisibility(View.GONE);

		}
		
		if(v.getId()==R.id.MemoryImage4){
			Log.i("ONCLICK", "memory iamge 4");
		clickOnImage(image4, shadow4);
		}
		if(v.getId()==R.id.MemoryImage4Shadow){
			
			Log.i("ONCLICK", "memory iamge4 shadow");
			image4.setVisibility(View.VISIBLE);
			shadow4.setVisibility(View.GONE);

		}
		
		if(v.getId()==R.id.MemoryImage5){
			Log.i("ONCLICK", "memory iamge 5");
		clickOnImage(image5, shadow5);
		}
		if(v.getId()==R.id.MemoryImage1Shadow){
			
			Log.i("ONCLICK", "memory iamge 5 shadow");
			image5.setVisibility(View.VISIBLE);
			shadow5.setVisibility(View.GONE);

		}
		
		if(v.getId()==R.id.MemoryImage6){
			Log.i("ONCLICK", "memory iamge 6");
		clickOnImage(image6, shadow6);
		}
		if(v.getId()==R.id.MemoryImage6Shadow){
			
			Log.i("ONCLICK", "memory iamge6 shadow");
			image6.setVisibility(View.VISIBLE);
			shadow6.setVisibility(View.GONE);

		}
		
		if(v.getId()==R.id.MemoryImage7){
			Log.i("ONCLICK", "memory iamge 7");
		clickOnImage(image7, shadow7);
		}
		if(v.getId()==R.id.MemoryImage7Shadow){
			
			Log.i("ONCLICK", "memory iamge 7 shadow");
			image7.setVisibility(View.VISIBLE);
			shadow7.setVisibility(View.GONE);

		}
		
		if(v.getId()==R.id.MemoryImage8){
			Log.i("ONCLICK", "memory iamge 8");
		clickOnImage(image8, shadow8);
		}
		if(v.getId()==R.id.MemoryImage8Shadow){
			
			Log.i("ONCLICK", "memory iamge 8 shadow");
			image8.setVisibility(View.VISIBLE);
			shadow8.setVisibility(View.GONE);

		}
			
	}
		
		
	
	private void clickOnImage(ImageView image1, ImageView shadow1){
		if(currentImage==null){
			currentImage=image1;
			currentShadowImage = shadow1;
		}else{
			if(currentImage.getId()==image1.getId()){
				currentImage.setVisibility(View.INVISIBLE);
				image1.setVisibility(View.INVISIBLE);
			}else{
				currentImage.setVisibility(View.GONE);
				currentShadowImage.setVisibility(View.VISIBLE);
				currentImage=currentShadowImage=null;
			}	
	}
	}
}
