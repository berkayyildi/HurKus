import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Mermi extends JLabel{

	private static final long serialVersionUID = 8289232994291265989L;
	
	public static ArrayList<Mermi> mermilerArray = new ArrayList<>();

	public Mermi(int StartX , int StartY) {
				
		this.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("heart.png")).getImage().getScaledInstance(12, 12, Image.SCALE_DEFAULT)));
		
		setBounds(StartX, StartY, 12, 12);
		
		mermilerArray.add(this);	//Mermiyi arraye ekle
		MoveInAreaTest.ekle(this);	//Mermiyi Görüntüye ekle

	}
	
	
	public int getPosY() {
		return getY();
	}
	public int getPosX() {
		return getX();
	}
	
	
}
