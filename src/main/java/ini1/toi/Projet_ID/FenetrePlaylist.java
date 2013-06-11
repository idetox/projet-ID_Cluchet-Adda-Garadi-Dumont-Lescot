package ini1.toi.Projet_ID;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class FenetrePlaylist extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static final short LONGUEUR = 800;
	public static final short HAUTEUR = 600;
	
	private JPopupMenu jpm = new JPopupMenu();
	private JMenuItem afficher = new JMenuItem("Afficher Playlist");
	private JMenuItem supprimer = new JMenuItem("Supprimer Playlist");
	
	private JTable tableau;
	
	private JPanel banner = new JPanel();
	private JLabel image  = new JLabel(new ImageIcon( "img\\Playlist.jpg"));
	
	private MDPlaylist model = new MDPlaylist();
	
	private String nomPlaylist;
	
	public FenetrePlaylist() throws IOException,ClassNotFoundException{
		setTitle("Playlists");
		setSize(LONGUEUR, HAUTEUR);
		this.setLayout(new BorderLayout());
		
		for(int i = 0;i<Fenetre.GP.getContenu().size();i++){
			Playlist P = (Playlist) Fenetre.GP.getContenu().get(i);
				DataPlaylist data = new DataPlaylist(P.getNom(),P.getContenu().size());
				model.addData(data);
		}
		tableau = new JTable(model);
		
		this.tableau.setDefaultRenderer(JButton.class, new TableComponent());
		
		tableau.addMouseListener(new MouseAdapter(){
		      public void mouseReleased(MouseEvent e){
		          //Seulement s'il s'agit d'un clic droit
		          //if(event.getButton() == MouseEvent.BUTTON3)
		    	  int row = tableau.rowAtPoint(e.getPoint());
		    	  int col = tableau.columnAtPoint(e.getPoint());
		    	  
		    	  nomPlaylist = tableau.getValueAt(row, 0).toString();
		    	  
		          if(e.getButton() == MouseEvent.BUTTON3 && col == 0){ 
		        	  jpm.add(afficher);
		        	  jpm.add(supprimer);
		        	  jpm.show(tableau, e.getX(), e.getY());
		          }
		        }
		      });
		
		afficher.addActionListener(this);
		supprimer.addActionListener(this);
		
		banner.add(image);
		
		this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		this.getContentPane().add(banner,BorderLayout.SOUTH);
		
		this.setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource() == afficher){
			try {
				@SuppressWarnings("unused")
				FenetreAffichagePlaylist f1 = new FenetreAffichagePlaylist(nomPlaylist);
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			} 
			dispose();
		}
		else if(ev.getSource() == supprimer){
			int compteur = 0;
			
			for(int i = 0;i<Fenetre.GP.getContenu().size();i++){
				Playlist P = (Playlist) Fenetre.GP.getContenu().get(i);
				if(P.getNom().equals(nomPlaylist)){
					model.removeData(compteur);
				}
				compteur++;
			}
			
			Fenetre.GP.supprimePlaylist(Fenetre.GP.retournePlaylist(nomPlaylist));
			Fenetre.fGP.ouvrir("ecriture");
			try {
				Fenetre.fGP.ecrire(Fenetre.GP);
				Fenetre.fGP.fermer();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}
}