import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DusmanUcagi extends JLabel{
	
	public static ArrayList<DusmanUcagi> dusmanucaklari = new ArrayList<DusmanUcagi>();

	private static final long serialVersionUID = 2892594439551034573L;
	

	int direction;
	static int speed = 2;
	public static int size = 120;
	public static int araMesafe = 40;
	
	public int yavasHareket = 0;
	public int patlamaIconIndex = 0;
	public boolean patlayacak = false;

	
	public DusmanUcagi(){

		int  random_id = new Random().nextInt(8) + 1; //min 1 max 8 random sayý oluþtur
		
		setIcon(new ImageIcon(new ImageIcon(getClass().getResource("enemy/fighter" + random_id +".png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		
		int soldanBosluk = (MoveInAreaTest.ScreenSizeX - (2 + MT.currentLevel) * size) / 2;	//(Ekran boyutu - Bu levelde gelecek dusman sayisi) /2
		
		setBounds(soldanBosluk + (dusmanucaklari.size()*size), -size, size, size);	//Düþman sayýsý kadar uçaðý ard arda diz
		
		dusmanucaklari.add(this);	//Yeni yaratýlan düþman objesini dusman ucaklarý arasýna ekle
		
		MoveInAreaTest.ekle(this);

	}
	


	public static void hareket() {
		
		DusmanUcagi dusmanimiz;
		DusmanUcagi OncekiDusmanimiz;
		DusmanUcagi SonrakiDusmanimiz;
		
		//---------------------------------- HAREKET ET -----------------------------------------------
		for (int i = 0; i<dusmanucaklari.size() ;i++) {

			dusmanimiz = dusmanucaklari.get(i);
			if (i > 0) {OncekiDusmanimiz = dusmanucaklari.get(i-1); } else { OncekiDusmanimiz = null;}
			if (i < dusmanucaklari.size()-1) { SonrakiDusmanimiz = dusmanucaklari.get(i+1); } else { SonrakiDusmanimiz = null;}

			dusmanimiz.yavasHareket++;
			if (dusmanimiz.yavasHareket > 6 ) { dusmanimiz.setLocation(dusmanimiz.getPosX(), dusmanimiz.getPosY()+1);	dusmanimiz.yavasHareket=0;}
			
			if (dusmanimiz.getPosX() - CreateGameArea.myucak.getPosX() < 3 ) {	//- Herhangi bir düþman uçaðý uçaðýmýn solunda kaldýysa
				
				if (SonrakiDusmanimiz != null) {													//-- Saðýnda bir uçak varsa
					if (SonrakiDusmanimiz.getX() > dusmanimiz.getX() + (size+araMesafe) ) {							//--- Çarpýþmayacaklarsa
						dusmanimiz.setLocation(dusmanimiz.getPosX()+speed, dusmanimiz.getPosY());	//---- Saða git
					}
				}else {																				//Saðýnda uçak yoksa (Demekki son uçak bu) O uçak saða gitsin
					dusmanimiz.setLocation(dusmanimiz.getPosX()+speed, dusmanimiz.getPosY());	
				}
		
			}
			
			else if (dusmanimiz.getPosX() - CreateGameArea.myucak.getPosX() > 3 ) {			//
				if (OncekiDusmanimiz == null) {												//
					dusmanimiz.setLocation(dusmanimiz.getPosX()-speed, dusmanimiz.getPosY());
				}else {																						
					if (OncekiDusmanimiz.getX() < dusmanimiz.getX() - (size+araMesafe) ) {
						dusmanimiz.setLocation(dusmanimiz.getPosX()-speed, dusmanimiz.getPosY());
					}
				}
			}
				
	
		}

		//---------------------------------------------------------------------------------

	}
	
	public void patlat() {

			setIcon(CreateGameArea.explosion_icons.get(patlamaIconIndex));
			patlamaIconIndex++;
			
			if (patlamaIconIndex > 10) {		//Patlama efekti bitti
				CreateGameArea.puanArttir();	//Puani patlama efkti bittiðinde 1 arttir
				dusmanucaklari.remove(this);
				MoveInAreaTest.sil(this);
			}
		
	}
	
	public void atesle() {

		new Mermi(getPosX()+22, getPosY()+70, false);
		new Mermi(getPosX()+92, getPosY()+70, false);
		Sound.playSound("fire");
	}

	public int getPosY() {
		return getY();
	}
	public int getPosX() {
		return getX();
	}
	
}
