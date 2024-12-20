package batailleNavale;

public class JoueurAutoNiveau2 extends JoueurAuto {
	private boolean[][] dejaTire;
	private boolean navireAdetruire = false;
	private boolean deuxiemeTouchee = false;
	private Coordonnee premiereNavire = null;
	private Coordonnee courante;
	private int essai = 0;
	private char sens;
	private int etatCourant;

	public JoueurAutoNiveau2(GrilleNavale g, String nom) {
		super(g, nom);
		dejaTire = new boolean[this.getTailleGrille()][this.getTailleGrille()];
	}

	public JoueurAutoNiveau2(GrilleNavale g) {
		super(g);
		dejaTire = new boolean[this.getTailleGrille()][this.getTailleGrille()];
	}

	protected void retourAttaque(Coordonnee c, int etat) {
		courante = c;
		etatCourant = etat;
		if (etat == TOUCHE && !navireAdetruire) {
			navireAdetruire = true;
			premiereNavire = new Coordonnee(c.getLigne(), c.getColonne());
			

		} else if (etat == TOUCHE && navireAdetruire && !deuxiemeTouchee) {
			deuxiemeTouchee = true;

		} else if (etat == COULE) {
			navireAdetruire = false;
			essai = 0;
			deuxiemeTouchee = false;
		}

	}

	public Coordonnee choixAttaque() {
		if (!navireAdetruire) {
			Coordonnee choix = null;
			int i = (int) (Math.random() * (this.getTailleGrille()));
			int j = (int) (Math.random() * (this.getTailleGrille()));
			if (dejaTire[i][j] == false) {
				dejaTire[i][j] = true;
				choix = new Coordonnee(i, j);
				return choix;
			} else {
				// Sinon on parcours le tableau a partie de l'indice aléatoire (pour éviter que
				// ça dure trop longtemps)
				i = i == this.getTailleGrille() - 1 ? 0 : i;
				j = j == this.getTailleGrille() - 1 ? 0 : j;
				for (; i < this.getTailleGrille(); i++) {

					for (; j < this.getTailleGrille(); j++) {
						if (dejaTire[i][j] == false) {
							dejaTire[i][j] = true;
							choix = new Coordonnee(i, j);
							return choix;
						}
						j = j == this.getTailleGrille() - 1 ? 0 : j;
					}
					i = i == this.getTailleGrille() - 1 ? 0 : i;
				}
			}
			return choix;
		} else {
			if (!deuxiemeTouchee) {
				// ON CHERCHE LA DEUXIEME CASE
				if (essai == 0) {
					essai += 1;
					if (!toutEnHaut(premiereNavire)
							&& dejaTire[premiereNavire.getLigne() - 1][premiereNavire.getColonne()] == false) {
						sens = 'n';
						return nord(premiereNavire);
					}

				}
				if (essai == 1) {
					essai += 1;
					if (!toutEnBas(premiereNavire)
							&& dejaTire[premiereNavire.getLigne() + 1][premiereNavire.getColonne()] == false) {
						sens = 's';
						return sud(premiereNavire);
					}
				}
				if (essai == 2) {
					essai += 1;
					if (!toutAgauche(premiereNavire)
							&& dejaTire[premiereNavire.getLigne()][premiereNavire.getColonne() - 1] == false) {
						sens = 'o';
						return ouest(premiereNavire);
					}
				}
				if (essai == 3) {
					essai += 1;
					if (!toutAdroite(premiereNavire)
							&& dejaTire[premiereNavire.getLigne()][premiereNavire.getColonne() + 1] == false) {
						sens = 'e';
						return est(premiereNavire);
					}
				}

			} else {
				return choixSuivant(sens);
			}

		}
		return null;
	}

	public Coordonnee choixSuivant(char c) {
		if (c == 'n') {
			if (toutEnHaut(courante) || etatCourant == A_L_EAU
					|| dejaTire[courante.getLigne() - 1][courante.getColonne()] == true) {
				sens = 's';
				return sud(premiereNavire);
			} else {
				return nord(courante);
			}
		} else if (c == 's') {
			if (toutEnBas(courante) || etatCourant == A_L_EAU
					|| dejaTire[courante.getLigne() + 1][courante.getColonne()] == true) {
				sens = 'n';
				return nord(premiereNavire);
			} else {
				return sud(courante);
			}
		} else if (c == 'o') {
			if (toutAgauche(courante) || etatCourant == A_L_EAU
					|| dejaTire[courante.getLigne()][courante.getColonne() - 1] == true) {
				sens = 'e';
				return est(premiereNavire);
			} else {
				return ouest(courante);
			}
		} else {
			if (toutAdroite(courante) || etatCourant == A_L_EAU
					|| dejaTire[courante.getLigne()][courante.getColonne() + 1] == true) {
				sens = 'o';
				return ouest(premiereNavire);
			} else {
				return est(courante);
			}
		}
	}

	public Coordonnee nord(Coordonnee c) {
		dejaTire[c.getLigne() - 1][c.getColonne()] = true;
		return new Coordonnee(c.getLigne() - 1, c.getColonne());
	}

	public Coordonnee sud(Coordonnee c) {
		dejaTire[c.getLigne() + 1][c.getColonne()] = true;
		return new Coordonnee(c.getLigne() + 1, c.getColonne());
	}

	public Coordonnee ouest(Coordonnee c) {
		dejaTire[c.getLigne()][c.getColonne() - 1] = true;
		return new Coordonnee(c.getLigne(), c.getColonne() - 1);
	}

	public Coordonnee est(Coordonnee c) {
		dejaTire[c.getLigne()][c.getColonne() + 1] = true;
		return new Coordonnee(c.getLigne(), c.getColonne() + 1);
	}

	public boolean toutAdroite(Coordonnee c) {
		if (c.getColonne() == this.getTailleGrille() - 1)
			return true;
		return false;
	}

	public boolean toutAgauche(Coordonnee c) {
		if (c.getColonne() == 0)
			return true;
		return false;
	}

	public boolean toutEnHaut(Coordonnee c) {
		if (c.getLigne() == 0)
			return true;
		return false;
	}

	public boolean toutEnBas(Coordonnee c) {
		if (c.getLigne() == this.getTailleGrille() - 1)
			return true;
		return false;
	}

}
