package com.example.thepiproject;

import java.util.List;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.playerdatabase.Player;
import com.example.playerdatabase.PlayerHelper;
import com.example.thepiproject.BackGroundMusic.LocalBinder;

public class PlayerActivity extends ListActivity implements OnClickListener{
	
	boolean mBound = false;
	BackGroundMusic music;

	private Button AddPlayer;
	private EditText nameEdit;
	private PlayerHelper ph;
	private List<com.example.playerdatabase.Player> PlayerList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		
		ph = new PlayerHelper(this);
		
		
//		Player update = ph.getPlayer(1);
//		
//		update.setPlayerLPointCurrent(50011);
//		update.setPlayerMPointCurrent(1000);
//		update.setPlayerSPointCurrent(2000);
//		
//		Log.i("Before update", update.toString());
//		ph.updateScore(1, update);
		
		
		
		
		AddPlayer = (Button) findViewById(R.id.addPlayer);
		AddPlayer.setOnClickListener(this);

		nameEdit = (EditText) findViewById(R.id.PlayerName);
//
		PlayerList =  ph.getAll2Player();
		setListAdapter(new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_1, PlayerList));
	
		//setListAdapter(adapter);
		
		//ArrayAdapter<Player> adapt = new ArrayAdapter<Player>(this, R.id.PlayerList, list);
		//setListAdapter(new ArrayAdapter<Player>(this,R.layout.activity_player,PlayerList));
//		ListView l = (ListView) findViewById(R.id.)
//		lv.setAdapter(adapter);

	}

	@Override
	public void onClick(View v) {
		

		if(v.getId() == AddPlayer.getId()){
			final String name = nameEdit.getText().toString();

			final Player player = new Player();
			player.setName(name);

					
			ph.addPlayer(player);
			
			notifyDataSetChanged();
		}		
	}
	
	private void notifyDataSetChanged() {
		((ArrayAdapter<Player>)getListAdapter()).notifyDataSetChanged();
	
	
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		doUnbindService();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		doBindService();
		if(MainActivity.musicPlaying){
			MainActivity.musicIntent = new Intent(this, BackGroundMusic.class);
			startService(MainActivity.musicIntent);
		}
	}
	
	private ServiceConnection sCon = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			music = null;	
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			LocalBinder binder = (LocalBinder) service;
			music = binder.getService();
		}
	};
	
	void doBindService() {
		bindService(new Intent(this, BackGroundMusic.class), sCon,
				Context.BIND_AUTO_CREATE);
		mBound = true;
	}

	void doUnbindService() {
		if (mBound) {
			unbindService(sCon);
			mBound = false;
		}
	}
}
