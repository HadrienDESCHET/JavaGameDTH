package miniFightingGame;

public class Player extends Character{
	public Player(int classSelected){
		this.assignClass(classSelected); //Assign the selected class to the player
		this.setCharacterName("Player"); //Assign the name "Player" (only used for displaying things in console)
	}
}
