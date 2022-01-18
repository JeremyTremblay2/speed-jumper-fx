package com.jeremyantoine.speedjumper.controleurs;

import com.jeremyantoine.speedjumper.donnees.CollectionRessources;
import com.jeremyantoine.speedjumper.donnees.NomFenetre;
import com.jeremyantoine.speedjumper.observateurs.Sujet;
import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuPrincipal extends Sujet {
    private Navigateur navigateur;

    public MenuPrincipal(Navigateur navigateur) throws IllegalArgumentException {
        if (navigateur == null) {
            throw new IllegalArgumentException("Le navigateur passé en paramètre ne peut aps être null.");
        }
        this.navigateur = navigateur;
    }

    public void fermetureFenetre(ActionEvent actionEvent) {
        navigateur.getStage().close();
    }

    public void ouvrirMenuJeu(ActionEvent event) {
        MenuJouer menu = new MenuJouer(navigateur);
        navigateur.naviguerVers(NomFenetre.MENU_JOUER, menu);
    }

    public void ouvrirOptions(ActionEvent event) {
        MenuOptions menu = new MenuOptions(navigateur);
        navigateur.naviguerVers(NomFenetre.MENU_JOUER, menu);
    }
}
