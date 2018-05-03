import java.awt.event.KeyEvent;

public class MT implements Runnable {
	
	private final int sleepTime;
	private boolean isFirstTime = true;

	public MT(int sleepTime) {
		
		this.sleepTime = sleepTime;
		
	}
	
	@Override
	public void run() {
		while(true) {
			try {

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
						 Mermi.mermilerArray.remove(i);	//Arrayden sil hareket etmesin mem de yer kaplamas�n
						 mermimiz.setVisible(false);//G�r�nt�den de sil (Memory de kal�yor ama !!!)
					}
					
					// ----------- Mermi Dusmana Carpt�m� bakmak i�in  -----------
					for (int j=0; j<DusmanUcagi.dusmanSayisi() ;j++) {
						boolean mermiyle_dusman = CreateGameArea.intersects(mermimiz, DusmanUcagi.dusmanucaklari.get(j));
						if (mermiyle_dusman) {	//�arp��t�larsa
							DusmanUcagi.dusmanucaklari.get(j).patlat();
							if (DusmanUcagi.dusmanucaklari.get(j).icon == 11) {

								MoveInAreaTest.sil(DusmanUcagi.dusmanucaklari.remove(j));
							}
						}
					}
					//--------------------------------------------------------------
					
				}
				//---------------------------------------------------------------------------------
				
				CreateGameArea.myucak.hareket();														//TU�LARI ALGILATMAK ���N

				Thread.sleep(sleepTime);																//10 ms uyu
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
