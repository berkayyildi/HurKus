import java.awt.event.KeyEvent;
import java.util.Random;

public class MT implements Runnable {
	
	private final int sleepTime;
	private boolean isFirstTime = true;
	private int mesafeAyarlayiciDelay = 100;
	private int mesafeAyarlayiciDelayer = 0;
	
	private int DusmanAtesiDelay = 50;	//Ate� i�in ne kadar saymas� gerekti�i
	private int DusmanAtesiDelayer = 0;	//Basit bi counter DusmanAtesiDelay e kadar say�nca ates eder.
	private int currentLevel = 1;

	public MT(int sleepTime) {

		bolumBaslat(currentLevel);
		
		this.sleepTime = sleepTime;
		
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				
				if (DusmanUcagi.dusmanSayisi() == 0) {
					
					currentLevel++;
					bolumBaslat(currentLevel);
				}

				mesafeAyarlayiciDelayer++; if (mesafeAyarlayiciDelayer > mesafeAyarlayiciDelay) { DusmanUcagi.araMesafe = new Random().nextInt(80) + 1;  mesafeAyarlayiciDelayer=0; } 		//Dusmanlar aras�dnaki mesafeyi random 0-80 aras� de�i�tir	
				
				
				//-------------------- U�a��n �lk A��l��ta Ekrana Y�r�mesi ��in ------------------------
				if (isFirstTime) {
					
					CreateGameArea.keys[KeyEvent.VK_W] = true; 
									 
	                if(CreateGameArea.myucak.getPosY() < MoveInAreaTest.ScreenSizeY - (Ucak.size)) {	//U�ak ekrana geldi ise
	                	CreateGameArea.keys[KeyEvent.VK_W] = false;										//�st tu�u art�k basma
	                	isFirstTime = false;															//First time den ��k
	                } 
					
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
					for (int j=0; j<DusmanUcagi.dusmanSayisi() ;j++) {
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
				DusmanAtesiDelayer++;
				if (DusmanAtesiDelayer > DusmanAtesiDelay) {
					int randomDusman = new Random().nextInt(DusmanUcagi.dusmanSayisi());
					DusmanUcagi atesEdecekDusman = DusmanUcagi.dusmanucaklari.get(randomDusman);
					atesEdecekDusman.atesle();
					DusmanAtesiDelayer = 0;
				}
				//---------------------------------------------------------------------------------
				
				CreateGameArea.myucak.hareket();														//TU�LARI ALGILATMAK ���N			

				DusmanUcagi.hareket();																	//YAPAY ZEKA �LE DUSMAN HAREKETI ICIN

				Thread.sleep(sleepTime);																//10 ms uyu
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	public void bolumBaslat(int lvl) {
		for (int i=0; i < (2*lvl)+1 ; i++) {
			new DusmanUcagi();
		}
	}
	
}
