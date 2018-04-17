import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class CreateGameArea extends JFrame implements KeyListener {

	Icon jet_icon=new ImageIcon(getClass().getResource("jet.png"));	//BUNLARI TRY CATCH E AL
	JLabel jet_label = new JLabel(jet_icon);
	
	Icon grass_icon=new ImageIcon(getClass().getResource("grass.jpg"));
	JLabel grass_label = new JLabel(grass_icon);
	
	
	public CreateGameArea()	//Sýraya Gore Altta kalan altta oluyor!!
	{
		super("Hurkus");
		
		addKeyListener(this);	//For KeyListener
		setFocusable(true);		//For Keylistener Fix
		setLayout(null);		//Absolute Layout

		
		JButton button = new JButton("Start");
		button.setBounds(5, 5, 500, 30);
		add(button);
		
		
		jet_label.setBounds(400, 526, 200, 200);
		add(jet_label);
		
		
		
		grass_label.setBounds(0, 0, 1024, 768);
		add(grass_label);



	}

	
	
	
	private void changeLayoutLeft()
	{	
			
		
	}
	
	private void changeLayoutRight()
	{	
			
		
	}
	
	private void changeLayoutUp()
	{	
			
		
	}
	private void changeLayoutDown()
	{	
			
		
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		
		System.out.println(jet_label.getX()+","+jet_label.getY());	//KOORDINATLARI LOGLA
		
		String whichKey=KeyEvent.getKeyText(event.getKeyCode());
		int speed = 4;
		if(whichKey.compareTo("Left")==0){
			jet_label.setBounds( jet_label.getX()-speed, jet_label.getY(), 200, 200);
		}else if(whichKey.compareTo("Right")==0){
			jet_label.setBounds( jet_label.getX()+speed, jet_label.getY(), 200, 200);
		}else if(whichKey.compareTo("Up")==0){
			jet_label.setBounds( jet_label.getX(), jet_label.getY()-speed, 200, 200);
		}else if(whichKey.compareTo("Down")==0){
			jet_label.setBounds( jet_label.getX(), jet_label.getY()+speed, 200, 200);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}


}


