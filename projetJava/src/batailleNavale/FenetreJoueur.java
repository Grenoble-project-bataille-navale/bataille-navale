package batailleNavale;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.SwingConstants;
import java.awt.Font;

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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(45*taille, 45*taille, 90*taille, 52*taille);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.grilleTirs = new GrilleGraphique(taille);
		this.grilleDefense = new GrilleNavaleGraphique(taille);
		this.grilleDefense.getGrilleGraphique().setClicActive(false);
		this.grilleDefense.getGrilleGraphique().colorie(new Coordonnee(0, 0), new Coordonnee(taille-1, taille-1), new Color(0,51,102));
		this.grilleTirs.setClicActive(true);
		setContentPane(contentPane);
		this.setTitle(nom);
		contentPane.setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.CENTER, 5, 5);
		panel.setLayout(fl_panel);
		
		JLabel lblGrilleDattaque = new JLabel("Grille d'attaque");
		lblGrilleDattaque.setFont(new Font("Dialog", Font.BOLD, 14));
		lblGrilleDattaque.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblGrilleDattaque);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblGrilleDeDfense = new JLabel("Grille de défense");
		lblGrilleDeDfense.setFont(new Font("Dialog", Font.BOLD, 14));
		lblGrilleDeDfense.setHorizontalAlignment(SwingConstants.CENTER);
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
					FenetreJoueur frame = new FenetreJoueur("oui", 18);
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
