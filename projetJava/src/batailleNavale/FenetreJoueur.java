package batailleNavale;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;

public class FenetreJoueur extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private GrilleNavaleGraphique grilleDefense;
	private GrilleGraphique grilleTirs;

	/**
	 * Launch the application.
	 */
	public FenetreJoueur() {
	this("Nom du joueur", 10);
	}
	public FenetreJoueur(String nom, int taille) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(40*taille, 40*taille, 85*taille, 50*taille);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.grilleTirs = new GrilleGraphique(taille);
		this.grilleDefense = new GrilleNavaleGraphique(taille);
		this.grilleDefense.getGrilleGraphique().setClicActive(false);
		this.grilleTirs.setClicActive(true);
		setContentPane(contentPane);
		this.setTitle(nom);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel lblGrilleDattaque = new JLabel("Grille d'attaque");
		panel.add(lblGrilleDattaque);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEADING, 5, 5));
		
		JLabel lblGrilleDeDfense = new JLabel("Grille de défense");
		panel_1.add(lblGrilleDeDfense);
		panel.add(grilleTirs);
		panel_1.add(grilleDefense.getGrilleGraphique());
	}
	public GrilleGraphique getGrilleTirs() {
		return this.grilleTirs;
	}
	public GrilleNavaleGraphique getGrilleDefense() {
		return this.grilleDefense;
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenetreJoueur frame = new FenetreJoueur("oui", 25);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */

}
