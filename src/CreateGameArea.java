
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class CreateGameArea extends JFrame implements KeyListener {

	

	
	Ucaklar myucak = new Ucak();

	
	public CreateGameArea()	//Sýraya Gore Altta kalan altta oluyor!!
	{
		super("Hurkus");
			
		addKeyListener(this);	//For KeyListener
		setFocusable(true);		//For Keylistener Fix
		setLayout(null);		//Absolute Layout

		add((JLabel)myucak);

		
		JButton button = new JButton("Resume");
		button.setBounds(920, 3, 100, 30);
		add(button);
		
		for (int i=0; i<5; i++) {
			JLabel label_heart = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("heart.png")).getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
			label_heart.setBounds(760+(i*50), 680, 48, 48);
			add(label_heart);
			
		}

		
		JLabel grass_label = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("grass.jpg")).getImage().getScaledInstance(1024, 768, Image.SCALE_DEFAULT)));
		grass_label.setBounds(0, 0, 1024, 768);
		add(grass_label);


	}
	
	
	public void HealthBar(int can) {
		
		
	}


	public void keyPressed(KeyEvent event) {
		
		myucak.hareket(event);
	}	


	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		myucak.stabil();
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}


