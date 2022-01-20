package com.jeremyantoine.speedjumper.affichages;

import com.jeremyantoine.speedjumper.observateurs.Observateur;
import com.jeremyantoine.speedjumper.monde.ArrierePlan;
import javafx.scene.image.Image;

import java.util.Objects;

/**
 * Classe permettant de gerer l'affichage de l'arriere plan
 */
public class ArrierePlanFX implements Observateur {
    private ArrierePlan arrierePlan;
    private Image image;

    /**
     * Constructeur de la classe
     * @param arrierePlan
     * @param image
     * @throws IllegalArgumentException
     */
    public ArrierePlanFX(ArrierePlan arrierePlan, Image image) throws IllegalArgumentException {
        if (arrierePlan == null) {
            throw new IllegalArgumentException("La tuile donnée en paramètre ne peut pas être nulle.");
        }
        this.arrierePlan = arrierePlan;
        this.image = image;
    }

    /**
     * methode permettant de recuperer l'arriere plan
     * @return
     */
    public ArrierePlan getArrierePlan() {
        return arrierePlan;
    }

    /**
     * retourne l'image
     * @return
     */
    public Image getImage() {
        return image;
    }

    /**
     * Mise a jour de l'affichage du fond
     */
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
