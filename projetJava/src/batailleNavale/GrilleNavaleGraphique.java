package batailleNavale;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class GrilleNavaleGraphique  extends GrilleNavale {
	private static int[] taillesNavire= {5,4,3,3,2};
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
//			this.grille.colorie(n.getDebut(), n.getFin(), Color.GREEN);
			if (n.getDebut().getLigne() == n.getFin().getLigne())
				this.grille.setImageNavireHorizontal(n.getDebut(), n.getFin());
			else
				this.grille.setImageNavireVertical(n.getDebut(), n.getFin());
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