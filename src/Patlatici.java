public class Patlatici implements Runnable{

	int sleepTime;
	
	public Patlatici(int sleepTime) {
		
		this.sleepTime = sleepTime;

	}
	
	
	@Override
	public void run() {

		while(true) {
			try {
				//Dusmanlarý patlat
				for (int j=0; j<DusmanUcagi.dusmanSayisi() ;j++) {
					
					DusmanUcagi dusmanimiz = DusmanUcagi.dusmanucaklari.get(j);
					if (dusmanimiz.patlayacak) {
						dusmanimiz.patlat(); 
					}
					
				}
				
				//Kendi uçaðýmý patlat
				Ucak ben = CreateGameArea.myucak;
				if (!ben.died) {	//Olmediysem
					if (ben.patlayacak) {ben.patlat(); }
				}

				Thread.sleep(sleepTime);													//17 ms uyu
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	
}
