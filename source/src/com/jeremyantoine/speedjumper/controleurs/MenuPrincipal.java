package com.jeremyantoine.speedjumper.controleurs;

import com.jeremyantoine.speedjumper.donnees.CollectionRessources;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouchesFX;
import com.jeremyantoine.speedjumper.jeu.EtatJeu;
import com.jeremyantoine.speedjumper.jeu.Jeu;
import com.jeremyantoine.speedjumper.observateurs.Sujet;
import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.event.ActionEvent;

/**
 * controleur de la fenetre MenuPrincipal
 */
public class MenuPrincipal extends Sujet {
    private Navigateur navigateur;
    private Jeu jeu;

    /**
     * Constructeur de la fenetre
     * @param navigateur navigateur
     * @throws IllegalArgumentException
     */
    public MenuPrincipal(Navigateur navigateur) throws IllegalArgumentException {
        if (navigateur == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ne peut aps être null.");
        }
        this.navigateur = navigateur;
        CollectionRessources ressources = CollectionRessources.getInstance();
        RecuperateurDeTouches recuperateur = new RecuperateurDeTouchesFX(ressources.getFichierConfigurationTouches(), navigateur.getSceneCourante());
        jeu = new Jeu(recuperateur);
        jeu.initialise();
        jeu.jouer();
        jeu.getManagerEtats().setEtatCourant(EtatJeu.ETAT_MENU_PAUSE);
    }

    /**+
     * methode fermant la fenetre
     * @param actionEvent
     */
    public void fermetureFenetre(ActionEvent actionEvent) {
        jeu.ferme();
        navigateur.getStage().close();
    }

    /**
     * methode ouvrant la vues des niveaux
     * @param event
     */
    public void ouvrirMenuJeu(ActionEvent event) {
        MenuJouer menu = new MenuJouer(navigateur, jeu);
        navigateur.naviguerVers(NomFenetre.MENU_JOUER, menu);
    }

    /**
     * Methode permettant de gerer l'ouverture de la vue option
     * @param event
     */
    public void ouvrirOptions(ActionEvent event) {
        MenuOptions menu = new MenuOptions(navigateur, jeu);
        navigateur.naviguerVers(NomFenetre.MENU_OPTIONS, menu);
    }
}
