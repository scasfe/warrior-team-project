package fr.warriorteam.server.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * TODO
 * 
 * @author Yvan Date : 13/01/2012
 */
public class ImagesUtils {

	/**
	 * Max width pour les images
	 */
	private static final int MAX_WIDTH = 320;
	private static final int MAX_HEIGHT = 240;

	public static void copyWithRedimImage(File file, String path) {
		try {

			// Création du dossier resize s'il n'existe pas
			File directory = new File(
					PropertiesUtils.getProperties("path_file_images") + path
							+ "/resize");
			directory.mkdir();

			// Exemple pour agrandir
			// BufferedImage img = ImageIO.read(new File("c:/j.png"));
			// BufferedImage imgnew = scale(img, 10);
			// ImageIO.write(imgnew, "png", new File("c:/jgrand.png"));

			// Exemple de reduction
			BufferedImage imag = ImageIO.read(file);
			int widht = imag.getWidth();
			int height = imag.getHeight();
			double factor = calculerCoeffRedimImage(widht, height);

			BufferedImage imagnew = scale(imag, factor);
			ImageIO.write(imagnew, "png",
					new File(PropertiesUtils.getProperties("path_file_images")
							+ path + "/resize/" + file.getName()));
		} catch (IOException ex) {
			// Logger.getLogger(ImageRedim.class.getName()).log(Level.SEVERE,
			// null, ex);
		}

	}

	private static BufferedImage scale(BufferedImage bImage, double factor) {
		int destWidth = (int) (bImage.getWidth() * factor);
		int destHeight = (int) (bImage.getHeight() * factor);
		// créer l'image de destination
		// GraphicsConfiguration configuration = GraphicsEnvironment
		// .getLocalGraphicsEnvironment().getDefaultScreenDevice()
		// .getDefaultConfiguration();
		// BufferedImage bImageNew = configuration.createCompatibleImage(
		// destWidth, destHeight);

		BufferedImage newImage = new BufferedImage(destWidth, destHeight, 1);
		Graphics2D graphics = newImage.createGraphics();
		graphics.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
		// dessiner l'image de destination

		graphics.drawImage(bImage, 0, 0, destWidth, destHeight, 0, 0,
				bImage.getWidth(), bImage.getHeight(), null);
		graphics.dispose();

		return newImage;
	}

	public static double calculerCoeffRedimImage(int width, int height) {
		// TODO Mettre le code de redimensionnement avec solution pour getter le
		// coeff de reduction

		double coeff = 1;
		double coeefHeight = 1;
		// taille reelle de l'image
		// int width = imageEnCours.getWidth();
		// int height = imageEnCours.getHeight();

		// si portait
		if (height > width) {
			if (height > MAX_HEIGHT) {
				coeff = (double) MAX_HEIGHT / (double) height;
				height = MAX_HEIGHT;
				width *= coeff;
				// cas ou le largeur est superieure encore a MAX_WIDTH
				if (width > MAX_WIDTH) {
					double coeff2 = (double) MAX_WIDTH / (double) width;
					width = MAX_WIDTH;
					height *= coeff2;

					coeff *= coeff2;

				}
			}
		} else {
			// paysage
			if (width > MAX_WIDTH) {
				coeff = (double) MAX_WIDTH / (double) width;
				width = MAX_WIDTH;
				height *= coeff;
				// cas ou la hauteur est superieure encore a MAX_HEIGHT
				if (height > MAX_HEIGHT) {
					double coeff2 = (double) MAX_HEIGHT / (double) height;
					height = MAX_HEIGHT;
					width *= coeff2;

					coeff *= coeff2;

				}
			}
		}

		return coeff;

	}
}
