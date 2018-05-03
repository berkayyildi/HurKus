import java.util.Random;

public class MT2 implements Runnable{

	int sleepTime;
	int i=0;
	Random rn = new Random();
	
	public MT2(CreateGameArea myMoveInArea, int level, int sleepTime) {

		this.sleepTime = sleepTime;
		
		for (int i=0; i < (2*level)+1 ; i++) {
			new DusmanUcagi();
		}
		
		


	}
	
	
	
	@Override
	public void run() {

		while(true) {
			try {
				
				i++; if (i > 100) { DusmanUcagi.araMesafe = rn.nextInt(80) + 1;  i=0; } 	//Dusmanlar arasýdnaki mesafeyi random 0-80 arasý deðiþtir
				
				DusmanUcagi.hareket();														//TUÞLARI ALGILATMAK ÝÇÝN

				Thread.sleep(sleepTime);													//17 ms uyu
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

	
}
