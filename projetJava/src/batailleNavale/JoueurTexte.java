package batailleNavale;

import java.util.Scanner;

public class JoueurTexte extends JoueurAvecGrille {
	private Scanner sc;

	public JoueurTexte(GrilleNavale g, String nom) {
		super(g, nom);
	}
	

	public JoueurTexte(GrilleNavale g) {
		super(g);
	}
	

	protected void retourAttaque(Coordonnee c, int etat) {
		//Joueur attaquant à fin tour de jeu
		System.out.println("Bilan d'attaque pour "+ this.getNom() +" :");
		if ( etat == GAMEOVER )
			System.out.println("Vous avez touché votre adversaire en "+c+" et coulé toute sa flotte c'est une victoire!");
		else if ( etat == TOUCHE)
			System.out.println("Vous avez touché votre adversaire en "+c);
		else if ( etat == COULE)
			System.out.println("Vous avez touché votre adversaire et coulé un bateau en" +c);
		else if ( etat == A_L_EAU)
		System.out.println("Vous avez visé dans l'eau :"+c);

	}

	protected void retourDefense(Coordonnee c, int etat) {
		//Joueur defeuseur fin tour de jeu;
		System.out.println("Bilan de défense pour "+ this.getNom() +" :");
		if ( etat == GAMEOVER )
			System.out.println("Votre adversaire a coulé votre dernier bateau vous avez perdu");
		else if ( etat == TOUCHE)
			System.out.println("Votre adversaire vous a touché en : "+c);
		else if ( etat == COULE)
			System.out.println("Votre adversaire a coulé un de vos navires en :" +c);
		else if ( etat == A_L_EAU)
		System.out.println("Votre adversaire a tiré en :"+c);
	}
	

	public Coordonnee choixAttaque() {
		sc = new Scanner(System.in);
		System.out.println( this.getNom()+"entrez la case que vous souhaitez viser : ");
		String coordonnee = sc.nextLine();
		return new Coordonnee(coordonnee);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
