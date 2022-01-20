package com.jeremyantoine.speedjumper.entrees;

import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.monde.Niveau;


/**
 * Classe gerant les actions utilisateur pendant le jeu
 */
import java.util.List;

public class GestionnaireActionUtilisateurJeu extends GestionnaireActionUtilisateur {
    private final Commande flecheGauche;
    private final Commande flecheDroite;
    private final Commande espace;
    private Commande echap;
    private final Commande aucuneAction;
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
        if (lesTouches.contains(Touche.ECHAP)) {
            pause = true;
        }
        return lesCommandes;
    }
}
