package miniFightingGame;

public abstract class Character {
	private int HP, HPmax, lastHP, MP, MPmax, AS, speed; // ==> Health Points, Magic Points, Attack Strength, lastHP is for damager's spell
	private String characterClass, characterName;
	private boolean alive=true, blocking=false;
	private int nextMove;

	
	
	public void assignClass(int classSelected){ //1=Damager, 2=Healer, 3=Tank
		switch(classSelected){ //Set HP/HPmax, MP/MPmax, AS and speed depending on the class selected
			case 1:
				this.setCharacterClass("Damager");
				this.setHP(3);
				this.setHPmax(3);
				this.setMP(1);
				this.setMPmax(1);
				this.setAS(2);
				this.setSpeed(1);
				break;
			case 2:
				this.setCharacterClass("Healer");
				this.setHP(4);
				this.setHPmax(4);
				this.setMP(1);
				this.setMPmax(1);
				this.setAS(1);
				this.setSpeed(1);
				break;
			case 3:
				this.setCharacterClass("Tank");
				this.setHP(5);
				this.setHPmax(5);
				this.setMP(2);
				this.setMPmax(2);
				this.setAS(1);
				this.setSpeed(2);
				break;
		}
	}
	
	public void attack(Character target){ //Attack the target
		System.out.println(this.getCharacterName() +" attacks");
		
		if(target.getBlocking()==false){ //If the target doesn't block the attack
			target.takeDamage(this.getAS());
		}
		else if(target.getBlocking()==true){ //If the target blocks the attack
			target.takeDamage(1);;
		}
	}
	
	public void block(){ //Block the next attack if there is one
		System.out.println(this.getCharacterName() +" is prepared to block the incoming attack...");
		this.setBlocking(true);
	}
	
	public void useSpell(Character target){ //Use the class spell
		switch(characterClass){
			case "Damager":
				int damagesDamagerSpell = this.getLastHP()-this.getHP(); //Damage taken by the character during this round
				
				if(this.getMP()>0){ //If he has enough MP
					if(this.getSpeed()>=target.getSpeed()){ //If character speed >= target speed
						switch(target.getNextMove()){ //Depending on which move the opponent makes
							case 1: //If he attacks, damages are returned
								target.takeDamage(target.getAS());
								System.out.println(this.getCharacterName() +" returned damages to his opponent");
								break;
							case 2: //If he blocks nothing happens
								System.out.println(target.getCharacterName() +" is blocking so nothing happens...");
								break;
							case 3: //If he uses is spell (depending on the opponent class)
								switch(target.getCharacterClass()){
									case "Damager": //If both Damager use their spell nothing happens because none of them attacks the other
										System.out.println(this.getCharacterName() +" didn't get hit so nothing happends...");
										break;
									case "Healer": //Healer spell heals himself so nothing happens here
										System.out.println(this.getCharacterName() +" didn't get hit so nothing happends...");
										break;
									case "Tank": //If tank, return all the damages he gives to the character
										target.takeDamage(target.getAS()+1);
										break;
								}
								break;
						}
					}
					else if(this.getSpeed()<target.getSpeed()){ //If character speed < target speed
						if(damagesDamagerSpell==this.getLastHP()){ //If character get damaged, he return the damages he took
							System.out.println(this.getCharacterName() +" didn't get hit so nothing happends...");
						}
						else if(damagesDamagerSpell!=this.getLastHP()){ //If character didn't get damaged nothing happens
							target.takeDamage(damagesDamagerSpell);
							System.out.println(this.getCharacterName() +" returned damages to his opponent");
						}	
					}
				this.useMP(1); //Uses 1 MP
				}
					
				else if(this.getMP()<0){ //If 0 MP available nothing happens
					System.out.println(this.getCharacterName() +" has not enough MP to cast his spell");
				}
				
				break;
				
			case "Healer": //Heals 2 HP while being a Healer
				if(this.getMP()>0){
					this.heal(2);
					this.setMP(this.getMP()-1);
				}
				else if(this.getMP()<=0){
					System.out.println(this.getCharacterName() +" tries to cast his spell but don't have enought MP...");
				}
				break;
					
			case "Tank": //Loses 1 HP but deals 2 damage point
				if(this.getMP()>0){
					if(this.getHP()>1){ //Only if he has more than 1 HP, otherwise he would die
						System.out.println(this.getCharacterName() +" sacrifices 1 HP to deal more damages to his opponent");
						this.takeDamage(1);
						if(target.getBlocking()==false){
							this.attack(target);
							this.attack(target);
						}
						else if(target.getBlocking()==true){
							this.attack(target);
						}
						this.setMP(this.getMP()-1);
					}
					else if(this.getHP()==1){ //Else nothing happens
						System.out.println(this.getCharacterName() +" not enough HP to do that");
					}
				}
				else if(this.getMP()<=0){
					System.out.println(this.getCharacterName() +" tries to cast his spell but don't have enought MP...");
				}
				break;
		}
	}
	
	public void heal(int healAmount){
		for(int i=0; i<healAmount; i++){ 
			if((this.getHP()+1)<=this.getHPmax()){ //If the character HP are below his HPmax, he heals himself
				System.out.println(this.getCharacterName() +" heals himself !");
				this.setHP(this.getHP()+1);
			}
			else if((this.getHP()+1)>this.getHPmax()){ //The character can't heal himself if it would make his HP go above his HPmax
				System.out.println(this.getCharacterName() +" can't heal himself, his life is currently at is maximum !");
			}
		}		
	}
	
	public void takeDamage(int damageAmount){
		if(this.getBlocking()==true){ //If character is blocking
			this.setHP(this.getHP()-1); //Only lose 1 HP
		}
		else if(this.getBlocking()==false){ //If character isn't blocking
			this.setHP(this.getHP()-damageAmount); //Character lose 'damageAmount' HP
		}
		
		if(this.getHP()<=0){ //If target's HP equals 0 or goes above 0, she dies
			this.setAlive(false);
		}
	}
	
	public void useMP(int MPused){
		this.setMP(this.getMP()-MPused);
	}
	

	
	
	
	/*
	GETTERS AND SETTERS
	*/
	
	
	
	
	
	public int getAS() {
		return AS;
	}
	public void setAS(int aS) {
		AS = aS;
	}
	public int getHP() {
		return HP;
	}
	public void setHP(int hP) {
		HP = hP;
	}
	public int getHPmax() {
		return HPmax;
	}
	public void setHPmax(int hPmax) {
		HPmax = hPmax;
	}
	public int getLastHP() {
		return lastHP;
	}

	public void setLastHP(int lastHP) {
		this.lastHP = lastHP;
	}
	public int getMP() {
		return MP;
	}
	public void setMP(int mP) {
		MP = mP;
	}
	public int getMPmax() {
		return MPmax;
	}
	public void setMPmax(int mPmax) {
		MPmax = mPmax;
	}
	public String getCharacterClass() {
		return characterClass;
	}
	public void setCharacterClass(String characterClass) {
		this.characterClass = characterClass;
	}
	public String getCharacterName() {
		return characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public boolean getAlive() {
		return alive;
	}

	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public boolean getBlocking() {
		return blocking;
	}

	public void setBlocking(boolean blocking) {
		this.blocking = blocking;
	}

	public int getNextMove() {
		return nextMove;
	}

	public void setNextMove(int nextMove) {
		this.nextMove = nextMove;
	}
}
