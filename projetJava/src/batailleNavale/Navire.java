package batailleNavale;

public class Navire {
	private Coordonnee debut;
	private Coordonnee fin;
	private int longueur;
	private boolean estVertical;
	private Coordonnee[] partiesTouchees;
	private int nbTouchees;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public Navire(Coordonnee debut, int longueur,
			boolean estVertical) {
		this.debut = debut;
		this.longueur = longueur;
		this.estVertical = estVertical;
		if ( estVertical) {
			fin = new Coordonnee(debut.getLigne()+longueur, debut.getColonne());
	
		}
		else {
			fin = new Coordonnee(debut.getLigne(), debut.getColonne()+longueur);
		}
		
	}
	public String toString() {
		String res = "Navire("+this.debut.toString()+", "longueur+", ";
		res += this.estVertical ? ""
	}
}
