package com.jeremyantoine.speedjumper.fenetres;

import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouches;
import com.jeremyantoine.speedjumper.entrees.RecuperateurDeTouchesFX;
import com.jeremyantoine.speedjumper.jeu.Jeu;
import com.jeremyantoine.speedjumper.jeu.JeuFX;
import javafx.scene.layout.TilePane;
import javafx.stage.Stage;

import java.net.MalformedURLException;
import java.net.URL;

public class FenetreJeu {
    private Jeu jeu;
    private TilePane grille;

    public FenetreJeu(Stage stage) throws MalformedURLException {
        RecuperateurDeTouches recuperateur = new RecuperateurDeTouchesFX(new URL("D:\\Cours\\2021-2022\\S1\\Conception et Prog Avancée\\speed-jumper\\source\\ressources\\touches.txt"),
                stage.getScene());
        jeu = new JeuFX(recuperateur);
    }


}
