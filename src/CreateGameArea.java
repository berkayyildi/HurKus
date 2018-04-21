
import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class CreateGameArea extends JFrame implements KeyListener {

	private static final long serialVersionUID = -4098657826131334620L;
	
	
	JLabel centerText = new JLabel();
	JLabel backGround = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("grass.jpg")).getImage().getScaledInstance(1024, 768, Image.SCALE_DEFAULT)));
	

	public static boolean[] keys = new boolean[256];
	
	Ucak myucak = new Ucak();

	
	public CreateGameArea()	//Sýraya Gore Altta kalan altta oluyor!!
	{
		super("Hurkus");
		
		dispose(); setUndecorated(true);			//FULLSCREEN
			
		addKeyListener(this);	//For KeyListener
		setFocusable(true);		//For Keylistener Fix
		setLayout(null);		//Absolute Layout
		

		backGround.setBounds(0, 0, 1024, 768);
		add(backGround);


		backGround.add(myucak);
		
		//--------------------------------------
		
		Timer timeroyunbasi = new Timer(); 
		timeroyunbasi.schedule( new TimerTask() 
		{ 
		    public void run() {
		    	CreateGameArea.keys[KeyEvent.VK_W] = true;    
                
                if(myucak.getPosY() < MoveInAreaTest.ScreenSizeY - (Ucak.size)) {
                	CreateGameArea.keys[KeyEvent.VK_W] = false;
                	timeroyunbasi.cancel();
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
				repaint();		//EKRANDAKI RESMI SUREKLI YENILE
		    } 
		}, 0, 25);
		//--------------------------------------
		
		//--------------------------------------
		Timer timer4 = new Timer(); 
		timer4.schedule( new TimerTask() 
		{ 
		    public void run() {
				for (int i = 0; i<Mermi.mermiler.size() ;i++) {
					
					Mermi mermimiz = Mermi.mermiler.get(i);
					mermimiz.setLocation(mermimiz.getPosX(),mermimiz.getPosY()-4);
					
					if (mermimiz.getPosY() < 0) {
						 Mermi.mermiler.remove(i);	//Arrayden sil hareket etmesin mem de yer kaplamasýn
						 mermimiz.setVisible(false);//Görüntüden de sil (Memory de kalýyor ama !!!)
					}
					
				}
		    } 
		}, 0, 10);
		//--------------------------------------
		
	
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


		

		pack();
		setVisible(true); //Display the window.
	}
	


	public void keyPressed(KeyEvent e) {
		
		keys[e.getKeyCode()] = true;
		
	}	

	@Override
	public void keyReleased(KeyEvent e) {
		
		keys[e.getKeyCode()] = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {

		if (centerText.isVisible()) {centerText.setVisible(false);}	//Merkez yazý varsa yok et
		
		if(CreateGameArea.keys[KeyEvent.VK_SPACE]){
		
			Mermi solmermi = new Mermi(myucak.getPosX()+32, myucak.getPosY()+40);
			Mermi sagmermi = new Mermi(myucak.getPosX()+102, myucak.getPosY()+40);
			backGround.add(solmermi);
			backGround.add(sagmermi);
			Mermi.mermiler.add(solmermi);
			Mermi.mermiler.add(sagmermi);
		}
		
	}

}


