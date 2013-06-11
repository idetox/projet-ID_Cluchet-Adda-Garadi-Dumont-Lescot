package ini1.toi.Projet_ID;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;

import javax.swing.*;

public class FenetreSelonArtiste extends JFrame {

	private static final long serialVersionUID = 1L;
	public static final short LONGUEUR = 800;
	public static final short HAUTEUR = 600;
	
	private JTable tableau;
	
	private ModeleDynamique modelD = new ModeleDynamique();
	
	private ArrayList<Morceau> gArtiste = new ArrayList<Morceau>();

	public FenetreSelonArtiste() throws IOException,ClassNotFoundException{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Recherche par Artiste");
		setSize(LONGUEUR, HAUTEUR);
		setLayout(new BorderLayout());
		
		if(Fenetre.nomArtiste.equals("")){
			Fenetre.nomArtiste = "Artiste Inconnu";
		}
		gArtiste = Fenetre.G.retrouverSelonArtiste(Fenetre.nomArtiste);
		
		for(int i = 0;i<gArtiste.size();i++){
				Morceau M1 = (Morceau) gArtiste.get(i);
				Data data = new Data(M1.getNom(),M1.getArtiste(),M1.getNomAlbum(),M1.getTag(),new JButton("play"));
				modelD.addData(data);
		}
		tableau = new JTable(modelD);
	    
		this.tableau.setDefaultRenderer(JButton.class, new TableComponent());
		
		tableau.addMouseListener(new java.awt.event.MouseAdapter() {
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
		
			this.addWindowListener( new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent ev) {
					try {
						@SuppressWarnings("unused")
						Fenetre f1 = new Fenetre();
					} catch (ClassNotFoundException | IOException e1) {
						e1.printStackTrace();
					}
				}
			} );
			
		this.setResizable(false);	
		this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
	}
}
