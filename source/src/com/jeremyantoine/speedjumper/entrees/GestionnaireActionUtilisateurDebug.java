package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.monde.Niveau;

import java.util.List;

public class GestionnaireActionUtilisateurDebug extends GestionnaireActionUtilisateur {
    private Commande flecheGauche;
    private Commande flecheDroite;
    private Commande flecheBas;
    private Commande flecheHaut;
    private Commande espace;
    private Commande aucuneAction;

    public GestionnaireActionUtilisateurDebug(RecuperateurDeTouches recuperateur, Niveau niveau)
            throws IllegalArgumentException {
        super(recuperateur, niveau);
        flecheGauche = new CommandeDeplacement(Direction.GAUCHE, niveau);
        flecheDroite = new CommandeDeplacement(Direction.DROITE, niveau);
        flecheBas = new CommandeDeplacement(Direction.BAS, niveau);
        flecheHaut = new CommandeDeplacement(Direction.HAUT, niveau);
        espace = new CommandeSaut(niveau);
        aucuneAction = new CommandeNulle();
    }

    @Override
    public void setNiveauCourant(Niveau niveauCourant) {
        super.setNiveauCourant(niveauCourant);
        flecheGauche = new CommandeDeplacement(Direction.GAUCHE, niveauCourant);
        flecheDroite = new CommandeDeplacement(Direction.DROITE, niveauCourant);
        flecheBas = new CommandeDeplacement(Direction.BAS, niveauCourant);
        flecheHaut = new CommandeDeplacement(Direction.HAUT, niveauCourant);
        espace = new CommandeSaut(niveauCourant);
    }

    @Override
    public List<Commande> attribuerAction() {
        lesTouches = recuperateurDeTouches.detecte();
        lesCommandes.clear();

        if (lesTouches.contains(Touche.ESPACE)) {
            lesCommandes.add(espace);
        }
        if (lesTouches.contains(Touche.FLECHE_DROITE)) {
            lesCommandes.add(flecheDroite);
        }
        if (lesTouches.contains(Touche.FLECHE_GAUCHE)) {
            lesCommandes.add(flecheGauche);
        }
        if (lesTouches.contains(Touche.FLECHE_HAUT)) {
            lesCommandes.add(flecheHaut);
        }
        if (lesTouches.contains(Touche.FLECHE_BAS)) {
            lesCommandes.add(flecheBas);
        }
        if (lesTouches.contains(Touche.ECHAP)) {
            pause = true;
        }
        return lesCommandes;
    }
}
