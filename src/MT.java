import java.awt.event.KeyEvent;
import java.util.Random;

public class MT implements Runnable {
	
	private final int sleepTime;
	private boolean isFirstTime = true;
	private int mesafeAyarlayiciDelay = 100;
	private int mesafeAyarlayiciDelayer = 0;
	
	private int DusmanAtesiDelay = 50;	//Ateþ için ne kadar saymasý gerektiði
	private int DusmanAtesiDelayer = 0;	//Basit bi counter DusmanAtesiDelay e kadar sayýnca ates eder.
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

				mesafeAyarlayiciDelayer++; if (mesafeAyarlayiciDelayer > mesafeAyarlayiciDelay) { DusmanUcagi.araMesafe = new Random().nextInt(80) + 1;  mesafeAyarlayiciDelayer=0; } 		//Dusmanlar arasýdnaki mesafeyi random 0-80 arasý deðiþtir	
				
				
				//-------------------- Uçaðýn Ýlk Açýlýþta Ekrana Yürümesi Ýçin ------------------------
				if (isFirstTime) {
					
					CreateGameArea.keys[KeyEvent.VK_W] = true; 
									 
	                if(CreateGameArea.myucak.getPosY() < MoveInAreaTest.ScreenSizeY - (Ucak.size)) {	//Uçak ekrana geldi ise
	                	CreateGameArea.keys[KeyEvent.VK_W] = false;										//Üst tuþu artýk basma
	                	isFirstTime = false;															//First time den çýk
	                } 
					
				}
				//--------------------------------------------------------------------------------
				
				//---------------------- Benim Merminin Hareketi Ýçin ----------------------------------
				for (int i = 0; i<Mermi.MermilerimArray.size() ;i++) {
					
					Mermi mermimiz = Mermi.MermilerimArray.get(i);
					mermimiz.setLocation(mermimiz.getPosX(),mermimiz.getPosY()-5);
					
					if (mermimiz.getPosY() < 0) {
						 Mermi.MermilerimArray.remove(i);
						 MoveInAreaTest.sil(mermimiz);	//RAMden de sil
					}
					
					// ----------- Mermi Dusmana Carptýmý bakmak için  -----------
					for (int j=0; j<DusmanUcagi.dusmanSayisi() ;j++) {
						DusmanUcagi dusmanimiz = DusmanUcagi.dusmanucaklari.get(j);
						boolean mermiyle_dusman = CreateGameArea.intersects(mermimiz, dusmanimiz);
						if (mermiyle_dusman && (!(dusmanimiz.patlayacak)) ) {	//Çarpýþýyolarsa ve daha oncden çarpýþtýklarý tespit edilmediyse
							dusmanimiz.patlayacak = true;	//Patlayacak olarak iþaretle
							MoveInAreaTest.sil(mermimiz);	//Mermimizi yok et arkadan geleni vurmasýn
							Sound.playSound("explosion");	//Patlama sesi çal
						}
					}
					//--------------------------------------------------------------
					
				}
				//---------------------------------------------------------------------------------
				
				//---------------------- Dusman Mermisinin Hareketi Ýçin ----------------------------------
				for (int i = 0; i<Mermi.DusmanMermiArray.size() ;i++) {
					Mermi mermimiz = Mermi.DusmanMermiArray.get(i);
					mermimiz.setLocation(mermimiz.getPosX(),mermimiz.getPosY()+5);
					
					if (mermimiz.getPosY() > MoveInAreaTest.ScreenSizeY) {
						 Mermi.DusmanMermiArray.remove(i);
						 MoveInAreaTest.sil(mermimiz);	//RAMden de sil
					}
					
					// ----------- Mermi Bana Carptýmý bakmak için  -----------
						Ucak ben = CreateGameArea.myucak;
						boolean mermiyle_ben = CreateGameArea.intersects(mermimiz, ben);
						
						if (mermiyle_ben) {
							MoveInAreaTest.sil(mermimiz);	//Mermimizi yok et arkadan geleni vurmasýn
						}
						
						if (mermiyle_ben && (!(ben.patlayacak)) ) {	//Çarpýþýyolarsa ve daha onceden çarpýþtýklarý tespit edilmediyse
							ben.patlayacak = true;			//Patlayacak olarak iþaretle
							CreateGameArea.vuruldu();		//Vurulma yada patlama efektini çalýþtýrýr
							Sound.playSound("explosion");	//Patlama sesi çal
					}
					//--------------------------------------------------------------
					
				}
				//---------------------------------------------------------------------------------
				
				//---------------------- Dusmana Ateþ Ettir ----------------------------------
				DusmanAtesiDelayer++;
				if (DusmanAtesiDelayer > DusmanAtesiDelay) {
					int randomDusman = new Random().nextInt(DusmanUcagi.dusmanSayisi());
					DusmanUcagi atesEdecekDusman = DusmanUcagi.dusmanucaklari.get(randomDusman);
					atesEdecekDusman.atesle();
					DusmanAtesiDelayer = 0;
				}
				//---------------------------------------------------------------------------------
				
				CreateGameArea.myucak.hareket();														//TUÞLARI ALGILATMAK ÝÇÝN			

				DusmanUcagi.hareket();																	//YAPAY ZEKA ÝLE DUSMAN HAREKETI ICIN

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
