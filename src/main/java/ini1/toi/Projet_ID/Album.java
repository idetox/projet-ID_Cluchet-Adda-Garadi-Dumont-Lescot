package ini1.toi.Projet_ID;
import java.io.Serializable;
import java.util.ArrayList;

/*
 * @Auteurs : Dumont Cluchet Garadi Lescot Adda
 * 
 * Classe Album : 
 * Cette classe decrit les caracteristiques d'un album. 
 * Cette classe herite de la classe Document.
 * Elle est serialisable.
 */
public class Album extends Document implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Morceau> cd;
	
	//Constructeur 1
	@SuppressWarnings("unused")
	private Album(){
		
	}
	
	//Constructeur 2
	public Album(String artiste,String nom){
		super(artiste,nom);
		cd = new ArrayList<Morceau>();
	}
	
	public Morceau getMorceau(String titre){
		for(int i=0;i<cd.size();i++){
			if(cd.get(i).getNom().equals(titre)){
				return cd.get(i);
			}
		}
		return null;
	}
	//Ajoute le morceau M dans l'album
	public void ajoutMorceau(Morceau M){
		if(M != null && !exists(M)){
			cd.add(M);
		}
	}
	
	//Supprime le morceau M dans l'album
	public void supprimeMorceau(Morceau M){
		if(M != null && exists(M)){
			cd.remove(M);
		}
	}
	
	/*
	 * Teste si le morceau M existe
	 * 
	 * @return : boolean selon l'existance du morceau
	 */
	public boolean exists(Morceau M){
		for(int i=0;i<cd.size();i++){
			if(cd.get(i).equals(M)){
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Morceau> getCD(){
		return cd;
	}
	
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder();
		for(int i=0;i<cd.size();i++){
			str.append(cd.get(i).toString()+'\n');
		}
		return str.toString();
	}
}
