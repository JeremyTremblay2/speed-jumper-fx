package com.jeremyantoine.speedjumper.controleurs;

import com.jeremyantoine.speedjumper.jeu.Jeu;
import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

/**
 * controleur de la vue Pause
 */
public class MenuPause {
    private Navigateur navigateur;

    /**
     * contructeur
     * @param navigateur la navigateur
     * @param jeu le jeu en cour
     * @throws IllegalArgumentException
     */
    public MenuPause(Navigateur navigateur, Jeu jeu) throws IllegalArgumentException {
        if (navigateur == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ne peut pas être null.");
        }
        this.navigateur = navigateur;
    }

    /**
     * methode permettant de revenir au menu
     * @param event
     */
    @FXML
    public void retourMenu(ActionEvent event) {
        System.out.println("j'y suis");
        navigateur.faireDemiTour();
        navigateur.faireDemiTour();
    }

    /**
     * methode permettant de reprendre le jeu
     * @param event
     */
    @FXML
    public void reprendreJeu(ActionEvent event) {
        System.out.println("j'y suis presque");
        navigateur.faireDemiTour();
    }
}
