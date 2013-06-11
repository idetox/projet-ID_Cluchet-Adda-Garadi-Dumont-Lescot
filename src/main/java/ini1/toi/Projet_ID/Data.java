package ini1.toi.Projet_ID;
import javax.swing.JButton;
 
public class Data{
	
    	private String morceau;
    	private String artiste;
    	private String album;
    	private String style;
    	private JButton bouton;
    	
    	
    	
    	
    	public Data(String morceau,String artiste,String album,String style,JButton bouton){
    		super();
    		this.setMorceau(morceau);
    		this.setArtiste(artiste);
    		this.setAlbum(album);
    		this.setStyle(style);
    		this.setBouton(bouton);
    	}





		public String getMorceau() {
			return morceau;
		}




		public void setMorceau(String morceau) {
			this.morceau = morceau;
		}




		public String getArtiste() {
			return artiste;
		}




		public void setArtiste(String artiste) {
			this.artiste = artiste;
		}




		public String getAlbum() {
			return album;
		}




		public void setAlbum(String album) {
			this.album = album;
		}




		public JButton getBouton() {
			return bouton;
		}




		public void setBouton(JButton bouton) {
			this.bouton = bouton;
		}
		
		
		public String getStyle(){
			return style;
		}
		
		public void setStyle(String style){
			this.style = style;
		}
}