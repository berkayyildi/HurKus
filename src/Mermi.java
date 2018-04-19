import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Mermi extends JLabel{
	
	public static ArrayList<ImageIcon> mermiler = new ArrayList<>();

	public Mermi(int StartX , int StartY) {
				
		this.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("heart.png")).getImage().getScaledInstance(12, 12, Image.SCALE_DEFAULT)));
		
		setBounds(StartX, StartY, 12, 12);
	}
	
}
