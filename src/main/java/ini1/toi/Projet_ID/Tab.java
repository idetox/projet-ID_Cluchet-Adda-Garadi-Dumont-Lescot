package ini1.toi.Projet_ID;
import javax.swing.*;

public class Tab {

	private JTable tableau;
	
	public JTable getTableau(){
		return tableau;
	}
	
	public Tab(){
		Object[][] data = {
		};
		String  title[] = {"Artiste", "Titre", "Duree"};
		tableau = new JTable(data,title);
	} 
}
