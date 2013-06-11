package ini1.toi.Projet_ID;

import java.io.Serializable;

/*
 * @Auteurs : Dumont Cluchet Garadi Lescrot Adda
 * 
 * Classe Morceau : 
 * Cette classe decrit les caracteristiques d'une musique. 
 * Cette classe herite de la classe Document.
 * Elle est serialisable.
 */
public class Morceau extends Document implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nomAlbum;
	private String cheminAcces;
	private String tag;
	
	//Constructeur 1
	@SuppressWarnings("unused")
	private Morceau(){
		
	}
	
	//Constructeur 2
	public Morceau(String artiste,String nom,String nomAlbum,String cheminAcces,String tag){
		super(artiste,nom);
		this.nomAlbum = nomAlbum;
		this.cheminAcces = cheminAcces;
		this.tag = tag;
	}
	
	public String getNomAlbum(){
		return nomAlbum;
	}
	
	//Retourne le chemin ou se trouve un morceau
	public String getCheminAcces(){
		return cheminAcces;
	}
	
	public String getTag(){
		return tag;
	}
	
	public void setTag(String tag){
		this.tag = tag;
	}
	
	@Override
    public boolean equals(Object obj){
        if(obj instanceof Morceau){
            Morceau M = (Morceau) obj;
            return getNom().equals(M.getNom()) && getArtiste().equals(M.getArtiste());
        }
        return false;
    }
	
	@Override 
	public int hashCode(){
		return getNom().hashCode()^getArtiste().hashCode();
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
	    str.append("Artiste = ");str.append(getArtiste());
	    str.append(" Titre = ");str.append(getNom());
	    str.append(" Album = ");str.append(getNomAlbum());
	    str.append(" Chemin = ");str.append(getCheminAcces());
	    return str.toString();
	}
}
