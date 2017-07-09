package miniFightingGame;

public class Enemy extends Character{
	public Enemy(int classSelected){
		this.assignClass(classSelected); //Assign the random class defined for the enemy
		this.setCharacterName("Enemy"); //Assign the name "Enemy" (only used for displaying thing in the console)
	}
}
