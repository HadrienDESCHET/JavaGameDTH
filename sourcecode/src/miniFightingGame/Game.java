package miniFightingGame;

import java.util.Random;
import java.util.Scanner;

public class Game {
	private int classSelected=0, enemyClass, worldSelected=0, round=0, moveSelected=0, enemyMove; //Classes : 1=Damager, 2=Healer, 3=Tank
	Scanner sc = new Scanner(System.in); //Scanner
	Random r = new Random(); //Random
	
	
	
	public void play(){
		while(classSelected <= 0 || classSelected > 3){ //Continues while the class choice isn't 1, 2 or 3
			System.out.println("Select your class :\n(the class with the highest speed moves first)\n1. Damager (3 HP / 1 MP / 2 AS / 1 speed)\n2. Healer (4 HP / 1 MP / 1 AS / 1 speed)\n3. Tank (5 HP / 2 MP / 1 AS / 2 speed)\n");
			this.setClassSelected(sc.nextInt()); //Get the class selected by the user
		}
	
		Player player1 = new Player(this.getClassSelected()); //Instantiate the player with the class selected
		System.out.println("\nCongratulations ! You're now a " +player1.getCharacterClass());
		
		this.setEnemyClass(1 + r.nextInt(3 - 1)); //Set a random class to the enemy
		Enemy enemy1 = new Enemy(this.getEnemyClass()); //Instantiate the enemy with the random class
		
		System.out.println("\nYour enemy is now a " +enemy1.getCharacterClass());
		 
		while(worldSelected <= 0 || worldSelected > 3){ //Continues while the world choice isn't 1, 2 or 3
			System.out.println("\nSelect the world :\n1. World 1\n2. World 2\n3. World 3\n");
			this.setWorldSelected(sc.nextInt());
		}
		
		Window win1 = new Window(this.getWorldSelected(), this.getClassSelected(), this.getEnemyClass()); //Instantiate the world selected
		
		if(player1.getSpeed()>enemy1.getSpeed()){ //Check the speed of the player and the enemy to see who will attack first
			System.out.println("\nYou are faster than your opponent, you'll move first !");
		}
		else if(player1.getSpeed()<enemy1.getSpeed()){
			System.out.println("\nYou are slower than your opponent, he will move first");
		}
		else if(player1.getSpeed()==enemy1.getSpeed()){
			System.out.println("\nYou and your opponent have the same speed but as\nI'm such a kind programmer... you'll move first");
		}

		this.gameLoop(player1, enemy1);
	}
	
	public void gameLoop(Character player1, Character enemy1){
		while(player1.getAlive()==true && enemy1.getAlive()==true){ //Game loop
			player1.setLastHP(player1.getHP());
			enemy1.setLastHP(enemy1.getHP());
			
			this.setRound(this.getRound()+1); //Set round number (start at 1)
			System.out.println("\nRound " +this.getRound() +" :"); //Displays round number
			
			player1.setBlocking(false); //Set blocking to false at the beginning of every round
			enemy1.setBlocking(false);
			
			System.out.println("\nYour stats : HP:" +player1.getHP() +"/" +player1.getHPmax() +" ; MP:" +player1.getMP() +"/" +player1.getMPmax()); //Displays player's stats
			System.out.println("Enemy stats : HP:" +enemy1.getHP() +"/" +enemy1.getHPmax() +" ; MP:" +enemy1.getMP() +"/" +enemy1.getMPmax()); //Displays enemy's stats
		
			this.setMoveSelected(0);
			
			while(moveSelected <= 0 || moveSelected > 3){ //Continues while the move choice isn't 1, 2 or 3
				System.out.println("\nSelect your next move :\n1. Attack\n2. Block\n3. Spell (cost 1 MP)");
				System.out.println("\n");
				this.setMoveSelected(sc.nextInt());
				player1.setNextMove(this.getMoveSelected());
			}
			
			this.setEnemyMove(1 + r.nextInt(3 - 1)); //Select a random move for the enemy (basic IA needs to be improved)
			enemy1.setNextMove(this.getEnemyMove());
			
			if(player1.getSpeed()>enemy1.getSpeed() || player1.getSpeed()==enemy1.getSpeed()){ //If player is faster than enemy or has the same speed
				switch(this.getMoveSelected()){ //Do the move selected by the player
					case 1:
						if(this.getEnemyMove()==2){ //If enemy's move is Block set Block to 'true'
							enemy1.block();
						}
						player1.attack(enemy1);
						break;
					case 2:
						if(this.getEnemyMove()==2){ //If enemy's move is Block set Block to 'true'
							enemy1.block();
						}
						player1.block();
						break;
					case 3:
						if(this.getEnemyMove()==2){ //If enemy's move is Block set Block to 'true'
							enemy1.block();
						}
						player1.useSpell(enemy1);
						break;
				}
				if(enemy1.getAlive()==true){ //If enemy is still alive, he does his move
					switch(this.getEnemyMove()){ //Do the move of the enemy
						case 1:
							enemy1.attack(player1);
							break;
						case 2: //Nothing here because Block is already switched to 'true' for this round
							break;
						case 3:
							enemy1.useSpell(player1);
							break;
					}
				}
				else if(enemy1.getAlive()==false){ //However, if enemy is dead
					System.out.println("Your enemy is dead, you won at round " +this.getRound() +" !");
				}
				if(player1.getAlive()==false){
					System.out.println("You died at round " +this.getRound() +"...");
				}
			}
			else if(player1.getSpeed()<enemy1.getSpeed()){ //If the enemy's speed is higher than player's speed
				switch(this.getEnemyMove()){ //Do the move of the enemy
					case 1:
						if(this.getMoveSelected()==2){ //If player's move is Block set Block to 'true'
							player1.block();
						}
						enemy1.attack(player1);
						break;
					case 2:
						if(this.getMoveSelected()==2){ //If player's move is Block set Block to 'true'
							player1.block();
						}
						enemy1.block();
						break;
					case 3:
						if(this.getMoveSelected()==2){ //If player's move is Block set Block to 'true'
							player1.block();
						}
						enemy1.useSpell(player1);
						break;
				}
				if(player1.getAlive()==true){ //If player is still alive, he does his move
					switch(this.getMoveSelected()){ //Do the move selected by the player
						case 1:
							player1.attack(enemy1);
							break;
						case 2: //Nothing here because Block is already switched to 'true' for this round
							break;
						case 3:
							player1.useSpell(enemy1);
							break;
					}
				}
				else if(player1.getAlive()==false){ //However, is player is dead
					System.out.println("You died at round " +this.getRound() +"...");
				}
				if(enemy1.getAlive()==false){
					System.out.println("Your enemy is dead, you won at round " +this.getRound() +" !");
				}
			}
		}
	}
	
	
	
		
	/*
	GETTERS AND SETTERS
	*/
	
	
	
	
	
	public int getClassSelected() {
		return classSelected;
	}
	public void setClassSelected(int classSelected) {
		this.classSelected = classSelected;
	}
	public int getEnemyClass() {
		return enemyClass;
	}
	public void setEnemyClass(int enemyClass) {
		this.enemyClass = enemyClass;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public int getMoveSelected() {
		return moveSelected;
	}
	public void setMoveSelected(int moveSelected) {
		this.moveSelected = moveSelected;
	}
	public int getWorldSelected() {
		return worldSelected;
	}
	public void setWorldSelected(int worldSelected) {
		this.worldSelected = worldSelected;
	}
	public int getEnemyMove() {
		return enemyMove;
	}
	public void setEnemyMove(int enemyMove) {
		this.enemyMove = enemyMove;
	}
}
