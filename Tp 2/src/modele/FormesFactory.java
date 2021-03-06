package modele;

import exception.FormeException;
import exception.ZoneDessinException;

public class FormesFactory {

	private int largeur = 0;
	private int hauteur = 0;

	public FormesFactory(int largeur, int hauteur) {
		if (validerDimension(largeur) && validerDimension(hauteur)) {
			setLargeur(largeur);
			setHauteur(hauteur);
		}
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	/**
	 * Crée une forme et valide si la forme entre dans la zone de dessin
	 * @param data
	 * @return
	 * @throws FormeException - si la forme ne peut être créée
	 * @throws ZoneDessinException - si la forme ne rentre pas dans la zone
	 */
	public Forme getInstance(DataFactory data) throws FormeException, ZoneDessinException {
		Forme forme = null;
		if (validerFormeDansZone(data.getLargeur(), data.getHauteur(), data.getPosX(), data.getPosY())){
			forme = data.envoyerForme(data.getType());
		} else {
			throw new ZoneDessinException();
		}
		return forme;
	}

	/**
	 * Vérifie que la dimension en paramètre existe
	 * @param dim
	 * @return
	 */
	private boolean validerDimension(int dim) {
		return (dim > 0);
	}

	private boolean validerFormeDansZone(int largeur, int hauteur, int posX, int posY) {
		return (validerX(largeur + posX) && validerY(hauteur + posY));
	}

	/**
	 * Vérifie que le x rentre dans la largeur de la zone de dessin
	 * @param x
	 * @return
	 */
	private boolean validerX(int x) {
		return ((x > 0) && (x < this.getLargeur()));
	}

	/**
	 * Vérifie que le y rentre dans la hauteur de la zone de dessin
	 * @param y
	 * @return
	 */
	private boolean validerY(int y) {
		return ((y > 0) && (y < this.getHauteur()));
	}
}
