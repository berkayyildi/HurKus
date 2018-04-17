import javax.swing.JFrame;


public class MoveInAreaTest {


	public static void main(String[] args) {

		CreateGameArea myMoveInArea=new CreateGameArea();
		myMoveInArea.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myMoveInArea.setSize(1024,768);
		myMoveInArea.setVisible(true);
		myMoveInArea.setLocationRelativeTo(null);
		
	}

}
