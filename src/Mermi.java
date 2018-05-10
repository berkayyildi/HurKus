import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Mermi extends JLabel{

	private static final long serialVersionUID = 8289232994291265989L;
	
	public static ArrayList<Mermi> MermilerimArray	= new ArrayList<>();
	public static ArrayList<Mermi> DusmanMermiArray	= new ArrayList<>();

	public Mermi(int StartX , int StartY, boolean updown) {

		if (updown) {	//Mermiyi arraye ekle
			
			this.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("bullet_blue.png")).getImage().getScaledInstance(20, 35, Image.SCALE_DEFAULT)));
			MermilerimArray.add(this);	//TRUE ise benim mermilere ekle
			
		}else {
			
			this.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("bullet_red.png")).getImage().getScaledInstance(20, 35, Image.SCALE_DEFAULT)));
			DusmanMermiArray.add(this);	//FALSE ise düþman mermilerine ekle
			
		}	
		
		setBounds(StartX, StartY, 20, 35);
		
		MoveInAreaTest.ekle(this);	//Mermiyi Görüntüye ekle

	}
	
	
	public int getPosY() {
		return getY();
	}
	public int getPosX() {
		return getX();
	}
	
	
}
