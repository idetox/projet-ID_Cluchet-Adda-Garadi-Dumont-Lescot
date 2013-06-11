package ini1.toi.Projet_ID;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class FenetreAjoutPlaylist extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static final short LONGUEUR = 800;
	public static final short HAUTEUR = 600;
	
	private JPopupMenu jpm = new JPopupMenu();
	private JMenuItem addPlaylist = new JMenuItem("Ajouter dans cette Playlist");
	
	private JTable tableau;
	
	private JPanel banner = new JPanel();
	private JLabel image  = new JLabel(new ImageIcon( "img\\Playlist.jpg"));
	
	private MDPlaylist model = new MDPlaylist();
	
	private String nomPlaylist;
	
	public FenetreAjoutPlaylist() throws IOException,ClassNotFoundException{
		setTitle("Selectionner la Playlist");
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
			@Override
		      public void mouseReleased(MouseEvent e){
		    	  int row = tableau.rowAtPoint(e.getPoint());
		    	  int col = tableau.columnAtPoint(e.getPoint());
		    	  
		    	  nomPlaylist = tableau.getValueAt(row, 0).toString();
		    	  
		         if(e.getButton() == MouseEvent.BUTTON3 && col == 0){ 	       
		        	  jpm.add(addPlaylist);
		        	  jpm.show(tableau, e.getX(), e.getY());
		          }
		        }
		      });
		
		addPlaylist.addActionListener(this);
		
		banner.add(image);
		
		this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		this.getContentPane().add(banner,BorderLayout.SOUTH);
		
		this.setResizable(false);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource() == addPlaylist){
			ArrayList<Morceau> album = Fenetre.G.retrouverSelonAlbum(Fenetre.nomAlbum);
			
			for(int i=0;i<album.size();i++){
				if(album.get(i).getNom().equals(Fenetre.nomMorceau)){
					if(Fenetre.GP.retournePlaylist(nomPlaylist).exists(album.get(i))){
						JOptionPane.showMessageDialog(null, "Fichier déjà dans la playlist", "Erreur", JOptionPane.ERROR_MESSAGE);
					}
					else{
						Fenetre.GP.retournePlaylist(nomPlaylist).ajoutMorceau(album.get(i));
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
			dispose();
		}
		
	}
}