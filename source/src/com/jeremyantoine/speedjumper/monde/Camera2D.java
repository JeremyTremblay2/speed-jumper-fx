package com.jeremyantoine.speedjumper.monde;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Position2D;

public abstract class Camera2D {
    private Carte carteCourante;
    private Position2D position;
    private Dimension zoneVisuelle;
    private Tuile[][] vision;

    public Camera2D(Carte carte, Dimension zoneVisuelle) throws IllegalArgumentException {
       if (zoneVisuelle == null || zoneVisuelle.getLargeur() <= 0 || zoneVisuelle.getHauteur() <= 0) {
           throw new IllegalArgumentException("La caméra ne peut pas avoir un champ visuel"
                    + " null ou inférieur à 0. Donné : " + zoneVisuelle);
       }
       this.zoneVisuelle = zoneVisuelle;
       vision = new Tuile[(int) zoneVisuelle.getLargeur()][(int) zoneVisuelle.getHauteur()];
       changeCarte(carte);
    }

    public void changeCarte(Carte carte) throws IllegalArgumentException {
        if (carte == null || carte.getDimension().getLargeur() == 0) {
            throw new IllegalArgumentException("La carte passée en paramètre de la caméra ne peut pas être nulle ou vide.");
        }
        carteCourante = carte;

        for (int x = 0; x < zoneVisuelle.getLargeur(); x++) {
            for (int y = 0; y < zoneVisuelle.getHauteur(); y++) {
                vision[x][y] = carte.getTuile(x, y);
            }
        }
    }

    public void centrerSurEntite(Entite entite) {

    }

    public void decalage(Direction direction) {

    }
}
