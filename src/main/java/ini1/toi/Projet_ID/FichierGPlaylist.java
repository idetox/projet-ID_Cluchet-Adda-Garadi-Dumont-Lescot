package ini1.toi.Projet_ID;
import java.io.*;

public class FichierGPlaylist{

	private ObjectOutputStream oW;
	private ObjectInputStream oR;
	private char mode;
	private String file = new String("playlist.dat");
	
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
	public void ecrire(GPlaylist G)throws IOException{
		if(G != null){
			oW.writeObject(G);
		}
	}
	
	//Stocker la lecture du fichier dans une GED
	public GPlaylist lire() throws ClassNotFoundException, IOException{
			GPlaylist G = (GPlaylist)oR.readObject();
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