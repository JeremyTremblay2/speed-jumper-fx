package com.jeremyantoine.speedjumper.monde;

import com.jeremyantoine.speedjumper.logique.Dimension;

public class Carte2D {
    private static int nombreCarte = 0;
    private final int idCarte;
    private final Dimension dimensionTuiles;
    private Dimension dimension;
    private Tuile[][] lesTuiles;

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

    public Dimension getDimension() {
        return dimension;
    }

    public Tuile[][] getLesTuiles() {
        return lesTuiles;
    }

    public Tuile getTuile(int x, int y) {
        return lesTuiles[x][y];
    }

    public int getIdCarte() {
        return idCarte;
    }

    public Dimension getDimensionTuiles() {
        return dimensionTuiles;
    }

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

    @Override
    public String toString() {
        double largeur = dimension.getLargeur();
        double hauteur = dimension.getHauteur();

        StringBuilder chaine = new StringBuilder("[");
        chaine.append(idCarte);
        chaine.append("] ");
        chaine.append(dimension.toString());
        chaine.append(" : \n");

        System.out.println(dimension);

        for (int x = 0; x < hauteur; x++) {
            for (int y = 0; y < largeur; y++) {
                chaine.append(lesTuiles[x][y].getIdTuile());
                chaine.append(" ");
            }
            chaine.append("\n");
        }
        return chaine.toString();
    }

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
