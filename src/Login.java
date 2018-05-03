import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{

    List<String> kullanicilar = new ArrayList<String>();	//Boþ kullanýcýlar arrayi oluþtur

	private static final long serialVersionUID = -3724396567092901733L;
	
	JLabel backGround = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("background.jpg")).getImage().getScaledInstance(1024, 768, Image.SCALE_DEFAULT)));
	JTextField textField=new JTextField();
	JPasswordField passField=new JPasswordField();
	JLabel uname = new JLabel("<html><font color='white' size='5'>User Name:</font></html>", JLabel.LEFT);
	JLabel pass = new JLabel("<html><font color='white' size='5'>Password:</font></html>", JLabel.LEFT);
	JButton btn1 = new JButton("Login");
	JButton newuser = new JButton("New user? Click here and register");

	public Login()	//Sýraya Gore Altta kalan altta oluyor!!
	{
		super("Login");
		
		   
		GetUserList();	//Kullanýcýlarý Güncelle

		dispose(); setUndecorated(true);			//FULLSCREEN

		setFocusable(true);		//For Keylistener Fix
		setLayout(null);		//Absolute Layout

		backGround.setBounds(0, 0, MoveInAreaTest.ScreenSizeX,  MoveInAreaTest.ScreenSizeY);
		add(backGround);

		

		uname.setBounds(250, 400, 200, 20);
		pass.setBounds(250, 470, 200, 20);
		textField.setBounds(360, 385, 280, 50);
		passField.setBounds(360, 460, 280, 50);
		btn1.setBounds(360, 540, 280, 50);
		newuser.setBounds(360, 600, 280, 30);
        
        backGround.add(uname);
        backGround.add(pass);
        backGround.add(textField);
        backGround.add(passField);
        backGround.add(btn1);
        backGround.add(newuser);
        
        btn1.addActionListener(this); 
        newuser.addActionListener(this); 

		setVisible(true); //Display the window.
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	 {
		
	   String uname = textField.getText();
	   @SuppressWarnings("deprecation")
	   String pass = passField.getText(); //Deprecated 
	   
	   JButton button = (JButton)ae.getSource();
       @SuppressWarnings("deprecation")
       String buttonLabel = button.getLabel(); //Deprecated 
	   
	   if (buttonLabel.equals("New user? Click here and register")) {
		   
		   btn1.setText("Register");
		   newuser.setVisible(false);
		   
	   }
	   
	   else if (buttonLabel.equals("Register")) {

			CreateUser(uname,pass,"0");
			btn1.setText("Login");
			newuser.setVisible(true);
			JOptionPane.showMessageDialog(this,"New User " + uname + " Created", "Info",JOptionPane.PLAIN_MESSAGE); 
		   
	   }

	   else if (buttonLabel.equals("Login")) {

		   
			for (int i=0; i<kullanicilar.size(); i+=3) {
				
				String username = kullanicilar.get(i);
				String password = kullanicilar.get(i+1);
				//String score = kullanicilar.get(i+2);
				
				if (username.equals(uname) && password.equals(pass)) {
					 //JOptionPane.showMessageDialog(this,"Welcome " + uname, "Info",JOptionPane.PLAIN_MESSAGE); ,
					 setVisible(false);// Login ekranýný yok et
					 MoveInAreaTest.oyunuBaslat(uname);	//Oyunu kullanýcý adýla baþlat
					 return;
				}
			}
	   		JOptionPane.showMessageDialog(this,"Incorrect login or password", "Error",JOptionPane.ERROR_MESSAGE); //Bulamazsa hatayý basar
	   }
	   
	 }
		


	

	public void CreateUser(String username, String password, String score) {
		
		GetUserList();

        kullanicilar.add(username);	// Verileri ekle
        kullanicilar.add(password);
        kullanicilar.add(score);

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("kullanicilar.ser");
            oos = new ObjectOutputStream(fos);
            oos.writeObject(kullanicilar);
            oos.flush();
            oos.close();
        } 
        catch (FileNotFoundException fnfex) {
            fnfex.printStackTrace();	//Dosya yoksa burda hata basýcaktýr olsun bassýn dosyayý create ile yaratýnca normale döner
        }
        catch (IOException ioex) {
            ioex.printStackTrace();
        }
        
	}

	@SuppressWarnings("unchecked")	//Bunu varsaymak zorundayýz biliyoruz ki istediðimiz tipte bir arraylist gelicek
	public void GetUserList() {

       FileInputStream fis = null;
       ObjectInputStream ois = null;

       try {
           fis = new FileInputStream("kullanicilar.ser");

           ois = new ObjectInputStream(fis);
           
           kullanicilar = (ArrayList<String>) ois.readObject();
       } 
       catch (FileNotFoundException fnfex) {
           fnfex.printStackTrace();
       }
       catch (IOException ioex) {
           ioex.printStackTrace();
       } 
       catch (ClassNotFoundException ccex) {
           ccex.printStackTrace();
       }
		
	}
	
	
}



