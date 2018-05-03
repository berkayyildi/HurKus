import java.awt.Image;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class DusmanUcagi extends JLabel{
	
	public static ArrayList<DusmanUcagi> dusmanucaklari = new ArrayList<DusmanUcagi>();

	public static ArrayList<ImageIcon> explosion_icons = new ArrayList<>();

	private static final long serialVersionUID = 2892594439551034573L;
	
	int dusmanId;
	int direction;
	static int speed = 2;
	public static int size = 120;
	public static int araMesafe = 40;
	
	public int yavasHareket = 0;
	public int icon = 0;

	
	public DusmanUcagi(){
		dusmanId = dusmanSayisi();
		setIcon(new ImageIcon(new ImageIcon(getClass().getResource("jet/dusman.png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		
		for (int i=1 ; i < 12 ; i++) {	//Patlama iconlar�n� arraye at								//BU HER UCAK ���N YAPILIYO OPTIMIZE ET BUNU
			DecimalFormat twodigits = new DecimalFormat("00");	//00 01 diye gitmesi i�in
			explosion_icons.add(new ImageIcon(new ImageIcon(getClass().getResource("explosion/boom" + twodigits.format(i) + ".png")).getImage().getScaledInstance(size, size, Image.SCALE_DEFAULT)));
		}
		
		setBounds((dusmanSayisi()*120), 0, size, size);	//D��man say�s� kadar u�a�� ard arda diz
		
		dusmanucaklari.add(this);	//Yeni yarat�lan d��man objesini dusman ucaklar� aras�na ekle
		
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
			if (i < dusmanSayisi()-1) { SonrakiDusmanimiz = dusmanucaklari.get(i+1); } else { SonrakiDusmanimiz = null;}

			dusmanimiz.yavasHareket++;
			if (dusmanimiz.yavasHareket > 6 ) { dusmanimiz.setLocation(dusmanimiz.getPosX(), dusmanimiz.getPosY()+1);	dusmanimiz.yavasHareket=0;}
			
			if (dusmanimiz.getPosX() - CreateGameArea.myucak.getPosX() < 3 ) {	//- Herhangi bir d��man u�a�� u�a��m�n solunda kald�ysa
				
				if (SonrakiDusmanimiz != null) {													//-- Sa��nda bir u�ak varsa
					if (SonrakiDusmanimiz.getX() > dusmanimiz.getX() + (size+araMesafe) ) {							//--- �arp��mayacaklarsa
						dusmanimiz.setLocation(dusmanimiz.getPosX()+speed, dusmanimiz.getPosY());	//---- Sa�a git
					}
				}else {																				//Sa��nda u�ak yoksa (Demekki son u�ak bu) O u�ak sa�a gitsin
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
			System.out.println("resim: " + icon);
			setIcon(explosion_icons.get(icon));
			icon++;
		
	}
	
	public static int dusmanSayisi() {
		return dusmanucaklari.size();
	}
	
	public int getPosY() {
		return getY();
	}
	public int getPosX() {
		return getX();
	}
	
}
