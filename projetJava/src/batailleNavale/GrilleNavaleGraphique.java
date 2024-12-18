package batailleNavale;

import java.awt.Color;

public class GrilleNavaleGraphique  extends GrilleNavale {
	private static int[] taillesNavire= {5,5,2,5,2};
	private GrilleGraphique grille;
	public GrilleNavaleGraphique(int taille) {
		
		super(taille,taillesNavire.length);
		this.grille =  new GrilleGraphique(taille);
		this.placementAuto(taillesNavire);
	}
	public GrilleGraphique getGrilleGraphique() {
		return grille;
	}
	public boolean ajouteNavire(Navire n) {
		
		if(super.ajouteNavire(n)) {
			if ( n.getDebut().getLigne() == n.getFin().getLigne() ) {
				// On colorie à l'horizontale
				for ( int i = n.getDebut().getColonne() ; i <= n.getFin().getColonne() ; i++ )
					this.grille.colorie(new Coordonnee(n.getDebut().getLigne(), i), Color.GREEN);
			}
			else {
				//On colorie à la verticale
				for ( int i = n.getDebut().getLigne() ; i <= n.getFin().getLigne() ; i++ )
					this.grille.colorie(new Coordonnee(i, n.getDebut().getColonne()), Color.GREEN);
			}
			return true;
		}
		return false;
	}
	public boolean recoitTir(Coordonnee c) {
		 boolean tir = super.recoitTir(c);
		
		 if (tir) {
			 
			 grille.colorie(c, Color.RED);
		 } else {
			 if ( !super.estTouche(c))
				 grille.colorie(c, Color.BLUE);
		 }
		 return tir;
	}

}
