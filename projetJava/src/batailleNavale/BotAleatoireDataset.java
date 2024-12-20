package batailleNavale;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BotAleatoireDataset extends JoueurAuto {

   private int[][] dejaTire;
   private List<String> dataset;
   private static int idPartie = 1;

   // Constructeur avec nom du joueur
   public BotAleatoireDataset(GrilleNavale g, String nom) {
       super(g, nom);
       this.dejaTire = new int[g.getTaille()][g.getTaille()];
       
       this.dataset = new ArrayList<>();
   }

   // Constructeur sans nom de joueur
   public BotAleatoireDataset(GrilleNavale g) {
       super(g);
       
       this.dejaTire = new int[g.getTaille()][g.getTaille()];// tableau de la taille de la grille
       
       this.dataset = new ArrayList<>();// initialise la liste du dataset
   }

   @Override
   public Coordonnee choixAttaque() {

       Coordonnee choix;

       do {
           int x = (int) (Math.random() * this.getTailleGrille());

           int y = (int) (Math.random() * this.getTailleGrille());// genere coordonnee aleatorie

           choix = new Coordonnee(x, y);

       } while (dejaTire[choix.getLigne()][choix.getColonne()] == 1);// verifie que la case na pas deja été ciblée

       dejaTire[choix.getLigne()][choix.getColonne()] = 1;// marque la case comme ciblé et la retourne
       
       return choix;
   }

   @Override // genere reponse apres chaque attaque, fonction du resultat
   protected void retourAttaque(Coordonnee c, int etat) {

       String resultat;
       switch (etat) {
           case TOUCHE:
               resultat = "TOUCHE";
               break;
           case COULE:
               resultat = "COULE";
               break;
           case A_L_EAU:
               resultat = "A_L_EAU";
               break;
           default:
               resultat = "INCONNU";
       }// genere reponse en fonction du resultat

       String entry = idPartie + "," + c.toString() + "," + resultat;

       dataset.add(entry);

       if (etat == GAMEOVER) {
           exportDataset();
           idPartie++;
       }// le resultat devient un chaine lisible pour le dataset
       // données de tir en ligne CSV, ajout dataset
       // puis export dataset quand game over
   }

   private void exportDataset() {
       try (FileWriter writer = new FileWriter("dataset.csv", true)) {

           for (String entry : dataset) {
               writer.write(entry + "\n");
           }//https://thierry-leriche-dessirier.developpez.com/tutoriels/java/csv-avec-java/
           
           dataset.clear(); // Reset dataset for next game

       } catch (IOException e) {
           System.err.println("Erreur lors de l'exportation du dataset : " + e.getMessage());
       }
   }
}
