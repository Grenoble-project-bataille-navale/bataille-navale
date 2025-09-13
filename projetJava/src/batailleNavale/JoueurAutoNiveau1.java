package batailleNavale;

import java.awt.desktop.ScreenSleepEvent;
import java.util.concurrent.TimeUnit;

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

	public Coordonnee choixAttaque()  {
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Faire attendre l'IA une seconde pour éviter que l'expérience de Jeu soit trop instantanné
		Coordonnee choix = null;
		int i = (int) (Math.random() * (this.getTailleGrille()));
		int j = (int) (Math.random() * (this.getTailleGrille()));
		if (dejaTire[i][j] == 0) {
			dejaTire[i][j] = 1;
			choix = new Coordonnee(i, j);
			return choix;
		} else {
			//Sinon on parcours le tableau a partie de l'indice aléatoire (pour éviter que ça dure trop longtemps)
			i = i == this.getTailleGrille() - 1 ? 0 : i;
			j = j == this.getTailleGrille() - 1 ? 0 : j;
			for (; i < this.getTailleGrille(); i++) {

				for (; j < this.getTailleGrille(); j++) {
					if (dejaTire[i][j] == 0) {
						dejaTire[i][j] = 1;
						choix = new Coordonnee(i, j);
						return choix;
					}
					j = j == this.getTailleGrille() - 1 ? 0 : j;
				}
				i = i == this.getTailleGrille() - 1 ? 0 : i;
			}
		}
		return choix;
	}
}
