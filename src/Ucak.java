import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Ucak extends JLabel{

	private static final long serialVersionUID = -8672217871097649905L;

	public static ArrayList<ImageIcon> icons_ucagim = new ArrayList<>();
	
	int direction;
	int speed = 6;
	public static int size = 150;

	public Ucak(){

		icons_ucagim.add(new ImageIcon(new ImageIcon(getClass().getResource("jet/fighter1.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		icons_ucagim.add(new ImageIcon(new ImageIcon(getClass().getResource("jet/fighter2.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		icons_ucagim.add(new ImageIcon(new ImageIcon(getClass().getResource("jet/fighter3.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		icons_ucagim.add(new ImageIcon(new ImageIcon(getClass().getResource("jet/fighter4.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		icons_ucagim.add(new ImageIcon(new ImageIcon(getClass().getResource("jet/fighter5.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
	
		setBounds(((MoveInAreaTest.ScreenSizeX-size)/2), MoveInAreaTest.ScreenSizeY, size, size);

	}
	
	 void direction_set(boolean sol_sag) {	//0, 1, 2, 3, 4 imageleri var (index 0 sol) (index 2 orta) (index 4 sað) eðik
		
			if (direction > 0 && sol_sag == false) {
				
				direction--;
				
			}else if (direction < icons_ucagim.size()-1 && sol_sag == true) {
				
				direction++;
				
			}

		
		this.setIcon(icons_ucagim.get(direction));
		
	}

	
	public void hareket() {

		//-------------- SOL SAG STABIL --------------
		if(CreateGameArea.keys[KeyEvent.VK_A] || CreateGameArea.keys[KeyEvent.VK_LEFT]){
			if ( getX() > 0) {
				direction_set(false);
				setLocation(getX()-speed, getY());
			}
		}
		else if(CreateGameArea.keys[KeyEvent.VK_D] || CreateGameArea.keys[KeyEvent.VK_RIGHT]){
			if ( getX() < (MoveInAreaTest.ScreenSizeX-size)) {
				direction_set(true);
				setLocation(getX()+speed, getY());
			}
		}
		else {
			//Duz hale getir
			// -------------- Uçaðýn 2 kademede standart hale gelmesi için --------------
			if (direction == 4) {	
				direction = 3;
				this.setIcon(icons_ucagim.get(3));	//Uçaðý düz hale getir
			}
			else if (direction == 3) {
				direction = 2;
				this.setIcon(icons_ucagim.get(2));	//Uçaðý düz hale getir
			}

			else if (direction == 1) {
				direction = 2;
				this.setIcon(icons_ucagim.get(2));	//Uçaðý düz hale getir
			}
			else if (direction == 0) {
				direction = 1;
				this.setIcon(icons_ucagim.get(1));	//Uçaðý düz hale getir
			}
			//-------------------------------------------------------------------------------
			
			
		}
		
		//-------------- YUKARI ASAGI --------------
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
		
		
	}
	
	
	public int getPosY() {
		return getY();
	}
	public int getPosX() {
		return getX();
	}
	
}
