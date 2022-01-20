package com.jeremyantoine.speedjumper.monde;

import com.jeremyantoine.speedjumper.logique.Dimension;

/**
 * Classe de la carte 2D
 */
public class Carte2D {
    private static int nombreCarte = 0;
    private final int idCarte;
    private final Dimension dimensionTuiles;
    private final Dimension dimension;
    private final Tuile[][] lesTuiles;

    /**
     * Constructeur de la carte
     * @param tuiles Tableau de tuiles de la carte
     * @param dimensionTuiles dimension des tuiles
     * @throws IllegalArgumentException
     */
    public Carte2D(Tuile[][] tuiles, Dimension dimensionTuiles) throws IllegalArgumentException {
        if (tuiles == null || tuiles.length == 0) {
            throw new IllegalArgumentException("Une carte ne peut pas être vide, elle doit contenir au minimum une tuile.");
        }
        if (dimensionTuiles == null || dimensionTuiles.getHauteur() <= 0 || dimensionTuiles.getLargeur() <= 0) {
            throw new IllegalArgumentException("Les dimensions des tuiles ne peuvent pas être négatives ou nulles.");
        }
        this.dimensionTuiles = dimensionTuiles;
        verificationDimensionsTuiles(tuiles);
        lesTuiles = tuiles;
        this.dimension = new Dimension(lesTuiles[0].length, lesTuiles.length);
        idCarte = nombreCarte;
        nombreCarte++;
    }

    /**
     * retourne la dimension des tuiles
     * @return
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * retourne le tableau des tuiles
     * @return
     */
    public Tuile[][] getLesTuiles() {
        return lesTuiles;
    }

    /**
     * Retourne une tuile en fonction de ses coordonnées
     * @param x coordonnées X
     * @param y coordonnées Y
     * @return
     */
    public Tuile getTuile(int x, int y) {
        return lesTuiles[x][y];
    }

    /**
     * retourne l'id d'une carte
     * @return
     */
    public int getIdCarte() {
        return idCarte;
    }

    /**
     * retourne la dimenssion des tuiles
     * @return
     */
    public Dimension getDimensionTuiles() {
        return dimensionTuiles;
    }

    /**
     * Compare deux objet entre eux ici deux Carte2D
     * @param o objet a comparé
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carte2D carte = (Carte2D) o;
        return equals(carte);
    }

    public boolean equals(Carte2D carte) {
        return idCarte == carte.getIdCarte();
    }

    @Override
    public int hashCode() {
        return 7 * idCarte;
    }

    /**
     * Affiche les données de la classe
     * @return
     */
    @Override
    public String toString() {
        double largeur = dimension.getLargeur();
        double hauteur = dimension.getHauteur();

        StringBuilder chaine = new StringBuilder("[");
        chaine.append(idCarte);
        chaine.append("] ");
        chaine.append(dimension);
        chaine.append(" : \n");

        for (int x = 0; x < hauteur; x++) {
            for (int y = 0; y < largeur; y++) {
                chaine.append(lesTuiles[x][y].getIdTuile());
                chaine.append(" ");
            }
            chaine.append("\n");
        }
        return chaine.toString();
    }

    /**
     * Vérifie la dimension des tuiles avec celle définis
     * @param lesTuiles Tableau de tuiles ou verifier la dimension
     * @throws IllegalArgumentException
     */
    private void verificationDimensionsTuiles(Tuile[][] lesTuiles) throws IllegalArgumentException {
        for (Tuile[] lesTuile : lesTuiles) {
            for (Tuile tuile : lesTuile) {
                if (!tuile.getDimension().equals(dimensionTuiles)) {
                    throw new IllegalArgumentException("Les tuiles doivent toutes avoir la même dimension dans la "
                            + "carte. Tuile donnée : " + tuile);
                }
            }
        }
    }
}
