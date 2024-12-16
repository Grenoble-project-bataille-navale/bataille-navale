package batailleNavale;

public class GrilleNavale {
	private Navire[] navires;
	private int nbNavires;
	private int taille;
	private Coordonnee[] tirsRecu;
	private int nbTirsRecus;
	
	public GrilleNavale(int taille, int[] taillesNavires) {
		if (taille <= 2 ) throw new IllegalArgumentException("Taille trop petite");
		if (taillesNavires == null ) throw new IllegalArgumentException("Il faut un tableau....");
		this.taille = taille;
		this.navires = new Navire [taillesNavires.length];
		this.nbNavires = 0;
		this.placementAuto(taillesNavires);
		this.tirsRecu = new Coordonnee[taille*taille];
		this.nbTirsRecus=0;
	}
	public GrilleNavale(int taille, int nbNavires) {
		if (nbNavires > taille) throw new IllegalArgumentException("Erreur Arguments");
		this.taille =  taille;
		this.nbNavires =  nbNavires;
		this.navires = new Navire[nbNavires];
		this.tirsRecu = new Coordonnee[taille*taille];
	}
	
	public String toString() {

		for (int i=0; i< this.taille; i++) {
			for (int j =0; j< this.taille ; j++ ) {
				Coordonnee c = new Coordonnee(i, j);
				
			}
		}
		return null;
	}
	public int getTaille() {
		return this.taille;
	}
	public boolean ajouterNavire(Navire n) {
		
		if(this.nbNavires >= this.navires.length) return false;
		for (int i=0; i< navires.length; i++) {
			if (n == null || navires[i].touche(n) || navires[i].chevauche(n)) return false;
		}
		navires[this.nbNavires+1] =n;
		this.nbNavires++;
		return true;
	}
	
	
	public void placementAuto(int[] taillesNavires) {
		for (int i=0; i<taillesNavires.length; i++) {
			boolean place=false;
			while(!place) {
				boolean[] estVerticale = {true, false, true, false};
				int debutAlea = (int) (Math.random()* taille +1);
				int lngAlea = (int) (Math.random()* taille +1);
				if (lngAlea < taille - debutAlea  ) continue;
				int indice = (int) (Math.random()*5);
				boolean v = estVerticale[indice];
				Coordonnee C = new Coordonnee(debutAlea,lngAlea);
				Navire n = new Navire(C,lngAlea, v);
				if(this.ajouterNavire(n)) {
					place=true;
				}
			}
		}
		
	}
	private boolean estDansGrille(Coordonnee c) {
		if (c.getColonne()<0 || c.getColonne() <0 || c.getColonne()>taille || c.getLigne() > taille) return false;
		return true;
	}
	
	private boolean estDansTirsRecus(Coordonnee c) {
		if (!this.estDansGrille(c)) return false;
		for (int i =0 ; i< tirsRecu.length; i++) {
			if(tirsRecu[i].equals(c)) return true;
		}
		return false;
	}
	
	private boolean ajouteDansTirsRecus(Coordonnee c) {
		if(estDansTirsRecus(c)) {
			tirsRecu[nbTirsRecus+1]=c;
			nbTirsRecus++;
			return true;
		}
		return false;
	}
	public boolean recoitTir(Coordonnee c) {
		if (!(ajouteDansTirsRecus(c))) return false;
//		for (int i = 0; i< navires.length; i++) {
//			if (navires[i].estTouche(c)) return true;
//		}
		return estTouche(c);
//		return false;
	}
	public boolean estTouche(Coordonnee c) {
		for(int i=0; i<navires.length; i++) {
			if (navires[i].estTouche(c)) return true;
		}
		return false;
	}
	
	public boolean estALeau(Coordonnee c) {
		return estDansTirsRecus(c) &&  recoitTir(c);
	}
	
	public boolean estCoule(Coordonnee c) {
		return false;
	}
	
	public boolean perdu(Coordonnee c) {
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
