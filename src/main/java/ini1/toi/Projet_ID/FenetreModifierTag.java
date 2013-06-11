package ini1.toi.Projet_ID;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class FenetreModifierTag extends JFrame implements ActionListener {

		private static final long serialVersionUID = 1L;
		public static final short LONGUEUR = 750;
		public static final short HAUTEUR = 100;
		public static final short CLONG = 150;
		public static final short CHAUT = 20;

		private JPanel pan = new JPanel();

		private JLabel label1;
		private JLabel label2 = new JLabel("  -  Nouveau Style : ");
		private JLabel labelCombo = new JLabel("Tags");
		private JComboBox<String> comboTag = new JComboBox<String>();
		
		private JButton submit = new JButton("Valider");

		public FenetreModifierTag() throws IOException,ClassNotFoundException{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Modifier tag");
			setSize(LONGUEUR, HAUTEUR);

			
			Album A1 = Fenetre.G.getAlbum(Fenetre.nomAlbum);
			Morceau M1 = A1.getMorceau(Fenetre.nomMorceau);
			
			label1  = new JLabel("   Style actuel  : " + M1.getTag());
			
			pan.add(label1);
			pan.add(label2);
			pan.add(comboTag);
			pan.add(submit);
			submit.addActionListener(this);
			
			comboTag.setPreferredSize(new Dimension(100, 20));
			comboTag.add(labelCombo);
			comboTag.addItem("Aucun");
		    comboTag.addItem("Country");
		    comboTag.addItem("Rap");
		    comboTag.addItem("Electro");
		    comboTag.addItem("Pop");
		    comboTag.addItem("Rock");
		    comboTag.addItem("Classique");
		    comboTag.addItem("Autre");
		    
			this.setResizable(false);
			this.add(pan);
			setVisible(true);
		}

		public void actionPerformed(ActionEvent ev) {
			if (ev.getSource() == submit) {
				String tag = comboTag.getSelectedItem().toString();;
				
				Album A1 = Fenetre.G.getAlbum(Fenetre.nomAlbum);
				Morceau M1 = A1.getMorceau(Fenetre.nomMorceau);
				
				M1.setTag(tag);
				
				
				for(int i = 0;i<Fenetre.GP.getContenu().size();i++){
					for(int j = 0; j<Fenetre.GP.getContenu().get(i).getContenu().size();j++){
						if(Fenetre.GP.getContenu().get(i).getContenu().get(j).equals(M1)){
							Fenetre.GP.getContenu().get(i).getContenu().get(j).setTag(tag);
						}
					}
					
				}
				
				Fenetre.fGP.ouvrir("ecriture");
				try {
					Fenetre.fGP.ecrire(Fenetre.GP);
					Fenetre.fGP.fermer();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Fenetre.fGP.ouvrir("ecriture");
				try {
					Fenetre.fGP.ecrire(Fenetre.GP);
					Fenetre.fGP.fermer();
				} catch (IOException e) {
					e.printStackTrace();
				}	
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				Fenetre.fGED.ouvrir("ecriture");
				try {
					Fenetre.fGED.ecrire(Fenetre.G);
					Fenetre.fGED.fermer();
					@SuppressWarnings("unused")
					Fenetre f = new Fenetre();
				} catch (ClassNotFoundException |IOException e) {
					e.printStackTrace();
				}
				dispose();
			}
		}
	}