package com.jeremyantoine.speedjumper.jeu;

import com.jeremyantoine.speedjumper.entites.PersonnageJouable;
import com.jeremyantoine.speedjumper.monde.CameraCarteTuiles;
import com.jeremyantoine.speedjumper.monde.Niveau;

public class AffichageJeu {
    private Niveau niveau;
    private PersonnageJouable joueur;
    private CameraCarteTuiles camera;

    public AffichageJeu(Niveau niveau, PersonnageJouable joueur, CameraCarteTuiles camera) {
        this.niveau = niveau;
        this.joueur = joueur;
        this.camera = camera;
    }

    public void affiche() {

    }
}
