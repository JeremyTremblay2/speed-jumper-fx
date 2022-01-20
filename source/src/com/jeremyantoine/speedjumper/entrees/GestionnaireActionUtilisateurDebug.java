package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.monde.Niveau;

public class GestionnaireActionUtilisateurDebug extends GestionnaireActionUtilisateur {
    private Commande flecheGauche;
    private Commande flecheDroite;
    private Commande flecheBas;
    private Commande flecheHaut;
    private Commande espace;
    private Commande echap;
    private Commande aucuneAction;
    private Niveau niveauCourant;

    public GestionnaireActionUtilisateurDebug(RecuperateurDeTouches recuperateur, Niveau niveau)
            throws IllegalArgumentException {
        super(recuperateur);
        if (niveau == null) {
            throw new IllegalArgumentException("Le niveau passé en paramètre ne peut pas être null.");
        }
        niveauCourant = niveau;
        flecheGauche = new CommandeDeplacement(Direction.GAUCHE, niveau);
        flecheDroite = new CommandeDeplacement(Direction.DROITE, niveau);
        flecheBas = new CommandeDeplacement(Direction.BAS, niveau);
        flecheHaut = new CommandeDeplacement(Direction.HAUT, niveau);
        espace = new CommandeSaut(niveau);
        aucuneAction = new CommandeNulle();
    }

    @Override
    public Commande attribuerAction() {
        lesTouches = recuperateurDeTouches.detecte();

        if (lesTouches.contains(Touche.ESPACE)) {
            return espace;
        }
        if (lesTouches.contains(Touche.FLECHE_DROITE)) {
            return flecheDroite;
        }
        if (lesTouches.contains(Touche.FLECHE_GAUCHE)) {
            return flecheGauche;
        }
        if (lesTouches.contains(Touche.FLECHE_HAUT)) {
            return flecheHaut;
        }
        if (lesTouches.contains(Touche.FLECHE_BAS)) {
            return flecheBas;
        }
        if (lesTouches.contains(Touche.ECHAP)) {
            return echap;
        }
        return aucuneAction;
    }
}
