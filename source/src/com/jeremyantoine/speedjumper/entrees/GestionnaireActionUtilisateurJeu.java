package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.monde.Niveau;

public class GestionnaireActionUtilisateurJeu extends GestionnaireActionUtilisateur {
    private Commande flecheGauche;
    private Commande flecheDroite;
    private Commande espace;
    private Commande echap;
    private Commande aucuneAction;
    private Niveau niveauCourant;

    public GestionnaireActionUtilisateurJeu(RecuperateurDeTouches recuperateur, Niveau niveau) {
        super(recuperateur);
        niveauCourant = niveau;
        flecheGauche = new CommandeDeplacement(Direction.GAUCHE, niveau);
        flecheDroite = new CommandeDeplacement(Direction.DROITE, niveau);
        espace = new CommandeSaut(niveau);
        aucuneAction = new CommandeNulle();
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
