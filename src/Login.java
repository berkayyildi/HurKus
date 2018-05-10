import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{

    public static List<String> kullanicilar = new ArrayList<String>();	//Boþ kullanýcýlar arrayi oluþtur

	private static final long serialVersionUID = -3724396567092901733L;
	
	public static boolean fullscreen = false;
	String topScoresHtml = "";
	JLabel backGround = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("background_big.jpg")).getImage().getScaledInstance(1024, 768, Image.SCALE_DEFAULT)));
	JTextField textField=new JTextField();
	JPasswordField passField=new JPasswordField();
	JLabel loginText = new JLabel("<html><font color='white' size='7'>Login</font></html>", JLabel.LEFT);
	JLabel regisText = new JLabel("<html><font color='white' size='7'>Register</font></html>", JLabel.LEFT);
	JLabel unameText = new JLabel("<html><font color='white' size='5'>User Name:</font></html>", JLabel.LEFT);
	JLabel passText = new JLabel("<html><font color='white' size='5'>Password:</font></html>", JLabel.LEFT);
	JButton btn1 = new JButton("Login");
	JButton newuser = new JButton("New user? Click here and register");
	JCheckBox checkBox1 = new JCheckBox("Full Screen");  

	public Login()	//Sýraya Gore Altta kalan altta oluyor!!
	{
		super("Login");
		   
		GetUserList();	//Kullanýcýlarý Güncelle

		dispose(); setUndecorated(true);			//FULLSCREEN

		setFocusable(true);		//For Keylistener Fix
		setLayout(null);		//Absolute Layout
		
		
		
		for (int i=0; i<kullanicilar.size(); i+=3) {
			
			String username = kullanicilar.get(i);
			//String password = kullanicilar.get(i+1);
			String score = kullanicilar.get(i+2);
			topScoresHtml += "<font color='white'><tr><td>" + username + " </td><td align=\"center\"> " +  score + "</td></tr></font>";
			//topScoresHtml += "<font color='white'><tr><td>" + username + "|" + password + " </td><td align=\"center\"> " +  score + "</td></tr></font>";
		}
		
		
		JLabel scoreboardTemplete = new JLabel("<html><table border=\"0\" cellpadding=\"5\" cellspacing=\"0\">"
				+"<tr><font size='4' color='white'>Username</font></td><font size='4' color='white'>Score</font><tr></b></td></tr>"
				+ "<font color='white' size='3'>"
				+ topScoresHtml
				+ "</table></html>", JLabel.LEFT);
		

		backGround.setBounds(0, 0, MoveInAreaTest.ScreenSizeX,  MoveInAreaTest.ScreenSizeY);
		add(backGround);

		loginText.setBounds	(450, 320, 200, 55);
		regisText.setBounds	(430, 320, 200, 55);
		unameText.setBounds	(250, 400, 200, 20);
		passText.setBounds	(250, 470, 200, 20);
		textField.setBounds	(360, 385, 280, 50);
		passField.setBounds	(360, 460, 280, 50);
		btn1.setBounds		(360, 540, 280, 50);
		newuser.setBounds	(360, 600, 280, 30);
		checkBox1.setBounds	(360, 650, 100, 30);
		
		
		scoreboardTemplete.setBounds(700, 100, 400, 500);
        
		backGround.add(loginText);
		backGround.add(regisText);
        backGround.add(unameText);
        backGround.add(passText);
        backGround.add(textField);
        backGround.add(passField);
        backGround.add(btn1);
        backGround.add(newuser);
        backGround.add(checkBox1);
        backGround.add(scoreboardTemplete);
        
        checkBox1.addItemListener(new ItemListener() {
		    @Override
		    public void itemStateChanged(ItemEvent e) {
		        if(e.getStateChange() == ItemEvent.SELECTED) {	//checkbox selected
		        	fullscreen = true;
		        } else {										//checkbox deselected
		        	fullscreen = false;
		        };
		    }
		});
        
        regisText.setVisible(false);	//Register Yazýsý Gizli
        
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
       
       if (uname.isEmpty() || pass.isEmpty() ) {
    	   
    	   JOptionPane.showMessageDialog(this,"Username or Password should be entered!", "Error",JOptionPane.PLAIN_MESSAGE); 
    	   return;
       }
	   
       else if (buttonLabel.equals("New user? Click here and register")) {
		   
		   
		   regisText.setVisible(true);	//Register olunmak isteniyo register yaz
		   loginText.setVisible(false);	//Register olunmak isteniyo register yaz
		   
		   btn1.setText("Register");
		   newuser.setVisible(false);
		   
	   }
	   
	   else if (buttonLabel.equals("Register")) {


		   regisText.setVisible(false);	//Register edildi artýk login yaz
		   loginText.setVisible(true);	//Register edildi artýk login yaz

		   CreateUser(uname,pass,"0");	//KAYIT ET
		   btn1.setText("Login");
		   newuser.setVisible(true);
		   
		   
	   }

	   else if (buttonLabel.equals("Login")) {

		   
			for (int i=0; i<kullanicilar.size(); i+=3) {
				
				String username = kullanicilar.get(i);
				String password = kullanicilar.get(i+1);
				//String score = kullanicilar.get(i+2);
				
				if (username.equals(uname) && password.equals(MD5(pass))) {
					 JOptionPane.showMessageDialog(this,"Welcome " + uname, "Info",JOptionPane.PLAIN_MESSAGE);
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
		
		for (int i=0; i<kullanicilar.size(); i+=3) {
			
			String unameForExistence = kullanicilar.get(i);
			if (username.equals(unameForExistence)) {
				JOptionPane.showMessageDialog(this,"This username already taken!", "Error",JOptionPane.ERROR_MESSAGE); //Bulamazsa hatayý basar
				return;
			}
		}
		
		JOptionPane.showMessageDialog(this,"New User " + username + " Created", "Info",JOptionPane.PLAIN_MESSAGE); //Kullanýcý oluþturuldu mesajý göster

        kullanicilar.add(username);	// Verileri ekle
        kullanicilar.add(MD5(password));
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
	
	
	
	public String MD5(String md5) {	//MD5 ile þifre depolamak için
		   try {
		        java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
		        byte[] array = md.digest(md5.getBytes());
		        StringBuffer sb = new StringBuffer();
		        for (int i = 0; i < array.length; ++i) {
		          sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
		       }
		        return sb.toString();
		    } catch (java.security.NoSuchAlgorithmException e) {
		    }
		    return null;
		}

	
	
}



