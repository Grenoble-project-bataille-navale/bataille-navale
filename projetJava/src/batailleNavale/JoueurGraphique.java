package batailleNavale;

import java.awt.Color;
import java.io.File;

import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.JOptionPane;

public class JoueurGraphique extends JoueurAvecGrille  {
private GrilleGraphique grilleTirs;
public JoueurGraphique(GrilleNavaleGraphique grilleDefense, GrilleGraphique grilleTirs, String nom) {
	super(grilleDefense,nom);
	this.grilleTirs = grilleTirs;
}
public JoueurGraphique(GrilleNavaleGraphique grilleDefense,GrilleGraphique grilleTirs) {
	super(grilleDefense);
	this.grilleTirs = grilleTirs;
	
}
public Coordonnee choixAttaque() {
	return grilleTirs.getCoordonneeSelectionnee();
}
protected void retourDefense(Coordonnee c, int etat) {
	switch (etat) {
	case TOUCHE: JOptionPane.showMessageDialog(grilleTirs, "Vous avez été touché en " + c);
		
		
	
	
	break;
	case COULE:	JOptionPane.showMessageDialog(grilleTirs, "Votre navire a coulé en " + c);
	try {
		ajouterSound("coulerSon.wav");
	} catch (Exception e) {
		// TODO Auto-generated catch block
	}
	break;
	case GAMEOVER:				JOptionPane.showMessageDialog(grilleTirs, "Vous avez Perdu!!!");
	
}
}
	protected void retourAttaque(Coordonnee c, int etat) {
			boolean enFeu = etat == A_L_EAU ? false : true;
			grilleTirs.setImage(c, enFeu);
			switch (etat) {
			case TOUCHE: JOptionPane.showMessageDialog(grilleTirs, "Vous avez touché un navire en " + c);
			
			break;
			case COULE:	JOptionPane.showMessageDialog(grilleTirs, "Vous avez coulé un navire en " + c);
			try {
				ajouterSound("coulerSon.wav");
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
			break;
			case GAMEOVER:				JOptionPane.showMessageDialog(grilleTirs, "Vous avez gagné!!!");
}
}
	
	protected void ajouterSound(String chemin) throws Exception {
		File f = new File("./src/batailleNavale/"+chemin);
	    AudioInputStream audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());  
	    Clip clip = AudioSystem.getClip();
	    clip.open(audioIn);
	    clip.start();
	}
}
