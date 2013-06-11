package ini1.toi.Projet_ID;
import java.io.IOException;

/*
 * @Auteurs : Dumont Cluchet Garadi Lescot Adda
 * 
 * Classe relative au logiciel Windows Media Player
 */
public class WMP {

	//Lance WMP et joue une musique dont le chemin a ete passe en argument
	public static void lancer(String chemin)throws IOException{
		String[] command = new String[]{"C:\\Program Files (x86)\\Windows Media Player\\wmplayer.exe",chemin};
		Runtime.getRuntime().exec(command);
	}
}