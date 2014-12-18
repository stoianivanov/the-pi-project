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
	
	public int getPlayerTotalPointBest() {
		return PlayerTPointBest;
	}
	
	public void setPlayerTotalPointBest(int PlayerTotalPointBest) {
		this.PlayerTPointBest = PlayerTotalPointBest;
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
		return PlayerTPointCurrent;
	}
	
	public void setPlayerTotalPointCurrent(int PlayerTPointCurrent) {
		this.PlayerTPointCurrent = PlayerTPointCurrent;
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
	
	public void checkBest(){
		if(PlayerLPointCurrent > PlayerLPointBest){
			PlayerLPointBest = PlayerLPointCurrent;
		}
		
		if(PlayerMPointCurrent > PlayerMPointBest){
			PlayerMPointBest = PlayerMPointCurrent;
		}
		
		if(PlayerSPointCurrent > PlayerSPointBest){
			PlayerSPointBest = PlayerSPointCurrent;
		}
		
		if(PlayerTPointBest > PlayerTPointBest){
			PlayerTPointBest = PlayerTPointCurrent;
		}
	}

	@Override
	public String toString() {
		return Name + "\n" +
				"Total best point: " + PlayerTPointBest
				+ "\nLogic point: " + PlayerLPointBest + ", Memory point: "
				+ PlayerMPointBest + ", Speed point: " + PlayerSPointBest;
//				+ "\n Total current point:" + PlayerTPointCurrent 
//				+ "\n Current logic point: " + PlayerLPointCurrent + ", Current memory point: "
//				+ PlayerMPointCurrent + ", Current speed point: " + PlayerSPointCurrent;
	}
	
}
