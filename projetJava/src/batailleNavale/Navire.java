package batailleNavale;

public class Navire {
	private Coordonnee debut;
	private Coordonnee fin;
	private Coordonnee[] partiesTouchees;
	private int nbTouchees;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Navire test = new Navire(new Coordonnee(0, 1), 2, true);
		Navire test2 = new Navire(new Coordonnee(1, 0), 3, false);
		System.out.println(test);
		System.out.println(test2);
		Coordonnee a = new Coordonnee("B1");
		Coordonnee b = new Coordonnee("B2");
		test.recoitTir(a);
	
		System.out.println(test.estCoule());
	}

	public Navire(Coordonnee debut, int longueur, boolean estVertical) {
		if (longueur < 1)
			throw new IllegalArgumentException("La longueur d'un bateau ne peut pas être négative ou nul :" + longueur);
		this.debut = debut;
		this.partiesTouchees = new Coordonnee[longueur];
		this.nbTouchees = 0;
		if (estVertical) {
			fin = new Coordonnee(debut.getLigne() + longueur - 1, debut.getColonne());

		} else {
			fin = new Coordonnee(debut.getLigne(), debut.getColonne() + longueur - 1);
		}

	}

	public String toString() {
		String axe;
		int longueur;
		if (estVertical()) {
			axe = "vertical";
			longueur = fin.getLigne() - debut.getLigne() + 1;

		} else {
			axe = "horizontal";
			longueur = fin.getColonne() - debut.getColonne() + 1;
		}
		return "Navire(" + debut.toString() + ", " + longueur + " ," + axe + " )";
	}

	private boolean estVertical() {
		// methode outil
		return (debut.getColonne() == fin.getColonne());

	}

	public Coordonnee getDebut() {
		return debut;
	}

	public Coordonnee getFin() {
		return fin;
	}

	public boolean contient(Coordonnee c) {
		if (c.getLigne() >= debut.getLigne() && c.getLigne() <= fin.getLigne())
			if (c.getColonne() >= debut.getColonne() && c.getColonne() <= fin.getColonne())
				return true;
		return false;
	}

	public boolean touche(Navire n) {
		return (((n.debut.getLigne() <= this.debut.getLigne() && n.fin.getLigne() >= this.debut.getLigne())
				|| (this.debut.getLigne() <= n.debut.getLigne() && this.fin.getLigne() >= n.debut.getLigne()))
				&& (n.debut.getColonne() == this.fin.getColonne() + 1
						|| this.debut.getColonne() == n.fin.getColonne() + 1))
				|| (((n.debut.getColonne() <= this.debut.getColonne() && n.fin.getColonne() >= this.debut.getColonne())
						|| (this.debut.getColonne() <= n.debut.getColonne()
								&& this.fin.getColonne() >= n.debut.getColonne()))
						&& (n.debut.getLigne() == this.fin.getLigne() + 1
								|| this.debut.getLigne() == n.fin.getLigne() + 1));
	}

	public boolean chevauche(Navire n) {
		return ((n.debut.getLigne() <= this.debut.getLigne() && n.fin.getLigne() >= this.debut.getLigne())
				|| (this.debut.getLigne() <= n.debut.getLigne() && this.fin.getLigne() >= n.debut.getLigne()))
				&& ((n.debut.getColonne() <= this.debut.getColonne() && n.fin.getColonne() >= this.debut.getColonne())
						|| (this.debut.getColonne() <= n.debut.getColonne()
								&& this.fin.getColonne() >= n.debut.getColonne()));
	}

	public boolean recoitTir(Coordonnee c) {
		// On peut tirer sur une partie déjà touché cela renverra True mais
		// n'incrémentera pas le compteur
		if (this.contient(c)) {
			if (!estTouche(c)) {
				partiesTouchees[this.nbTouchees] = c;
				this.nbTouchees += 1;
			}
			return true;
		}
		return false;
	}

	public boolean estTouche(Coordonnee c) {
		for (int i = 0; i < this.nbTouchees; i++) {
			if (partiesTouchees[i].equals(c))
				return true;
		}
		return false;
	}

	public boolean estTouche() {
		return this.nbTouchees > 0;
	}

	public boolean estCoule() {
		return this.nbTouchees == this.partiesTouchees.length;
	}
}
