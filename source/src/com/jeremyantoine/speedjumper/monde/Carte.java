package com.jeremyantoine.speedjumper.monde;

import com.jeremyantoine.speedjumper.logique.Dimension;

public class Carte {
    private static int nombreCarte = 0;
    private final int idCarte;
    private Dimension dimension;
    private Tuile[][] lesTuiles;

    public Carte(Tuile[][] tuiles) throws IllegalArgumentException {
        if (tuiles == null || tuiles.length == 0) {
            throw new IllegalArgumentException("Une carte ne peut pas être vide, elle doit contenir au minimum une tuile.");
        }
        lesTuiles = tuiles;
        this.dimension = new Dimension(lesTuiles.length, lesTuiles[0].length);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carte carte = (Carte) o;
        return equals(carte);
    }

    public boolean equals(Carte carte) {
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
        chaine.append("] : ");
        chaine.append(dimension.toString());
        chaine.append("\n");

        for (int x = 0; x < largeur; x++) {
            for (int y = 0; y < hauteur; y++) {
                chaine.append(lesTuiles[x][y].getIdTuile());
                chaine.append(" ");
            }
            chaine.append("\n");
        }
        return chaine.toString();
    }
}
