package batailleNavale;

public class Coordonnee implements Comparable<Coordonnee>{

	private int ligne;
	private int colonne;

	public Coordonnee() {
		// TODO Auto-generated constructor stub
	}

	public Coordonnee(int ligne, int colonne) {
		if (ligne < 0 || ligne >= 26 || colonne < 0 || colonne >= 26) {
			throw new IllegalArgumentException("numero de ligne/colonne non autorisé :"+ ligne + " "+ colonne);
		} 
		this.ligne = ligne;
		this.colonne = colonne;
	}

	public Coordonnee(String s) {
		if (s == null || s.length() < 2) {
			throw new IllegalArgumentException("ce ne sont pas des coordonnées");
		}

		char lettre = s.charAt(0);// je pioche la lettre
		int numero = Integer.parseInt(s.substring(1));// je pioche le chiffre dans s
		this.colonne = lettre - 'A'; // Conversion de lettre vers indice (ex : 'A' -> 0)
		this.ligne = numero - 1; // Conversion de numéro vers indice (ex : 1 -> 0)
	}

	@Override
	public String toString() {
		return (char) ('A' + colonne) + Integer.toString(ligne + 1);

	}

	public int getColonne() {
		return colonne;
	}

	public int getLigne() {
		return ligne;

	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		if (!(obj instanceof Coordonnee))
			return false;
		//Est-ce vraiment nécessaire ?

		Coordonnee other = (Coordonnee) obj;
		return this.ligne == other.ligne && this.colonne == other.colonne;

	}

	public boolean voisine(Coordonnee c) {
		if ((Math.abs(this.ligne - c.ligne) == 1 && this.colonne == c.colonne)
				|| (Math.abs(this.colonne - c.colonne) == 1 && this.ligne == c.ligne)) {
			return true;
		} else {
			return false;
		}
	}

	public int compareTo(Coordonnee c) {
		if (this.ligne != c.ligne) {
			return this.ligne - c.ligne;
		}
		return this.colonne - c.colonne;
	} // un resultat positif indique que l objet courant est superieur

}