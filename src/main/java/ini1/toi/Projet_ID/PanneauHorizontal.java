package ini1.toi.Projet_ID;
import java.awt.Graphics;
import javax.swing.JPanel;
  
public class PanneauHorizontal extends JPanel { 
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public void paintComponent(Graphics g){
    //x1, y1, width, height, arcWidth, arcHeight
    g.fillRoundRect(1, 2, 780, 32, 10, 10);
  }               
}         