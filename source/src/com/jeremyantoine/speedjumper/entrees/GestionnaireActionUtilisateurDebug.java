package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.monde.Niveau;

import java.util.List;

public class GestionnaireActionUtilisateurDebug extends GestionnaireActionUtilisateur {
    private final Commande flecheGauche;
    private final Commande flecheDroite;
    private final Commande flecheBas;
    private final Commande flecheHaut;
    private final Commande espace;
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
