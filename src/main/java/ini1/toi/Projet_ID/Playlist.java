package ini1.toi.Projet_ID;

import java.io.Serializable;
import java.util.ArrayList;

public class Playlist implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nom;
	private ArrayList<Morceau> contenu;
	
	public String getNom(){
		return nom;
	}
	
	public Playlist(String nom){
		this.nom = nom;
		contenu = new ArrayList<Morceau>();
	}
	
	public void ajoutMorceau(Morceau M){
		if(M != null && !exists(M))
		contenu.add(M);
	}
	
	public void supprimeMorceau(Morceau M){
		if(M != null && exists(M)){
			contenu.remove(M);
		}
	}
	
	public boolean exists(Morceau M){
		for(int i=0;i<contenu.size();i++){
			if(contenu.get(i).equals(M)){
				return true;
			}
		}
		return false;
	}
	
	@Override
    public boolean equals(Object obj){
        if(obj instanceof Playlist){
            Playlist P = (Playlist) obj;
            return getNom().equals(P.getNom());
        }
        return false;
    }
	
	@Override 
	public int hashCode(){
		return getNom().hashCode();
	}
	
	public ArrayList<Morceau> getContenu(){
		return contenu;
	}
	
	public Morceau morceauSelonNom(String nomMorceau){
		for(int i = 0;i<contenu.size();i++){
			if(contenu.get(i).getNom().equals(nomMorceau)){
				return contenu.get(i);
			}
		}
		return null;
	}
}
