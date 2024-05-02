package Control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modele.joueur;

public class joueurC implements Ijoueur {
	public Connection connectionDB(){
	try {
	conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/joueur","root","");

	System.out.println("Connection is established" );
	return conn;
	} catch (Exception e) {
	e.printStackTrace();
	System.out.println("connection failed");
	return null;
	}
	}
    public void AjouterJoueur(joueur j) {
        conn = connectionDB();
        try {

            PreparedStatement checkStatement = conn.prepareStatement("SELECT COUNT(*) FROM joueur WHERE nujoueur = ?");
            checkStatement.setInt(1, j.getnujoueur());
            ResultSet rs = checkStatement.executeQuery();
            rs.next();
            int rowCount = rs.getInt(1);
            checkStatement.close();

            if (rowCount > 0) {
                JOptionPane.showMessageDialog(null, "Le numéro de joueur existe déjà dans la table", "Attention", JOptionPane.ERROR_MESSAGE);
                return; 
            }
            
            ps = conn.prepareStatement("INSERT INTO joueur(nujoueur, nom, prenom, annais, nationalite) VALUES(?, ?, ?, ?, ?)");
            ps.setInt(1, j.getnujoueur());
            ps.setString(2, j.getNom());
            ps.setString(3, j.getPrenom());
            ps.setInt(4, j.getDatenais());
            ps.setString(5, j.getNationalite());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "the player has been added successfully");
            ps.close();
            conn.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de l'ajout du joueur", "Attention", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void ModifierJoueur(joueur j) {
        conn = connectionDB();
        try {

        	ps = conn.prepareStatement("UPDATE joueur SET nom = ?, prenom = ?, annais = ?, nationalite = ? WHERE nujoueur = ?");
            ps.setString(1, j.getNom());
            ps.setString(2, j.getPrenom());
            ps.setString(4, j.getNationalite());
            ps.setInt(3, j.getDatenais());
            ps.setInt(5, j.getnujoueur());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "update has been made successfully");
            ps.close();
            conn.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la modification du joueur", "Attention", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void DeleteJoueur(joueur j) {
        conn = connectionDB();
        try {
            ps = conn.prepareStatement("DELETE FROM joueur WHERE nujoueur = ?");
            ps.setInt(1, j.getnujoueur());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "the player has been deleted successfully");
            ps.close();
            conn.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "Delete is failed");
        }
    }

    public joueur ChercherJoueur(joueur j) {
        conn = connectionDB();
        joueur jj = new joueur();
        try {
            ps = conn.prepareStatement("SELECT * FROM joueur WHERE nujoueur = ?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1, j.getnujoueur());
            rs = ps.executeQuery();
            rs.last();
            int nbrerow = rs.getRow();
            if (nbrerow != 0) {
                rs.beforeFirst();
                while (rs.next()) {
                    jj.setnujoueur(rs.getInt(1));
                    jj.setNom(rs.getString(2));
                    jj.setPrenom(rs.getString(3));
                    jj.setNationalite(rs.getString(5));
                    jj.setDatenais(rs.getInt(4));
                }
                rs.close();
                return jj;
            } else {
                JOptionPane.showMessageDialog(null, "le joueur n'existe pas dans la table ", "Attention", JOptionPane.ERROR_MESSAGE);
            }
            rs.close();
            return jj;
        } catch (SQLException e1) {
            e1.printStackTrace();
            return null;
        }
    }
    private Connection conn;
    private PreparedStatement ps;
    private ResultSet rs;
    
    
}
