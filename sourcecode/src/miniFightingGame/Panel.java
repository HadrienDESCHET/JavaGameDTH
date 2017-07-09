package miniFightingGame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Panel extends JPanel{
	private static final long serialVersionUID = -4803942642892195961L;
	
	private String worldPath, playerPath, enemyPath;
	
	public Panel(String worldPath, String playerPath, String enemypath){
		this.setWorldPath(worldPath);
		this.setPlayerPath(playerPath);
		this.setEnemyPath(enemypath);
	}
	
	public void paintComponent(Graphics g){
	    try{
	        BufferedImage background = ImageIO.read(new File(this.getWorldPath()));
	        g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
	        
	        BufferedImage player1 = ImageIO.read(new File(this.getPlayerPath()));
	        g.drawImage(player1, 250, 300, this);
	        
	        BufferedImage enemy1 = ImageIO.read(new File(this.getEnemyPath()));
	        g.drawImage(enemy1, 800, 300, this);
	    } catch (IOException e) {
	        e.printStackTrace();
	    } 
	}
	
	
	
	
	
	/*
	GETTERS AND SETTERS
	*/
	
	
	
	
	
	public String getWorldPath() {
		return worldPath;
	}

	public void setWorldPath(String worldPath) {
		this.worldPath = worldPath;
	}

	public String getPlayerPath() {
		return playerPath;
	}

	public void setPlayerPath(String playerPath) {
		this.playerPath = playerPath;
	}

	public String getEnemyPath() {
		return enemyPath;
	}

	public void setEnemyPath(String enemyPath) {
		this.enemyPath = enemyPath;
	}
}
