package com.jeremyantoine.speedjumper.logique;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Objects;

/**
 * Classe permettant de créer une position en 2D
 */
public class Position2D {

    /**
     * Property de la coordonées X
     */
    private final DoubleProperty x = new SimpleDoubleProperty();
        public void setX(double x) { this.x.set(x);}
        public double getX(){return x.get();}
        public DoubleProperty xProperty(){return  x;}

    /**
     * Property de la coordonées Y
     */


    private final DoubleProperty y = new SimpleDoubleProperty();
         public void setY(double y) {
             this.y.set(y);
         }
         public double getY() {
             return y.get();
         }
         public DoubleProperty yProperty() {
             return y;
         }


    /**
     * Constructeur de la classe Position2D
     * @param x coordonée X
     * @param y coordonée Y
     */
    public Position2D(double x, double y) {
        setX(x);
        setY(y);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position2D position = (Position2D) o;
        return equals(position);
    }

    public boolean equals(Position2D position) {
        return Double.compare(position.getX(), this.getX()) == 0
                && Double.compare(position.getY(), this.getY()) == 0;
    }

    @Override
    public int hashCode() {
        return 7 * Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "{" + x + "; " + y + "}";
    }
}
