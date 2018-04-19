
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class CreateGameArea extends JFrame implements KeyListener {

	
	public static boolean[] keys = new boolean[256];
	
	Ucaklar myucak = new Ucak();

	
	public CreateGameArea()	//Sýraya Gore Altta kalan altta oluyor!!
	{
		super("Hurkus");
		
		dispose(); setUndecorated(true);			//FULLSCREEN
			
		addKeyListener(this);	//For KeyListener
		setFocusable(true);		//For Keylistener Fix
		setLayout(null);		//Absolute Layout

		add((JLabel)myucak);
		
		//--------------------------------------
		
		Timer timeroyunbasi = new Timer(); 
		timeroyunbasi.schedule( new TimerTask() 
		{ 
		    public void run() {
		    	CreateGameArea.keys[KeyEvent.VK_W] = true;    
                
                if(myucak.ggY() < MoveInAreaTest.ScreenSizeY-150) {
                	CreateGameArea.keys[KeyEvent.VK_W] = false;
                	timeroyunbasi.cancel();
                    return;
                } 
		    
		    } 
		}, 0, 10);
		//--------------------------------------
		
		//--------------------------------------
		Timer timer = new Timer(); 
		timer.schedule( new TimerTask() 
		{ 
		    public void run() {
		    	myucak.hareket();
		    } 
		}, 0, 10);
		//--------------------------------------
		
		JButton button = new JButton("Resume");
		button.setBounds(924, 0, 90, 25);
		add(button);
		
		for (int i=0; i<5; i++) {
			JLabel label_heart = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("heart.png")).getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
			label_heart.setBounds((MoveInAreaTest.ScreenSizeX-(48*5)-20)+(i*50), MoveInAreaTest.ScreenSizeY-48, 48, 48);
			add(label_heart);
			
		}

		
		JLabel grass_label = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("grass.jpg")).getImage().getScaledInstance(1024, 768, Image.SCALE_DEFAULT)));
		grass_label.setBounds(0, 0, 1024, 768);
		add(grass_label);

		pack();
		setVisible(true); //Display the window.
	}
	


	public void keyPressed(KeyEvent e) {
		
		keys[e.getKeyCode()] = true;

	}	


	@Override
	public void keyReleased(KeyEvent e) {
		
		keys[e.getKeyCode()] = false;
		
		myucak.stabil();
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}


