package batailleNavale;

public abstract class Joueur {
	public final static int TOUCHE = 1;
	public final static int COULE = 2;
	public final static int A_L_EAU = 3;
	public final static int GAMEOVER = 4;
	private Joueur adversaire;
	private int tailleGrille;
	private String nom;

public Joueur(int tailleGrille, String nom) {
	if (tailleGrille <= 2)
		throw new IllegalArgumentException("Taille trop petite");
	if (nom == null) throw new IllegalArgumentException("Il faut le nom du joueur");
	this.tailleGrille = tailleGrille;
	this.nom = nom;
}

public Joueur(int tailleGrille) {
	if (tailleGrille <= 2)
		throw new IllegalArgumentException("Taille trop petite");
	this.tailleGrille = tailleGrille;
	this.nom = "Joueur 1";
}

public int getTailleGrille() {
	return tailleGrille;
}

public String getNom() {
	return nom;
}

public void jouerAvec(Joueur j) {
	if (tailleGrille != j.tailleGrille)
		throw new IllegalArgumentException("Les deux tailles ne peuvent pas etres differentes.");
	adversaire =j;
	j.adversaire=this;
	deroulementJeu(this, j);
}

	private static void deroulementJeu(Joueur attaquant, Joueur defenseur) {
		int res = 0;
		while (res != GAMEOVER) {
			Coordonnee c = attaquant.choixAttaque();
			res = defenseur.defendre(c);
			attaquant.retourAttaque(c, res);
			defenseur.retourDefense(c, res);		
			Joueur x = attaquant;
			attaquant = defenseur;
			defenseur = x;
		}
	}

	protected abstract void retourAttaque(Coordonnee c, int etat);

	protected abstract void retourDefense(Coordonnee c, int etat);

	public abstract Coordonnee choixAttaque();

	public abstract int defendre(Coordonnee c);
}
