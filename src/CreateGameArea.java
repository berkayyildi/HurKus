import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class CreateGameArea extends JFrame implements KeyListener {

	private static final long serialVersionUID = -4098657826131334620L;


	
	JLabel centerText = new JLabel();
	JLabel backGround = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("background.jpg")).getImage().getScaledInstance(1024, 768, Image.SCALE_DEFAULT)));

	public static boolean[] keys = new boolean[256];
	
	static Ucak myucak = new Ucak();
	
	public CreateGameArea()	//Sýraya Gore Altta kalan altta oluyor!!
	{
		super("Hurkus");

		
		dispose(); setUndecorated(true);			//FULLSCREEN
			
		addKeyListener(this);	//For KeyListener
		setFocusable(true);		//For Keylistener Fix
		setLayout(null);		//Absolute Layout

		
		for (int i=1 ; i < 12 ; i++) {	//Patlama iconlarýný arraye at
			DusmanUcagi.explosion_icons.add(new ImageIcon(new ImageIcon(getClass().getResource("explosion/boom" + new DecimalFormat("00").format(i) + ".png")).getImage().getScaledInstance(DusmanUcagi.size, DusmanUcagi.size, Image.SCALE_DEFAULT)));
		}
		

		backGround.setBounds(0, 0, MoveInAreaTest.ScreenSizeX,  MoveInAreaTest.ScreenSizeY);
		add(backGround);

		backGround.add(myucak);

	
		JLabel button = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("if_pause.png")).getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		button.setBackground(Color.lightGray);
		button.setBounds(954, 5, 90, 25);
		backGround.add(button);

		
		centerText.setText("<html><font color='red' size='10'>Press any key to start!</font></html>");
		centerText.setBounds(MoveInAreaTest.ScreenSizeX/2-200, MoveInAreaTest.ScreenSizeY/2, 500, 100);
		backGround.add(centerText);
		
		
		for (int i=0; i<5; i++) {
			JLabel label_heart = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("heart.png")).getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
			label_heart.setBounds((MoveInAreaTest.ScreenSizeX-(48*5)-20)+(i*50), MoveInAreaTest.ScreenSizeY-48, 48, 48);
			backGround.add(label_heart);
			
		}

		
		setVisible(true); //Display the window.
	}
	


	public void keyPressed(KeyEvent e) {
		
		if (centerText.isVisible() && (e.getKeyCode() == KeyEvent.VK_ENTER) ) {
			centerText.setVisible(false);
		
			MoveInAreaTest.thread1.start();
			MoveInAreaTest.thread2.start();

			}	//Merkez yazý varsa (oyun baþlamadýysa) yazýyý yok et ve oyunu baþlat
		
		keys[e.getKeyCode()] = true;
		
	}	

	@Override
	public void keyReleased(KeyEvent e) {
		
		keys[e.getKeyCode()] = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {

		if(CreateGameArea.keys[KeyEvent.VK_SPACE]){
		
			new Mermi(myucak.getPosX()+32, myucak.getPosY()+40);
			new Mermi(myucak.getPosX()+102, myucak.getPosY()+40);
			Sound.playSound("fire");

			
		}
	
	}
	
	
	public static boolean intersects(JLabel testa, JLabel testb){
	    Area areaA = new Area(testa.getBounds());
	    Area areaB = new Area(testb.getBounds());

	    return areaA.intersects(areaB.getBounds2D());
	}
	
		

}


