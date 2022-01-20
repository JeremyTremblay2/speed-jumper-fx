package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.monde.Niveau;


/**
 * Classe gerant les actions utilisateur pendant le jeu
 */
public class GestionnaireActionUtilisateurJeu extends GestionnaireActionUtilisateur {
    private Commande flecheGauche;
    private Commande flecheDroite;
    private Commande espace;
    private Commande echap;
    private Commande aucuneAction;
    private Niveau niveauCourant;

    /**
     * constructeur de la classe
     * @param recuperateur recuperateur de touche
     * @param niveau niveai en cour pour les actions
     * @throws IllegalArgumentException
     */
    public GestionnaireActionUtilisateurJeu(RecuperateurDeTouches recuperateur, Niveau niveau)
            throws IllegalArgumentException {
        super(recuperateur);
        if (niveau == null) {
            throw new IllegalArgumentException("Le niveau passé en paramètre ne peut pas être null.");
        }
        niveauCourant = niveau;
        flecheGauche = new CommandeDeplacement(Direction.GAUCHE, niveau);
        flecheDroite = new CommandeDeplacement(Direction.DROITE, niveau);
        espace = new CommandeSaut(niveau);
        aucuneAction = new CommandeNulle();
    }

    /**
     * attribue un action en fonction de la touche pressée
     * @return
     */
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
