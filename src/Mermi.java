import java.awt.Image;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Mermi extends JLabel{

	private static final long serialVersionUID = 8289232994291265989L;
	
	//Arraylist kullan�rsan java.util.ConcurrentModificationException hatas� al�yorum bunda da umar�m kasma s�k�nt�s� olmaz
	
	public static Vector<Mermi> mermiler = new Vector<>();

	public Mermi(int StartX , int StartY) {
				
		this.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("heart.png")).getImage().getScaledInstance(12, 12, Image.SCALE_DEFAULT)));
		
		setBounds(StartX, StartY, 12, 12);
	}
	
	
	public int getPosY() {
		return getY();
	}
	public int getPosX() {
		return getX();
	}
	
	
}
