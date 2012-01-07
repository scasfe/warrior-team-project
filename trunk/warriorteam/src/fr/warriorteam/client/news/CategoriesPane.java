package fr.warriorteam.client.news;

import fr.warriorteam.client.WTVerticalPane;

import com.google.gwt.user.client.ui.HTML;

/**
 * Entry point classes define <code>onModuleLoad()</code>. Classe d'entrée
 * affichant un root panel permettant de s'identifier
 */
public class CategoriesPane extends WTVerticalPane {

	private static CategoriesPane instance;

	private CategoriesPane() {

	}

	/**
	 * Méthode permettant de créer le panel en tant que singleton
	 * 
	 * @return instance du panel
	 */
	public static CategoriesPane getInstance() {
		if (instance == null) {
			instance = new CategoriesPane();
			setup();

		}
		loadDynamicData();
		return instance;
	}

	private static void loadDynamicData() {
		// TODO Auto-generated method stub

		// Check de session avant tout

	}

	private static void setup() {

		// Le conteneur d' images

		// images.setBorderWidth(1);
		instance.getElement().setId("corps");

		// Une photo
		instance.add(new HTML(
				"CATEGORIES PANE"
						+ "<br><br>"
						+ "hfdsjdsjhdsf kffffffffffffffffffffffffffffff ffffffffffffffff fffffffff fffffffffffffff ffffffffffffffffffffff ffretretert ertretrretr etertertretre"
						+ "hfdsjd  sjhdsfkfffffffffff ff ffffffffff fffffffffffffff fffffffffffffffffffff fffffffffffffffff ffffffffffffff fffffretre tertertret retretertert retre"
						+ "hfdsjdsjhdsf kffff ffffffffffffff ffffff ffffffff fffffff fffffffffff  fffffffff fffffffffff fffffff ffffffffffffffffffretretert ertretrretretertertretre"
						+ "hfdsjdsjhdsfkffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff ff fffffff fffretretertertretrretre ertertretre"
						+ "hfdsjd sjhdsfkffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff fffffffffffffffffffffffffffffffret etertertretrretrete r tertretre"
						+ "hfdsjd sjhdsfkffffffffffffffffffffffffffffffffff fffffffffffffffff fffffffffffffffffff ffffffffffffffffffffffffretr etertertretrretretertertretre"
						+ "hfdsjdsjhdsfkffffffffffffffffffffffff ffffffffffffffffffffffffffffffffffffffffffffffffffffffff fffffffffffffffretretertertre trretret ertertretre"
						+ "hfdsjdsjh dsfk fffffffffffffff fff  fffffffffffffffffffffffffff ffffffffffffffffffffffffffffffffffffffffffffffffretretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkfffffffffffffffffffffffffffffffffffffffffffffffff fff fffffffffffffffffffffffffffffffffffffffffretretertertret r retretertertretre"
						+ "hfdsjdsjhdsfkffffffffffffffff fffffffffffffffffffffffffffffffffffffff fffffffffffffffffffffffffffffffffffffffretretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkfffffffffffffff fffffffffffffffff fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffretretertertretrre tretertertretre"
						+ "hfdsjds jhdsfkfffffffffffffff fffffffffffffff  fffffffffffffffffffffffffffffff ffffffffffffffffffffffffffffffffffretretertertretrretret rtertretre"
						+ "hfdsjdsjhds kffffffffffffff fffffffffffffff ffffffffffffffffffffffffffffffffffffffff ffffffff fffffff ffffffffr etreterter tretrr etretertertretre"
						+ "hfdsjdsjhdsfkff fffff ffff fffffffffffffff ffffffff fffffff fffffffff ffffffffffffffff fffffffffffffffff fffffffffretretert ertret rretretertertretre"
						+ "hfdsjdsjhdsfkffffffffffffffffffffffffffffffffff fffffffffffffffffffffffffffffffffffffff ffffffffffffffffffffffretretertertretrre  tretertertretre"
						+ "hfdsjdsjhdsfkffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff fffffffffffff fffff fffffffffffffffretretertertretrretretertertretre"
						+ "hfdsjd sjhdsfkffffffff fffffffffffffffffffffffffffffff ffffff fffffffff fffffffffffffffff fffffffff ffffffffffffffretretertertretr retretertertretre"
						+ "hfdsjd sjhdsfkffff fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff ffffffffffffffret retertertretrretretertertretre"
						+ "hfdsjdsjhds fkff fffffff ffffffff fffffffff fffffffffffffffff fffffffffffffffffffffffffff fffffffffffffffffffffffffretreterter tre trretretertertretre"
						+ "hfdsjd dsjhdsfkffffffffffffffffffffffffffff  fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffretreterte rt retrretretertertretre"
						+ "hfdsjd sjhdsfkffffffffffffffffffffffffffffff fffffffffffff fffffffffffffffffffffffffffffffffffff  fffffffffffffffretretertertretrret ertertretre"
						+ "hfdsj  dsjhdsfkfffffff ffffffffffff ff ff fffffff ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffretretertertretrretre tertertretre"
						+ "hfdsjdsjhdsfkffffffffffffff ffffffffffffff fffffffffffff ffffffffffffffffffffffffffffffffffffffffffffffffffffretretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkfffffffffffffffffffffffffffffffffffffffffffffffffffff ffffffffffffffffffffffffffffffffffffffffffretretertertretrret retertertretre"
						+ "hfdsjdsjhdsfkffffffffffffffffffffffffffffffffffffff fffffffffffffffffffffffff fffffffff fff ffffffffffffffffffffretre ertertretrr etretertertretre"
						+ "hfdsj dsjhdsfkfffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffretretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkff fffffffff fffffff ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffre tretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkfffffffffffffffffffffffffffffffff fffffffffffffffffff fffffff fffffffffff ffff ffffff fff ffffffretretertertretrretretertertretre"
						+ "hf dsjdsjhdsfkff  ffff ffffffffffff fffffff ffffffffff  ffffffffff ffffffffffffffffffffffffff fffffffffffffffff  fffff tertretrretretertertretre"
						+ "hfdsjdsjhd sfkfffffffffffff fffff ffffffff ffffffff f ffffffffffff ffffffffffffffffffffffffffffffffffffffffffffffffretretertertr etrretretertertretre"
						+ "hfdsjdsjhdsfkffffffffff fffffffffffffffffffffffffffff fffffff ffff ffffffffffffffffffffffff ffffffffffffffffffffretretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkff fffffffff fffffff ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffre tretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkfffffffffffffffffffffffffffffffff fffffffffffffffffff fffffff fffffffffff ffff ffffff fff ffffffretretertertretrretretertertretre"
						+ "hf dsjdsjhdsfkff  ffff ffffffffffff fffffff ffffffffff  ffffffffff ffffffffffffffffffffffffff fffffffffffffffff  fffff tertretrretretertertretre"
						+ "hfdsjdsjhd sfkfffffffffffff fffff ffffffff ffffffff f ffffffffffff ffffffffffffffffffffffffffffffffffffffffffffffffretretertertr etrretretertertretre"
						+ "hfdsjdsjhdsfkffffffffff fffffffffffffffffffffffffffff fffffff ffff ffffffffffffffffffffffff ffffffffffffffffffffretretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkff fffffffff fffffff ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffre tretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkfffffffffffffffffffffffffffffffff fffffffffffffffffff fffffff fffffffffff ffff ffffff fff ffffffretretertertretrretretertertretre"
						+ "hf dsjdsjhdsfkff  ffff ffffffffffff fffffff ffffffffff  ffffffffff ffffffffffffffffffffffffff fffffffffffffffff  fffff tertretrretretertertretre"
						+ "hfdsjdsjhd sfkfffffffffffff fffff ffffffff ffffffff f ffffffffffff ffffffffffffffffffffffffffffffffffffffffffffffffretretertertr etrretretertertretre"
						+ "hfdsjdsjhdsfkffffffffff fffffffffffffffffffffffffffff fffffff ffff ffffffffffffffffffffffff ffffffffffffffffffffretretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkff fffffffff fffffff ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffre tretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkfffffffffffffffffffffffffffffffff fffffffffffffffffff fffffff fffffffffff ffff ffffff fff ffffffretretertertretrretretertertretre"
						+ "hf dsjdsjhdsfkff  ffff ffffffffffff fffffff ffffffffff  ffffffffff ffffffffffffffffffffffffff fffffffffffffffff  fffff tertretrretretertertretre"
						+ "hfdsjdsjhd sfkfffffffffffff fffff ffffffff ffffffff f ffffffffffff ffffffffffffffffffffffffffffffffffffffffffffffffretretertertr etrretretertertretre"
						+ "hfdsjdsjhdsfkffffffffff fffffffffffffffffffffffffffff fffffff ffff ffffffffffffffffffffffff ffffffffffffffffffffretretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkff fffffffff fffffff ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffre tretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkfffffffffffffffffffffffffffffffff fffffffffffffffffff fffffff fffffffffff ffff ffffff fff ffffffretretertertretrretretertertretre"
						+ "hf dsjdsjhdsfkff  ffff ffffffffffff fffffff ffffffffff  ffffffffff ffffffffffffffffffffffffff fffffffffffffffff  fffff tertretrretretertertretre"
						+ "hfdsjdsjhd sfkfffffffffffff fffff ffffffff ffffffff f ffffffffffff ffffffffffffffffffffffffffffffffffffffffffffffffretretertertr etrretretertertretre"
						+ "hfdsjdsjhdsfkffffffffff fffffffffffffffffffffffffffff fffffff ffff ffffffffffffffffffffffff ffffffffffffffffffffretretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkff fffffffff fffffff ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffre tretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkfffffffffffffffffffffffffffffffff fffffffffffffffffff fffffff fffffffffff ffff ffffff fff ffffffretretertertretrretretertertretre"
						+ "hf dsjdsjhdsfkff  ffff ffffffffffff fffffff ffffffffff  ffffffffff ffffffffffffffffffffffffff fffffffffffffffff  fffff tertretrretretertertretre"
						+ "hfdsjdsjhd sfkfffffffffffff fffff ffffffff ffffffff f ffffffffffff ffffffffffffffffffffffffffffffffffffffffffffffffretretertertr etrretretertertretre"
						+ "hfdsjdsjhdsfkffffffffff fffffffffffffffffffffffffffff fffffff ffff ffffffffffffffffffffffff ffffffffffffffffffffretretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkff fffffffff fffffff ffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffre tretertertretrretretertertretre"
						+ "hfdsjdsjhdsfkfffffffffffffffffffffffffffffffff fffffffffffffffffff fffffff fffffffffff ffff ffffff fff ffffffretretertertretrretretertertretre"
						+ "hf dsjdsjhdsfkff  ffff ffffffffffff fffffff ffffffffff  ffffffffff ffffffffffffffffffffffffff fffffffffffffffff  fffff tertretrretretertertretre"
						+ "hfdsjdsjhd sfkfffffffffffff fffff ffffffff ffffffff f ffffffffffff ffffffffffffffffffffffffffffffffffffffffffffffffretretertertr etrretretertertretre"
						+ "hfdsjdsjhdsfkffffffffff fffffffffffffffffffffffffffff fffffff ffff ffffffffffffffffffffffff ffffffffffffffffffffretretertertretrretretertertretre"
						+ ""));

	}

	/**
	 * Override de WTWidget pour recharger pour les données en fonction de si
	 * l'utilisateur est connecté ou non
	 */
	public void reloadData() {
		loadDynamicData();
	}

}
