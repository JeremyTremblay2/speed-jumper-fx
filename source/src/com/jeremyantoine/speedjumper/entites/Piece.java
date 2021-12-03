package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.coordonnees.Position;

import java.util.Objects;

public class Piece extends Entite {

    private int valeur;
    private static final int VALEUR_PAR_DEFAUT = 5;

    public Piece(Position position, int valeur) throws IllegalArgumentException {
        super(position);

        if (valeur <= 0) {
            this.valeur = VALEUR_PAR_DEFAUT;
        }
        else {
            this.valeur = valeur;
        }
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
