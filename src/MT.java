import java.awt.event.KeyEvent;
import java.util.Random;

public class MT implements Runnable {
	
	public static volatile boolean allowToRun = true;
	
	private final int sleepTime;				//Thread Sleep Time
	private boolean isFirstTime = true;			//Ilk acilis mi
	private int mesafeAyarlayiciDelay = 100;	//Dusman ucaklari arasindaki mesafeyi degistirme s�resi
	private int mesafeAyarlayiciDelayer = 0;	//Dusman ucaklari arasindaki mesafeyi degistirme sayaci
	
	private int DusmanAtesiDelay = 50;	//Ate� i�in ne kadar saymas� gerekti�i
	private int DusmanAtesiDelayer = 0;	//Basit bi counter DusmanAtesiDelay e kadar say�nca ates eder.
	public static int currentLevel = 1;		//�u anki leveli tutar

	
	private int mermiSpamDelay = 150;	//Mermi spam s�f�rlama s�resi
	private int mermiSpamDelayer = 0;	//Spam s�re sayaci


	public MT(int sleepTime) {

		bolumBaslat(currentLevel);
		
		this.sleepTime = sleepTime;
		
	}
	
	
	public void bolumBaslat(int lvl) {	//Yeni B�l�m ��in u�aklar� yarat�r
		
		CreateGameArea.topRightText.setText("<html><font color='green' size='6'>Level " + lvl +"</font></html>");	//Sa� �stte yazan b�l�m yaz�s�n� g�ncelle
		
		if (lvl >= 5) { lvl =5; }	//level 5 ten b�y�k olsa da sadece 5 lvl de olan u�ak kadar u�ak yarat
			
			for (int i=0; i < 2+lvl ; i++) {
				new DusmanUcagi();	//D��ma yarat
			}	
		if (lvl < 10) {
			DusmanAtesiDelay = DusmanAtesiDelay - 5;	//Mermi hizlarini arttir
		}
	}
	
	
	@Override
	public void run() {

		while(true) {
			if (allowToRun) {
				try {
					
					if (DusmanUcagi.dusmanucaklari.size() == 0) {
						currentLevel++;
						bolumBaslat(currentLevel);
					}
					
					
	
					mesafeAyarlayiciDelayer++; if (mesafeAyarlayiciDelayer > mesafeAyarlayiciDelay) { DusmanUcagi.araMesafe = new Random().nextInt(80) + 1;  mesafeAyarlayiciDelayer=0; } 		//Dusmanlar aras�dnaki mesafeyi random 0-80 aras� de�i�tir	
					
					
					
					MoveInAreaTest.arkaplaniKaydir();
					
					//-------------------- U�a��n �lk A��l��ta Ekrana Y�r�mesi ��in ------------------------
					if (isFirstTime) {	//bu 0 ise performans icin bidaha iceri bakmaz
						
						CreateGameArea.keys[KeyEvent.VK_W] = true; 
										 
		                if(CreateGameArea.myucak.getPosY() < MoveInAreaTest.ScreenSizeY - (Ucak.size)) {	//U�ak ekrana geldi ise
		                	CreateGameArea.keys[KeyEvent.VK_W] = false;										//�st tu�u art�k basma
		                	isFirstTime = false;															//First time den ��k
		                } 
						
					}
					//--------------------------------------------------------------------------------
					
					//-------------------- Dusman Ucaklarinin Ekrana Y�r�mesi ��in ------------------------

					for (int j=0; j<DusmanUcagi.dusmanucaklari.size() ;j++) {
						DusmanUcagi dusmanimiz = DusmanUcagi.dusmanucaklari.get(j);
						if (dusmanimiz.getY() < 0) {dusmanimiz.setLocation(dusmanimiz.getX(),dusmanimiz.getY()+1);}
							
					}

					//--------------------------------------------------------------------------------
					
					//---------------------- Benim Merminin Hareketi ��in ----------------------------------
					for (int i = 0; i<Mermi.MermilerimArray.size() ;i++) {
						
						Mermi mermimiz = Mermi.MermilerimArray.get(i);
						mermimiz.setLocation(mermimiz.getPosX(),mermimiz.getPosY()-5);
						
						if (mermimiz.getPosY() < 0) {
							 Mermi.MermilerimArray.remove(i);
							 MoveInAreaTest.sil(mermimiz);	//RAMden de sil
						}
						
						// ----------- Mermi Dusmana Carpt�m� bakmak i�in  -----------
						for (int j=0; j<DusmanUcagi.dusmanucaklari.size() ;j++) {
							DusmanUcagi dusmanimiz = DusmanUcagi.dusmanucaklari.get(j);
							boolean mermiyle_dusman = CreateGameArea.intersects(mermimiz, dusmanimiz);
							if (mermiyle_dusman && (!(dusmanimiz.patlayacak)) ) {	//�arp���yolarsa ve daha oncden �arp��t�klar� tespit edilmediyse
								dusmanimiz.patlayacak = true;	//Patlayacak olarak i�aretle
								MoveInAreaTest.sil(mermimiz);	//Mermimizi yok et arkadan geleni vurmas�n
								Sound.playSound("explosion");	//Patlama sesi �al
							}
						}
						//--------------------------------------------------------------
						
					}
					//---------------------------------------------------------------------------------
					
					//---------------------- Dusman Mermisinin Hareketi ��in ----------------------------------
					for (int i = 0; i<Mermi.DusmanMermiArray.size() ;i++) {
						Mermi mermimiz = Mermi.DusmanMermiArray.get(i);
						mermimiz.setLocation(mermimiz.getPosX(),mermimiz.getPosY()+5);
						
						if (mermimiz.getPosY() > MoveInAreaTest.ScreenSizeY) {
							 Mermi.DusmanMermiArray.remove(i);
							 MoveInAreaTest.sil(mermimiz);	//RAMden de sil
						}
						
						// ----------- Mermi Bana Carpt�m� bakmak i�in  -----------
							Ucak ben = CreateGameArea.myucak;
							boolean mermiyle_ben = CreateGameArea.intersects(mermimiz, ben);
							
							if (mermiyle_ben) {
								MoveInAreaTest.sil(mermimiz);	//Mermimizi yok et arkadan geleni vurmas�n
							}
							
							if (mermiyle_ben && (!(ben.patlayacak)) ) {	//�arp���yolarsa ve daha onceden �arp��t�klar� tespit edilmediyse
								ben.patlayacak = true;			//Patlayacak olarak i�aretle
								CreateGameArea.vuruldu();		//Vurulma yada patlama efektini �al��t�r�r
								Sound.playSound("explosion");	//Patlama sesi �al
						}
						//--------------------------------------------------------------
						
					}
					//---------------------------------------------------------------------------------
					
					//---------------------- Dusmana Ate� Ettir ----------------------------------
					++DusmanAtesiDelayer;
					if (DusmanAtesiDelayer > DusmanAtesiDelay) {
						int randomDusman = new Random().nextInt(DusmanUcagi.dusmanucaklari.size());
						DusmanUcagi atesEdecekDusman = DusmanUcagi.dusmanucaklari.get(randomDusman);
						atesEdecekDusman.atesle();
						DusmanAtesiDelayer = 0;
						
					}
					//---------------------------------------------------------------------------------
					
					//---------------------- Mermi Spam Kontrol ----------------------------------
					++mermiSpamDelayer;
					if (mermiSpamDelayer > mermiSpamDelay) {
						Ucak.mermiSpamCount = 0;
						mermiSpamDelayer = 0;
						CreateGameArea.bottomLeftText.setVisible(false);
					}
					//---------------------------------------------------------------------------------
					
					CreateGameArea.myucak.hareket();														//TU�LARI ALGILATMAK ���N			
	
					DusmanUcagi.hareket();																	//YAPAY ZEKA �LE DUSMAN HAREKETI ICIN
	
					Thread.sleep(sleepTime);																//10 ms uyu
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}	//Allow To Run
		}
	}

	
}
