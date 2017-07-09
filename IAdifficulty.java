	public void assignDifficulty(int difficultySelected){ //1-EASY 2-MEDIUM 3-HARD
		switch(difficultySelected){ //Adding stats depending on the difficulty
			case 1 : 
				switch(IAclassEASY){ //same stats
				case 1:
					this.setCharacterClass("DamagerEASY");
					this.setHP(3);
					this.setHPmax(3);
					this.setMP(1);
					this.setMPmax(1);
					this.setAS(2);
					this.setSpeed(1);
					break;
				case 2:
					this.setCharacterClass("HealerEASY");
					this.setHP(4);
					this.setHPmax(4);
					this.setMP(1);
					this.setMPmax(1);
					this.setAS(1);
					this.setSpeed(1);
					break;
				case 3:
					this.setCharacterClass("TankEASY");
					this.setHP(5);
					this.setHPmax(5);
					this.setMP(2);
					this.setMPmax(2);
					this.setAS(1);
					this.setSpeed(2);
					break;
				}
			case 2 :
				switch(IAclassMEDIUM){// +1 hp +1 speed
				case 1:
					this.setCharacterClass("DamagerMEDIUM");
					this.setHP(4);
					this.setHPmax(4);
					this.setMP(1);
					this.setMPmax(1);
					this.setAS(2);
					this.setSpeed(2);
					break;
				case 2:
					this.setCharacterClass("HealerMEDIUM");
					this.setHP(5);
					this.setHPmax(5);
					this.setMP(1);
					this.setMPmax(1);
					this.setAS(1);
					this.setSpeed(2);
					break;
				case 3:
					this.setCharacterClass("TankMEDIUM");
					this.setHP(6);
					this.setHPmax(6);
					this.setMP(2);
					this.setMPmax(2);
					this.setAS(1);
					this.setSpeed(3);
					break;
				}
			case 3 :
				switch(IAclasseHARD){ // +1 hp +1 mp +1speed
				case 1:
					this.setCharacterClass("DamagerHARD");
					this.setHP(4);
					this.setHPmax(4);
					this.setMP(2);
					this.setMPmax(2);
					this.setAS(2);
					this.setSpeed(2);
					break;
				case 2:
					this.setCharacterClass("HealerHARD");
					this.setHP(5);
					this.setHPmax(5);
					this.setMP(2);
					this.setMPmax(2);
					this.setAS(1);
					this.setSpeed(1);
					break;
				case 3:
					this.setCharacterClass("TankHARD");
					this.setHP(6);
					this.setHPmax(6);
					this.setMP(3);
					this.setMPmax(3);
					this.setAS(1);
					this.setSpeed(3);
					break;
				}	
		}
	}
