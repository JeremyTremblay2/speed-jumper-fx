package com.jeremyantoine.speedjumper.affichages;

import com.jeremyantoine.speedjumper.entites.Entite;
import com.jeremyantoine.speedjumper.observateurs.Observateur;
import javafx.scene.image.Image;

import java.util.List;
import java.util.Objects;

public class EntiteFX implements Observateur {
    private Entite entite;
    private List<List<Image>> lesImages;
    private Image imageCourante;
    private int indexImage;

    public EntiteFX(Entite entite, List<List<Image>> lesImages) {
        if (entite == null) {
            throw new IllegalArgumentException("L'entité passée en paramètre ne peut pas être nulle.");
        }
        this.entite = entite;
        this.lesImages = lesImages;
    }

    public Image getImageCourante() {
        return imageCourante;
    }

    public Entite getEntite() {
        return entite;
    }

    public int getIndexImage() {
        return indexImage;
    }

    public List<List<Image>> getLesImages() {
        return lesImages;
    }

    @Override
    public void miseAjour() {

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntiteFX entiteFX = (EntiteFX) o;
        return equals(entiteFX);
    }

    public boolean equals(EntiteFX entiteFX) {
        return entite.equals(entiteFX.getEntite())
                && lesImages.equals(entiteFX.getLesImages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(entite, lesImages);
    }

    @Override
    public String toString() {
        return "Version graphique de Entite : " + entite.toString()
                + "\nIndex de l'image courante ["
                + imageCourante + "] : " + indexImage;
    }
}
