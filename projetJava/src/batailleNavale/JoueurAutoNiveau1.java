package batailleNavale;

public class JoueurAutoNiveau1 extends JoueurAuto {
	private int[][] dejaTire;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public JoueurAutoNiveau1(GrilleNavale g, String nom) {
		super(g, nom);
		dejaTire = new int[this.getTailleGrille()][this.getTailleGrille()];
	}

	public JoueurAutoNiveau1(GrilleNavale g) {
		super(g);
		dejaTire = new int[this.getTailleGrille()][this.getTailleGrille()];
	}

	protected void retourAttaque(Coordonnee c, int etat) {
	}

	protected void retourDefense(Coordonnee c, int etat) {

	}

	public Coordonnee choixAttaque() {
		Coordonnee choix = null;
		while (choix == null) {
			int i = (int) (Math.random() * (this.getTailleGrille()));
			int j = (int) (Math.random() * (this.getTailleGrille()));
			if (dejaTire[i][j] == 0) {
				dejaTire[i][j] = 1;
				choix = new Coordonnee(i, j);
			}
		}
		return choix;
	}
}
