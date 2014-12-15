package com.example.playerdatabase;

public class Player {
	
	

	private int id;
	private String Name;
	private int PlayerTPoint;
	private int PlayerLPoint;
	private int PlayerMPoint;
	private int PlayerSPoint;
	
	
	public Player(){
		this.Name="Test";

	}

	public Player(int parseInt, String string, int parseInt2, int parseInt3, int parseInt4, int parseInt5) {
		this.id = parseInt;
		this.Name = string;
		
		
		this.PlayerTPoint = parseInt2;
		this.PlayerLPoint = parseInt3;
		this.PlayerSPoint = parseInt4;
		this.PlayerMPoint = parseInt5;
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
	public void setName(String name) {
		Name = name;
	}
	public int getPlayerTotalPoint() {
		return PlayerTPoint;
	}
	public void setPlayerTotalPoint(int playerTotalPoint) {
		PlayerTPoint = playerTotalPoint;
	}

	public int getPlayerLPoint() {
		return PlayerLPoint;
	}

	public void setPlayerLPoint(int playerLPoint) {
		PlayerLPoint = playerLPoint;
	}

	public int getPlayerMPoint() {
		return PlayerMPoint;
	}

	public void setPlayerMPoint(int playerMPoint) {
		PlayerMPoint = playerMPoint;
	}

	public int getPlayerSPoint() {
		return PlayerSPoint;
	}

	public void setPlayerSPoint(int playerSPoint) {
		PlayerSPoint = playerSPoint;
	}
	
	@Override
	public String toString() {
		return "Player" + Name + "/n" +
				"Total point=" + PlayerTPoint
				+ ", Logic point=" + PlayerLPoint + ", Memory point"
				+ PlayerMPoint + ", Speed point=" + PlayerSPoint;
	}
	
}
