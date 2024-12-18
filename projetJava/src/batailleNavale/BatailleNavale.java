package batailleNavale;

import javax.swing.*;
import java.awt.*;



public class BatailleNavale {
    private Joueur joueur1, joueur2;
    private int tailleGrille;
    private JFrame frame;
    
   

 public BatailleNavale() {

    // la fenêtre principale
    
    frame = new JFrame("Bataille Navale");
    frame.setSize(400, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLayout(new GridLayout(6, 2));


// la taille de la grille

    JTextField tailleField = new JTextField("10");
    JLabel tailleLabel = new JLabel("Taille de la grille :");


// les champs du joueur 1
   
   
    JLabel joueur1Label = new JLabel("Nom Joueur 1 :");
    JLabel typeJoueur1Label = new JLabel("Type Joueur 1 :");
    JTextField nomJoueur1Field = new JTextField("Joueur 1");
    JComboBox<String> typeJoueur1 = new JComboBox<>(new String[]{"Joueur Graphique", "Joueur Texte", "IA facile", "IA Moyenne"});



// les champs du joueur 2

    
    JLabel joueur2Label = new JLabel("Nom Joueur 2 :");
    JLabel typeJoueur2Label = new JLabel("Type Joueur 2 :");
    JTextField nomJoueur2Field = new JTextField("Joueur 2");
    JComboBox<String> typeJoueur2 = new JComboBox<>(new String[]{"Joueur Graphique", "Joueur Texte", "IA facile", "IA Moyenne"});


// Le bouton lancer


    JButton boutonLancer = new JButton("Lancer partie");



// Les composants de la fenêtre
    frame.add(tailleLabel);
        frame.add(tailleField);
        frame.add(joueur1Label);
        frame.add(nomJoueur1Field);
        frame.add(typeJoueur1Label);
        frame.add(typeJoueur1);
        frame.add(joueur2Label);
        frame.add(nomJoueur2Field);
        frame.add(typeJoueur2Label);
        frame.add(typeJoueur2);
        frame.add(new JLabel()); 
        frame.add(boutonLancer);
    
    
    frame.setVisible(true);



// action bouton


    boutonLancer.addActionListener(e -> {
        try {
        tailleGrille = Integer.parseInt(tailleField.getText());
        joueur1 = creerJoueur(nomJoueur1Field.getText(), (String) typeJoueur1.getSelectedItem(), tailleGrille);
        joueur2 = creerJoueur(nomJoueur2Field.getText(), (String) typeJoueur2.getSelectedItem(), tailleGrille);
        demarrerPartie();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "La taille de la grille doit être un nombre valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    });

 }


// création joueur selon type


 private Joueur creerJoueur(String nom, String type, int taille){
    GrilleNavaleGraphique grilleDefense = new GrilleNavaleGraphique(taille);
    if (type.equals("Joueur Graphique")){
    	FenetreJoueur fj= new FenetreJoueur(nom, taille);
    	fj.setVisible(true);
        return new JoueurGraphique(fj.getGrilleDefense(), fj.getGrilleTirs(), nom);
    }else if (type.equals("Joueur Texte")){
        return new JoueurTexte(grilleDefense, nom);
    } else if (type.equals("IA facile")){
        return new JoueurAutoNiveau1(grilleDefense, nom);
    }
    else {
        return new JoueurAutoNiveau2(grilleDefense, nom);

    }
 }


 private void demarrerPartie(){
    new Thread(() -> joueur1.jouerAvec(joueur2)).start();

 }


 public static void main(String[] args){
    new BatailleNavale();
 }



 }


