package batailleNavale;

import java.util.LinkedList;
import java.util.Queue;// le but est de fabriquer "une liste des attaques à venir"

public class JoueurAutoStrategique extends JoueurAuto {
	
	//https://www.ocamil.com/index.php/javaee/java-thread/files-d-attente/19-java-thread
	//https://docs.oracle.com/fr-fr/iaas/Content/queue/queue-management.htm

   private int[][] dejaTire;

   private Queue<Coordonnee> tirsCibles;

   // Constructeur avec nom du joueur
   public JoueurAutoStrategique(GrilleNavale g, String nom) {
      super(g, nom);
      this.dejaTire = new int[g.getTaille()][g.getTaille()];
   
      this.tirsCibles = new LinkedList<>();
   }

   // Constructeur sans nom de joueur
   public JoueurAutoStrategique(GrilleNavale g) {

       super(g);
       this.dejaTire = new int[g.getTaille()][g.getTaille()];// allocation de place 
       this.tirsCibles = new LinkedList<>();// tirsCibles est initialisé en tant que file vide
   }

   @Override
   public Coordonnee choixAttaque() {
       // Si on a des cibles prioritaires, on tire sur celles-ci en premier.

        if(!tirsCibles.isEmpty()) {
           return tirsCibles.poll(); //retour de la prochaine coord prio
       }

       // Sinon, on choisit une case aléatoire non encore attaquée.
       Coordonnee choix;

       do {
           int x = (int)(Math.random() * this.getTailleGrille());
           int y = (int)(Math.random() * this.getTailleGrille());
          
           choix = new Coordonnee(x, y);
       } while (dejaTire[choix.getLigne()][choix.getColonne()] == 1);
       // genere coord aleatoire jusqu a tomber sur une case non encore ciblée

       dejaTire[choix.getLigne()][choix.getColonne()] = 1; // la case tirée est marquée 
       
       return choix;
   }

   @Override
   protected void retourAttaque(Coordonnee c, int etat) {

       if (etat == TOUCHE || etat == COULE) {

           ajouterCasesVoisines(c);

           if (etat == COULE) {
               // Si le navire est coulé, on peut vider les cibles prioritaires restantes.
               tirsCibles.clear(); // nettoyagecibles 
           }

       }
   }

   private void ajouterCasesVoisines(Coordonnee c) {
       int[][] directions = {
// cases haut, bas, gau, droite ajoutés dans tirsCible
           {-1, 0}, 
           {1, 0}, 
           {0, -1}, 
           {0, 1}
       };

       for (int[] dir : directions) {

           int x = c.getLigne() + dir[0];
           int y = c.getColonne() + dir[1];

           if (x >= 0 && x < getTailleGrille() && y >= 0 && y < getTailleGrille()) {

               Coordonnee voisine = new Coordonnee(x, y);

               if (dejaTire[x][y] == 0 && !tirsCibles.contains(voisine)) {

                   tirsCibles.add(voisine);
               }// on parcourt chaque direction et on calcule les coordonnées voisines

           }// on joue au hasard mais on a un mecanisme de calcul de tirs prioritaires,
           // en evitant les repetitions de tir, avec un systeme de file pour l execution des 
           // tirs prioritaires

       }

   }
}
