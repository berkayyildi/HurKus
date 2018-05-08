import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Area;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class CreateGameArea extends JFrame implements KeyListener {

	private static final long serialVersionUID = -4098657826131334620L;

	public static ArrayList<ImageIcon> explosion_icons = new ArrayList<>();
	public static ArrayList<JLabel> heartArray = new ArrayList<>();
	public static int score = 0;

	JLabel centerText = new JLabel();
	static JLabel topLeftText = new JLabel();
	JLabel SaydamKatman = new JLabel();	//Boþ bir jframe
	JLabel arkaplan = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("background_big.jpg")).getImage()));
	JLabel arkaplan2 = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("background_big.jpg")).getImage()));
	
	public static boolean[] keys = new boolean[256];
	
	static Ucak myucak = new Ucak();
	
	public CreateGameArea()	//Sýraya Gore Altta kalan altta oluyor!!
	{
		super("Hurkus");
		
		dispose(); setUndecorated(true);			//FULLSCREEN
			
		addKeyListener(this);	//For KeyListener
		setFocusable(true);		//For Keylistener Fix
		setLayout(null);		//Absolute Layout

		//Patlama iconlarýný arraye at
		for (int i=1 ; i < 12 ; i++) {	
			explosion_icons.add(new ImageIcon(new ImageIcon(getClass().getResource("explosion/boom" + new DecimalFormat("00").format(i) + ".png")).getImage().getScaledInstance(DusmanUcagi.size, DusmanUcagi.size, Image.SCALE_DEFAULT)));
		}
		
		//-------- KALPLERI EKLE --------
		for (int i=0; i<5; i++) {
			heartArray.add( new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("heart.png")).getImage().getScaledInstance(32, 32, Image.SCALE_DEFAULT))) );
			heartArray.get(i).setBounds((MoveInAreaTest.ScreenSizeX-(32*5)-20)+(i*35), MoveInAreaTest.ScreenSizeY-32, 32, 32);
			SaydamKatman.add(heartArray.get(i));
		}
		//-------------------------------

		SaydamKatman.setBounds(0, 0, MoveInAreaTest.ScreenSizeX,  MoveInAreaTest.ScreenSizeY);
		add(SaydamKatman);		//Saydam Katman Jlabelini Arkaplanýn üstüne eklþe

		arkaplan.setBounds(0, 0, MoveInAreaTest.ScreenSizeX,  MoveInAreaTest.ScreenSizeY);
		add(arkaplan);		//Arkaplaný en Alta Ekle
		
		arkaplan2.setBounds(0, - MoveInAreaTest.ScreenSizeY, MoveInAreaTest.ScreenSizeX,  MoveInAreaTest.ScreenSizeY);	//Bu arkaplan bi arkaplan boyutu üstte yaratýldý
		add(arkaplan2);		//Arkaplaný en Alta Ekle

		SaydamKatman.add(myucak);	//Uçaðýmý Ekle

		JLabel button = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("if_pause.png")).getImage().getScaledInstance(48, 48, Image.SCALE_DEFAULT)));
		button.setBackground(Color.lightGray);
		button.setBounds(954, 5, 90, 25);
		SaydamKatman.add(button);

		
		centerText.setText("<html><font color='red' size='10'>Press Enter to start!</font></html>");
		centerText.setBounds(MoveInAreaTest.ScreenSizeX/2-200, (MoveInAreaTest.ScreenSizeY/2)-10 , 550, 100);
		SaydamKatman.add(centerText);
		
		topLeftText.setText("<html><font color='green' size='8'>0</font></html>");
		topLeftText.setBounds(0, 0, 100, 100);
		SaydamKatman.add(topLeftText);

		
		setVisible(true); //Display the window.
	}
	


	public void keyPressed(KeyEvent e) {
		
		if (centerText.isVisible() && (e.getKeyCode() == KeyEvent.VK_ENTER) ) {
			centerText.setVisible(false);
		
			MoveInAreaTest.thread1.start();
			MoveInAreaTest.thread2.start();
			return;
			}	//Merkez yazý varsa (oyun baþlamadýysa) yazýyý yok et ve oyunu baþlat
		
		keys[e.getKeyCode()] = true;
		
	}	

	@Override
	public void keyReleased(KeyEvent e) {
		
		if(CreateGameArea.keys[KeyEvent.VK_SPACE]){
			myucak.atesle();
		}
		
		keys[e.getKeyCode()] = false;

	}

	@Override
	public void keyTyped(KeyEvent e) {
	
	}
	
	
	
	public static void puanArttir() {
		
		score++;
		topLeftText.setText("<html><font color='green' size='8'>" + score +"</font></html>");
	}
	
	
	public static void vuruldu() {
		
		if(myucak.kalanCan == 0) {
			myucak.patlat();
			return;
		}
		
		MoveInAreaTest.sil(heartArray.get(myucak.kalanCan-1));
		myucak.kalanCan--;
	}
	
	
	
	public static boolean intersects(JLabel testa, JLabel testb){
	    Area areaA = new Area(testa.getBounds());
	    Area areaB = new Area(testb.getBounds());

	    return areaA.intersects(areaB.getBounds2D());
	}
	
		

}


