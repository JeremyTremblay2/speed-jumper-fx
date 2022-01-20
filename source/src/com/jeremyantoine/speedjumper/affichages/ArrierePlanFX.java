package com.jeremyantoine.speedjumper.affichages;

import com.jeremyantoine.speedjumper.observateurs.Observateur;
import com.jeremyantoine.speedjumper.monde.ArrierePlan;
import javafx.scene.image.Image;

import java.util.Objects;

public class ArrierePlanFX implements Observateur {
    private ArrierePlan arrierePlan;
    private Image image;

    public ArrierePlanFX(ArrierePlan arrierePlan, Image image) throws IllegalArgumentException {
        if (arrierePlan == null) {
            throw new IllegalArgumentException("La tuile donnée en paramètre ne peut pas être nulle.");
        }
        this.arrierePlan = arrierePlan;
        this.image = image;
    }

    public ArrierePlan getArrierePlan() {
        return arrierePlan;
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
        ArrierePlanFX arrierePlanFX = (ArrierePlanFX) o;
        return equals(arrierePlanFX);
    }

    public boolean equals(ArrierePlanFX arrierePlanFX) {
        return arrierePlan.equals(arrierePlanFX.getArrierePlan())
                && image.equals(arrierePlanFX.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(arrierePlan, image);
    }

    @Override
    public String toString() {
        return "Version graphique de ArrierePlan : " + arrierePlan.toString();
    }
}
