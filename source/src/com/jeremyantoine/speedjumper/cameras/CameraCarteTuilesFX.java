package com.jeremyantoine.speedjumper.cameras;

import com.jeremyantoine.speedjumper.affichages.TuileFX;
import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.monde.Carte2D;
import com.jeremyantoine.speedjumper.monde.Tuile;

import java.util.List;

public class CameraCarteTuilesFX extends CameraCarteTuiles {
    private List<TuileFX> lesTuilesGraphiques;
    private TuileFX[][] visionGraphique;

    public CameraCarteTuilesFX(Carte2D carte, Dimension zoneVisuelle, List<TuileFX> lesTuilesGraphiques)
            throws IllegalArgumentException {
        super(carte, zoneVisuelle);
        if (lesTuilesGraphiques == null) {
            throw new IllegalArgumentException("La liste de tuiles graphique doit être non nulle pour la création de la caméra.");
        }
        this.lesTuilesGraphiques = lesTuilesGraphiques;
        visionGraphique = new TuileFX[(int) zoneVisuelle.getHauteur()][(int) zoneVisuelle.getLargeur()];
    }

    public TuileFX getTuileGraphique(int x, int y) {
        return visionGraphique[x][y];
    }

    @Override
    public void centrerSurEntite(Entite entite) {
        super.centrerSurEntite(entite);
        miseAJour();
    }

    private void miseAJour() {
        double largeurCamera = zoneVisuelle.getLargeur();
        double hauteurCamera = zoneVisuelle.getHauteur();

        for (int x = 0; x < hauteurCamera; x++) {
            for (int y = 0; y < largeurCamera; y++) {
                visionGraphique[x][y] = lesTuilesGraphiques.get(vision[x][y].getIdTuile());
            }
        }
    }
}
