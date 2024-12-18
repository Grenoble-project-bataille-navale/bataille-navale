package batailleNavale;

import java.awt.Color;

public class GrilleNavaleGraphique  extends GrilleNavale {
	private GrilleGraphique grille;
	public GrilleNavaleGraphique(int taille) {
		super(taille,0);
		this.grille =  new GrilleGraphique(taille);
	}
	public GrilleGraphique getGrilleGraphique() {
		return grille;
	}
	public boolean ajouteNavire(Navire n) {
		return false;
	}
	public boolean recoitTir(Coordonnee c) {
		 boolean tir = super.recoitTir(c);
		 if (tir) {
			 
			 grille.colorie(c, Color.RED);
		 } else {
			 grille.colorie(c, Color.BLUE);
		 }
		 return tir;
	}

}

