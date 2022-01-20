package com.jeremyantoine.speedjumper.cameras;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Position2D;
import jdk.jshell.spi.ExecutionControl;

/**
 * classe de la camera2D
 */
public abstract class Camera2D {
    protected Position2D position;
    protected Dimension decalageRelatif;
    protected Dimension decalageAbsolu;
    protected Dimension zoneVisuelle;
    protected Dimension milieuEcran;

    /**
     * constructeur de la camera
     * @param zoneVisuelle
     * @throws IllegalArgumentException
     */
    public Camera2D(Dimension zoneVisuelle) throws IllegalArgumentException {
       if (zoneVisuelle == null || zoneVisuelle.getLargeur() <= 0 || zoneVisuelle.getHauteur() <= 0) {
           throw new IllegalArgumentException("La caméra ne peut pas avoir un champ visuel"
                    + " null ou inférieur à 0. Donné : " + zoneVisuelle);
       }
       milieuEcran = new Dimension(zoneVisuelle.getLargeur() / 2, zoneVisuelle.getHauteur() / 2);
       this.zoneVisuelle = zoneVisuelle;
       decalageRelatif = new Dimension(0, 0);
       decalageAbsolu = new Dimension(0, 0);
       position = new Position2D(0, 0);
    }

    /**
     * retourne la position de la camera
     * @return
     */
    public Position2D getPosition() {
        return position;
    }

    /**
     * set la position de la camera
     * @param position
     */
    protected void setPosition(Position2D position) {
        this.position = position;
    }

    /**
     * retourne la zone affichée par la camera
     * @return
     */
    public Dimension getZoneVisuelle() {
        return zoneVisuelle;
    }

    /**
     * centre la camera sur l'entite
     * @param entite
     */
    public abstract void centrerSurEntite(Entite entite);

    /**
     * methode retournant le decalage relatif
     * @return
     */
    public Dimension getDecalageRelatif() {
        return decalageRelatif;
    }

    /**
     * retourne le decalge absolu
     * @return
     */
    public Dimension getDecalageAbsolu() {
        return decalageAbsolu;
    }

    /**
     * methode permettant de decaler la camera dans une direction
     * @param direction
     * @throws ExecutionControl.NotImplementedException
     */
    public abstract void decalage(Direction direction) throws ExecutionControl.NotImplementedException;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Camera2D camera = (Camera2D) o;
        return equals(camera);
    }

    public boolean equals(Camera2D camera) {
        return position.equals(camera.getPosition())
                && zoneVisuelle.equals(camera.getZoneVisuelle());
    }

    @Override
    public int hashCode() {
        return 7 * zoneVisuelle.hashCode() + 3 * position.hashCode();
    }


    @Override
    public String toString() {
        return "Caméra en " + position.toString() + " de dimensions " + zoneVisuelle.toString();
    }
}
