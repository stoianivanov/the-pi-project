package com.example.playerdatabase;

public class Player {
	
	

	private int id;
	private String Name;
	private int PlayerTPointBest;
	private int PlayerLPointBest;
	private int PlayerMPointBest;
	private int PlayerSPointBest;
	
	private int PlayerTPointCurrent;
	private int PlayerLPointCurrent;
	private int PlayerMPointCurrent;
	private int PlayerSPointCurrent;
	
	
	public Player(){
		this.Name="Null name";

	}

//	public Player(int parseInt, String string, int parseInt2, int parseInt3, int parseInt4, int parseInt5) {
//		this.id = parseInt;
//		this.Name = string;
//		
//		
//		this.PlayerTPoint = parseInt2;
//		this.PlayerLPoint = parseInt3;
//		this.PlayerSPoint = parseInt4;
//		this.PlayerMPoint = parseInt5;
//	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName (String name) {
		Name = name;
	}
	public int getPlayerTotalPoint() {
		int tPoint = PlayerLPointBest + PlayerMPointBest + PlayerSPointBest;
		return tPoint;
	}
	public void setPlayerTotalPoint(int playerTotalPoint) {
		PlayerTPointBest = playerTotalPoint;
	}

	public int getPlayerLPointBest() {
		return PlayerLPointBest;
	}

	public void setPlayerLPointBest(int playerLPointBest) {
		PlayerLPointBest = playerLPointBest;
	}

	public int getPlayerMPointBest() {
		return PlayerMPointBest;
	}

	public void setPlayerMPointBest (int playerMPointBest) {
		PlayerMPointBest = playerMPointBest;
	}

	public int getPlayerSPointBest() {
		return PlayerSPointBest;
	}

	public void setPlayerSPointBest (int playerSPointBest) {
		PlayerSPointBest = playerSPointBest;
	}

	public int getPlayerTotalPointCurrent() {
		int tPointOld = PlayerLPointCurrent + PlayerMPointCurrent + PlayerSPointCurrent;
		return tPointOld;
	}

	public int getPlayerLPointCurrent() {
		return PlayerLPointCurrent;
	}

	public void setPlayerLPointCurrent (int playerLPointCurrent) {
		PlayerLPointCurrent = playerLPointCurrent;
	}

	public int getPlayerMPointCurrent() {
		return PlayerMPointCurrent;
	}

	public void setPlayerMPointCurrent (int playerMPointCurrent) {
		PlayerMPointCurrent = playerMPointCurrent;
	}

	public int getPlayerSPointCurrent() {
		return PlayerSPointCurrent;
	}

	public void setPlayerSPointCurrent (int playerSPointCurrent) {
		PlayerSPointCurrent = playerSPointCurrent;
	}

	@Override
	public String toString() {
		return Name + "\n" +
				"Total point: " + PlayerTPointBest
				+ ", Logic point: " + PlayerLPointBest + ", Memory point: "
				+ PlayerMPointBest + ", Speed point: " + PlayerSPointBest;
	}
	
}
