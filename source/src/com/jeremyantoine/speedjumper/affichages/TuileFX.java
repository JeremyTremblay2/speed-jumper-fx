package com.jeremyantoine.speedjumper.affichages;

import com.jeremyantoine.speedjumper.observateurs.Observateur;
import com.jeremyantoine.speedjumper.monde.Tuile;
import javafx.scene.image.Image;

import java.util.Objects;

public class TuileFX implements Observateur {
    private Tuile tuile;
    private Image image;

    public TuileFX(Tuile tuile, Image image) throws IllegalArgumentException {
        if (tuile == null) {
            throw new IllegalArgumentException("La tuile donnée en paramètre ne peut pas être nulle.");
        }
        this.tuile = tuile;
        this.image = image;
    }

    public Tuile getTuile() {
        return tuile;
    }

    public Image getImage() {
        return image;
    }

    @Override
    public void miseAjour() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TuileFX tuileFX = (TuileFX) o;
        return equals(tuileFX);
    }

    public boolean equals(TuileFX tuileFX) {
        return tuile.equals(tuileFX.getTuile())
                && image.equals(tuileFX.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(tuile, image);
    }

    @Override
    public String toString() {
        return "Version graphique de Tuile : " + tuile.toString();
    }
}
