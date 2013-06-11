package ini1.toi.Projet_ID;
import java.awt.Dimension;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class FenetreNewPlaylist extends JFrame implements ActionListener {

		private static final long serialVersionUID = 1L;
		public static final short LONGUEUR = 750;
		public static final short HAUTEUR = 100;
		public static final short CLONG = 150;
		public static final short CHAUT = 20;

		private JPanel pan = new JPanel();
		
		private JTextField field = new JTextField();

		private JLabel label1 = new JLabel("Nom de la playlist : ");
		
		private JButton submit = new JButton("Valider");

		public FenetreNewPlaylist() throws IOException,ClassNotFoundException{
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Ajouter une playlist");
			setSize(LONGUEUR, HAUTEUR);

			pan.add(label1);
			field.setPreferredSize(new Dimension(CLONG, CHAUT));
			pan.add(field);
			pan.add(submit);
			submit.addActionListener(this);
		    
			this.setResizable(false);
			this.add(pan);
			setVisible(true);
		}

		@Override
		public void actionPerformed(ActionEvent ev) {
			if (ev.getSource() == submit) {
				String nom = field.getText();
				Playlist P = new Playlist(nom);
				if(Fenetre.GP.exists(P)){
					JOptionPane.showMessageDialog(null, "La Playlist existe deja !!!", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else{
					Fenetre.GP.ajoutPlaylist(P);
					Fenetre.fGP.ouvrir("ecriture");
					try {
						Fenetre.fGP.ecrire(Fenetre.GP);
						Fenetre.fGP.fermer();
					} catch (IOException e) {
						e.printStackTrace();
					}
					dispose();
				}
			}
		}
	}