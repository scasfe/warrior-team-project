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
	 * Cette méthode stocke le fichier Properties à l'emplacement spécifié
	 * 
	 * @param props
	 *            Le fichier à stocker
	 * @param fileLocation
	 *            L'emplacement où le fichier doit être stocké
	 * @param comments
	 *            Commentaires à insérer en tête du fichier
	 * @throws FileNotFoundException
	 * @throws IOException
	 *             si une erreur est survenue lors de l'écriture du fichier
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
	 * Cette méthode lit un fichier Properties à l'emplacement spécifié
	 * 
	 * @param propertiesFileLocation
	 *            L'emplacemnt du fichier
	 * @return Le fichier Properties chargé
	 * @throws FileNotFoundException
	 *             si le fichier n'a pas été trouvé
	 * @throws IOException
	 *             si une erreur est survenue durant la lecture
	 */
	public static void loadProperties(String propertiesFileLocation)
			throws FileNotFoundException, IOException {
		properties.load(new FileInputStream(propertiesFileLocation));
		logger.debug("Properties chargées");

	}

	/**
	 * 
	 * Cette méthode affiche cahque paire [clé,valuer] d'un fichier Properties
	 * 
	 * @param props
	 *            Le fichier à afficher
	 */
	public static String getProperties(String key) {
		String propertyValue = properties.getProperty(key);

		if (propertyValue != null) {
			// logger.debug("Property trouvée : " + key + "=" + propertyValue);
		} else {
			logger.error("Property non trouvée : " + key);
		}

		return propertyValue;
	}

	/**
	 * Cette méthode permet de démarrer la démo.
	 * 
	 * On y crée un fichier Properties que l'on remplitavec des paires
	 * [clé,valeur] puis on le stocke sur le disque. Ensuite on le re depuis le
	 * disaue et, enfin, son contenu est affiché.
	 * 
	 * @param args
	 *            non utilsé
	 */
	// public static void main(String[] args) {
	// PropertiesUtils demo = new PropertiesUtils();
	//
	// // Emplacement où sera stocké le fichier
	// String propertiesFileLocation = "d:/myProperties.properties";
	//
	// // On instancie un nouvel objet Properties
	// Properties myProps = new Properties();
	// // On y insère des paires [clé,valeur]
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
	// // On crée un nouvel objet Properties en lisant le fichier sur le
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
