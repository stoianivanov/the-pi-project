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

	}

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

	public PlayerHelper(Context context){
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i("Databse", "Creating");
		db.execSQL(CREATE_DB);
		setDefaultLabel(db);
		Log.i("Database", "Created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL(DELETE_STATEMENT);
		onCreate(db);
	}


	public void addPlayer(Player player){
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		//Creating the player with 0 points in all category
//		values.put(Players.COLUMN_ID, id);
		values.put(Players.COLUMN_NAME, player.getName());
//		values.put(Players.COLUMN_LOGIC_POINT_N, 0);
//		values.put(Players.COLUMN_MEMORY_POINT_N, 0);
//		values.put(Players.COLUMN_SPEED_POINT_N,0);
//		values.put(Players.COLUMN_TOTAL_POINT_N,0);
//		values.put(Players.COLUMN_LOGIC_POINT_O, 0);
//		values.put(Players.COLUMN_MEMORY_POINT_O, 0);
//		values.put(Players.COLUMN_SPEED_POINT_O,0);
//		values.put(Players.COLUMN_TOTAL_POINT_O,0);

		
		db.insert(Players.TABLE_NAME, null , values);


		db.close();

	}

	//	TODO!!!	
	public void updateScore(SQLiteDatabase db ,int RowID, int newScore){

		ContentValues cv = new ContentValues();

		cv.put("Point", newScore);

		db.update(Players.TABLE_NAME, cv, "_id" + "=" + RowID, null);

	}

		public Player getPlayer(int id){
			
			//reach DB
			final SQLiteDatabase db = this.getReadableDatabase();
			
			
			final String[] allColumn = {Players.COLUMN_ID, Players.COLUMN_NAME, 
					//Player's best point 
					Players.COLUMN_LOGIC_POINT_BEST,
					Players.COLUMN_MEMORY_POINT_BEST, Players.COLUMN_SPEED_POINT_BEST,
					//Player's current point
					Players.COLUMN_LOGIC_POINT_CURRENT,
					Players.COLUMN_MEMORY_POINT_CURRENT, Players.COLUMN_SPEED_POINT_CURRENT};
		
			//Creating cursor with allColumnn string
			Cursor cursor = db.query(DATABASE_NAME, allColumn, Players.COLUMN_ID + "=?",
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
			return pl;
			}
			return null;
		}


//	public List<Player> getAllPlayer(){
//		List<Player> all = new ArrayList<Player>();
//
//		final SQLiteDatabase db = getReadableDatabase();
//
//		//		Cursor b = db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy)
//		Cursor c = db.query(Players.TABLE_NAME, new String[] {}, Players.COLUMN_ID + "=?", null, null ,null, "name");
//
//		c.moveToFirst();
//
//		while(!c.isAfterLast()){
//			final Player player = cursorToPlayer(c);
//			all.add(player);
//			c.moveToNext(); 
//		}
//
//		c.close();
//		db.close();
//
//		return all;
//	}

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
		List<Player> playerList = new ArrayList<Player>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + Players.TABLE_NAME;

		SQLiteDatabase db = getReadableDatabase();

		Cursor cursor = db.rawQuery(selectQuery, null);

//		Log.i("cursor", Integer.toString(cursor.getCount()));
		if (cursor.getCount() != 0) {
			cursor.moveToFirst();

			do {
				Player pl = new Player();
				Log.i("Players IDDD" , cursor.getString(0).toString());
				//pl.setId(Integer.parseInt(cursor.getString(0)));
				pl.setName(cursor.getString(1));
				pl.setPlayerTotalPoint(Integer.parseInt(cursor.getString(2)));
				//// Adding player to list
				playerList.add(pl);
			} while (cursor.moveToNext());
		}



		return playerList;		    			

	}

	//	public int updatePlayer(Player player) {
	//		SQLiteDatabase db = this.getWritableDatabase();
	//
	//		ContentValues values = new ContentValues();
	//		values.put(Players.COLUMN_NAME, player.getName());
	//		values.put(Players.COLUMN_TOTLA_POINT, player.getPlayerTotalPoint());
	//		values.put(Players.COLUMN_MEMORY_POINT, player.getPlayerMPoint());
	//		values.put(Players.COLUMN_LOGIC_POINT, player.getPlayerLPoint());
	//		values.put(Players.COLUMN_SPEED_POINT, player.getPlayerSPoint());
	//
	//		// updating row
	//		return db.update(Players.TABLE_NAME, values, Players.COLUMN_ID + " = ?",
	//				new String[] { String.valueOf(player.getId()) });
	//	}

	public int getPlayerCount() {
		String countQuery = "SELECT  * FROM " + Players.TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		return cursor.getCount();
	}

	public void setDefaultLabel(SQLiteDatabase db) {
		// create default label
//		ContentValues values = new ContentValues();
//		values.put(Players.COLUMN_ID, "Default");
//		db.insert(Players.TABLE_NAME, null, values);
	}

}
