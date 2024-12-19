package batailleNavale;

import java.awt.Color;


import javax.swing.JOptionPane;

public class JoueurGraphique extends JoueurAvecGrille  {
private GrilleGraphique grilleTirs;
public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs, String nom) {
	super(grilleDefense,nom);
	this.grilleTirs = grilleTirs;
}
public JoueurGraphique(GrilleNavaleGraphique grilleDefense,GrilleGraphique grilleTirs) {
	super(grilleDefense);
	this.grilleTirs = grilleTirs;
	
}
public Coordonnee choixAttaque() {
	return grilleTirs.getCoordonneeSelectionnee();
}
protected void retourDefense(Coordonnee c, int etat) {
	switch (etat) {
	case A_L_EAU: JOptionPane.showMessageDialog(grilleTirs, "Votre adversaire a tire dans l'eau");
	break;
	case TOUCHE: JOptionPane.showMessageDialog(grilleTirs, "Vous avez été touché en " + c);
	break;
	case COULE:	JOptionPane.showMessageDialog(grilleTirs, "Votre navire a coulé en " + c);
	break;
	case GAMEOVER:				JOptionPane.showMessageDialog(grilleTirs, "Vous avez Perdu!!!");
	
}
}
	protected void retourAttaque(Coordonnee c, int etat) {
			Color couleur = etat == A_L_EAU ? Color.BLUE : Color.RED;
			grilleTirs.colorie(c, couleur);
			switch (etat) {
			case A_L_EAU: JOptionPane.showMessageDialog(grilleTirs, "Dans l'eau!");
			break;
			case TOUCHE: JOptionPane.showMessageDialog(grilleTirs, "Vous avez touché un navire en " + c);
			break;
			case COULE:	JOptionPane.showMessageDialog(grilleTirs, "Vous avez coulé un navire en " + c);
			break;
			case GAMEOVER:				JOptionPane.showMessageDialog(grilleTirs, "Vous avez gagné!!!");
}
}
}
