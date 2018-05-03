import java.awt.event.KeyEvent;
import java.util.Random;

public class MT implements Runnable {
	
	private final int sleepTime;
	private boolean isFirstTime = true;
	private int delayer = 0;
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

				delayer++; if (delayer > 100) { DusmanUcagi.araMesafe = new Random().nextInt(80) + 1;  delayer=0; } 		//Dusmanlar aras�dnaki mesafeyi random 0-80 aras� de�i�tir	
				
				
				//-------------------- U�a��n �lk A��l��ta Ekrana Gelmesi ------------------------
				if (isFirstTime) {
					
					CreateGameArea.keys[KeyEvent.VK_W] = true; 
									 
	                if(CreateGameArea.myucak.getPosY() < MoveInAreaTest.ScreenSizeY - (Ucak.size)) {	//U�ak ekrana geldi ise
	                	CreateGameArea.keys[KeyEvent.VK_W] = false;										//�st tu�u art�k basma
	                	isFirstTime = false;															//First time den ��k
	                } 
					
				}
				//--------------------------------------------------------------------------------
				
				//---------------------- Merminin Hareketi ��in ----------------------------------
				for (int i = 0; i<Mermi.mermilerArray.size() ;i++) {
					
					Mermi mermimiz = Mermi.mermilerArray.get(i);
					mermimiz.setLocation(mermimiz.getPosX(),mermimiz.getPosY()-5);
					
					if (mermimiz.getPosY() < 0) {
						 Mermi.mermilerArray.remove(i);
						 MoveInAreaTest.sil(mermimiz);	//RAMden de sil
					}
					
					// ----------- Mermi Dusmana Carpt�m� bakmak i�in  -----------
					for (int j=0; j<DusmanUcagi.dusmanSayisi() ;j++) {
						DusmanUcagi dusmanimiz = DusmanUcagi.dusmanucaklari.get(j);
						boolean mermiyle_dusman = CreateGameArea.intersects(mermimiz, dusmanimiz);
						if (mermiyle_dusman && (!(dusmanimiz.patlayacak)) ) {	//�arp���yolarsa ve daha oncden �arp��t�klar� tespit edilmediyse
							dusmanimiz.patlayacak = true;
							Sound.playSound("explosion");
						}
					}
					//--------------------------------------------------------------
					
				}
				//---------------------------------------------------------------------------------
				
				CreateGameArea.myucak.hareket();														//TU�LARI ALGILATMAK ���N			

				DusmanUcagi.hareket();																	//YAPAY ZEKA ICIN

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
