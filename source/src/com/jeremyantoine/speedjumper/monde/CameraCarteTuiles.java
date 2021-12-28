package com.jeremyantoine.speedjumper.monde;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Position2D;
import jdk.jshell.spi.ExecutionControl;

public class CameraCarteTuiles extends Camera2D {
    private Carte2D carteCourante;
    private Tuile[][] vision;

    public CameraCarteTuiles(Carte2D carte, Dimension zoneVisuelle) {
        super(zoneVisuelle);
        vision = new Tuile[(int) zoneVisuelle.getLargeur()][(int) zoneVisuelle.getHauteur()];
        changeCarte(carte);
    }

    public Tuile[][] getVision() {
        return vision;
    }

    public Carte2D getCarteCourante() {
        return carteCourante;
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
        int positionCameraX = (int) position.getX();
        int positionCameraY = (int) position.getY();

        for (int x = positionCameraX; x < largeurCamera; x++) {
            for (int y = positionCameraY; y < hauteurCamera; y++) {
                vision[x][y] = carteCourante.getTuile(x, y);
            }
        }
    }

    private void effacer() throws IndexOutOfBoundsException {
        double largeurCamera = zoneVisuelle.getLargeur();
        double hauteurCamera = zoneVisuelle.getHauteur();
        int positionCameraX = (int) position.getX();
        int positionCameraY = (int) position.getY();
        Tuile tuileIgnoree = Tuile.getTuileIgnoree();

        for (int x = positionCameraX; x < largeurCamera; x++) {
            for (int y = positionCameraY; y < hauteurCamera; y++) {
                vision[x][y] = tuileIgnoree;
            }
        }
    }
}
