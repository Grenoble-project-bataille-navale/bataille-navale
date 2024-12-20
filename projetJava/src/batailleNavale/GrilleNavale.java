package batailleNavale;

public class GrilleNavale {
	private Navire[] navires;
	private int nbNavires;
	private int taille;
	private Coordonnee[] tirsRecus;
	private int nbTirsRecus;

	public GrilleNavale(int taille, int[] taillesNavires) {
		if (taille <= 2)
			throw new IllegalArgumentException("Taille trop petite");
		if (taillesNavires == null)
			throw new IllegalArgumentException("Il faut un tableau....");

		this.taille = taille;
		this.navires = new Navire[taillesNavires.length];
		this.tirsRecus = new Coordonnee[taille * taille];
		this.nbTirsRecus = 0;
		this.nbNavires = 0;
		this.placementAuto(taillesNavires);

	}

	public GrilleNavale(int taille, int nbNavires) {
		if (nbNavires > taille)
			throw new IllegalArgumentException("Erreur Arguments");
		this.taille = taille;
		this.navires = new Navire[nbNavires];
		this.nbNavires = 0;
		this.tirsRecus = new Coordonnee[taille * taille];
	}

	public String toString() {
		StringBuffer sb = new StringBuffer("");
		sb.append(initLettres());
		for (int i = 0; i < taille; i++) {
			if (i < 9)
				sb.append(' ');
			sb.append(i + 1);
			sb.append(' ');
			for (int j = 0; j < taille; j++) {
				Coordonnee courant = new Coordonnee(i, j);
				if (estDansTirsRecus(courant)) {
					if (estTouche(courant)) {
						sb.append('x');
					} else {
						sb.append('O');
					}
				} else {
					if (navirePresent(courant))
						sb.append('#');
					else
						sb.append('.');
				}
				sb.append(' ');
			}
			sb.append('\n');
		}
		return sb.toString();
	}

	private String initLettres() {
		// methode outil pour le ToString
		StringBuffer sb = new StringBuffer("   ");
		for (int i = 0; i < taille; i++) {
			sb.append((char) ('A' + i));
			sb.append(' ');
		}
		sb.append('\n');
		return sb.toString();

	}

	// méthode outil pour le ToSring
	private boolean navirePresent(Coordonnee c) {
		for (int i = 0; i < navires.length; i++) {
			if (navires[i].contient(c))
				return true;
		}

		return false;

	}

	public int getTaille() {
		return this.taille;
	}

	public boolean ajouteNavire(Navire n) {
		if (this.nbNavires > this.navires.length)
			return false;
		for (int i = 0; i < this.nbNavires; i++) {
			if (navires[i].touche(n))
				return false;
			if (navires[i].chevauche(n)) {

				return false;
			}
		}
		navires[this.nbNavires] = n;
		this.nbNavires++;
		return true;
	}

	public void placementAuto(int[] taillesNavires) {
		taillesNavires = sortTaillesNavires(taillesNavires);
		for (int i = 0; i < taillesNavires.length; i++) {
			if (taillesNavires[i] > taille)
				throw new IllegalArgumentException("Un bateau est plus grand que la grille");
			boolean place = false;
			while (!place) {
				boolean[] estVerticale = { true, false };
				int indice = (int) (Math.random() * 2);
				boolean estVertical = estVerticale[indice];
				int ligneAlea = estVertical ? (int) (Math.random() * (taille-taillesNavires[i])) : (int) (Math.random()*taille); 
				int colonneAlea = !estVertical ? (int) (Math.random() * (taille-taillesNavires[i])) : (int) (Math.random()*taille); 
				Coordonnee c = new Coordonnee(ligneAlea, colonneAlea);
				Navire n = new Navire(c, taillesNavires[i], estVertical);

				if (ajouteNavire(n)) {
					place = true;				}
			}
		}

	}
	private int[] sortTaillesNavires(int[] taillesNavires) {
		//Méthode outil de trie car ranger les bateaux du plus grand au plus petit est plus rapide
		for (int i=0; i< taillesNavires.length-1; i++) {
			for (int j=0; j<taillesNavires.length-i-1; j++) {
				if(taillesNavires[j]<taillesNavires[j+1]) {
					int temp = taillesNavires[j+1];
					taillesNavires[j+1] = taillesNavires[j];
					taillesNavires[j] = temp;
				}
			}
		}
		
		return taillesNavires;
	}

	private boolean estDansGrille(Coordonnee c) {
		return c.getColonne() >= 0 && c.getColonne() < taille && c.getLigne() >= 0 && c.getLigne() < taille;
	}

	private boolean estDansTirsRecus(Coordonnee c) {
		if (c == null || !this.estDansGrille(c))
			return false;
		for (int i = 0; i < nbTirsRecus; i++) {
			if (tirsRecus[i].equals(c))
				return true;
		}
		return false;
	}

	private boolean ajouteDansTirsRecus(Coordonnee c) {
		if ( c == null)
			throw new IllegalArgumentException("coordonnee nulle");
		if (estDansTirsRecus(c))
			return false;
		if (nbTirsRecus >= tirsRecus.length) {
			throw new ArrayIndexOutOfBoundsException("Le tableau des tirs reçus est plein.");
		}
		tirsRecus[nbTirsRecus] = c;
		nbTirsRecus++;

		return true;
	}

	public boolean recoitTir(Coordonnee c) {
		if (c == null || !(ajouteDansTirsRecus(c)))
			return false;
		for (int i = 0; i < navires.length; i++) {
			if (navires[i].recoitTir(c))
				return true;
		}

		return false;
	}

	public boolean estTouche(Coordonnee c) {
		for (int i = 0; i < navires.length; i++) {
			if (navires[i].estTouche(c))
				return true;
		}
		return false;
	}

	public boolean estALeau(Coordonnee c) {
		return estDansTirsRecus(c) && !estTouche(c);
	}

	public boolean estCoule(Coordonnee c) {
		for (int i = 0; i < navires.length; i++) {
			if (navires[i].estTouche(c) && navires[i].estCoule())
				return true;
		}
		return false;
	}

	public boolean perdu() {
		for (int i = 0; i < navires.length; i++) {
			if (!(navires[i].estCoule()))
				return false;
		}
		return true;
	}

	public Coordonnee[] getTirsRecus() {
		return tirsRecus;
	}

	

}
