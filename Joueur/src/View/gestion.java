package view;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modele.joueur;
import control.joueurC;

public class gestion extends JFrame implements ActionListener{
	
	private JLabel nujoueur, nom, prenom, nationalite, datenaissance;
    private JTextField tnujoueur, tnom, tprenom, tnationalite, tdatenais;
    private JButton ajouter, modifier, supprimer, chercher;
    
	public static void main(String[] args) {
        gestion frame = new gestion();
        frame.setTitle("Gestion des joueurs");
        frame.setSize(750, 150);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.initComponents();
        frame.setVisible(true);
	}
	
	public gestion() {
        super();
    }

    public void initComponents() {
        Container c = getContentPane();
        c.setLayout(new FlowLayout());

        nujoueur = new JLabel("Identifiant ");
        c.add(nujoueur);
        tnujoueur = new JTextField(10);
        c.add(tnujoueur);

        nom = new JLabel("Nom ");
        c.add(nom);
        tnom = new JTextField(10);
        c.add(tnom);

        prenom = new JLabel("Prénom ");
        c.add(prenom);
        tprenom = new JTextField(10);
        c.add(tprenom);
        
        nationalite = new JLabel("Nationalité ");
        c.add(nationalite);
        tnationalite = new JTextField(10);
        c.add(tnationalite);
        
        datenaissance = new JLabel("Date de naissance ");
        c.add(datenaissance);
        tdatenais = new JTextField(4);
        c.add(tdatenais);

        ajouter = new JButton("Ajouter");
        chercher = new JButton("Chercher");
        modifier = new JButton("Modifier");
        supprimer = new JButton("Supprimer");
        c.add(ajouter);
        c.add(chercher);
        c.add(modifier);
        c.add(supprimer);

        modifier.addActionListener(this);
        ajouter.addActionListener(this);
        chercher.addActionListener(this);
        supprimer.addActionListener(this);
    }

    public void actionPerformed(ActionEvent a) {
        Object source = a.getSource();
        if (source == ajouter) {
            String txtnujoueur = tnujoueur.getText();
            String txtnom = tnom.getText();
            String txtprenom = tprenom.getText();
            String txtnationalite = tnationalite.getText();
            String txtdatenais = tdatenais.getText();
            try {
                int nujoueur = Integer.parseInt(txtnujoueur);
                int datenais = Integer.parseInt(txtdatenais);
                joueur j = new joueur(nujoueur, txtnom, txtprenom,datenais,txtnationalite);
                joueurC jc = new joueurC();
                jc.AjouterJoueur(j);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Le champ identifiant doit être un nombre entier", "Attention", JOptionPane.ERROR_MESSAGE);
            }
        } else if (source == modifier) {
            String txtnujoueur = tnujoueur.getText();
            try {
                int nujoueur = Integer.parseInt(txtnujoueur);
                int datenais = Integer.parseInt(tdatenais.getText());
                joueur j = new joueur(nujoueur, tnom.getText(), tprenom.getText(), datenais ,tnationalite.getText());
                joueurC ecm = new joueurC();
                ecm.ModifierJoueur(j);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Verifier le champs identifiant doit etre nombre", "attention", JOptionPane.ERROR_MESSAGE);
            }
        } else if (source == chercher) {
            String txtnujoueur = tnujoueur.getText();
            try {
                int nujoueur = Integer.parseInt(txtnujoueur);
                joueur j = new joueur(nujoueur);
                joueurC ecm = new joueurC();
                joueur echer = ecm.ChercherJoueur(j);
                if (echer != null) {
                    tnom.setText(echer.getNom());
                    tprenom.setText(echer.getPrenom());
                    tdatenais.setText(String.valueOf(echer.getDatenais()));
                    tnationalite.setText(echer.getNationalite());
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Verifier le champs identifiant doit etre un nombre", "attention", JOptionPane.ERROR_MESSAGE);
            }
        } else if (source == supprimer) {
            String txtnujoueur = tnujoueur.getText();
            try {
                int nujoueur = Integer.parseInt(txtnujoueur);
                joueur j = new joueur(nujoueur);
                joueurC ecm = new joueurC();
                ecm.DeleteJoueur(j);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Verifier le champs identifiant doit etre nombre", "attention", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
   
}