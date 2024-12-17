package batailleNavale;

public abstract class JoueurAvecGrille extends Joueur {
	private GrilleNavale grille;

	public JoueurAvecGrille(GrilleNavale g, String nom) {
		super(g.getTaille(), nom);
		this.grille = g;
	}

	public JoueurAvecGrille(GrilleNavale g) {
		super(g.getTaille());
		this.grille = g;
	}

	public int defendre(Coordonnee c) {
		grille.recoitTir(c);
		if (grille.perdu())
			return GAMEOVER;
		else if (grille.estCoule(c))
			return COULE;
		else if (grille.estTouche(c))
			return TOUCHE;
		else {
			return A_L_EAU;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
