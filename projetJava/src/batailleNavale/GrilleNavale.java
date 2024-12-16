package batailleNavale;

public class GrilleNavale {
	private Navire[] navires;
	private int nbNavires;
	private int taille;
	private Coordonnee[] tirsRecu;
	private int nbTirsRecus;
	
	public GrilleNavale(int taille, int[] taillesNavires) {
		if (taille <= 2  ) throw new IllegalArgumentException("Taille trop petite");
		if (taillesNavires == null  ) throw new IllegalArgumentException("Il faut un tableau....");
		
		this.taille = taille;
		this.navires = new Navire [taillesNavires.length];
		this.tirsRecu = new Coordonnee[taille*taille];
		this.nbTirsRecus=0;
		this.nbNavires = 0;
		this.placementAuto(taillesNavires);
		
	}
	public GrilleNavale(int taille, int nbNavires) {
		if (nbNavires > taille) throw new IllegalArgumentException("Erreur Arguments");
		this.taille =  taille;
		this.nbNavires =  nbNavires;
		this.navires = new Navire[nbNavires];
		this.tirsRecu = new Coordonnee[taille*taille];
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer("");
		sb.append(initLettres());
		for (int i = 0; i < taille ; i++) {
			sb.append(i+1);
			sb.append(' ');
			for ( int j = 0; j < taille ;j++) {
				Coordonnee courant = new Coordonnee(i,j);
				if (estDansTirsRecus(courant)){
					if(estTouche(courant)) {
						sb.append('x');
					}
					else {
						sb.append('O');
					}
				}
				else {
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
		//methode outil pour le ToString
		StringBuffer sb = new StringBuffer(" ");
		for ( int i = 0; i < taille; i++) {
			sb.append( (char) ('A'+i));
			sb.append(' ');
		}
		sb.append('\n');
		return sb.toString();
		
		
	}
		//méthode outil pour le ToSring
	private boolean navirePresent(Coordonnee c) {
		for (int i = 0; i < navires.length; i++) {
			if ( navires[i].contient(c))
				return true;
		}
	
		return false;
		
	}
	public int getTaille() {
		return this.taille;
	}
	public boolean ajouterNavire(Navire n) {
		
		if(this.nbNavires >= this.navires.length) return false;
		for (int i=0; i< navires.length; i++) {
			if (navires[i] == null || navires[i].touche(n) || navires[i].chevauche(n)) continue;
		}
		navires[this.nbNavires] =n;
		this.nbNavires++;
		return true;
	}
	
	
	public void placementAuto(int[] taillesNavires) {
		for (int i=0; i<taillesNavires.length; i++) {
			System.out.println(i);
			boolean place=false;
			while(!place) {
				System.out.println(place);
				boolean[] estVerticale = {true, false};
				int debutAlea = (int) (Math.random()* taille +1);
				int lngAlea = (int) (Math.random()* taille +1);
//				if (lngAlea > taille - debutAlea  ) {
//					continue;
//				};
				int indice = (int) (Math.random()*2);
				boolean v = estVerticale[indice];
				Coordonnee C = new Coordonnee(debutAlea,lngAlea);
				System.out.println(C);
				Navire n = new Navire(C,lngAlea, v);
				if ((v && lngAlea > taille - C.getLigne()) || (!v && lngAlea> taille - C.getColonne())) continue;
				if(ajouterNavire(n)) {
					place=true;
				}
			}
		}
		
	}
	private boolean estDansGrille(Coordonnee c) {
		if (c.getColonne()<0 || c.getLigne() <0 || c.getColonne()>taille || c.getLigne() > taille) return false;
		return true;
	}
	
	private boolean estDansTirsRecus(Coordonnee c) {
		if (!this.estDansGrille(c)) return false;
		for (int i =0 ; i< nbTirsRecus; i++) {
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
		for (int i = 0; i< navires.length; i++) {
			if (navires[i].recoitTir(c)) return true;
		}
		//return estTouche(c);
		return false;
	}
	public boolean estTouche(Coordonnee c) {
		for(int i=0; i<navires.length; i++) {
			if (navires[i].estTouche(c)) return true;
		}
		return false;
	}
	
	public boolean estALeau(Coordonnee c) {
		return  estDansTirsRecus(c) &&  estTouche(c);
	}
	
	public boolean estCoule(Coordonnee c) {
		for (int i=0; i<navires.length; i++) {
			if (navires[i].estTouche(c) && navires[i].estCoule()) return true;
		}
		return false;
	}
	
	public boolean perdu() {
		for (int i=0; i<navires.length; i++) {
			if(!(navires[i].estCoule())) return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] tailleNavires = {2,3,5};
		GrilleNavale g = new GrilleNavale(10,tailleNavires);
		System.out.println(g);

	}

}
