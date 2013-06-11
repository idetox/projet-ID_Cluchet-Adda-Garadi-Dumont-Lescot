package ini1.toi.Projet_ID;
 
public class DataPlaylist{
	
    	private String nomPlaylist;
    	private int nbMorceau;
    	
    	public DataPlaylist(String nomPlaylist,int nbMorceau){
    		super();
    		this.setNomPlaylist(nomPlaylist);
    		this.setNbMorceau(nbMorceau);
    	}

		public String getNomPlayList() {
			return nomPlaylist;
		}

		public void setNomPlaylist(String nomPlaylist) {
			this.nomPlaylist = nomPlaylist;
		}

		public int getNbMorceau() {
			return nbMorceau;
		}


		public void setNbMorceau(int nbMorceau) {
			this.nbMorceau = nbMorceau;
		}
}