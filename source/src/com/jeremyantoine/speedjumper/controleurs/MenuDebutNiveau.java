package com.jeremyantoine.speedjumper.controleurs;

import com.jeremyantoine.speedjumper.monde.Niveau;
import com.jeremyantoine.speedjumper.utilitaire.Navigateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class MenuDebutNiveau {
    private Navigateur navigateur;
    private Niveau niveau;

    public MenuDebutNiveau(Navigateur navigateur, Niveau niveau) throws IllegalArgumentException {
        if (niveau == null || navigateur == null) {
            throw new IllegalArgumentException("Le navigateur ou le niveau passé en paramètre ne peuvent pas être null.");
        }
        this.navigateur = navigateur;
        this.niveau = niveau;
    }

    @FXML
    public void retourMenu(ActionEvent event) {
        navigateur.faireDemiTour();
    }

    @FXML
    public void ouvertureJeu(ActionEvent event) {
        FenetreJeu fenetre = new FenetreJeu(navigateur);
        navigateur.naviguerVers(fenetre.getScene());
    }
}
