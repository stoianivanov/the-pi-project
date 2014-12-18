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
import android.widget.ListView;


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

	//Creating view;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);

		AddPlayer = (Button) findViewById(R.id.addPlayer);
		AddPlayer.setOnClickListener(this);

		nameEdit = (EditText) findViewById(R.id.PlayerName);

		//Take all players from DB
		ph = new PlayerHelper(this);
		PlayerList =  ph.getAll2Player();

		//Create adapter
		setListAdapter(new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_1, PlayerList));
	}

	//Add new player
	@Override
	public void onClick(View v) {


		if(v.getId() == AddPlayer.getId()){
			final String name = nameEdit.getText().toString();

			//Create new player
			final Player player = new Player();
			player.setName(name);
			//Add to DB
			PlayerList = ph.getAll2Player();
			boolean isUnique = true;
			
			for(int i = 0; i < PlayerList.size(); i++){
				if(player.getName().equals(PlayerList.get(i).getName())){
					isUnique = false;
					break;
				}
			}

			if(isUnique){
				ph.addPlayer(player);

				//Update current list
				PlayerList =  ph.getAll2Player();
				setListAdapter(new ArrayAdapter<Player>(this, android.R.layout.simple_list_item_1, PlayerList));

				notifyDataSetChanged();
			}



		}		
	}

	//Make selected player - current
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		Player current = (Player) this.getListAdapter().getItem(position);
		Intent i = new Intent();

		i.putExtra("currentPlayer", current.getId());
		finish();

		return;
	}

	//Catch creating new player
	@SuppressWarnings("unchecked")
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
