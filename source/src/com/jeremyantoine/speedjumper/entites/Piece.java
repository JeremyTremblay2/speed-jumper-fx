package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.comportement.Comportement;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

import java.util.Objects;

public class Piece extends Entite {
    private static final int VALEUR_PAR_DEFAUT = 5;
    private int valeur;

    public Piece(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                 double velocite, int valeur) throws IllegalArgumentException {
        super(position, collision, dimension, comportement, velocite);

        if (valeur <= 0) {
            this.valeur = VALEUR_PAR_DEFAUT;
        }
        else {
            this.valeur = valeur;
        }
    }
    public Piece(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                 double velocite) throws IllegalArgumentException {
        this(position, collision, dimension, comportement, velocite, VALEUR_PAR_DEFAUT);
    }

    public int getValeur() {
        return valeur;
    }

    private void setValeur(int valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return super.toString() + " $" + valeur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Piece piece = (Piece) o;
        return equals(piece);
    }

    public boolean equals(Piece piece) {
        return valeur == piece.getValeur()
                && super.equals(piece);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), valeur);
    }
}
