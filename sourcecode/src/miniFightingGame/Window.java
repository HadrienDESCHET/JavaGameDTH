package miniFightingGame;

import javax.swing.JFrame;

public class Window extends JFrame{
	private static final long serialVersionUID = 2249793877815155589L;

	public Window(int worldSelected, int classSelected, int enemyClass){
		String worldPath="!Invalid worldPath", playerPath="!Invalid playerPath", enemyPath="!Invalid enemypath";
		
		switch(worldSelected){ //Assign the world selected by the user
		case 1:
			worldPath="C:/Users/Utilisateur/Desktop/JAva/world1.jpg";
			break;
		case 2:
			worldPath="C:/Users/Utilisateur/Desktop/JAva/world2.png";
			break;
		case 3:
			worldPath="C:/Users/Utilisateur/Desktop/JAva/world3.png";
			break;
	}
	
	switch(classSelected){ //Assign the model of the character selected by the user
		case 1:
			playerPath="C:/Users/Utilisateur/Desktop/JAva/damagerStanding2.png";
			break;
		case 2:
			playerPath="C:/Users/Utilisateur/Desktop/JAva/healerStanding2.png";
			break;
		case 3:
			playerPath="C:/Users/Utilisateur/Desktop/JAva/tankStanding.png";
			break;
	}
	
	switch(enemyClass){ //Assign the model of the character of the enemy
		case 1:
			enemyPath="C:/Users/Utilisateur/Desktop/JAva/damagerStanding2.png";
			break;
		case 2:
			enemyPath="C:/Users/Utilisateur/Desktop/JAva/healerStanding.png";
			break;
		case 3:
			enemyPath="C:/Users/Utilisateur/Desktop/JAva/tankStanding2.png";
			break;
		}
		
		this.setTitle("miniFightingGame"); //Title of the window
		this.setSize(1280, 720); //Size x y of the window
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);          
		
		Panel pan = new Panel(worldPath, playerPath, enemyPath);
		this.setContentPane(pan);
		
		this.setVisible(true); //Set the window to be visible
	}
}
