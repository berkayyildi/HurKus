import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class MoveInAreaTest {

	static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int user_screen_width	= (int)screenSize.getWidth();
	static int user_screen_height	= (int)screenSize.getHeight();
	
	public static int ScreenSizeX = 1024;
	public static int ScreenSizeY = 768;
	public static int arkaplanHizi = 3;
	public static String current_username;
	
	static Thread thread1, thread2;

	static CreateGameArea myMoveInArea;
	static Login myMoveLogInArea;
	
	public static void main(String[] args) {
		/*
		oyunuBaslat("Berkay");//LOGIN OLMADAN OTOMATIK AC
		*/
		Login myMoveLogInArea=new Login();
		myMoveLogInArea.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myMoveLogInArea.setSize(ScreenSizeX,ScreenSizeY);
		myMoveLogInArea.setVisible(true);
		myMoveLogInArea.setLocationRelativeTo(null);	//CENTER
		myMoveLogInArea.setResizable(false);

	}
	
	
	public static void oyunuBaslat(String username) {
		
		if(Login.fullscreen) {
			ScreenSizeX = user_screen_width;
			ScreenSizeY = user_screen_height;
		}
		
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
		
		CreateGameArea.SaydamKatman.add(eklenecek);
		myMoveInArea.repaint();
	}
	
	public static void sil(JLabel silinecek) {
		
		CreateGameArea.SaydamKatman.remove(silinecek);
		myMoveInArea.repaint();
	}
	
	public static void arkaplaniKaydir() {
		
		JLabel arkaplan = CreateGameArea.arkaplan;
		JLabel arkaplan2 = CreateGameArea.arkaplan2;

		arkaplan.setLocation(arkaplan.getX(),arkaplan.getY() + arkaplanHizi);		//Arkaplan 1 i a�a�� kayd�r
		arkaplan2.setLocation(arkaplan2.getX(),arkaplan2.getY() + arkaplanHizi);	//Arkaplan 2 yi a�a�� kayd�r
		
		if  (arkaplan.getY() > MoveInAreaTest.ScreenSizeY) {						//�lk arkaplan ekran boyutunun alt�na d��t�yse
			arkaplan2.setLocation(arkaplan2.getX(), - MoveInAreaTest.ScreenSizeY);	//�kinci arkaplan� ba�lang�� konumuna al
			arkaplan.setLocation(arkaplan.getX(), 0);								//�lk arkaplan� ba�lang�� konumuna al	
		}

	}
	
	@SuppressWarnings("deprecation")
	public static void oyunuBitir() {
		
		//-----GAME OVER YAZISINI YAZ-----
		JLabel centerText = new JLabel();
		centerText.setText("<html><font color='red' size='10'>Game Over!</font></html>");
		centerText.setBounds(MoveInAreaTest.ScreenSizeX/2-100, MoveInAreaTest.ScreenSizeY/2-50, 250, 100);
		ekle(centerText);
		//--------------------------------
		
		// ---------------- Score update et ----------------
		int currscore = CreateGameArea.score;
		for (int i=0; i<Login.kullanicilar.size(); i+=3) {
			
			String uname = Login.kullanicilar.get(i);
			String dbScore = Login.kullanicilar.get(i+2);
			if (current_username.equals(uname) && ( currscore > Integer.parseInt(dbScore) ) ) {	//Kullan�c�y� arrayde bulduysan ve yeni score daha y�ksekse
				Login.kullanicilar.set(i+2, Integer.toString((currscore)));	//Kullan�c� score unu yeni score ile g�ncelle
			}
		}

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
        	
        	
            fos = new FileOutputStream("kullanicilar.ser");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(Login.kullanicilar);
            oos.flush();
            oos.close();
        } 
        catch (FileNotFoundException fnfex) {
            fnfex.printStackTrace();	//Dosya yoksa burda hata bas�cakt�r olsun bass�n dosyay� create ile yarat�nca normale d�ner
        }
        catch (IOException ioex) {
            ioex.printStackTrace();
        }
        //---------------------------------------------------
		
		
		
		
		thread1.stop();	//BURDAN SONRASI �ALI�MAZ
		thread2.stop();	//BURDAN SONRASI �ALI�MAZ
		
		

		
		
	}


}
