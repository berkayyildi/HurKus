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
	public boolean patlayacak = false;
	public int patlamaIconIndex = 0;
	public boolean died = false;
	public int kalanCan = 5;

	public Ucak(){

		for (int i=1 ; i<5 ; i++) {	//1 den 5 e 
		
			icons_ucagim.add(new ImageIcon(new ImageIcon(getClass().getResource("jet/fighter" + i + ".png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));

		}
		
		setBounds(((MoveInAreaTest.ScreenSizeX-size)/2), MoveInAreaTest.ScreenSizeY, size, size);

	}
	
	 void direction_set(boolean sol_sag) {	//0, 1, 2, 3, 4 imageleri var (index 0 sol) (index 2 orta) (index 4 sað) eðik

			if (patlamaIconIndex > 0) { return; }	//Uçak Hareket Etsin Ama Patlama Animasyonu Yok Olmasýn Diye Patlama Animasyonu Çalýþýyorken Direction Deðiþtirme
			
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
				direction_set(false);	//Sola dönüþ animasyonu
				setLocation(getX()-speed, getY());
			}
		}
		else if(CreateGameArea.keys[KeyEvent.VK_D] || CreateGameArea.keys[KeyEvent.VK_RIGHT]){
			if ( getX() < (MoveInAreaTest.ScreenSizeX-size)) {
				direction_set(true);	//Saða dönüþ animasyonu
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
	
	public void atesle() {
		new Mermi(getPosX()+32, getPosY()+40, true);
		new Mermi(getPosX()+102, getPosY()+40, true);
		Sound.playSound("fire");
	}
	

	public void patlat() {

		if (!died) {
			setIcon(CreateGameArea.explosion_icons.get(patlamaIconIndex));
			patlamaIconIndex++;
		}

		
		if (patlamaIconIndex > 10) {
			
			patlamaIconIndex = 0;		//Patlama animasyonunu baþa al
			patlayacak=false;

		}
		
		if (kalanCan == 0) {
			
			MoveInAreaTest.sil(this);	//Uçaðýmý sil
			died = true;
			
			MoveInAreaTest.oyunuBitir();
			
		}
	
	}
	
	
	public int getPosY() {
		return getY();
	}
	public int getPosX() {
		return getX();
	}
	
}
