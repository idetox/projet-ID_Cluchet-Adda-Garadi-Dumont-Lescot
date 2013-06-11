package ini1.toi.Projet_ID;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class Fenetre extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	public static final short LONGUEUR = 800;
	public static final short HAUTEUR = 600;

	private JMenuBar menuBar = new JMenuBar();
	private JMenu field1 = new JMenu("Fichier");
	private JMenuItem item1 = new JMenuItem("Ajouter un Morceau");
	private JMenuItem item2 = new JMenuItem("Ajouter une Playlist");
	
	private JMenu field2 = new JMenu("Playlists");
	private JMenuItem item3 = new JMenuItem("Afficher les Playlists");
	
	private JPopupMenu jpm = new JPopupMenu();
	private JMenuItem mTag = new JMenuItem("Tagger");
	private JMenuItem delete = new JMenuItem("Supprimer Morceau");
	private JMenuItem addPlaylist = new JMenuItem("Ajouter à une playlist");
	  
	private JButton boutonParcourir = new JButton();
	
	private JTable tableau;
	
	private JPanel pan = new JPanel();
	private JLabel tags = new JLabel("Tags",JLabel.CENTER);
	private JButton tagAucun = new JButton("Aucun");
	private JButton tagCountry = new JButton("Country");
	private JButton tagRap = new JButton("Rap");
	private JButton tagElectro = new JButton("Electro");
	private JButton tagPop = new JButton("Pop");
	private JButton tagRock = new JButton("Rock");
	private JButton tagClassique = new JButton("Classique");
	private JButton tagAutre = new JButton("Autre");
	
	private JPanel banner = new JPanel();
	private JLabel portee  = new JLabel(new ImageIcon( "img\\piano.jpg"));
	
	private PanneauHorizontal recherche = new PanneauHorizontal();
	private JTextField champRecherche  = new JTextField();
	private JLabel labelCombo = new JLabel("Type");
	private JComboBox<String> combo = new JComboBox<String>();
	private JButton boutonRecherche = new JButton("Rechercher");
	
	private ModeleDynamique modelD = new ModeleDynamique();

	public static GED G = new GED("Ma ged");
	public static GPlaylist GP = new GPlaylist("Mes Playlists");
	public static Fichier_GED fGED = new Fichier_GED();
	public static FichierGPlaylist fGP = new FichierGPlaylist();
	public static String chemin;
	public static String nomMorceau;
	public static String nomAlbum;
	public static String nomArtiste;

	public Fenetre() throws IOException,ClassNotFoundException{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("GED");
		setSize(LONGUEUR, HAUTEUR);
		this.setLayout(new BorderLayout());
		pan.setLayout(new GridLayout(9,1));//new BoxLayout(pan, BoxLayout.PAGE_AXIS));
        
		if (fGED.ouvrir("lecture")) {
			G = fGED.lire();
			fGED.fermer();
		}
		if (fGP.ouvrir("lecture")) {
			GP = fGP.lire();
			fGP.fermer();
		}
		
		for(int i = 0;i<G.getGED().size();i++){
			Album A1 = (Album) G.getGED().get(i);
			for(int j=0;j<A1.getCD().size();j++){
				Morceau M1 = (Morceau) A1.getCD().get(j);
				Data data = new Data(M1.getNom(),M1.getArtiste(),M1.getNomAlbum(),M1.getTag(),new JButton("play"));
				modelD.addData(data);
			}
		}
		tableau = new JTable(modelD);
		
		this.menuBar.add(field1);
		this.menuBar.add(field2);
		this.field1.add(item1);
		this.field1.add(item2);
		this.field2.add(item3);
		
		combo.setPreferredSize(new Dimension(100, 20));
		combo.add(labelCombo);
	    combo.addItem("Artiste");
	    combo.addItem("Album");
	    champRecherche.setPreferredSize(new Dimension(150, 20));
	    recherche.add(combo);
	    recherche.add(champRecherche);
	    recherche.add(boutonRecherche);
		
		this.tableau.setDefaultRenderer(JButton.class, new TableComponent());
		
		tableau.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			int row = tableau.rowAtPoint(e.getPoint());
			int col = tableau.columnAtPoint(e.getPoint());

			String titre = tableau.getValueAt(row, 0).toString();
			
			try {
				if(e.getButton() != MouseEvent.BUTTON3 && col == 4)
					WMP.lancer(G.retrouverMorceau(titre).getCheminAcces());
			} catch (IOException e1) {
				System.out.println("Le lecteur est introuvable");
			}
		}});
		
		tableau.addMouseListener(new MouseAdapter(){
			@Override
		      public void mouseReleased(MouseEvent e){
		          //Seulement s'il s'agit d'un clic droit
		          //if(event.getButton() == MouseEvent.BUTTON3)
		    	  int row = tableau.rowAtPoint(e.getPoint());
		    	  int col = tableau.columnAtPoint(e.getPoint());
		    	  
		    	  nomMorceau = tableau.getValueAt(row, 0).toString();
		    	  nomAlbum = tableau.getValueAt(row, 2).toString();
		    	  
		          if(e.getButton() == MouseEvent.BUTTON3 && col != 4){ 
		        	  jpm.add(mTag);
		        	  jpm.add(addPlaylist);
		        	  jpm.add(delete);
		        	  jpm.show(tableau, e.getX(), e.getY());
		          }
		        }
		      });
		
		item1.addActionListener(this);
		item2.addActionListener(this);
		item3.addActionListener(this);
		boutonRecherche.addActionListener(this);
		delete.addActionListener(this);
		mTag.addActionListener(this);
		addPlaylist.addActionListener(this);
		
		
		
		tagCountry.setName("Country");
		tagAucun.setName("Aucun");
		tagRap.setName("Rap");
		tagElectro.setName("Electro");
		tagRock.setName("Rock");
		tagClassique.setName("Classique");
		tagAutre.setName("Autre");
		tagPop.setName("Pop");
		
		pan.add(tags);
		pan.add(tagAucun);
		pan.add(tagCountry);
		pan.add(tagRap);
		pan.add(tagElectro);
		pan.add(tagPop);
		pan.add(tagRock);
		pan.add(tagClassique);
		pan.add(tagAutre);
		banner.add(portee);
		
		tagCountry.addActionListener(this);
		tagAucun.addActionListener(this);
		tagRap.addActionListener(this);
		tagElectro.addActionListener(this);
		tagRock.addActionListener(this);
		tagClassique.addActionListener(this);
		tagAutre.addActionListener(this);
		tagPop.addActionListener(this);
		
		this.getContentPane().add(new JScrollPane(tableau), BorderLayout.CENTER);
		this.getContentPane().add(pan,BorderLayout.WEST);
		this.getContentPane().add(recherche,BorderLayout.NORTH);
		this.getContentPane().add(banner,BorderLayout.SOUTH);
		
		this.setResizable(false);
		this.setJMenuBar(menuBar);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent ev) {
		if (ev.getSource() == item1) {
			final JFileChooser fc = new JFileChooser();
			int returnVal = fc.showDialog(boutonParcourir,"Selectionner un morceau a ajouter");
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File f = fc.getSelectedFile();
				chemin = fc.getSelectedFile().getAbsolutePath().toString();
				nomMorceau = f.getName();
				if(getFileExtension(nomMorceau).equals("mp3")){
					dispose();
					try{
						@SuppressWarnings("unused")
						FenetreNewMorceau f1 = new FenetreNewMorceau();
					} catch(IOException | ClassNotFoundException e){
						e.printStackTrace();
					}
				}
				else{
					JOptionPane.showMessageDialog(null, "Le format n'est pas mp3", "Erreur", JOptionPane.ERROR_MESSAGE);	
				}
			}
		}
		else if(ev.getSource() == item2){
			try {
				@SuppressWarnings("unused")
				FenetreNewPlaylist f1 = new FenetreNewPlaylist();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		else if(ev.getSource() == item3){
			try {
				@SuppressWarnings("unused")
				FenetrePlaylist f1 = new FenetrePlaylist();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		else if(ev.getSource() == boutonRecherche){
				dispose();
				switch(combo.getSelectedItem().toString()){
				case "Artiste":
					nomArtiste = champRecherche.getText();
					try {
						@SuppressWarnings("unused")
						FenetreSelonArtiste f1 = new FenetreSelonArtiste();
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					break;
				case "Album":
					nomAlbum = champRecherche.getText();
					try {
						@SuppressWarnings("unused")
						FenetreSelonAlbum f1 = new FenetreSelonAlbum();
					} catch (ClassNotFoundException | IOException e) {
						e.printStackTrace();
					}
					break;
				default:
					break;
				}
		}
		else if(ev.getSource() == mTag){
			dispose();
			
			try {
				@SuppressWarnings("unused")
				FenetreModifierTag f1 = new FenetreModifierTag();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			} 
		}
		else if(ev.getSource() == addPlaylist){
			try {
				@SuppressWarnings("unused")
				FenetreAjoutPlaylist f1 = new FenetreAjoutPlaylist();
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
			
		}
		else if(ev.getSource() == delete){
			
			int compteur = 0;
			
			for(int i = 0;i<G.getGED().size();i++){
				Album A = (Album) G.getGED().get(i);
				for(int j=0;j<A.getCD().size();j++){
					Morceau M = (Morceau) A.getCD().get(j);
					if(M.getNom().equals(nomMorceau) && M.getNomAlbum().equals(nomAlbum)){
						modelD.removeData(compteur);
					}
					compteur++;
				}
			}
			
			Album A1 = G.getAlbum(nomAlbum);
			Morceau M1 = A1.getMorceau(nomMorceau);
			A1.supprimeMorceau(M1);
			
			for(int i = 0;i<GP.getContenu().size();i++){
				if(GP.getContenu().get(i).exists(M1)){
					GP.getContenu().get(i).supprimeMorceau(M1);
				}
				
			}
			
			int a = 0;
			int b = GP.getContenu().size();
			for(int i = 0;i<b;i++){
				if(GP.getContenu().get(a).getContenu().size() == 0){
					GP.getContenu().remove(a);
					a = a - 1;
				}
				a++;				
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
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(ev.getSource() == tagCountry || ev.getSource() == tagPop
				|| ev.getSource() == tagRap || ev.getSource() == tagRock
				|| ev.getSource() == tagClassique ||ev.getSource() == tagAucun
				|| ev.getSource() == tagAutre || ev.getSource() == tagElectro){
			try {
				dispose();
				@SuppressWarnings("unused") 
				FenetreSelonTag fTag = new FenetreSelonTag(((JButton) ev.getSource()).getName());
			} catch (ClassNotFoundException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public String getFileExtension(String NomFichier) {
		File tmpFichier = new File(NomFichier);
		tmpFichier.getName();
		int posPoint = tmpFichier.getName().lastIndexOf('.');
		if (0 < posPoint && posPoint <= tmpFichier.getName().length() - 2 ) {
			return tmpFichier.getName().substring(posPoint + 1);
		}    
		return "";
	}
}