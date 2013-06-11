package ini1.toi.Projet_ID;
import java.io.Serializable;

public abstract class Document implements Serializable{

	private static final long serialVersionUID = 1L;
	private String artiste;
	private String nom;
	
	public String getArtiste() {
		return artiste;
	}
	
	public String getNom() {
		return nom;
	}
	
	public Document(){
		
	}
	
	public Document(String artiste,String nom){
		this.artiste = artiste;
		this.nom = nom;
	}
	
	public abstract String toString();
}
