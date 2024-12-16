package batailleNavale;

public class Coordonnee {

	
	private int ligne;
	private int colonne;

	public Coordonnee() {
		// TODO Auto-generated constructor stub
	}
	public Coordonnee(int ligne, int colonne) {
		this.ligne = ligne;
		this.colonne = colonne;
	}
	
	public  Coordonnee(String s) {
		this.colonne = s.charAt(0)-'A';
		this.ligne = Integer.parseInt(s.substring(1))-1;

	}
	
	public String toString() {
		return(char) ('A'+colonne)+Integer.toString(ligne+1);
		
	}
	
	public int getColonne() {
		return colonne;
	}
	
	public int getLigne() {
		return ligne;
		
	}
	
	public boolean equals(Object obj) {
		if (!(obj instanceof Coordonnee)) return false;
		Coordonnee other = (Coordonnee) obj;
		return this.ligne == other.ligne && this.colonne == other.colonne;
		
	}
	
//	public boolean voisine(Coordonne c) {
//		return (Math.abs(this.ligne - c.ligne))== 1 && this.colonne == c.colonne) ||;
//		(Math.abs(this.colonne - c.colonne))==1 && this.ligne == c.ligne);
//		
//	}
	
	

}
