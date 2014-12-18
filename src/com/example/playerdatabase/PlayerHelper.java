package com.example.playerdatabase;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PlayerHelper extends SQLiteOpenHelper{

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "Player info";

	public static class Players{

		private static final String TABLE_NAME = "Player";
		private static final String COLUMN_ID = "_id";
		private static final String COLUMN_NAME = "Name";
		private static final String COLUMN_TOTAL_POINT_CURRENT = "CT_Point";
		private static final String COLUMN_LOGIC_POINT_CURRENT = "CL_Point";
		private static final String COLUMN_MEMORY_POINT_CURRENT = "CM_Point";
		private static final String COLUMN_SPEED_POINT_CURRENT = "CS_Point";
		private static final String COLUMN_TOTAL_POINT_BEST = "BT_BestPoint";
		private static final String COLUMN_LOGIC_POINT_BEST = "BL_BestPoint";
		private static final String COLUMN_MEMORY_POINT_BEST = "BM_BestPoint";
		private static final String COLUMN_SPEED_POINT_BEST = "BS_BestPoint";
		private static final String TEXT_TYPE = " TEXT NOT NULL";
		private static final String INT_TYPE = " INTEGER DEFAULT 0";
		private static final String COMMA_SEP = ", ";

		private static final String CREATE_DB = "CREATE TABLE " + Players.TABLE_NAME
				+ "( " + Players.COLUMN_ID + " integer primary key autoincrement, "
				+ Players.COLUMN_NAME + Players.TEXT_TYPE + Players.COMMA_SEP
				+ Players.COLUMN_TOTAL_POINT_CURRENT + Players.INT_TYPE + Players.COMMA_SEP
				+ Players.COLUMN_LOGIC_POINT_CURRENT +  Players.INT_TYPE + Players.COMMA_SEP 
				+ Players.COLUMN_MEMORY_POINT_CURRENT + Players.INT_TYPE + Players.COMMA_SEP
				+ Players.COLUMN_SPEED_POINT_CURRENT +  Players.INT_TYPE + Players.COMMA_SEP
				+ Players.COLUMN_TOTAL_POINT_BEST +  Players.INT_TYPE + Players.COMMA_SEP 
				+ Players.COLUMN_LOGIC_POINT_BEST +  Players.INT_TYPE + Players.COMMA_SEP
				+ Players.COLUMN_MEMORY_POINT_BEST +  Players.INT_TYPE + Players.COMMA_SEP
				+ Players.COLUMN_SPEED_POINT_BEST +  Players.INT_TYPE + ")";


		private static final String DELETE_STATEMENT = "DELETE IF EXISTS TABLE " + Players.TABLE_NAME;

		private static final String[] allColumn = {Players.COLUMN_ID, Players.COLUMN_NAME, 
			
			//Player's best point 
			Players.COLUMN_LOGIC_POINT_BEST,
			Players.COLUMN_MEMORY_POINT_BEST, Players.COLUMN_SPEED_POINT_BEST,
			
			//Player's current point
			Players.COLUMN_LOGIC_POINT_CURRENT,
			Players.COLUMN_MEMORY_POINT_CURRENT, Players.COLUMN_SPEED_POINT_CURRENT};

	}



	public PlayerHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("Database cr", Players.CREATE_DB);
		db.execSQL(Players.CREATE_DB);
		db.execSQL("insert into " + Players.TABLE_NAME + "(" + Players.COLUMN_ID + ","
                + Players.COLUMN_NAME + ") values(1,'Guest')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(Players.DELETE_STATEMENT);
		onCreate(db);
	}


	public void addPlayer(Player player){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();

		//Creating the player with 0 points in all category
		values.put(Players.COLUMN_NAME, player.getName());


		//Inserting a player in DB
		db.insert(Players.TABLE_NAME, null , values);


		db.close();

	}

	public void updateScore(int id, Player player){
		//Player update
		player.checkBest();
		
		//Database
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues cv = new ContentValues();
		
		//Set current points
		cv.put(Players.COLUMN_LOGIC_POINT_CURRENT, player.getPlayerLPointCurrent());
		cv.put(Players.COLUMN_MEMORY_POINT_CURRENT, player.getPlayerMPointCurrent());
		cv.put(Players.COLUMN_SPEED_POINT_CURRENT, player.getPlayerSPointCurrent());
		//Set best points
		cv.put(Players.COLUMN_LOGIC_POINT_BEST, player.getPlayerLPointBest());
		cv.put(Players.COLUMN_MEMORY_POINT_BEST, player.getPlayerMPointBest());
		cv.put(Players.COLUMN_SPEED_POINT_BEST, player.getPlayerSPointBest());

		//Update DB
		db.update(Players.TABLE_NAME, cv, Players.COLUMN_ID + "=?", new String[] {String.valueOf(id)});

		Log.i("Player from", player.toString());

		Player p = getPlayer(id);

		Log.i("After update from DB", p.toString() );

	}



	public Player getPlayer(int id){

		//reach DB
		final SQLiteDatabase db = this.getReadableDatabase();

		//Creating cursor with allColumnn string
		Cursor cursor = db.query(DATABASE_NAME, Players.allColumn, Players.COLUMN_ID + "=?",
				new String[] {String.valueOf(id)}, null, null, null);

		//Creating player and set 
		if (cursor != null && cursor.moveToFirst()){
			cursor.moveToFirst();
			Player pl = new Player();

			//			//set attributes to the player
			Log.i("setId", cursor.getString(0));
			pl.setId(cursor.getInt(0));
			Log.i("setName", cursor.getString(1));
			pl.setName(cursor.getString(1));

			//Set score to the player
			//Best score
			Log.i("setPlayerLPointBest", cursor.getString(2));
			pl.setPlayerLPointBest(cursor.getInt(2));
			pl.setPlayerMPointBest(cursor.getInt(3));
			pl.setPlayerSPointBest(cursor.getInt(4));

			//			Current score
			pl.setPlayerLPointCurrent(cursor.getInt(5));
			pl.setPlayerMPointCurrent(cursor.getInt(6));
			pl.setPlayerSPointCurrent(cursor.getInt(7));
			//			
			Log.i("Player from getPlayer", pl.toString());
			pl.checkBest();
			return pl;
		}
		return null;
	}

	private static Player cursorToPlayer(Cursor c){

		int idIndex = c.getColumnIndex(Players.COLUMN_ID);
		int nameIndex = c.getColumnIndex(Players.COLUMN_NAME);
		int tnPoint = c.getColumnIndex(Players.COLUMN_TOTAL_POINT_CURRENT);
		int lnPoint = c.getColumnIndex(Players.COLUMN_LOGIC_POINT_CURRENT);
		int mnPoint = c.getColumnIndex(Players.COLUMN_MEMORY_POINT_CURRENT);
		int snPoint = c.getColumnIndex(Players.COLUMN_SPEED_POINT_CURRENT);
		int toPoint = c.getColumnIndex(Players.COLUMN_TOTAL_POINT_BEST);
		int loPoint = c.getColumnIndex(Players.COLUMN_LOGIC_POINT_BEST);
		int moPoint = c.getColumnIndex(Players.COLUMN_MEMORY_POINT_BEST);
		int soPoint = c.getColumnIndex(Players.COLUMN_SPEED_POINT_BEST);

		int id = c.getInt(idIndex);
		String name = c.getString(nameIndex);
		//Best
		//		int totalPointBest = c.getInt(tnPoint);
		int logicPointBest = c.getInt(lnPoint);
		int memoryPointBest = c.getInt(mnPoint);
		int speedPointBest = c.getInt(snPoint);
		//Current
		//		int totalPointCurrent = c.getInt(toPoint);
		int logicPointCurrent = c.getInt(loPoint);
		int memoryPointCurrent = c.getInt(moPoint);
		int speedPointCurrent = c.getInt(soPoint);

		Player p = new Player();

		//		p.setId(id);
		p.setName(name);
		//Total point are calculated
		//		p.setPlayerTotalPoint(totalPoint);
		p.setPlayerLPointBest(logicPointBest);
		p.setPlayerSPointBest(speedPointBest);
		p.setPlayerMPointBest(memoryPointBest);

		p.setPlayerLPointCurrent(logicPointCurrent);
		p.setPlayerMPointCurrent(memoryPointCurrent);
		p.setPlayerSPointCurrent(speedPointCurrent);

		return p;

	}


	public  List<Player> getAll2Player() {
		
		//ArayList that will be returned
		List<Player> playerList = new ArrayList<Player>();

		//Reach DB
		SQLiteDatabase db = getReadableDatabase();
		
		// Select All Query
		Cursor cursor = db.query(Players.TABLE_NAME, Players.allColumn, 
				null, null, null, null, null);
		
		//Create players and put them to araylist
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();

			do {
				Player pl = new Player();

				//set attributes to the player
				Log.i("setId", cursor.getString(0));
				pl.setId(cursor.getInt(0));
				Log.i("setName", cursor.getString(1));
				pl.setName(cursor.getString(1));

				//Set score to the player
				//Best score
				Log.i("setPlayerLPointBest", cursor.getString(2));
				pl.setPlayerLPointBest(cursor.getInt(2));
				Log.i("setPlayerLPointBest", cursor.getString(3));
				pl.setPlayerMPointBest(cursor.getInt(3));
				Log.i("setPlayerLPointBest", cursor.getString(4));
				pl.setPlayerSPointBest(cursor.getInt(4));

				//Current score
				Log.i("setPlayerLPointCurrent", cursor.getString(5));
				pl.setPlayerLPointCurrent(cursor.getInt(5));
				Log.i("setPlayerMPointCurrent", cursor.getString(6));
				pl.setPlayerMPointCurrent(cursor.getInt(6));
				Log.i("setPlayerSPointCurrent", cursor.getString(7));
				pl.setPlayerSPointCurrent(cursor.getInt(7));
				pl.checkBest();
				playerList.add(pl);
				Log.i("Player", pl.toString());
			} while (cursor.moveToNext());
		}

		return playerList;		    		

	}

	public int getPlayerCount() {
		String countQuery = "SELECT  * FROM " + Players.TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		return cursor.getCount();
	}

}
