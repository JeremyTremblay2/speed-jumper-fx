package com.jeremyantoine.speedjumper.monde;

import com.jeremyantoine.speedjumper.logique.Dimension;
import com.jeremyantoine.speedjumper.logique.Rectangle;


/**
 * Cette classe définis une tuile
 */
public class Tuile implements Comparable<Tuile> {
    private static final Dimension DIMENSION_PAR_DEFAUT = new Dimension(32, 32);
    private static final Tuile TUILE_IGNOREE = new Tuile(null);
    private static int nombreTuiles = 0;
    private final int idTuile;
    private Dimension dimension;
    private Rectangle collision;

    /**
     * Constructeur de la Tuile
     * @param collision Rectangle pour la collision
     * @param dimension dimension de la tuile
     */
    public Tuile(Rectangle collision, Dimension dimension) {
        this.collision = collision;
        this.dimension = dimension;
        idTuile = nombreTuiles;
        nombreTuiles++;
    }

    /**
     * Autre constructeur de la tuile en mettant une dimension par defaut
     * @param collision Rectangle pour la collision
     */
    public Tuile(Rectangle collision) {
        this(collision, DIMENSION_PAR_DEFAUT);
    }

    /**
     * Retourne la dimension de la tuile
     * @return dimension
     */
    public Dimension getDimension() {
        return dimension;
    }

    /**
     * Retourne le rectangle de la collision de la tuile
     * @return collision
     */
    public Rectangle getCollision() {
        return collision;
    }

    /**
     * Retourne l'id de la tuile
     * @return id de la tuile
     */
    public int getIdTuile() {
        return idTuile;
    }

    /**
     * Retourne une tuile null
     * @return
     */
    public static Tuile getTuileIgnoree() {
        return TUILE_IGNOREE;
    }

    /**
     * retourne le numero de la tuile
     * @return numero de la tuile
     */
    public static int getNombreTuiles() {
        return nombreTuiles;
    }

    /**
     * Methode pour comparer deux tuiles entre elles
     * @param o objet ici une tuile
     * @return retourne un boolean en fonction de la correspondance entre les tuiles
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuile tuile = (Tuile) o;
        return equals(tuile);
    }

    /**
     *
     * @param tuile
     * @return
     */
    public boolean equals(Tuile tuile) {
        return idTuile == tuile.getIdTuile();
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        return 7 * idTuile;
    }

    /**
     * Affiche les données d'une tuile
     * @return
     */
    @Override
    public String toString() {
        return "[" + idTuile + "]" + collision + " " + dimension.toString();
    }


    /**
     * Compare une tuile avec celle courante par leur ID
     * @param tuile tuile a comparer
     * @return la comparaison : 1 si l'id de la tuile courrante est plus grand, -1 sinon
     */
    @Override
    public int compareTo(Tuile tuile) {
        int comp = 0;
        if (idTuile > tuile.getIdTuile())
            comp = +1;
        else if (idTuile < tuile.getIdTuile())
            comp = -1;
        return comp;
    }
}
