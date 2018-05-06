import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Mermi extends JLabel{

	private static final long serialVersionUID = 8289232994291265989L;
	
	public static ArrayList<Mermi> MermilerimArray	= new ArrayList<>();
	public static ArrayList<Mermi> DusmanMermiArray	= new ArrayList<>();

	public Mermi(int StartX , int StartY, boolean updown) {
				
		this.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("heart.png")).getImage().getScaledInstance(12, 12, Image.SCALE_DEFAULT)));
		
		setBounds(StartX, StartY, 12, 12);
		
		if (updown) {	//Mermiyi arraye ekle
			MermilerimArray.add(this);	//TRUE ise benim mermilere ekle
		}else {
			DusmanMermiArray.add(this);	//FALSE ise düþman mermilerine ekle
		}	
		
		MoveInAreaTest.ekle(this);	//Mermiyi Görüntüye ekle

	}
	
	
	public int getPosY() {
		return getY();
	}
	public int getPosX() {
		return getX();
	}
	
	
}
