import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Ucak extends JLabel implements Ucaklar{

	public static ArrayList<ImageIcon> icons_ucagim = new ArrayList<>();
	
	int direction;
	int speed = 1;
	int size = 150;

	public Ucak(){

		icons_ucagim.add(new ImageIcon(new ImageIcon(getClass().getResource("jet/fighter1.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		icons_ucagim.add(new ImageIcon(new ImageIcon(getClass().getResource("jet/fighter2.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		icons_ucagim.add(new ImageIcon(new ImageIcon(getClass().getResource("jet/fighter3.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		icons_ucagim.add(new ImageIcon(new ImageIcon(getClass().getResource("jet/fighter4.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		icons_ucagim.add(new ImageIcon(new ImageIcon(getClass().getResource("jet/fighter5.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));

		direction = (icons_ucagim.size()/2)+1;
		
		direction_set(-1);	//default 
		
		setBounds(((MoveInAreaTest.ScreenSizeX-size)/2), MoveInAreaTest.ScreenSizeY, size, size);

	}
	
	public void direction_set(int sol_sag) {
		

			if (direction > 0 && sol_sag == 0) {
				
				direction--;
				
			}else if (direction < icons_ucagim.size()-1 && sol_sag == 1) {
				
				direction++;
				
			}

		
		this.setIcon(icons_ucagim.get(direction));
		
	}
	
	public void stabil() {
	direction=2;
	speed=5;
	this.setIcon(icons_ucagim.get(icons_ucagim.size()/2));
	
}

	
	public void hareket() {

		if(CreateGameArea.keys[KeyEvent.VK_A] || CreateGameArea.keys[KeyEvent.VK_LEFT]){
			if ( getX() > 0) {
				direction_set(0);
				setLocation(getX()-speed, getY());
			}
		}
		else if(CreateGameArea.keys[KeyEvent.VK_D] || CreateGameArea.keys[KeyEvent.VK_RIGHT]){
			if ( getX() < (MoveInAreaTest.ScreenSizeX-size)) {
				direction_set(1);
				setLocation(getX()+speed, getY());
			}
		}
		if(CreateGameArea.keys[KeyEvent.VK_W] || CreateGameArea.keys[KeyEvent.VK_UP]){
			if ( getY() > 0) {
				setLocation(getX(), getY()-(speed/2));
			}
		}
		else if(CreateGameArea.keys[KeyEvent.VK_S] || CreateGameArea.keys[KeyEvent.VK_DOWN]){
			if ( getY() < (MoveInAreaTest.ScreenSizeY-size)) {
				setLocation(getX(), getY()+(speed/2));
			}
		}
		
		if(CreateGameArea.keys[KeyEvent.VK_SPACE]){
			
			Mermi solmermi = new Mermi(32, 40);
			Mermi sagmermi = new Mermi(102, 40);
			add((JLabel)solmermi);
			add((JLabel)sagmermi);
		}
		
	}
	
	
	public int ggY() {
		
		return getY();
		
	}
	
	
}
