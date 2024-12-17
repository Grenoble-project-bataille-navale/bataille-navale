package batailleNavale;

public abstract class JoueurAuto extends JoueurAvecGrille {
public JoueurAuto(GrilleNavale g, String nom) {
	super(g,nom);
}

public JoueurAuto(GrilleNavale g) {
	super(g);
}

protected void retourAttaque(Coordonnee c, int etat) {}

protected void retourDefense(Coordonnee c, int etat) {}

public abstract Coordonnee choixAttaque();


	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
