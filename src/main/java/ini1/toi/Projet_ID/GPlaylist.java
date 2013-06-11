package ini1.toi.Projet_ID;

import java.io.Serializable;
import java.util.ArrayList;

public class GPlaylist implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nom;
	private ArrayList<Playlist> contenu;
	
	public String getNom(){
		return nom;
	}
	
	public GPlaylist(String nom){
		this.nom = nom;
		contenu = new ArrayList<Playlist>();
	}
	
	public void ajoutPlaylist(Playlist P){
		if(P != null && !exists(P)){
			contenu.add(P);
		}
	}
	
	public void supprimePlaylist(Playlist P){
		if(P != null && exists(P)){
			contenu.remove(P);
		}
	}
	
	public boolean exists(Playlist P){
		for(int i=0;i<contenu.size();i++){
			if(contenu.get(i).equals(P)){
				return true;
			}
		}
		return false;
	}
	
	public Playlist retournePlaylist(String nom){
		for(int i = 0;i<contenu.size();i++){
			if(contenu.get(i).getNom().equals(nom)){
				return contenu.get(i);
			}
		}
		return null;
	}
	
	public ArrayList<Playlist> getContenu(){
		return contenu;
	}
}
