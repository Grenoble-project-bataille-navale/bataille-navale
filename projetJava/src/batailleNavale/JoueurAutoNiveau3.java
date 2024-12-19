package batailleNavale;

public class JoueurAutoNiveau3 extends JoueurAuto {
	private int[][] dejaTire;
	private  boolean[][]  attaquePrecedentes;
	private Coordonnee derniereTouchee; 
	private Coordonnee premiereTouchee;
	private int directionActuelle;
	
	public JoueurAutoNiveau3(GrilleNavale g, String nom) {
		super(g, nom);
		attaquePrecedentes = new boolean[this.getTailleGrille()][this.getTailleGrille()];
		dejaTire = new int[this.getTailleGrille()][this.getTailleGrille()];
		this.derniereTouchee = null;
		this.directionActuelle = -1;
		
	}

	public JoueurAutoNiveau3(GrilleNavale g) {
		super(g);
		attaquePrecedentes = new boolean[this.getTailleGrille()][this.getTailleGrille()];
		dejaTire = new int[this.getTailleGrille()][this.getTailleGrille()];
		
	}

	protected void retourAttaque(Coordonnee c, int etat) {
	    if (etat == TOUCHE) {
	        if (premiereTouchee == null) {
	            premiereTouchee = c; 
	        }
	        derniereTouchee = c; 
	        System.out.println("Dernière case touchée : " + derniereTouchee);
	    } else if (etat == COULE) {
	        premiereTouchee = null; 
	        derniereTouchee = null;
	        directionActuelle = -1;
	    }
	}

	protected void retourDefense(Coordonnee c, int etat) {
		
	}
	
	@Override
	public Coordonnee choixAttaque() {
		// TODO Auto-generated method stub
		Coordonnee choix = null;
		 if (derniereTouchee != null) {
			 System.out.println(derniereTouchee);
	            choix = continuerAttaque();
	            if (choix != null) return choix;
	        }
		while (choix == null) {
			int i = (int) (Math.random() * (this.getTailleGrille()));
			System.out.println(i);
			int j = (int) (Math.random() * (this.getTailleGrille()));
			System.out.println(j);
			 if ((i + j) % 2 == 0 && !attaquePrecedentes[i][j]) {
	                choix = new Coordonnee(i, j);
	                System.out.println(choix);
	                attaquePrecedentes[i][j] = true;
	            }
			
		}
		return choix;
		
	}
	private Coordonnee continuerAttaque() {
	    int i = derniereTouchee.getLigne();
	    int j = derniereTouchee.getColonne();

	    if (directionActuelle != -1) {
	        switch (directionActuelle) {
	            case 0: 
	                if (caseValide(i - 1, j)) {
	                    attaquePrecedentes[i - 1][j] = true;
	                    return new Coordonnee(i - 1, j);
	                }
	                break;
	            case 1: 
	                if (caseValide(i + 1, j)) {
	                    attaquePrecedentes[i + 1][j] = true;
	                    return new Coordonnee(i + 1, j);
	                }
	                break;
	            case 2: 
	                if (caseValide(i, j - 1)) {
	                    attaquePrecedentes[i][j - 1] = true;
	                    return new Coordonnee(i, j - 1);
	                }
	                break;
	            case 3: 
	                if (caseValide(i, j + 1)) {
	                    attaquePrecedentes[i][j + 1] = true;
	                    return new Coordonnee(i, j + 1);
	                }
	                break;
	        }

	    
	        derniereTouchee = premiereTouchee;
	        directionActuelle = -1; 
	    }

	   
	    if (directionActuelle == -1) {
	        i = premiereTouchee.getLigne();
	        j = premiereTouchee.getColonne();

	        if (caseValide(i - 1, j)) { 
	            directionActuelle = 0;
	            attaquePrecedentes[i - 1][j] = true;
	            return new Coordonnee(i - 1, j);
	        }
	        if (caseValide(i + 1, j)) {
	            directionActuelle = 1;
	            attaquePrecedentes[i + 1][j] = true;
	            return new Coordonnee(i + 1, j);
	        }
	        if (caseValide(i, j - 1)) { 
	            directionActuelle = 2;
	            attaquePrecedentes[i][j - 1] = true;
	            return new Coordonnee(i, j - 1);
	        }
	        if (caseValide(i, j + 1)) { 
	            directionActuelle = 3;
	            attaquePrecedentes[i][j + 1] = true;
	            return new Coordonnee(i, j + 1);
	        }
	    }

	    return null; 
	}

	 private boolean caseValide(int i, int j) {
	        return i >= 0 && i < this.getTailleGrille() && j >= 0 && j < this.getTailleGrille() && !attaquePrecedentes[i][j];
	    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] tailleNavires = { 2};
		GrilleNavale g = new GrilleNavale(3, tailleNavires);
		JoueurTexte j1 = new JoueurTexte(g);
		JoueurAutoNiveau3 j2 = new JoueurAutoNiveau3(g);
		j1.jouerAvec(j2);
	}

}