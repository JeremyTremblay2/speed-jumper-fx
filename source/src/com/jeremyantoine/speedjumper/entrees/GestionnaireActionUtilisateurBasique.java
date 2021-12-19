package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.logique.Direction;

public class GestionnaireActionUtilisateurBasique extends GestionnaireActionUtilisateur {
    private Commande flecheGauche = new CommandeDeplacement(Direction.GAUCHE);
    private Commande flecheDroite = new CommandeDeplacement(Direction.DROITE);
    private Commande espace;
    private Commande echap;
    private Commande aucuneAction = new CommandeNulle();

    public GestionnaireActionUtilisateurBasique(RecuperateurDeTouches recuperateur) {
        super(recuperateur);
    }

    @Override
    public Commande attribuerAction() {
        lesTouches = recuperateurDeTouches.detecte();

        if (lesTouches.contains(Touche.FLECHE_DROITE)) {
            return flecheDroite;
        }
        if (lesTouches.contains(Touche.FLECHE_GAUCHE)) {
            return flecheGauche;
        }
        if (lesTouches.contains(Touche.ESPACE)) {
            return espace;
        }
        if (lesTouches.contains(Touche.ECHAP)) {
            return echap;
        }
        return aucuneAction;
    }
}
