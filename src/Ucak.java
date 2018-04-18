import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Ucak extends JLabel implements Ucaklar{

	public static ArrayList<ImageIcon> icons_ucagim = new ArrayList<>();
	
	int direction;
	int speed = 5;
	int size = 180;

	public Ucak(){

		icons_ucagim.add(new ImageIcon(new ImageIcon(getClass().getResource("jet/fighter1.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		icons_ucagim.add(new ImageIcon(new ImageIcon(getClass().getResource("jet/fighter2.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		icons_ucagim.add(new ImageIcon(new ImageIcon(getClass().getResource("jet/fighter3.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		icons_ucagim.add(new ImageIcon(new ImageIcon(getClass().getResource("jet/fighter4.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		icons_ucagim.add(new ImageIcon(new ImageIcon(getClass().getResource("jet/fighter5.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));

		direction = (icons_ucagim.size()/2)+1;
		
		direction_set(-1);	//default 
		
		setBounds(400, 456, size, size);

	}
	
	public void direction_set(int sol_sag) {
		

			if (direction > 0 && sol_sag == 0) {
				
				direction--;
				speed=6;
				
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

	
	public void hareket(KeyEvent event) {
		
		System.out.println(getX()+","+getY()+"Dir:" + direction);	//KOORDINATLARI LOGLA
		
		String whichKey=KeyEvent.getKeyText(event.getKeyCode());
		
		if(whichKey.compareTo("Left")==0){
			if ( getX() > 0) {
				direction_set(0);
				setBounds( getX()-speed, getY(), size, size);
			}
		}else if(whichKey.compareTo("Right")==0){
			if ( getX() < 820) {
				direction_set(1);
				setBounds( getX()+speed, getY(), size, size);
			}
		}else if(whichKey.compareTo("Up")==0){
			if ( getY() > 0) {
				setBounds( getX(), getY()-speed, size, size);
			}
		}else if(whichKey.compareTo("Down")==0){
			if ( getY() < 550) {
				setBounds( getX(), getY()+speed, size, size);
			}
		}
		
	}
	
	
	
}
