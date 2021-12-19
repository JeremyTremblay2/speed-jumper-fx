package com.jeremyantoine.speedjumper.monde;

import com.jeremyantoine.speedjumper.logique.Dimension;

public class Carte {
    private Dimension dimension;
    private Tuile[][] lesTuiles;

    public Carte(Tuile[][] tuiles, Dimension dimension) throws IllegalArgumentException {
        if (tuiles == null || tuiles.length == 0) {
            throw new IllegalArgumentException("Une carte ne peut pas être vide, elle doit contenir au minimum une tuile.");
        }
        lesTuiles = tuiles;
        new Dimension(lesTuiles.length, lesTuiles[0].length);
        this.dimension = dimension;
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
}
