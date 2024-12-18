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
		if(super.ajouterNavire(n)) {
			if ( n.getDebut().getLigne() == n.getFin().getLigne() ) {
				// On colorie à l'horizontale
				for ( int i = n.getDebut().getColonne() ; i < n.getFin().getColonne() ; i++ )
					this.grille.colorie(new Coordonnee(n.getDebut().getLigne(), i), Color.GREEN);
			}
			else {
				//On colorie à la verticale
				for ( int i = n.getDebut().getLigne() ; i < n.getFin().getLigne() ; i++ )
					this.grille.colorie(new Coordonnee(i, n.getDebut().getColonne()), Color.GREEN);
			}
		}
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

