package com.jeremyantoine.speedjumper.cameras;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Direction;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.monde.Carte2D;
import com.jeremyantoine.speedjumper.monde.Tuile;
import jdk.jshell.spi.ExecutionControl;

/**
 * Classe gerant la partie logique de la camera
 */
public class CameraCarteTuiles extends Camera2D {
    private Carte2D carteCourante;
    protected Tuile[][] vision;

    private double largeurCarte;
    private double hauteurCarte;
    private double largeurTuile;
    private double hauteurTuile;

    /**
     * Constructeur de la cameraCartesTuiles
     * @param carte carte ou doit etre afficher la camera
     * @param zoneVisuelle dimension de la zone visible
     * @throws IllegalArgumentException
     */
    public CameraCarteTuiles(Carte2D carte, Dimension zoneVisuelle) throws IllegalArgumentException {
        super(zoneVisuelle);
        if (carte == null) {
            throw new IllegalArgumentException("La carte donnée à la caméra est nulle.");
        }
        if (carte.getDimension().getLargeur() < zoneVisuelle.getLargeur()
                || carte.getDimension().getHauteur() < zoneVisuelle.getHauteur()) {
            throw new IllegalArgumentException("La zone visuelle de la caméra (" + zoneVisuelle + ") ne peut pas "
                    + "être plus grande que les dimensions de la carte : " + carte.getDimension());
        }
        vision = new Tuile[(int) zoneVisuelle.getHauteur()][(int) zoneVisuelle.getLargeur()];
        changeCarte(carte);
    }

    /**
     * retourne le tableau de tuile visible
     * @return
     */
    public Tuile[][] getVision() {
        return vision;
    }

    /**
     * retourne la carte courante
     * @return
     */
    public Carte2D getCarteCourante() {
        return carteCourante;
    }

    /**
     * retourne la tuile pour certains coordonnés
     * @param x coordonnés X
     * @param y coordonnés Y
     * @return
     */
    public Tuile getTuile(int x, int y) {
        return vision[x][y];
    }

    /**
     * Methode permettant de changer la carte actuelle
     * @param carte
     * @throws IllegalArgumentException
     */
    public void changeCarte(Carte2D carte) throws IllegalArgumentException {
        if (carte == null || carte.getDimension().getLargeur() == 0) {
            throw new IllegalArgumentException("La carte passée en paramètre de la caméra ne peut pas être nulle ou vide.");
        }
        carteCourante = carte;

        largeurCarte = carteCourante.getDimension().getLargeur();
        hauteurCarte = carteCourante.getDimension().getHauteur();
        largeurTuile = carteCourante.getDimensionTuiles().getLargeur();
        hauteurTuile = carteCourante.getDimensionTuiles().getHauteur();
        miseAJour();
    }

    /**
     * Methode permettant de gerer le centrage de la camera sur l'entite
     * @param entite
     */
    @Override
    public void centrerSurEntite(Entite entite) {
        int positionEntiteX = (int) ((entite.getPosition().getX() + entite.getDimension().getLargeur() / 2) / largeurTuile);
        int positionEntiteY = (int) ((entite.getPosition().getY() + entite.getDimension().getHauteur() / 2) / hauteurTuile);

        double decalageX = (int) ((entite.getPosition().getX() + entite.getDimension().getLargeur() / 2) % largeurTuile);
        double decalageY = (int) ((entite.getPosition().getY() + entite.getDimension().getHauteur() / 2) % hauteurTuile);

        double milieuEcranX = milieuEcran.getLargeur();
        double milieuEcranY = milieuEcran.getHauteur();
        double nouvellePositionX;
        double nouvellePositionY;

        if (positionEntiteX < milieuEcran.getLargeur()) {
            nouvellePositionX = 0;
            decalageX = 0;
        }
        else if (positionEntiteX > (largeurCarte - milieuEcranX)) {
            nouvellePositionX = largeurCarte - zoneVisuelle.getLargeur();
            decalageX = largeurTuile;
        }
        else {
            nouvellePositionX = positionEntiteX - milieuEcranX;
        }

        if (positionEntiteY < milieuEcranY) {
            nouvellePositionY = 0;
            decalageY = 0;
        }
        else if (positionEntiteY > (hauteurCarte - milieuEcranY)) {
            nouvellePositionY = hauteurCarte - zoneVisuelle.getHauteur();
            decalageY = hauteurTuile;
        }
        else {
            nouvellePositionY = positionEntiteY - milieuEcranY;
        }
        position = new Position2D(nouvellePositionX, nouvellePositionY);
        decalageRelatif = new Dimension(decalageX, decalageY);
        miseAJour();
    }

    /**
     * methode permettant de decaler la camera
     * @param direction
     * @throws ExecutionControl.NotImplementedException
     */
    @Override
    public void decalage(Direction direction) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Le système de décalage de la caméra 2D n'est pas implémenté.");
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

    /**
     * Methode permettant de mettre a jour l'affichage de la camera
     * @throws IndexOutOfBoundsException
     */
    private void miseAJour() throws IndexOutOfBoundsException {
        double largeurCamera = zoneVisuelle.getLargeur();
        double hauteurCamera = zoneVisuelle.getHauteur();

        int positionX = (int) position.getX();
        int positionY = (int) position.getY();

        for (int x = 0; x < hauteurCamera; x++) {
            for (int y = 0; y < largeurCamera; y++) {
                vision[x][y] = carteCourante.getTuile(x + positionY, y + positionX);
            }
        }
    }

    /**
     * Methode permettant de supprimer le visuel de la camera
     * @throws IndexOutOfBoundsException
     */
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
