import javax.swing.JFrame;
import javax.swing.JLabel;


public class MoveInAreaTest {

	
	public static int ScreenSizeX = 1024;
	public static int ScreenSizeY = 768;
	public static String current_username;
	

	static Thread thread1, thread2;

	static CreateGameArea myMoveInArea;
	
	public static void main(String[] args) {

		oyunuBaslat("asd");//LOGIN OLMADAN OTOMATIK AC
		/*
		Login myMoveInArea=new Login();
		myMoveInArea.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myMoveInArea.setSize(ScreenSizeX,ScreenSizeY);
		myMoveInArea.setVisible(true);
		myMoveInArea.setLocationRelativeTo(null);	//CENTER
		myMoveInArea.setResizable(false);
		*/
	}
	
	
	public static void oyunuBaslat(String username) {
		
		current_username = username;
		
		myMoveInArea=new CreateGameArea();
		myMoveInArea.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myMoveInArea.setSize(ScreenSizeX,ScreenSizeY);
		myMoveInArea.setVisible(true);
		myMoveInArea.setLocationRelativeTo(null);	//CENTER
		myMoveInArea.setResizable(false);

		thread1 = new Thread(new MT(17));
		thread2 = new Thread(new Patlatici(60));
		
		
	}
	
	
	public static void ekle(JLabel eklenecek) {
		
		myMoveInArea.backGround.add(eklenecek);
		myMoveInArea.repaint();
	}
	
	public static void sil(JLabel silinecek) {
		
		myMoveInArea.backGround.remove(silinecek);
		myMoveInArea.repaint();
	}


}
