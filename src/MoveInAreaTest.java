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

		arkaplan.setLocation(arkaplan.getX(),arkaplan.getY() + arkaplanHizi);		//Arkaplan 1 i aþaðý kaydýr
		arkaplan2.setLocation(arkaplan2.getX(),arkaplan2.getY() + arkaplanHizi);	//Arkaplan 2 yi aþaðý kaydýr
		
		if  (arkaplan.getY() > MoveInAreaTest.ScreenSizeY) {						//Ýlk arkaplan ekran boyutunun altýna düþtüyse
			arkaplan2.setLocation(arkaplan2.getX(), - MoveInAreaTest.ScreenSizeY);	//Ýkinci arkaplaný baþlangýç konumuna al
			arkaplan.setLocation(arkaplan.getX(), 0);								//Ýlk arkaplaný baþlangýç konumuna al	
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
			if (current_username.equals(uname) && ( currscore > Integer.parseInt(dbScore) ) ) {	//Kullanýcýyý arrayde bulduysan ve yeni score daha yüksekse
				Login.kullanicilar.set(i+2, Integer.toString((currscore)));	//Kullanýcý score unu yeni score ile güncelle
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
            fnfex.printStackTrace();	//Dosya yoksa burda hata basýcaktýr olsun bassýn dosyayý create ile yaratýnca normale döner
        }
        catch (IOException ioex) {
            ioex.printStackTrace();
        }
        //---------------------------------------------------
		
		
		
		
		thread1.stop();	//BURDAN SONRASI ÇALIÞMAZ
		thread2.stop();	//BURDAN SONRASI ÇALIÞMAZ
		
		

		
		
	}


}
