package ini1.toi.Projet_ID;
import java.io.*;

/*
 * @Auteurs : Dumont Cluchet Garadi Lescot Adda
 * 
 * Classe Fichier_GED : Cette classe gere l'ouverture du fichier de sauvegarde ainsi que la lecture et l'ecriture dans ce fichier
 */
public class Fichier_GED{

	private ObjectOutputStream oW;
	private ObjectInputStream oR;
	private char mode;
	private String file = new String("music.dat");
	
	/*
	 * Ouvre en lecture ou ecriture selon le mode mis en argument
	 * @return : boolean en fonction de la reussite de l'ouverture
	 */
	public boolean ouvrir(String md){
		mode = md.toUpperCase().charAt(0);
		
		try{
			if(mode == 'L'){
				oR = new ObjectInputStream(new FileInputStream(file));
			}
			else if(mode == 'E'){
				oW = new ObjectOutputStream(new FileOutputStream(file));
			}
			return true;
		}
		catch(IOException e){
			return false;
		}
	}
	
	//Ecrire l'ensemble des metadonnees d'une GED dans un fichier objet
	public void ecrire(GED G)throws IOException{
		if(G != null){
			oW.writeObject(G);
		}
	}
	
	//Stocker la lecture du fichier dans une GED
	public GED lire() throws ClassNotFoundException, IOException{
			GED G = (GED)oR.readObject();
			return G;
	}
	
	//Fermeture du fichier
	public void fermer()throws IOException{
		if(mode == 'L'){
			oR.close();
		}
		else if(mode =='E'){
			oW.close();
		}
	}
	
}
