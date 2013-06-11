package ini1.toi.Projet_ID;

import java.io.Serializable;
import java.util.ArrayList;

/*
 * @Auteurs : Dumont Cluchet Garadi Lescot Adda
 * 
 * Classe GED : 
 * Cette classe decrit les caracteristiques d'une GED.
 * Gere l'ensemble de notre bibliotheque musicale
 * Elle est serialisable.
 */
public class GED implements Serializable{

	private static final long serialVersionUID = 1L;
	private ArrayList<Album> ged;
	private String nomGed;
	
	//Constructeur 1
	public GED(){
		
	}
	
	//Constructeur 2
	public GED(String nomGed){
		this.nomGed = nomGed;
		ged = new ArrayList<Album>();
		ged.add(new Album(null,"Album Inconnu"));
	}
	
	public String getNomGed(){
		return nomGed;
	}
	
	public ArrayList<Album> getGED(){
		return ged;
	}
	
	//Ajoute un album à la GED
	public void ajoutAlbum(Album A){
		if(A != null && !exists(A)){
			ged.add(A);
		}
	}
	
	public int getPosition(Album A){
		for(int i=0;i<ged.size();i++){
			if(A.equals(ged.get(i))){
				return i;
			}
		}
		return -1;
	}
	
	//Supprime un album de la GED
	public void supprimeAlbum(Album A){
		if(A != null && exists(A)){
			ged.remove(A);
		}
	}
	
	/*
	 * Cherche si l'album entre en argument existe dans la GED
	 * 
	 * @return : Album que l'on souhaite trouver
	 */
	public Album getAlbum(String nomAlbum){
		for(int i=0;i<ged.size();i++){
			Album A1 = (Album) ged.get(i);
			if(A1.getNom().equals(nomAlbum)){
				return A1;
			}
		}
		return null;
	}
	
	public ArrayList<Morceau> retrouverSelonAlbum(String nomAlbum){
		ArrayList<Morceau> liste = new ArrayList<Morceau>();
		for(int i=0;i<ged.size();i++){
			Album A1 = (Album) ged.get(i);
			for(int j=0;j<A1.getCD().size();j++){
				Morceau M1 = (Morceau) A1.getCD().get(j);
				if(M1.getNomAlbum().equals(nomAlbum)){
					liste.add(M1);
				}
			}
		}
		return liste;
	}
	
	public ArrayList<Morceau> retrouverSelonTag(String tag){
		ArrayList<Morceau> liste = new ArrayList<Morceau>();
		for(int i=0;i<ged.size();i++){
			Album A1 = (Album) ged.get(i);
			for(int j=0;j<A1.getCD().size();j++){
				Morceau M1 = (Morceau) A1.getCD().get(j);
				if(M1.getTag().equals(tag)){
					liste.add(M1);
				}
			}
		}
		return liste;
	}
	
	/*
	 * Cherche si le morceau entre en argument existe dans la GED
	 * 
	 * @return : Morceau que l'on souhaite trouver 
	 */
	public Morceau retrouverMorceau(String nomMorceau){
		for(int i=0;i<ged.size();i++){
			Album album = (Album)ged.get(i);
			for(int j=0;j<album.getCD().size();j++){
				Morceau tmp = (Morceau)album.getCD().get(j);
				if(tmp.getNom().equals(nomMorceau)){
					return tmp;
				}
			}
		}
		return null;
	}
	
	/*
	 * Cherche ce que l'artiste mis en argument a compose comme morceau
	 * 
	 * @return : Une liste de morceau avec le même artiste 
	 */
	public ArrayList<Morceau> retrouverSelonArtiste(String nomArtiste){
		ArrayList<Morceau> liste = new ArrayList<Morceau>();
		for(int i=0;i<ged.size();i++){
			Album A1 = (Album) ged.get(i);
			for(int j=0;j<A1.getCD().size();j++){
				Morceau M1 = (Morceau) A1.getCD().get(j);
				if(M1.getArtiste().equals(nomArtiste)){
					liste.add(M1);
				}
			}
		}
		return liste;
	}
	
	public boolean exists(Album A){
		for(int i=0;i<ged.size();i++){
			if(ged.get(i).equals(A)){
				return true;
			}
		}
		return false;
	}
	
	public Album nameAlbumExists(String nomAlbum){
		for(int i=0;i<ged.size();i++){
			if(ged.get(i).getNom().equals(nomAlbum)){
				return ged.get(i);
			}
		}
		return null;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		for(int i=0;i<ged.size();i++){
			str.append(ged.get(i).toString());
		}
		return str.toString();
	}
}
