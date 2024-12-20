package batailleNavale;

public class JoueurAutoNiveau3 extends JoueurAuto {
	private int[][] dejaTire;
	private  boolean[][]  attaquePrecedentes;
	private Coordonnee derniereTouchee; 
	private Coordonnee premiereTouchee;
	private int directionActuelle;
	 private int damierIndex;
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
        int i = c.getLigne();
        int j = c.getColonne();

        if (etat == TOUCHE) {
            dejaTire[i][j] = 2;
            if (premiereTouchee == null) {
                premiereTouchee = c; 
            }
            derniereTouchee = c; 
        } else if (etat == COULE) {
            dejaTire[i][j] = 2;
            premiereTouchee = null; 
            derniereTouchee = null;
            directionActuelle = -1;
        } else if (etat == A_L_EAU) {
            
        }
    }

	protected void retourDefense(Coordonnee c, int etat) {
		
	}
	
	@Override
	 public Coordonnee choixAttaque() {
        if (derniereTouchee != null) {
            Coordonnee choix = continuerAttaque();
            if (choix != null) {
                dejaTire[choix.getLigne()][choix.getColonne()] = 1; 
                return choix;
            }
        }
        
        int taille = getTailleGrille();
        while (damierIndex < taille * taille) {
            int i = damierIndex / taille;
            int j = damierIndex % taille;
            damierIndex++;
            if ((i + j) % 2 == 0 && dejaTire[i][j] == 0) {
                Coordonnee choix = new Coordonnee(i, j);
                dejaTire[i][j] = 1;
                return choix;
            }
        }

     
        for (int i = 0; i < taille; i++) {
            for (int j = 0; j < taille; j++) {
                if (dejaTire[i][j] == 0) {
                    Coordonnee choix = new Coordonnee(i, j);
                    dejaTire[i][j] = 1;
                    return choix;
                }
            }
        }

  
        return null; 
    }

	 private Coordonnee continuerAttaque() {
	        int i = derniereTouchee.getLigne();
	        int j = derniereTouchee.getColonne();

	        if (directionActuelle != -1) {
	            Coordonnee suivant = attaquerDirection(i, j, directionActuelle);
	            if (suivant != null) return suivant;
	            derniereTouchee = premiereTouchee;
	            directionActuelle = -1; 
	        }

	        i = premiereTouchee.getLigne();
	        j = premiereTouchee.getColonne();

	        
	        if (caseValide(i - 1, j)) {
	            directionActuelle = 0;
	            return new Coordonnee(i - 1, j);
	        }
	       
	        if (caseValide(i + 1, j)) {
	            directionActuelle = 1;
	            return new Coordonnee(i + 1, j);
	        }
	     
	        if (caseValide(i, j - 1)) {
	            directionActuelle = 2;
	            return new Coordonnee(i, j - 1);
	        }
	      
	        if (caseValide(i, j + 1)) {
	            directionActuelle = 3;
	            return new Coordonnee(i, j + 1);
	        }
	        premiereTouchee = null;
	        derniereTouchee = null;
	        directionActuelle = -1;
	        return null; 
	    }
	 
	 private Coordonnee attaquerDirection(int i, int j, int direction) {
	        int ni = i;
	        int nj = j;
	        switch (direction) {
	            case 0: ni = i - 1; nj = j; break; 
	            case 1: ni = i + 1; nj = j; break; 
	            case 2: ni = i;     nj = j - 1; break; 
	            case 3: ni = i;     nj = j + 1; break;
	        }
	        if (caseValide(ni, nj)) {
	            return new Coordonnee(ni, nj);
	        }
	        return null;
	    }

	 private boolean caseValide(int i, int j) {
	        return i >= 0 && i < this.getTailleGrille() && j >= 0 && j < this.getTailleGrille() && dejaTire[i][j] == 0;
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
