package com.jeremyantoine.speedjumper.entites;

import com.jeremyantoine.speedjumper.comportement.Comportement;
import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Position2D;
import com.jeremyantoine.speedjumper.logique.Rectangle;

import java.util.Objects;

/**
 * Classe permettant de gerer les pieces
 */
public class Piece extends Entite {
    private static final int VALEUR_PAR_DEFAUT = 5;
    private int valeur;

    /**
     * Constructeur d'une piece
     * @param position position 2D de la piece
     * @param collision rectangle de collision de la piece
     * @param dimension dimension de la piece
     * @param comportement comportement de la piece
     * @param velocite velocite de la piece
     * @param valeur sa valeur
     * @throws IllegalArgumentException
     */
    public Piece(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                 double velocite, int valeur) throws IllegalArgumentException {
        super(position, collision, dimension, comportement, velocite);
        this.valeur = valeur <= 0 ? VALEUR_PAR_DEFAUT : valeur;
    }

    /**
     * Autre constructeur de la piece
     * @param position position 2D de la piece
     * @param collision rectangle de collision de la piece
     * @param dimension dimension de la piece
     * @param comportement comportement de la piece
     * @param velocite sa valeur
     * @throws IllegalArgumentException
     */
    public Piece(Position2D position, Rectangle collision, Dimension dimension, Comportement comportement,
                 double velocite) throws IllegalArgumentException {
        this(position, collision, dimension, comportement, velocite, VALEUR_PAR_DEFAUT);
    }

    /**
     * retourne la valeur de la piece
     * @return
     */
    public int getValeur() {
        return valeur;
    }

    /**
     * set la valeur de la piece
     * @param valeur
     */
    private void setValeur(int valeur) {
        this.valeur = valeur;
    }

    @Override
    public String toString() {
        return super.toString()
                + "\n$" + valeur;
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
