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
		private static final String COLUMN_TOTLA_POINT = "T_Point";
		private static final String COLUMN_LOGIC_POINT = "L_Point";
		private static final String COLUMN_MEMORY_POINT = "M_Point";
		private static final String COLUMN_SPEED_POINT = "s_Point";

	}

	private static final String CREATE_DB = "CREATE TABLE " + Players.TABLE_NAME
			+ "(" + Players.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
			+ Players.COLUMN_NAME + " TEXT, "
			+ Players.COLUMN_TOTLA_POINT + " INTEGER" 
			+ Players.COLUMN_LOGIC_POINT + " INTEGER" 
			+ Players.COLUMN_MEMORY_POINT + " INTEGER"
			+ Players.COLUMN_SPEED_POINT + " INTEGER" + ")";

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
		final SQLiteDatabase db = this.getWritableDatabase();

		final ContentValues values = new ContentValues();
		//Creating the player with 0 points in all category
		values.put(Players.COLUMN_NAME, player.getName());
		values.put(Players.COLUMN_LOGIC_POINT, 0);
		values.put(Players.COLUMN_MEMORY_POINT, 0);
		values.put(Players.COLUMN_SPEED_POINT,0);
		values.put(Players.COLUMN_TOTLA_POINT,0);

		db.insert(Players.TABLE_NAME, null, values);
				
		db.close();
	}

	//	TODO!!!	
	public void updateScore(SQLiteDatabase db ,int RowID, int newScore){

		ContentValues cv = new ContentValues();

		cv.put("Point", newScore);

		db.update(Players.TABLE_NAME, cv, "_id" + "=" + RowID, null);

	}

	public Player getPlayer(int id){

		final SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(Players.COLUMN_NAME, null, Players.COLUMN_ID + "=" + id	 ,
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		Player pl = new Player(Integer.parseInt(null, cursor.getInt(0)),
				cursor.getString(1), Integer.parseInt(null, cursor.getInt(2)),
				Integer.parseInt(null, cursor.getInt(3))
				,Integer.parseInt(null, cursor.getInt(4))
				,Integer.parseInt(null, cursor.getInt(5)));
		// return contact
		return pl;
	}


	public List<Player> getAllPlayer(){
		List<Player> all = new ArrayList<Player>();

		final SQLiteDatabase db = getReadableDatabase();

		Cursor c = db.query(Players.TABLE_NAME, null, null, null, null ,null, "name");

		c.moveToFirst();

		while(!c.isAfterLast()){
			final Player person = cursorToPlayer(c);
			all.add(person);
			c.moveToNext(); 
		}

		c.close();
		db.close();

		return all;
	}

	private static Player cursorToPlayer(Cursor c){
		int idIndex = c.getColumnIndex(Players.COLUMN_ID);
		int nameIndex = c.getColumnIndex(Players.COLUMN_NAME);
		int tPoint = c.getColumnIndex(Players.COLUMN_TOTLA_POINT);
		int lPoint = c.getColumnIndex(Players.COLUMN_LOGIC_POINT);
		int mPoint = c.getColumnIndex(Players.COLUMN_MEMORY_POINT);
		int sPoint = c.getColumnIndex(Players.COLUMN_SPEED_POINT);

		int id = c.getInt(idIndex);
		String name = c.getString(nameIndex);
		int totalPoint = c.getInt(tPoint);
		int logicPoint = c.getInt(lPoint);
		int memoryPoint = c.getInt(mPoint);
		int speedPoint = c.getInt(sPoint);

		Player p = new Player();

		p.setId(id);
		p.setName(name);
		p.setPlayerTotalPoint(totalPoint);
		p.setPlayerLPoint(logicPoint);
		p.setPlayerMPoint(memoryPoint);
		p.setPlayerSPoint(speedPoint);

		return p;

	}


	public  List<Player> getAll2Player() {
		List<Player> playerList = new ArrayList<Player>();
		// Select All Query
		String selectQuery = "SELECT  * FROM " + Players.TABLE_NAME;

		SQLiteDatabase db = getReadableDatabase();
		try{
			Cursor cursor = db.rawQuery(selectQuery, null);

			try {
				if (cursor.getCount() != 0) {
					cursor.moveToFirst();
					
					do {
						Player pl = new Player();
						pl.setId(Integer.parseInt(cursor.getString(0)));
						pl.setName(cursor.getString(1));
						pl.setPlayerTotalPoint(Integer.parseInt(cursor.getString(2)));
						// Adding player to list
						playerList.add(pl);
					} while (cursor.moveToNext());
				}
			} finally {
				try { cursor.close(); } catch (Exception ignore) {}
			}
		}finally{
			try {db.close(); } catch (Exception ignore){}
		}


		return playerList;		    			

	}

	public int updatePlayer(Player player) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(Players.COLUMN_NAME, player.getName());
		values.put(Players.COLUMN_TOTLA_POINT, player.getPlayerTotalPoint());
		values.put(Players.COLUMN_MEMORY_POINT, player.getPlayerMPoint());
		values.put(Players.COLUMN_LOGIC_POINT, player.getPlayerLPoint());
		values.put(Players.COLUMN_SPEED_POINT, player.getPlayerSPoint());

		// updating row
		return db.update(Players.TABLE_NAME, values, Players.COLUMN_ID + " = ?",
				new String[] { String.valueOf(player.getId()) });
	}

	public int getPlayerCount() {
		String countQuery = "SELECT  * FROM " + Players.TABLE_NAME;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		return cursor.getCount();
	}

	public void setDefaultLabel(SQLiteDatabase db) {
		// create default label
		ContentValues values = new ContentValues();
		values.put(Players.COLUMN_ID, "Default");
		db.insert(Players.TABLE_NAME, null, values);
	}

}
