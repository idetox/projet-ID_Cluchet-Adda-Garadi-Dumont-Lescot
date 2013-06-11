package ini1.toi.Projet_ID;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class FenetreNewMorceau extends JFrame implements ActionListener {

		private static final long serialVersionUID = 1L;
		public static final short LONGUEUR = 750;
		public static final short HAUTEUR = 100;
		public static final short CLONG = 150;
		public static final short CHAUT = 20;

		private JPanel pan = new JPanel();
		
		private JTextField artiste = new JTextField();
		private JTextField album = new JTextField();

		private JLabel label1 = new JLabel("Artiste : ");
		private JLabel label2 = new JLabel("Album : ");
		private JLabel labelCombo = new JLabel("Tags");
		private JComboBox<String> comboTag = new JComboBox<String>();
		
		private JButton submit = new JButton("Valider");

		public FenetreNewMorceau() throws IOException,ClassNotFoundException{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Ajouter Morceau");
			setSize(LONGUEUR, HAUTEUR);

			pan.add(label1);
			artiste.setPreferredSize(new Dimension(CLONG, CHAUT));
			pan.add(artiste);

			pan.add(label2);
			album.setPreferredSize(new Dimension(CLONG, CHAUT));
			pan.add(album);
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
				Album A1;
				Morceau M1;
				String tag;
				
				Fenetre.nomAlbum = album.getText();				
				Fenetre.nomArtiste = artiste.getText();
				
				if(Fenetre.nomArtiste.equals("")){
					Fenetre.nomArtiste = "Artiste Inconnu";
				}
				
				tag = comboTag.getSelectedItem().toString();
				
				if(Fenetre.nomAlbum.equals("")){
					Fenetre.nomAlbum = "Album Inconnu";
					M1 = new Morceau(Fenetre.nomArtiste, Fenetre.nomMorceau,Fenetre.nomAlbum, Fenetre.chemin, tag);
					Fenetre.G.getGED().get(0).ajoutMorceau(M1);
				}
				else{
					M1 = new Morceau(Fenetre.nomArtiste, Fenetre.nomMorceau,Fenetre.nomAlbum, Fenetre.chemin, tag);
					if(Fenetre.G.nameAlbumExists(Fenetre.nomAlbum) == null){
						A1 = new Album(Fenetre.nomArtiste, Fenetre.nomAlbum);
						A1.ajoutMorceau(M1);
						Fenetre.G.ajoutAlbum(A1);
					}
					else{
						Fenetre.G.nameAlbumExists(Fenetre.nomAlbum).ajoutMorceau(M1);
					}
					
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