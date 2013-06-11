package ini1.toi.Projet_ID;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class FenetreAffichagePlaylist extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static final short LONGUEUR = 800;
	public static final short HAUTEUR = 600;
	
	private JTable tableau;
	
	private JPopupMenu jpm = new JPopupMenu();
	private JMenuItem deleteMorceau = new JMenuItem("Supprimer de cette Playlist");
	
	private ModeleDynamique modelD = new ModeleDynamique();
	
	private ArrayList<Morceau> listeMorceau = new ArrayList<Morceau>();
	
	private String namePlaylist;
	private String nomMorceau;
	
	private JPanel banner = new JPanel();
	private JLabel image  = new JLabel(new ImageIcon( "img\\Playlist.jpg"));

	public FenetreAffichagePlaylist(final String nomPlaylist) throws IOException,ClassNotFoundException{
		setTitle("Playlist : " + nomPlaylist);
		setSize(LONGUEUR, HAUTEUR);
		setLayout(new BorderLayout());
		
		listeMorceau = Fenetre.GP.retournePlaylist(nomPlaylist).getContenu();
		
		for(int i = 0;i<listeMorceau.size();i++){
				Morceau M1 = (Morceau) listeMorceau.get(i);
				Data data = new Data(M1.getNom(),M1.getArtiste(),M1.getNomAlbum(),M1.getTag(),new JButton("play"));
				modelD.addData(data);
		}
		tableau = new JTable(modelD);
	    
		this.tableau.setDefaultRenderer(JButton.class, new TableComponent());
		
		tableau.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(java.awt.event.MouseEvent e) {
			int row = tableau.rowAtPoint(e.getPoint());
			int col = tableau.columnAtPoint(e.getPoint());

			String titre = tableau.getValueAt(row, 0).toString();
			
			try {
				if(col == 4)
					WMP.lancer(Fenetre.G.retrouverMorceau(titre).getCheminAcces());
			} catch (IOException e1) {
				System.out.println("Le lecteur est introuvable");
			}
			}});
			
		tableau.addMouseListener(new MouseAdapter(){
			@Override
		      public void mouseReleased(MouseEvent e){
		    	  int row = tableau.rowAtPoint(e.getPoint());
		    	  int col = tableau.columnAtPoint(e.getPoint());
		    	  
		    	  namePlaylist = nomPlaylist;
		    	  nomMorceau = tableau.getValueAt(row, 0).toString();
		    	  
		         if(e.getButton() == MouseEvent.BUTTON3 && col != 4){ 	       
		        	  jpm.add(deleteMorceau);
		        	  jpm.show(tableau, e.getX(), e.getY());
		          }
		        }
		      });
		
		deleteMorceau.addActionListener(this);
		
		banner.add(image);
		this.setResizable(false);
		this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		this.getContentPane().add(banner, BorderLayout.SOUTH);
		this.setVisible(true);
}

	public void actionPerformed(ActionEvent ev) {
		if(ev.getSource() == deleteMorceau){
			
			int compteur = 0;
			
			Playlist P = Fenetre.GP.retournePlaylist(namePlaylist);
			Morceau M = P.morceauSelonNom(nomMorceau);
			
			for(int i = 0;i<P.getContenu().size();i++){
				if(P.getContenu().get(i).getNom().equals(nomMorceau)){
					modelD.removeData(compteur);
				}
				compteur++;
			}
			
			P.supprimeMorceau(M);
			
			if(P.getContenu().size() == 0){
				Fenetre.GP.supprimePlaylist(P);
			}
			
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