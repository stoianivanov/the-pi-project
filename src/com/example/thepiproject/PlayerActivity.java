package com.example.thepiproject;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import com.example.playerdatabase.Player;
import com.example.playerdatabase.PlayerHelper;

public class PlayerActivity extends ListActivity implements OnClickListener{
	

	private Button AddPlayer;
	private EditText nameEdit;
	private PlayerHelper ph;
	private List<com.example.playerdatabase.Player> PlayerList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player);
		
		ph = new PlayerHelper(this);
		PlayerList =  ph.getAll2Player();
		
		
		AddPlayer = (Button) findViewById(R.id.addPlayer);
		AddPlayer.setOnClickListener(this);

		nameEdit = (EditText) findViewById(R.id.PlayerName);
		
		ArrayAdapter<Player> adapter = new ArrayAdapter<Player>(this, R.layout.activity_player, PlayerList);
	
		setListAdapter(adapter);
		
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
			player.setPlayerLPoint(0);
			player.setPlayerMPoint(0);
			player.setPlayerSPoint(0);
			player.setPlayerTotalPoint(0);
					
			ph.addPlayer(player);
			
			notifyDataSetChanged();
		}		
	}
	
	private void notifyDataSetChanged() {
		((ArrayAdapter<Player>)getListAdapter()).notifyDataSetChanged();
	}
}
