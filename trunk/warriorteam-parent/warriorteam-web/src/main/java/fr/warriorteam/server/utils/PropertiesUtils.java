package fr.warriorteam.server.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/*
 * Created on Jan 24, 2007
 *
 */

public class PropertiesUtils {

	private static final Logger logger = Logger
			.getLogger(PropertiesUtils.class);

	private static final Properties properties = new Properties();

	private PropertiesUtils() {

	}

	/**
	 * Cette m�thode stocke le fichier Properties � l'emplacement sp�cifi�
	 * 
	 * @param props
	 *            Le fichier � stocker
	 * @param fileLocation
	 *            L'emplacement o� le fichier doit �tre stock�
	 * @param comments
	 *            Commentaires � ins�rer en t�te du fichier
	 * @throws FileNotFoundException
	 * @throws IOException
	 *             si une erreur est survenue lors de l'�criture du fichier
	 */
	public void saveProperties(String fileLocation, String comments)
			throws FileNotFoundException, IOException {
		OutputStream out = new FileOutputStream(fileLocation);
		properties.store(out, comments);
		out.flush();
		out.close();

		logger.debug("Ajout des properties dans le fichier" + fileLocation
				+ "commentaire : " + comments);
	}

	/**
	 * Cette m�thode lit un fichier Properties � l'emplacement sp�cifi�
	 * 
	 * @param propertiesFileLocation
	 *            L'emplacemnt du fichier
	 * @return Le fichier Properties charg�
	 * @throws FileNotFoundException
	 *             si le fichier n'a pas �t� trouv�
	 * @throws IOException
	 *             si une erreur est survenue durant la lecture
	 */
	public static void loadProperties(String propertiesFileLocation)
			throws FileNotFoundException, IOException {
		properties.load(new FileInputStream(propertiesFileLocation));
		logger.debug("Properties charg�es");

	}
	

	/**
	 * 
	 * Cette m�thode affiche cahque paire [cl�,valuer] d'un fichier Properties
	 * 
	 * @param props
	 *            Le fichier � afficher
	 */
	public static String getProperties(String key) {
		String propertyValue = properties.getProperty(key);

		if (propertyValue != null) {
			// logger.debug("Property trouv�e : " + key + "=" + propertyValue);
		} else {
			logger.error("Property non trouv�e : " + key);
		}

		return propertyValue;
	}

	/**
	 * Cette m�thode permet de d�marrer la d�mo.
	 * 
	 * On y cr�e un fichier Properties que l'on remplitavec des paires
	 * [cl�,valeur] puis on le stocke sur le disque. Ensuite on le re depuis le
	 * disaue et, enfin, son contenu est affich�.
	 * 
	 * @param args
	 *            non utils�
	 */
	// public static void main(String[] args) {
	// PropertiesUtils demo = new PropertiesUtils();
	//
	// // Emplacement o� sera stock� le fichier
	// String propertiesFileLocation = "d:/myProperties.properties";
	//
	// // On instancie un nouvel objet Properties
	// Properties myProps = new Properties();
	// // On y ins�re des paires [cl�,valeur]
	// myProps.setProperty("user.name", "HackTrack");
	// myProps.setProperty("os.name", "Linux");
	// myProps.setProperty("java.ide", "Eclipse3.2");
	// myProps.setProperty("java.applicationserver.name", "JBoss AS");
	// myProps.setProperty("java.applicationserver.version", "4.0.5");
	// myProps.setProperty("user.function", "Developer");
	// myProps.setProperty("user.age", "You are too curious!");
	//
	// try {
	// // On stocke le fichier sur le disque
	// demo.saveProperties(myProps, propertiesFileLocation,
	// "This is a demo on Properties by HackTrack");
	// // On cr�e un nouvel objet Properties en lisant le fichier sur le
	// // disque
	// Properties loadedProps = demo
	// .loadProperties(propertiesFileLocation);
	// // On affiche le contenu du fichier
	// demo.displayProperties(loadedProps);
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
}
