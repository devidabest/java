package control;

import modele.joueur;

public interface Ijoueur {
    public void AjouterJoueur(joueur j);
    public void ModifierJoueur(joueur j);
    public void DeleteJoueur(joueur j);
}