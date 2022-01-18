package com.jeremyantoine.speedjumper.controleurs;

import com.jeremyantoine.speedjumper.donnees.CollectionRessources;
import com.jeremyantoine.speedjumper.donnees.NomFenetre;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouchesFX;
import com.jeremyantoine.speedjumper.jeu.Jeu;
import com.jeremyantoine.speedjumper.observateurs.Sujet;
import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.event.ActionEvent;

public class MenuPrincipal extends Sujet {
    private Navigateur navigateur;
    private Jeu jeu;

    public MenuPrincipal(Navigateur navigateur) throws IllegalArgumentException {
        if (navigateur == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ne peut aps être null.");
        }
        this.navigateur = navigateur;
        CollectionRessources ressources = CollectionRessources.getInstance();
        RecuperateurDeTouches recuperateur = new RecuperateurDeTouchesFX(ressources.getFichierConfigurationTouches(), navigateur.getSceneCourante());
        jeu = new Jeu(recuperateur);
    }

    public void fermetureFenetre(ActionEvent actionEvent) {
        navigateur.getStage().close();
    }

    public void ouvrirMenuJeu(ActionEvent event) {
        System.out.println("ouverture menu jeu");
        MenuJouer menu = new MenuJouer(navigateur, jeu);
        navigateur.naviguerVers(NomFenetre.MENU_JOUER, menu);
    }

    public void ouvrirOptions(ActionEvent event) {
        MenuOptions menu = new MenuOptions(navigateur, jeu);
        navigateur.naviguerVers(NomFenetre.MENU_OPTIONS, menu);
    }
}
