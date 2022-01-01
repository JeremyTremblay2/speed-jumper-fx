package com.jeremyantoine.speedjumper.monde;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Position2D;
import jdk.jshell.spi.ExecutionControl;

import java.util.Arrays;

public class CameraCarteTuiles extends Camera2D {
    private Carte2D carteCourante;
    private Tuile[][] vision;

    public CameraCarteTuiles(Carte2D carte, Dimension zoneVisuelle) throws IllegalArgumentException {
        super(zoneVisuelle);
        if (carte == null) {
            throw new IllegalArgumentException("La carte donnée à la caméra est nulle.");
        }
        if (carte.getDimension().getLargeur() < zoneVisuelle.getLargeur()
                || carte.getDimension().getHauteur() < zoneVisuelle.getHauteur()) {
            throw new IllegalArgumentException("La zone visuelle de la caméra (" + zoneVisuelle + ") ne peut pas "
                    + "être plus petite que les dimensions de la carte : " + carte.getDimension());
        }
        vision = new Tuile[(int) zoneVisuelle.getHauteur()][(int) zoneVisuelle.getLargeur()];
        changeCarte(carte);
    }

    public Tuile[][] getVision() {
        return vision;
    }

    public Carte2D getCarteCourante() {
        return carteCourante;
    }

    public Tuile getTuile(int x, int y) {
        return vision[x][y];
    }

    public void changeCarte(Carte2D carte) throws IllegalArgumentException {
        if (carte == null || carte.getDimension().getLargeur() == 0) {
            throw new IllegalArgumentException("La carte passée en paramètre de la caméra ne peut pas être nulle ou vide.");
        }
        carteCourante = carte;
        miseAJour();
    }

    @Override
    public void centrerSurEntite(Entite entite) {
        double largeurCarte = carteCourante.getDimension().getLargeur();
        double hauteurCarte = carteCourante.getDimension().getHauteur();
        double largeurTuile = carteCourante.getDimensionTuiles().getLargeur();
        double hauteurTuile = carteCourante.getDimensionTuiles().getHauteur();

        int positionEntiteX = (int) ((entite.getPosition().getX() + entite.getDimension().getLargeur() / 2) / largeurTuile);
        int positionEntiteY = (int) ((entite.getPosition().getY() + entite.getDimension().getHauteur() / 2) / hauteurTuile);

        double milieuEcranX = milieuEcran.getLargeur();
        double milieuEcranY = milieuEcran.getHauteur();
        double nouvellePositionX;
        double nouvellePositionY;

        if (positionEntiteX < milieuEcran.getLargeur()) {
            nouvellePositionX = 0;
        }
        else if (positionEntiteX > (largeurCarte - milieuEcranX)) {
            nouvellePositionX = largeurCarte - zoneVisuelle.getLargeur();
        }
        else {
            nouvellePositionX = positionEntiteX - milieuEcranX;
        }

        if (positionEntiteY < milieuEcranY) {
            nouvellePositionY = 0;
        }
        else if (positionEntiteY > (hauteurCarte - milieuEcranY)) {
            nouvellePositionY = hauteurCarte - zoneVisuelle.getHauteur();
        }
        else {
            nouvellePositionY = positionEntiteY - milieuEcranY;
        }
        position = new Position2D(nouvellePositionX, nouvellePositionY);

        miseAJour();
    }

    @Override
    public void decalage(Direction direction) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Le système de décalage de la cméra 2D n'est pas implémenté.");
    }

    private void miseAJour() throws IndexOutOfBoundsException {
        double largeurCamera = zoneVisuelle.getLargeur();
        double hauteurCamera = zoneVisuelle.getHauteur();

        System.out.println("l : " + vision[0].length + " h : " + vision.length);
        System.out.println(carteCourante.getDimension());
        //System.out.println(carteCourante);

        for (int x = 0; x < hauteurCamera; x++) {
            for (int y = 0; y < largeurCamera; y++) {
                vision[x][y] = carteCourante.getTuile(x, y);
            }
            if (x == 9) {
                System.out.println(Arrays.deepToString(vision));
            }
            System.out.println();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CameraCarteTuiles camera = (CameraCarteTuiles) o;
        return equals(camera);
    }

    public boolean equals(CameraCarteTuiles camera) {
        return super.equals(camera)
                && carteCourante.equals(camera.getCarteCourante());
    }

    @Override
    public int hashCode() {
        return super.hashCode() + 2 * carteCourante.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder chaine = new StringBuilder(super.toString());
        chaine.append("\nCarte courante : ");
        chaine.append(carteCourante.toString());
        chaine.append("\nVision : \n");

        double largeurCamera = zoneVisuelle.getLargeur();
        double hauteurCamera = zoneVisuelle.getHauteur();

        for (int x = 0; x < hauteurCamera; x++) {
            for (int y = 0; y < largeurCamera; y++) {
                chaine.append(vision[x][y].getIdTuile());
                chaine.append(" ");
            }
            chaine.append("\n");
        }

        return chaine.toString();
    }

    private void effacer() throws IndexOutOfBoundsException {
        double largeurCamera = zoneVisuelle.getLargeur();
        double hauteurCamera = zoneVisuelle.getHauteur();
        Tuile tuileIgnoree = Tuile.getTuileIgnoree();

        for (int x = 0; x < largeurCamera; x++) {
            for (int y = 0; y < hauteurCamera; y++) {
                vision[x][y] = tuileIgnoree;
            }
        }
    }
}
