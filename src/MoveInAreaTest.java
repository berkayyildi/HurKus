import javax.swing.JFrame;


public class MoveInAreaTest {

	
	public static int ScreenSizeX = 1024;
	public static int ScreenSizeY = 768;
	
	public static void main(String[] args) {

		
		Login myMoveInArea=new Login();
		myMoveInArea.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myMoveInArea.setSize(400,200);
		myMoveInArea.setVisible(true);
		myMoveInArea.setLocationRelativeTo(null);	//CENTER
		myMoveInArea.setResizable(false);
		
		basla();

	}
	
	
	public static void basla() {
		
		CreateGameArea myMoveInArea=new CreateGameArea();
		myMoveInArea.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myMoveInArea.setSize(ScreenSizeX,ScreenSizeY);
		myMoveInArea.setVisible(true);
		myMoveInArea.setLocationRelativeTo(null);	//CENTER
		myMoveInArea.setResizable(false);
		
	}

}
