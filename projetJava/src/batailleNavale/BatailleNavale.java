package batailleNavale;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BatailleNavale {
    private Joueur joueur1, joueur2;
    private int tailleGrille;
    private JFrame frame;
    
   

 public BatailleNavale() {

    // la fenêtre principale
	Color couleurPolice = Color.LIGHT_GRAY;
    frame = new JFrame("Bataille Navale");
    frame.setSize(600, 400);
    Font myFont1 = new Font("Arial", Font.BOLD, 16);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Panel conteneur = new Panel();
    conteneur.setLayout(new GridLayout(6, 2));
    conteneur.setBackground(Color.BLACK);
    
  
    frame.add(conteneur);

// la taille de la grille
    JTextField tailleField = new JTextField("10", SwingConstants.CENTER);
    tailleField.setHorizontalAlignment(JTextField.CENTER);
    JLabel tailleLabel = new JLabel("Taille de la grille :", SwingConstants.CENTER);
    tailleLabel.setFont(myFont1);
    tailleLabel.setForeground(couleurPolice);
    
 


// les champs du joueur 1
    JLabel joueur1Label = new JLabel("Nom Joueur 1 :", SwingConstants.CENTER);
    joueur1Label.setFont(myFont1);
    joueur1Label.setForeground(couleurPolice);
    JLabel typeJoueur1Label = new JLabel("Type Joueur 1 :", SwingConstants.CENTER);
    typeJoueur1Label.setFont(myFont1);
    typeJoueur1Label.setForeground(couleurPolice);
    JTextField nomJoueur1Field = new JTextField("Joueur 1");
    nomJoueur1Field.setHorizontalAlignment(JTextField.CENTER);
    JComboBox<String> typeJoueur1 = new JComboBox<>(new String[]{"Joueur Graphique", "Joueur Texte", "IA facile", "IA Moyenne", "IA difficile"});
    //typeJoueur1.setAlignmentX(JComboBox.CENTER_ALIGNMENT);
    ((JLabel)typeJoueur1.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);


// les champs du joueur 2
    JLabel joueur2Label = new JLabel("Nom Joueur 2 :",SwingConstants.CENTER);
    joueur2Label.setFont(myFont1);
    joueur2Label.setForeground(couleurPolice);
    JLabel typeJoueur2Label = new JLabel("Type Joueur 2 :",SwingConstants.CENTER);
    typeJoueur2Label.setForeground(couleurPolice);
    typeJoueur2Label.setFont(myFont1);
    JTextField nomJoueur2Field = new JTextField("Joueur 2");
    nomJoueur2Field.setHorizontalAlignment(JTextField.CENTER);
    JComboBox<String> typeJoueur2 = new JComboBox<>(new String[]{"Joueur Graphique", "Joueur Texte", "IA facile", "IA Moyenne", "IA difficile"});
    ((JLabel)typeJoueur2.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);


// Le bouton lancer
    JButton boutonLancer = new JButton("Lancer partie");

// Les composants de la fenêtre
   conteneur.add(tailleLabel);
   conteneur.add(tailleLabel);
   conteneur.add(tailleField);
   conteneur.add(joueur1Label);
   conteneur.add(nomJoueur1Field);
   conteneur.add(typeJoueur1Label);
   conteneur.add(typeJoueur1);
   conteneur.add(joueur2Label);
   conteneur.add(nomJoueur2Field);
   conteneur.add(typeJoueur2Label);
   conteneur.add(typeJoueur2);
   conteneur.add(new JLabel()); 
   conteneur.add(boutonLancer);
    
    
    frame.setVisible(true);



// action bouton
    boutonLancer.addActionListener(e -> {
        try {
        tailleGrille = Integer.parseInt(tailleField.getText());
        if ( tailleGrille < 10 || tailleGrille > 25) {
            JOptionPane.showMessageDialog(frame, "La taille de la grille doit être entre 10 et 26", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }
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
    	fj.setLocationRelativeTo(null);
    	fj.setVisible(true);
        return new JoueurGraphique(fj.getGrilleDefense(), fj.getGrilleTirs(), nom);
    }else if (type.equals("Joueur Texte")){
        return new JoueurTexte(grilleDefense, nom);
    } else if (type.equals("IA facile")){
        return new JoueurAutoNiveau1(grilleDefense, nom);
    }
    else if (type.equals("IA Moyenne"))
    {
        return new JoueurAutoNiveau2(grilleDefense, nom);

    }
    else {
    	return new JoueurAutoNiveau3(grilleDefense, nom);
    }
 }


 private void demarrerPartie(){
    new Thread(() -> joueur1.jouerAvec(joueur2)).start();

 }


 public static void main(String[] args){
    new BatailleNavale();
    
 }



 }


