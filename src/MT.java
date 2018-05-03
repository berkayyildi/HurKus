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

				//-------------------- Uçaðýn Ýlk Açýlýþta Ekrana Gelmesi ------------------------
				if (isFirstTime) {
					
					CreateGameArea.keys[KeyEvent.VK_W] = true; 
									 
	                if(CreateGameArea.myucak.getPosY() < MoveInAreaTest.ScreenSizeY - (Ucak.size)) {	//Uçak ekrana geldi ise
	                	CreateGameArea.keys[KeyEvent.VK_W] = false;										//Üst tuþu artýk basma
	                	isFirstTime = false;															//First time den çýk
	                } 
					
				}
				//--------------------------------------------------------------------------------
				
				//---------------------- Merminin Hareketi Ýçin ----------------------------------
				for (int i = 0; i<Mermi.mermilerArray.size() ;i++) {
					
					Mermi mermimiz = Mermi.mermilerArray.get(i);
					mermimiz.setLocation(mermimiz.getPosX(),mermimiz.getPosY()-5);
					
					if (mermimiz.getPosY() < 0) {
						 Mermi.mermilerArray.remove(i);	//Arrayden sil hareket etmesin mem de yer kaplamasýn
						 mermimiz.setVisible(false);//Görüntüden de sil (Memory de kalýyor ama !!!)
					}
					
					// ----------- Mermi Dusmana Carptýmý bakmak için  -----------
					for (int j=0; j<DusmanUcagi.dusmanSayisi() ;j++) {
						boolean mermiyle_dusman = CreateGameArea.intersects(mermimiz, DusmanUcagi.dusmanucaklari.get(j));
						if (mermiyle_dusman) {	//Çarpýþtýlarsa
							DusmanUcagi.dusmanucaklari.get(j).patlat();
							if (DusmanUcagi.dusmanucaklari.get(j).icon == 11) {

								MoveInAreaTest.sil(DusmanUcagi.dusmanucaklari.remove(j));
							}
						}
					}
					//--------------------------------------------------------------
					
				}
				//---------------------------------------------------------------------------------
				
				CreateGameArea.myucak.hareket();														//TUÞLARI ALGILATMAK ÝÇÝN

				Thread.sleep(sleepTime);																//10 ms uyu
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
